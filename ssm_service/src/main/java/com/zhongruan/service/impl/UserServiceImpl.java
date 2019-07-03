package com.zhongruan.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhongruan.bean.Role;
import com.zhongruan.bean.UserInfo;
import com.zhongruan.dao.RoleDao;
import com.zhongruan.dao.UserDao;
import com.zhongruan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<UserInfo> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public Boolean doLogin(UserInfo userInfo) {
        UserInfo userInfo1=userDao.doLogin(userInfo);
        if(null!=userInfo1)
        {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void save(UserInfo userInfo) {
        userDao.save(userInfo);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public void update(UserInfo userInfo) {
        userDao.update(userInfo);
    }

    @Override
    public UserInfo queryById(long id) {
        return userDao.queryById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=userDao.findByUserName(username);
        User user=null;
        if(userInfo!=null){
            List<Role> roles=roleDao.findRoleByUserId(userInfo.getId());
            userInfo.setRoles(roles);
            user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(roles));
        }
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
}
