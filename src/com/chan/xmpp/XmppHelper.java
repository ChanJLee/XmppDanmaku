package com.chan.xmpp;


import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.DiscoverItems;

import java.util.Iterator;

/**
 * Created by chan on 17/2/16.
 */
public class XmppHelper {
    public static final int XMPP_PORT = 5222;
    public static final String XMPP_DOMAIN = "192.168.1.101";
    public static final String XMPP_CHAT_ROOM_ID = "conference.192.168.1.101";

    private static volatile XMPPConnection sXmppConnection;

    public static XMPPConnection getXmppConnection() throws XMPPException {
        if (sXmppConnection == null) {
            synchronized (XmppHelper.class) {
                if (sXmppConnection == null) {
                    ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(XMPP_DOMAIN, XMPP_PORT);
                    sXmppConnection = new XMPPConnection(connectionConfiguration);
                }
            }
        }

        if (!sXmppConnection.isConnected()) {
            sXmppConnection.connect();
            sXmppConnection.login("admin", "19940525");
        }

        return sXmppConnection;
    }

    public static String queryChatRoom(String name) throws XMPPException {
        XMPPConnection connection = getXmppConnection();
        ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(connection);
        DiscoverItems discoItems = discoManager.discoverItems(XMPP_CHAT_ROOM_ID);
        Iterator<DiscoverItems.Item> it = discoItems.getItems();
        while (it.hasNext()) {
            DiscoverItems.Item item = it.next();
            if (StringUtils.equals(item.getName(), name)) {
                return item.getEntityID();
            }
        }

        return null;
    }

    public static void createChatRoom(String name, String title) throws XMPPException {
        XMPPConnection connection = getXmppConnection();
        System.out.print(String.format("%s@%s", name, XMPP_CHAT_ROOM_ID));
        MultiUserChat multiUserChat = new MultiUserChat(connection, String.format("%s@%s", name, XMPP_CHAT_ROOM_ID));
        multiUserChat.create(title);
        multiUserChat.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
    }


    public static MultiUserChat mMultiUserChat;
    public static void foo() {
        try {
            MultiUserChat multiUserChat = mMultiUserChat = new MultiUserChat(getXmppConnection(), "spark_name@conference.192.168.1.101");
            multiUserChat.addMessageListener(new PacketListener() {
                @Override
                public void processPacket(Packet packet) {
                    Message message = (Message) packet;
                    System.out.println("from" + packet.getFrom());
                    System.out.println("content" + message.getBody());
                }
            });
            multiUserChat.join("lee");
            multiUserChat.sendMessage("this is servlet");
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }
}
