package com.cjt.service;

import com.cjt.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huanglin on 2017/5/17.
 */
public interface UseService {
    void insert(User record);

    List<User> getUser();
}
