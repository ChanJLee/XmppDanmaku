package com.chan.xmpp;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

/**
 * Created by chan on 17/2/16.
 */
public class XmppHelper {
    private static final int XMPP_PORT = 5222;
    private static final String XMPP_DOMAIN = "localhost";
    private static AbstractXMPPConnection sXMPPConnection;

    public static XMPPConnection getXMPPConnection() {

        if (sXMPPConnection == null) {
            synchronized (XmppHelper.class) {
                if (sXMPPConnection == null) {
                    XMPPTCPConnectionConfiguration connectionConfiguration =
                }
            }
        }

        return sXMPPConnection;
    }

    public static void createRoom(String roomName) throws XMPPException {
        XMPPConnection xmppConnection = getXMPPConnection();
        if (!xmppConnection.isConnected()) {
            xmppConnection.connect();
        }
    }

    public static void createUser(String username, String password) throws XMPPException {
        XMPPConnection xmppConnection = getXMPPConnection();
        if (!xmppConnection.isConnected()) {
            xmppConnection.connect();
        }
        AccountManager accountManager = xmppConnection.getAccountManager();
        accountManager.createAccount(username, password);
    }

    public static void deleteUser(String username) throws XMPPException {
        XMPPConnection xmppConnection = getXMPPConnection();
        if (!xmppConnection.isConnected()) {
            xmppConnection.connect();
        }
        AccountManager accountManager = xmppConnection.getAccountManager();
        accountManager.deleteAccount();
    }
}
