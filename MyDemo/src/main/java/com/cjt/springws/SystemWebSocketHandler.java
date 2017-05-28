package com.cjt.springws;

import com.alibaba.fastjson.JSON;
import com.cjt.config.Constants;
import com.cjt.domain.WSBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;


/**
 * Created by huanglin on 2017/5/27.
 */
public class SystemWebSocketHandler implements WebSocketHandler {
    private static Logger logger = LoggerFactory
            .getLogger(SystemWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String sessionId = (String) webSocketSession.getAttributes().get(Constants.WEBSOCKET_USERID);
        if (sessionId != null && sessionId.length() > 0)
            SpringWebSocketUtil.put(sessionId, webSocketSession);
        logger.debug("connect to the success......" + "   " + webSocketSession.getAttributes().get(Constants.WEBSOCKET_USERID));
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage instanceof PongMessage)
            logger.debug("connect to the websocket handleMessage......PongMessage");
        else if (webSocketMessage instanceof PingMessage) {
            logger.debug("connect to the websocket handleMessage......PingMessage");
//            ByteBuffer byteBuffer = (ByteBuffer) data;
//            byte[] bytes = new byte[byteBuffer.capacity()];
//            byteBuffer.get(bytes, 0, bytes.length);
//            logger.debug("connect to the websocket handleMessage......" + Arrays.toString(bytes));
        } else if (webSocketMessage instanceof BinaryMessage)
            logger.debug("connect to the websocket handleMessage......BinaryMessage");
        else if (webSocketMessage instanceof TextMessage) {
            WSBean bean = JSON.parseObject(((TextMessage) webSocketMessage).getPayload().toString(), WSBean.class);
            broadcast(bean.getMessage(), bean.getFrom(), bean.getTo());
        }
        logger.debug("connect to the websocket handleMessage......TextMessage");


    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.debug("connect to the error......");
        String sessionId = (String) webSocketSession.getAttributes().get(Constants.WEBSOCKET_USERID);
        if (sessionId != null && sessionId.length() > 0)
            SpringWebSocketUtil.remove(sessionId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("connect to the close......");
        String sessionId = (String) webSocketSession.getAttributes().get(Constants.WEBSOCKET_USERID);
        if (sessionId != null && sessionId.length() > 0)
            SpringWebSocketUtil.remove(sessionId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void broadcast(String message, String from, String to) {
        if ("-1".equals(to)) {
            List<WebSocketSession> sessions = SpringWebSocketUtil.getOtherSession(from);
            if (sessions.size() > 0) {
                for (WebSocketSession s : sessions) {
                    sendText(s, message);
                }
            }

        } else {
            WebSocketSession session = SpringWebSocketUtil.get(to);
            if (null != session && session.isOpen()) {
                sendText(session, message);
            } else {
                //获取自己的session
                sendText(SpringWebSocketUtil.get(from), message);
            }
        }
    }

    public boolean sendText(WebSocketSession webSocketSession, String message) {
        try {
            webSocketSession.sendMessage(new TextMessage(message));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
