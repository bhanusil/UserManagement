package com.moneylion.usermanagement.dao;

import com.moneylion.usermanagement.model.Feature;
import com.moneylion.usermanagement.model.User;

public interface UserDao extends BaseDao<User>{

    User getUserByEmail(String email);

}
