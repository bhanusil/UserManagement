package com.moneylion.usermanagement.dao.impl;

import com.moneylion.usermanagement.dao.UserDao;
import com.moneylion.usermanagement.model.Feature;
import com.moneylion.usermanagement.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);

        criteriaQuery.where(cb.equal(from.get("email"), email));

        TypedQuery<User> q = getCurrentSession().createQuery(criteriaQuery);

        return q.getSingleResult();
    }
}
