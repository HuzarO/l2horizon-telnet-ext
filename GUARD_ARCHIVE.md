# Guard archive — 2026-09-08

Branch: `codex/guard-archive-20260908`.

This branch preserves the client/server Guard work and its test/deployment artifacts for later review. It is an archive, not a production release recommendation. The user is retaining the previous l2.exe while the antivirus detections for runtime-fix2 are unresolved.

The reported runtime-fix2 SHA-256 is `2945c4bbcda4c99e121cec6ef5cab79a2e6ca00fe33f58e2fd66f00dcab48c10`. Reported detections: Microsoft `Trojan:Win32/Wacatac.C!ml`, MaxSecure `Trojan.Malware.300983.susgen`, Trapmine `Malicious.moderate.ml.score`. Local ESET reported no detections; local Microsoft scanning was unavailable. This is not a confirmed false positive or an antivirus-cleared release.

Related repositories on the same archive branch:

- https://github.com/HuzarO/L2Launch
- https://github.com/HuzarO/L2HorizonEncDec
- https://github.com/HuzarO/l2horizon-server
- https://github.com/HuzarO/l2horizon-telnet-ext

The Launch repository includes the detailed antivirus triage and prepared, unsent vendor review request under `Docs/Implementation/Antivirus/`.

The server archive contains the previously built extension JARs, signed audit manifests and AuthServer hashes for the new client. Do not deploy its Guard configuration unchanged when retaining the old executable. The original client requires matching legacy AuthGuard hashes and a compatible GameServer configuration; pushing this archive does not perform a production deployment or rollback.

Private signing keys remain local and are not included. The AuthGuard release key is separate from Authenticode code signing. Existing Git histories and original working directories are retained. The temporary Guard worktrees can be recreated from this branch after cleanup.
