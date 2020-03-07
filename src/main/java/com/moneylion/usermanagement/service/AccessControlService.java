package com.moneylion.usermanagement.service;

import com.moneylion.usermanagement.dao.FeatureDao;
import com.moneylion.usermanagement.model.Feature;
import com.moneylion.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccessControlService {

    @Autowired
    private FeatureDao featureDao;

    @Transactional
    public Feature getFeature(User user, String featureName){
        try{
            return featureDao.getFeatureByUserAndFeatureName(user, featureName);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Transactional
    public Feature createFeature(Feature feature){
        return featureDao.createAndGet(feature);
    }

}
