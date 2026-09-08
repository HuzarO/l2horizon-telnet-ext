# HorizonGuard — integracja Lucera2

Implementacja dla konkretnych JAR-ów z `l2horizon-server` i klienta `horizon-guard-20260907-audit-v1`. Obejmuje nową sesję ochrony GameServer, backend HTTP do wystawienia przez HTTPS oraz ograniczony parser dotychczasowego AuthGuard. Nie uruchamia automatycznych banów ani klasyfikatora zachowania graczy.

## Budowanie i testy

Wymagania: Python 3 i JDK 17 lub nowszy. ASM 9.10.1 służy wyłącznie do budowania; jego przypięty SHA-256 jest sprawdzany przez skrypt. Kod działającego serwera nie zależy od ASM, Maven ani Ant.

```powershell
python build.py --server "C:/Users/bartf/source/repos/l2horizon-server" --jdk "C:/Program Files/OpenLogic/jdk-17.0.17.10-hotspot" --test
```

Na Linuxie podaj odpowiednie ścieżki do dystrybucji serwera i JDK. WSL może budować za pomocą natywnego JDK Windows, podając ścieżki `/mnt/c/...`; skrypt przelicza argumenty dla `java.exe`. Wyniki: `dist/HorizonGuard.ext.jar` i `dist/AuthGuardHardening.ext.jar`. Pliki tymczasowe i testy są w `build/`. Wejściowe JAR-y i konfiguracja serwera pozostają tylko do odczytu.

Testy używają prawdziwych klas z JAR-ów, kontrolowanych obiektów sesji, publicznych wektorów C++ i podpisanego manifestu pilota. Testy nie uruchamiają bazy danych, świata gry ani połączeń z produkcją. Weryfikator JVM sprawdza zmodyfikowane klasy, a testy wykonują wspólną bramkę pakietów i oryginalny setter stanu. Osobne testy HTTP dotyczą wewnętrznego backendu, nie publicznego certyfikatu TLS.

## Integracja z rdzeniem

W repozytorium nie ma kompletnych źródeł rdzenia ani AuthGuardExt. `tools/PatchCore.java` podczas budowania odczytuje dwa konkretne pliki klas z `server.jar`. Odmawia pracy, jeśli ich SHA-256 różni się od zatwierdzonego w kodzie. Zachowuje instrukcje i ramki stosu oryginalnych metod, umieszczając wokół nich małe metody wywołujące guard:

- `GameClient.setState`: po uwierzytelnieniu konta i przejściu do AUTHED wydaje bootstrap FE/4A02;
- `GameClient.onDisconnection`: unieważnia ticket i sesję tego połączenia przed sprzątaniem obiektów gry;
- finalna `L2GameClientPacket.run`: sprawdza dopuszczenie przed `CharacterSelected`, `EnterWorld` i wykonywaniem pakietów postaci w świecie.

Do wynikowego JAR-a trafia również SHA-256 całego wejściowego `server.jar`. Kontrola offline i normalny start odmawiają działania po podmianie rdzenia bez przebudowania integracji. Parametr `-Dhorizon.guard.core` służy testom lub jawnie innemu położeniu pliku; standardowo sprawdzany jest `server.jar` w katalogu roboczym GameServera.

Klient już będący w wyborze postaci może potrzebować ponownie nacisnąć wejście, jeżeli pomiary nie zdążyły się zakończyć. W audit nowa bramka nie blokuje rozgrywki. Osobne sockety mają niezależne sesje, również dla tego samego konta/IP. Zmiana postaci na istniejącym sockecie nie wydaje ponownie ticketu.

`AuthGuardHardening.ext.jar` zachowuje publiczny ABI dwóch klas AuthGuardExt. Wymaga dokładnie sześciu plików, ogranicza długość nazw i hashów, odrzuca duplikaty i nie alokuje kolekcji według niezweryfikowanej liczby z pakietu. Zachowuje dopuszczalny trailer szyfrowania L2. Loader XML blokuje zewnętrzne encje i pobieranie DTD; niepoprawna polityka daje odmowę weryfikacji. Dotychczasowy opcode 0x14 i `FileHashesResult` pozostają zgodne.

Poprawka `AuthGuard trailing data`: rzeczywisty Engine.dll (SHA-256 `20f29e430464ccdb3cbeb2661da649c7d8e402ffcbc6298f0d72f5e281be62ca`) tworzy końcówkę w dwóch miejscach: RVA `0x300410` wyrównuje dane do 8 bajtów i dodaje 8 bajtów, a wywołanie RVA `0x2ca9c0` dodaje następne 8 przed Blowfish. Obie funkcje sprawdzono bezpośrednio w pliku DLL. Raport sześciu plików ma 905 bajtów z opcode, 928 po szyfrowaniu i 930 z nagłówkiem długości. Parser musi dopuścić 23 bajty końcówki; wcześniejszy limit 11 błędnie odrzucał poprawne logowanie. Granica ramki nadal pochodzi z `SelectorThread`, a ograniczenia rekordów pozostają aktywne.

Test regresji odtwarza oba kroki tworzenia końcówki, szyfruje jawnym kluczem testowym przez JCE Blowfish z kolejnością słów klienta, a następnie wykonuje rzeczywiste `LoginCrypt`, `L2LoginClient.handlePacket`, `SelectorThread.parseClientPacket` i `RequestFileHashes.run`. Obejmuje też dodatkowe bajty kolejnego pakietu w buforze odbioru i odrzucenie końcówki dłuższej niż 23 bajty. Nie wymaga uruchamiania gry ani bazy danych.

## Polityka i limity

