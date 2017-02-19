package com.chan.biz;


import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by chan on 17/2/15.
 */
public class ChatRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration("192.168.1.101", 5222);
        XMPPConnection xmppConnection = new XMPPConnection(connectionConfiguration);
        try {
            xmppConnection.connect();
            xmppConnection.login("servlet", "1234", "192.168.1.101");

            MultiUserChat.getHostedRooms(xmppConnection, "192.168.1.101");
//            Collection<HostedRoom> hostrooms;
//            hostrooms = MultiUserChat.getHostedRooms(xmppConnection, "192.168.1.101");
//            for (HostedRoom entry : hostrooms) {
//                System.out.println(entry.getJid() + "->" + entry.getName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
