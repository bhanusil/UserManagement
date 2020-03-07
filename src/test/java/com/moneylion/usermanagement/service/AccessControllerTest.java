package com.moneylion.usermanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneylion.usermanagement.dto.request.FeatureRequestDTO;
import com.moneylion.usermanagement.dto.response.FeatureResponseDTO;
import com.moneylion.usermanagement.model.User;
import com.moneylion.usermanagement.service.controller.AccessController;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Response;
import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessControllerTest {

    @Autowired
    AccessController accessController;

    @Test
    public void testA_getFeature_Without_AnyData() throws Exception {
        String email = "01@gmail.com";
        String featureName = "feature01";
        FeatureResponseDTO featureResponseDTO = accessController.getFeature(email,featureName);

        assertNotNull(featureResponseDTO);
        assertEquals(false, featureResponseDTO.getCanAccess());
    }

    @Test
    public void testB_addFeature_1stTime() throws Exception {

        String email = "01@gmail.com";
        String featureName = "feature01";
        FeatureRequestDTO featureRequestDTO = new FeatureRequestDTO();
        featureRequestDTO.setEmail(email);
        featureRequestDTO.setFeatureName(featureName);
        featureRequestDTO.setEnable(true);

        ResponseEntity<Resource> response = accessController.addFeature(featureRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    public void testC_getFeature_AfterAddingData() throws Exception {
        String email = "01@gmail.com";
        String featureName = "feature01";
        FeatureResponseDTO featureResponseDTO = accessController.getFeature(email,featureName);

        assertNotNull(featureResponseDTO);
        assertEquals(true, featureResponseDTO.getCanAccess());
    }

    @Test
    public void testD_addFeature_TryingToAddSameFeature2ndTime() throws Exception {
        String email = "01@gmail.com";
        String featureName = "feature01";
        FeatureRequestDTO featureRequestDTO = new FeatureRequestDTO();
        featureRequestDTO.setEmail(email);
        featureRequestDTO.setFeatureName(featureName);
        featureRequestDTO.setEnable(true);

        ResponseEntity<Resource> response = accessController.addFeature(featureRequestDTO);

        assertEquals(HttpStatus.NOT_MODIFIED, response.getStatusCode());
        assertNull(response.getBody());
    }

}
