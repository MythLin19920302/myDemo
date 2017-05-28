package com.cjt.web;

import com.alibaba.fastjson.JSON;
import com.cjt.domain.User;
import com.cjt.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by huanglin on 2017/5/17.
 */
@Controller
@RequestMapping("/use")
public class UseController {
    @Autowired
    UseService useService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private String list() {
        List<User> list = useService.getUser();
        return JSON.toJSONString(list);
    }

}
