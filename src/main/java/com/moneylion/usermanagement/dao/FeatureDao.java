package com.moneylion.usermanagement.dao;

import com.moneylion.usermanagement.model.Feature;
import com.moneylion.usermanagement.model.User;

public interface FeatureDao extends BaseDao<Feature>{

    Feature getFeatureByUserAndFeatureName(User user, String featureName);

}
