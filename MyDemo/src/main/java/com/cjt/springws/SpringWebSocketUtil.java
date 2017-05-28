package com.cjt.springws;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huanglin on 2017/5/28.
 */
public class SpringWebSocketUtil {
    private static Map<String, WebSocketSession> map = new ConcurrentHashMap<>();

    private static final String PREFIX = "mws";

    public static void put(String userid, WebSocketSession session) {
        map.put(getKey(userid), session);
    }


    public static WebSocketSession get(String userid) {
        return map.get(getKey(userid));

    }

    public static List<WebSocketSession> getOtherSession(String userid) {
        List<WebSocketSession> result = new ArrayList<WebSocketSession>();
        Set<Map.Entry<String, WebSocketSession>> set = map.entrySet();
//        Iterator<Map.Entry<String, Session>> iterator= set.iterator();
        for (Map.Entry<String, WebSocketSession> s : set) {
            if (!s.getKey().equals(getKey(userid))) {
                result.add(s.getValue());

            }
        }
        return result;
    }

    public static void remove(String userid) {
        map.remove(getKey(userid));
    }

    public static boolean hasConnection(String userid) {
        return map.containsKey(getKey(userid));
    }


    private static String getKey(String userid) {
        return PREFIX + "_" + userid;
    }
}
