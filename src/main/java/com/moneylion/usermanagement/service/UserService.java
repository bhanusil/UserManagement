package com.moneylion.usermanagement.service;

import com.moneylion.usermanagement.dao.UserDao;
import com.moneylion.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUserByEmail(String email){
        try{
            return userDao.getUserByEmail(email);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Transactional
    public User createUser(User user){
        return userDao.createAndGet(user);
    }

}
