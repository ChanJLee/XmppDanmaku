package com.chan.biz;


import com.chan.xmpp.XmppHelper;
import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smack.XMPPException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by chan on 17/2/15.
 */
public class ChatRoomServlet extends HttpServlet {

    private static final String PARAM_NAME = "room_name";
    private static final String PARAM_TITLE = "room_title";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String roomName = request.getParameter(PARAM_NAME);
        String rootTitle = request.getParameter(PARAM_TITLE);

        if (StringUtils.isBlank(roomName) || StringUtils.isBlank(rootTitle)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            XmppHelper.createChatRoom(roomName, rootTitle);
        } catch (XMPPException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomName = req.getParameter(PARAM_NAME);
        if (StringUtils.isBlank(roomName)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            String id = XmppHelper.queryChatRoom(roomName);
            if (StringUtils.isBlank(id)) {
                id = "";
            }
            XmppHelper.foo();
            ServletOutputStream outputStream = resp.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(id);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
