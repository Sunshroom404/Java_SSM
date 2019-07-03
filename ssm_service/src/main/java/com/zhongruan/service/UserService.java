package com.zhongruan.service;

import com.zhongruan.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<UserInfo> findAll(int page,int size);

    public Boolean doLogin (UserInfo userInfo);

    public void save(UserInfo userInfo);

    public void delete(long id);

    public void update(UserInfo userInfo);

    public UserInfo queryById(long id);

}