Backend rejestruje wyłącznie podpisane manifesty z lokalnego katalogu administratora. Weryfikuje RSA-PSS SHA-256/MGF1 SHA-256/salt 32 oraz odczytuje klucz publiczny CNG. Rejestr, profile, minimalną wersję i tryb porównuje z raportem; nie przyjmuje manifestów od graczy. Usunięcie wydania i restart usługi wycofuje jego dopuszczenie. Klucz prywatny nie trafia na GameServer.

Jawne parametry PSS są konieczne, ponieważ domyślne parametry tej specyfikacji nie odpowiadają użytemu profilowi. [Dokumentacja Java PSSParameterSpec](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/security/spec/PSSParameterSpec.html).

Ticket ma domyślnie 30 sekund. Zaakceptowana sesja ma maksymalnie 60 sekund od ostatniego przyjętego raportu. Identyczna retransmisja nie odnawia terminu i nie przetwarza ponownie zdarzeń. Odpowiedź na duplikat podaje pozostały czas. Retry również nie przedłuża ticketu ani lease. Wymagane moduły muszą mieć zgodne pokrycie, ukończony pomiar i postępujące liczniki; zastój ocenia zegar serwera.

Backend nasłuchuje wyłącznie na IPv4 `127.0.0.1:9087`. Ma ograniczoną pulę wątków i kolejkę, limit 16 KiB, ścisły parser oraz limit żądań na uwierzytelnioną sesję. Nie ufa IP lub nagłówkom proxy jako tożsamości sesji. Poznanie samego sessionId nie pozwala zużyć limitu gracza ani odrzucić jego sesji — najpierw potrzebny jest ticket/nonce.

Limity nagłówków, połączeń oraz czasu żądania i odpowiedzi są ustawiane przed pierwszym uruchomieniem dostawcy OpenJDK HttpServer. Są globalne dla procesu JVM. Parametry `-D` mogą je zmieniać; po zmianie JDK uruchom ponownie testy powolnych połączeń. Publiczna warstwa TLS/proxy musi niezależnie ograniczać ruch i buforować całe body przed przesłaniem go do Javy. [API wbudowanego HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/module-summary.html).

`log/guard-events.jsonl` jest ograniczony do około 10 MiB plus jednej poprzedniej wersji. ACK oznacza przyjęcie zdarzenia do ograniczonej kolejki, nie gwarancję fsync; utrata kolejki przy awarii procesu lub błąd dysku są ograniczeniami tej wersji. Wzrost `eventsLost` jest alarmem operacyjnym. Log nie zawiera ticketów, nonce ani body HTTP. Log uwierzytelnienia mapuje losowy identyfikator sesji na konto i GameServer.

Metryki backendu: lokalne GET `/guard/health`; nie wystawiaj tej ścieżki publicznie. Dodatkowo co minutę logowane są liczniki decyzji i zapisanych/utraconych zdarzeń. Rozróżniaj odrzucenie protokołu, niespójność pomiarów i utratę łączności.

## Wdrożenie

Dokładna procedura znajduje się w `l2horizon-server/tools/guard/WDROZENIE.md`. Paczka serwerowa jest nakładką na osobną kopię pełnego serwera. Zawiera własny entrypoint `GuardMain`; zmienione skrypty startowe ustawiają jednoznaczną kolejność JAR-ów. Rdzeniowy `server.jar` pozostaje bez zmian.

Istotne ograniczenie: client user mode może sfabrykować własne pomiary. Ten protokół podnosi koszt ingerencji i daje serwerowi kontrolę nad dopuszczeniem; nie stanowi sprzętowej atestacji. Zasady walki/handlu nadal muszą być autorytatywne po stronie Lucera2. AFK, multibox i private store buff nie są same w sobie powodem kary. Ocena realnej gry, współbieżnych zakupów i botów pozostaje częścią pilota.

## Odczyt wyników pilota

`accepted` liczy przyjęte raporty, także z niepoprawnymi pomiarami w audit. Nowe liczniki `acceptedHealthy`, `acceptedPending` i `acceptedWithFailures` rozróżniają odpowiednio poprawne, oczekujące i błędne pomiary. Są dostępne w logu minutowym i prywatnym `/guard/health`; retransmisje nie zwiększają ich ponownie. Przed enforce sprawdź wzrost `acceptedHealthy` po pierwszym pełnym skanie oraz status Verified i rosnące cycles wszystkich czterech modułów klienta.

## Przyczyny pending i wolniejsze klienty

`staleMs` (45 s) ogranicza brak postępu skanowania modułu. `maxCycleMs` (120 s) osobno ogranicza czas bez ukończenia pełnego cyklu. Obie granice mierzy zegar serwera od zaobserwowanej zmiany licznika/znacznika; klient nie przesyła serwerowego terminu ważności. Przesuwanie czasu fragmentów nie przesuwa terminu pełnego cyklu, duplikaty żądań niczego nie odnawiają, a regresja liczników nadal jest błędem. Pierwszy pełny skan pozostaje obowiązkowy w enforce. Wdrożenie dla dwóch okien/słabszych komputerów używa jawnego ticketMs=120000 i podpisanego startupGraceMs=120000; lease nadal ma 60 s.

Minutowy log i prywatne health pokazują acceptedPendingNetwork, acceptedPendingStartup oraz acceptedPendingStale. To rozłączny podział acceptedPending: priorytet ma network, potem stale, potem startup. Log JSON ma pełne pendingReasons: network-pending, first-scan-pending, module-not-ready, scan-progress-stale lub cycle-stale, wraz z nazwą modułu, licznikiem cycles, progressAgeMs/cycleAgeMs i aktualnymi limitami. Nie zawiera ticketu, nonce ani treści okien.
