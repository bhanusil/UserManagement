package com.moneylion.usermanagement.service.controller;

import com.moneylion.usermanagement.dto.request.FeatureRequestDTO;
import com.moneylion.usermanagement.dto.response.FeatureResponseDTO;
import com.moneylion.usermanagement.model.Feature;
import com.moneylion.usermanagement.model.User;
import com.moneylion.usermanagement.service.AccessControlService;
import com.moneylion.usermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class AccessController {

    private Logger logger = LoggerFactory.getLogger(AccessController.class);

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/feature")
    @ResponseStatus(HttpStatus.OK)
    public FeatureResponseDTO getFeature(@RequestParam String email, @RequestParam String featureName){
        final FeatureResponseDTO response = new FeatureResponseDTO();
        try{

            logger.info("[getFeature: email, featureName]={},{}", email, featureName);
            User userDB = userService.getUserByEmail(email);
            if(userDB != null){
                Feature featureDB = accessControlService.getFeature(userDB, featureName);
                if(featureDB!=null){
                    response.setCanAccess(featureDB.getEnable());

                }

            }else{
                logger.error("[getFeature: Invalid User Email: email]={} ", email);
            }

        }catch (Exception e){
            logger.error("[getFeature: General Exception]={} ", e);
        }

        logger.info("[getFeature: Response to front end]={}", response);
        return response;
    }

    @PostMapping("/feature")
    public ResponseEntity<Resource> addFeature(@RequestBody FeatureRequestDTO featureRequestDTO) {
        logger.info("[addFeature : Request from front end]={} ", featureRequestDTO);
        ResponseEntity<Resource> response = null;
        try{
            Feature feature = new Feature();
            BeanUtils.copyProperties(featureRequestDTO, feature);
            User userDB = userService.getUserByEmail(featureRequestDTO.getEmail());
            if(userDB == null){
                logger.info("[addFeature: User not existing, adding new user: userEmail]={}", featureRequestDTO.getEmail());
                User userNew = new User();
                userNew.setEmail(featureRequestDTO.getEmail());
                userDB = userService.createUser(userNew);

                logger.info("[addFeature: User adding successful, checking feature: userId]={}", userDB.getId());

            }else{
                logger.info("[addFeature: User is existing, checking feature: userId]={}", userDB.getId());

            }

            if(accessControlService.getFeature(userDB, featureRequestDTO.getFeatureName()) == null){
                feature.setUser(userDB);
                accessControlService.createFeature(feature);

                logger.info("[addFeature: Feature adding successful: featureName, userEmail]={},{}", feature.getFeatureName(), userDB.getEmail());
                response = ResponseEntity.ok().body(null);

            }else{
                logger.info("[addFeature: Existing feature, adding skipped: featureName, userEmail]={},{}", feature.getFeatureName(), userDB.getEmail());

                response = ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
            }

        }catch (Exception e){
            logger.error("[addFeature: General Exception]={}", e);
            response = ResponseEntity.ok().body(null);
        }

        return response;
    }

}
