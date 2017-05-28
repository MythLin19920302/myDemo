package com.cjt.springws;

import com.cjt.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

/**
 * Created by huanglin on 2017/5/27.
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory
            .getLogger(HandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String msg = "beforeHandshake*******************\r\nuri:" + uri + ";\r\nremoteAddress;" + remoteAddress;
        if (request instanceof ServletServerHttpRequest) {
            Map<String, String[]> params = ((ServletServerHttpRequest) request).getServletRequest().getParameterMap();
            for (String key : params.keySet()) {
                if (key.equals(Constants.WEBSOCKET_USERID)) {
                    String[] values = params.get(key);
                    attributes.put(Constants.WEBSOCKET_USERID, values[0]);
                    break;
                }
            }
        }

        System.err.println(msg);
        return true;


    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
        URI uri = request.getURI();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String msg = "afterHandshake*******************\r\nuri:" + uri + ";\r\nremoteAddress;" + remoteAddress;
        System.err.println(msg);
//        logger.debug(msg);

    }
}