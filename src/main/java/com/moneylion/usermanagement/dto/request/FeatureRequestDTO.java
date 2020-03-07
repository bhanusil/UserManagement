package com.moneylion.usermanagement.dto.request;

import java.io.Serializable;

public class FeatureRequestDTO implements Serializable{
    private static final long serialVersionUID = 6641330348956140935L;

    private String featureName;

    private String email;

    private Boolean enable;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "FeatureRequestDTO{" +
                "featureName=" + featureName +
                ", email=" + email +
                ", enable=" + enable +
                '}';
    }
}
