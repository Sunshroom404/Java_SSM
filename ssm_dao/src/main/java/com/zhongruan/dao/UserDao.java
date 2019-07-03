package com.zhongruan.dao;

import com.zhongruan.bean.UserInfo;

import java.util.List;

public interface UserDao {

    public List<UserInfo> findAll();

    public UserInfo doLogin (UserInfo userInfo);

    public void save(UserInfo userInfo);

    public void delete(long id);

    public void update(UserInfo userInfo);

    public UserInfo queryById(long id);

    public UserInfo findByUserName(String username);
}
