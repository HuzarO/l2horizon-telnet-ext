package l2.authserver.network.l2;

import l2.authserver.accounts.Account;
import l2.authserver.crypt.LoginCrypt;
import l2.authserver.network.l2.c2s.AuthGameGuard;
import l2.authserver.network.l2.c2s.L2LoginClientPacket;
import l2.authserver.network.l2.c2s.RequestAuthLogin;
import l2.authserver.network.l2.c2s.RequestCmdLogin;
import l2.authserver.network.l2.c2s.RequestPIAgreement;
import l2.authserver.network.l2.c2s.RequestServerList;
import l2.authserver.network.l2.c2s.RequestServerLogin;
import l2.authserver.network.l2.s2c.AccountKicked;
import l2.authserver.network.l2.s2c.L2LoginServerPacket;
import l2.authserver.network.l2.s2c.LoginFail;
import l2.commons.net.nio.impl.MMOConnection;
import l2.commons.net.nio.impl.ReceivablePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

public final class L2LoginClient extends BaseLoginClient<L2LoginClient> {
    private static final Logger IIlllI11 = LoggerFactory.getLogger(L2LoginClient.class);

    private LoginClientState state;
    private LoginCrypt loginCrypt;
    private String login;
    private SessionKey sessionKey;
    private Account account;

    public L2LoginClient(MMOConnection<L2LoginClient> con) {
        super(con);
        state = LoginClientState.CONNECTED;
        _loginClient = this;
    }

    public LoginCrypt getLoginCrypt() {
        return loginCrypt;
    }

    public L2LoginClient setLoginCrypt(LoginCrypt loginCrypt) {
        this.loginCrypt = loginCrypt;
        return this;
    }

    @Override
    public ReceivablePacket<L2LoginClient> handlePacket(ByteBuffer buf, L2LoginClient client) {
        int opcode = buf.get() & 0xFF;
        L2LoginClientPacket packet = null;

        switch (getState()) {
            case CONNECTED:
                if (opcode == 7) {
                    packet = new AuthGameGuard();
                }
                break;
            case AUTHED_GG:
                if (opcode == 0) {
                    packet = new RequestAuthLogin();
                } else if (opcode == 11) {
                    packet = new RequestCmdLogin();
                }
                break;
            case AUTHED_LOGIN:
                if (opcode == 5) {
                    packet = new RequestServerList();
                } else if (opcode == 2) {
                    packet = new RequestServerLogin();
                } else if (opcode == 15) {
                    packet = new RequestPIAgreement();
                }
                break;
        }

        return packet;
    }

    @Override
    public boolean decrypt(ByteBuffer buf, int size) {
        boolean result;
        try {
            result = loginCrypt.decrypt(buf, buf.position(), size);
        } catch (IOException e) {
            IIlllI11.error("Decrypt exception", e);
            closeNow(true);
            return false;
        }

        if (!result) {
            closeNow(true);
        }
        return result;
    }

    @Override
    public boolean encrypt(ByteBuffer buf, int size) {
        int offset = buf.position();
        try {
            size = loginCrypt.encrypt(buf, offset, size);
        } catch (IOException e) {
            IIlllI11.error("Encrypt exception", e);
            return false;
        }

        buf.position(offset + size);
        return true;
    }

    public LoginClientState getState() {
        return state;
    }

    public void setState(LoginClientState state) {
        this.state = state;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(SessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getSessionId() {
        return getLoginCrypt().getCookieId();
    }

    public void sendPacket(L2LoginServerPacket packet) {
        if (isConnected()) {
            getConnection().sendPacket(packet);
        }
    }

    public void close(LoginFail.LoginFailReason reason) {
        if (isConnected()) {
            getConnection().close(new LoginFail(reason));
        }
    }

    public void close(AccountKicked.AccountKickedReason reason) {
        if (isConnected()) {
            getConnection().close(new AccountKicked(reason));
        }
    }

    public void close(L2LoginServerPacket packet) {
        if (isConnected()) {
            getConnection().close(packet);
        }
    }

    @Override
    public void onDisconnection() {
        state = LoginClientState.DISCONNECTED;
        sessionKey = null;
        loginCrypt = null;
    }

    @Override
    public String toString() {
        switch (state) {
            case AUTHED_LOGIN:
                return "[ Account : " + getLogin() + " IP: " + getIpAddr() + "]";
            default:
                return "[ State : " + getState() + " IP: " + getIpAddr() + "]";
        }
    }

    @Override
    protected void onForcedDisconnection() {
    }

    public static enum LoginClientState {
        CONNECTED,
        AUTHED_GG,
        AUTHED_LOGIN,
        DISCONNECTED
    }
}
