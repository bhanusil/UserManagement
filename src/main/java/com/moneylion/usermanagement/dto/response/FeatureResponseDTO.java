package com.moneylion.usermanagement.dto.response;

import java.io.Serializable;

public class FeatureResponseDTO implements Serializable{
    private static final long serialVersionUID = 6641330348956140935L;

    private Boolean canAccess = Boolean.FALSE;

    public Boolean getCanAccess() {
        return canAccess;
    }

    public void setCanAccess(Boolean canAccess) {
        this.canAccess = canAccess;
    }

    @Override
    public String toString() {
        return "FeatureResponseDTO{" +
                "canAccess=" + canAccess +
                '}';
    }
}
