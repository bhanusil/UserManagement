package com.moneylion.usermanagement.dao.impl;

import com.moneylion.usermanagement.dao.FeatureDao;
import com.moneylion.usermanagement.model.Feature;
import com.moneylion.usermanagement.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FeatureDaoImpl extends BaseDaoImpl<Feature> implements FeatureDao {

    public Feature getFeatureByUserAndFeatureName(User user, String featureName) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();

        CriteriaQuery<Feature> criteriaQuery = cb.createQuery(Feature.class);
        Root<Feature> feature = criteriaQuery.from(Feature.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        ParameterExpression<Integer> where = cb.parameter(Integer.class);
        predicates.add(cb.equal(feature.get("featureName"), featureName));
        predicates.add(cb.equal(feature.get("user"), user));

        TypedQuery<Feature> q = getCurrentSession().createQuery(criteriaQuery.select(feature).where(predicates.toArray(new Predicate[]{})));

        return q.getSingleResult();
    }

}
