package com.mythlin.test.dao;

import com.cjt.domain.User;
import com.cjt.service.UseService;
import com.mythlin.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huanglin on 2017/5/17.
 */
public class UseTest extends BaseTest {
    @Autowired
    UseService useService;

    @Test
    public void testgetUser() throws Exception {
        List<User> user = useService.getUser();
        for (User u : user) {
            System.out.println(u);
        }
    }
}
