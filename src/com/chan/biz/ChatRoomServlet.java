package com.chan.biz;


import com.chan.xmpp.XmppHelper;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.DiscoverItems;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chan on 17/2/15.
 */
public class ChatRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration("192.168.1.101", 5222);
//        XMPPConnection xmppConnection = new XMPPConnection(connectionConfiguration);
//        try {
//            xmppConnection.connect();
//            xmppConnection.login("servlet", "1234");
//            MultiUserChat multiUserChat = new MultiUserChat(xmppConnection, "servlet_name___foo@conference.192.168.1" +
//                    ".101");
//            multiUserChat.create("servlet_nickname");
//            multiUserChat.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
//            Collection<HostedRoom> hostrooms;
//            hostrooms = MultiUserChat.getHostedRooms(xmppConnection, "192.168.1.101");
//            for (HostedRoom entry : hostrooms) {
//                System.out.println(entry.getJid() + "->" + entry.getName());
//            }
//
//            ArrayList<DiscoverItems.Item> listDiscoverItems = new ArrayList<DiscoverItems.Item>();
//            // 获得与XMPPConnection相关的ServiceDiscoveryManager
//            ServiceDiscoveryManager discoManager = ServiceDiscoveryManager
//                    .getInstanceFor(xmppConnection);
//
//            // 获得指定XMPP实体的项目
//            // 这个例子获得与在线目录服务相关的项目
//            DiscoverItems discoItems;
//            try {
//                discoItems = discoManager.discoverItems("");
//                // 获得被查询的XMPP实体的要查看的项目
//                Iterator it = discoItems.getItems();
//                // 显示远端XMPP实体的项目
//                while (it.hasNext()) {
//                    DiscoverItems.Item item = (DiscoverItems.Item) it.next();
//                    System.out.println(item.getEntityID());
//                    System.out.println(item.getName());
//                    listDiscoverItems.add(item);
//                }
//            } catch (XMPPException e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            XmppHelper.createChatRoom("while_hahaha", "test");
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
