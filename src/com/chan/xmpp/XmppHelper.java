package com.chan.xmpp;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

/**
 * Created by chan on 17/2/16.
 */
public class XmppHelper {
    private static final int XMPP_PORT = 5222;
    private static final String XMPP_DOMAIN = "localhost";
    private static AbstractXMPPConnection sXMPPConnection;

    public static AbstractXMPPConnection getXMPPConnection() throws IOException, XMPPException, SmackException {

        if (sXMPPConnection == null) {
            synchronized (XmppHelper.class) {
                if (sXMPPConnection == null) {
                    XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
                            .setHost("192.168.1.101")
                            .setPort(5222)
                            .setServiceName("192.168.1.101")
                            .build();
                    sXMPPConnection = new XMPPTCPConnection(configuration);
                }
            }
        }

        return sXMPPConnection;
    }
}
