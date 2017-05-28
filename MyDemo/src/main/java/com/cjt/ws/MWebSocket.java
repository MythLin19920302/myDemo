//package com.cjt.ws;
//
//import com.alibaba.fastjson.JSON;
//import com.cjt.domain.WSBean;
//import com.cjt.utils.WebSocketUtils;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.List;
//
///**
// * Created by huanglin on 2017/5/25.
// */
//@ServerEndpoint("/websocket/{userid}")
//public class MWebSocket {
//
//
//    @OnOpen
//    public void onOpen(@PathParam("userid") String userid, Session session) {
//        System.out.print(userid + "  QueryString" + session.getQueryString() + "  id" + session.getId());
//
////        // add binary based message handler
//        session.addMessageHandler(new MessageHandler.Whole<ByteBuffer>() {
//
//            @Override
//            public void onMessage(ByteBuffer buffer) {
//                System.out.println("binary message: " + new String(buffer.array()));
//            }
//        });
////
//
//        session.addMessageHandler(new MessageHandler.Whole<PongMessage>() {
//
//            @Override
//            public void onMessage(PongMessage pongMessage) {
//                StringBuffer pong = new StringBuffer();
//                pong.append("pong message: ")
//                        .append(new String(pongMessage.getApplicationData()
//                                .array()));
//                System.out.println(pong.toString());
//
//
//            }
//        });
//
//        WebSocketUtils.put(userid, session);
//
//    }
//
//
//    @OnMessage
//    public void onMessage(String message, @PathParam("userid") String userid) throws IOException {
//        System.out.print("MWebSocket" + "String  " + message);
//        WSBean bean = JSON.parseObject(message, WSBean.class);
//        System.out.println(bean.toString());
//        broadcast(userid + ":" + bean.getMessage(), bean.getFrom(), bean.getTo());
//        System.out.print(message);
//    }
//
//
//    @OnError
//    public void onError(@PathParam("userid") String userid, Throwable t) {
//        WebSocketUtils.remove(userid);
//    }
//
//    @OnClose
//    public void onClose(@PathParam("userid") String userid) {
//        WebSocketUtils.remove(userid);
//    }
//
//    private void broadcast(String message, String from, String to) {
//        if ("-1".equals(to)) {
//            List<Session> sessions = WebSocketUtils.getOtherSession(from);
//            if (sessions.size() > 0) {
//                for (Session s : sessions) {
//                    s.getAsyncRemote().sendText(message);
//                }
//            }
//
//        } else {
//            Session session = WebSocketUtils.get(to);
//            if (null != session && session.isOpen()) {
//                session.getAsyncRemote().sendText(message);
//            } else {
//                //获取自己的session
//                Session self = WebSocketUtils.get(from);
//                self.getAsyncRemote().sendText("对方已下线");
//            }
//        }
//
//    }
//}
//
