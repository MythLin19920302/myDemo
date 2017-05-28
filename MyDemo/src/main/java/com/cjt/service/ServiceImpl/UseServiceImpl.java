package com.cjt.service.ServiceImpl;


import com.cjt.dao.UserMapper;
import com.cjt.domain.User;
import com.cjt.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by huanglin on 2017/5/17.
 */
@Service
public class UseServiceImpl implements UseService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void insert(User record) {
        userMapper.insert(record);
    }

    @Override
    public List<User> getUser() {
        return userMapper.getUser();
    }
}
