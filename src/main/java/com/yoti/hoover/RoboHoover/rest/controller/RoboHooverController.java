package com.yoti.hoover.RoboHoover.rest.controller;

import com.yoti.hoover.RoboHoover.Exceptions.RoboHooverException;
import com.yoti.hoover.RoboHoover.model.RoboHooverAudit;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverError;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverResponse;
import com.yoti.hoover.RoboHoover.service.RoboHooverService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.RasterFormatException;
import java.util.Arrays;

/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@RestController
@RequestMapping("/roboHoover")
public class RoboHooverController {

    private final Logger logger = LoggerFactory.getLogger(RoboHooverController.class);

    @Autowired
    private RoboHooverService roboHooverService;

    @ApiOperation(value = "Robo Hoover Demo",
            notes = "Robo Hoover cleaning room patches",
            response = RoboHooverResponse.class,
            httpMethod = "POST"
    )
    @RequestMapping(value = "/dirt-patches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public HttpEntity<RoboHooverResponse> roboHooverPatches(@RequestBody RoboHooverRequest roboHooverReq) {
        logger.info("Fetching ...");
        RoboHooverResponse roboHooverResp;
        RoboHooverAudit roboHooverAudit;
        try {

            roboHooverAudit = roboHooverService.runCleaningDirtPatches(roboHooverReq);
            roboHooverResp = roboHooverAudit.getRoboHooverResponse();
            roboHooverService.saveRoboHooverAuditDetails(roboHooverAudit);

        } catch (RoboHooverException e) {
            logger.error(e.getMessage());
            roboHooverResp = RoboHooverResponse.errorRoboHooverResponse(
                    Arrays.asList(new RoboHooverError(false, "Request details are not valid.", e.getMessage())));
            return new ResponseEntity<>(roboHooverResp, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            logger.error(e.getMessage());
            roboHooverResp = RoboHooverResponse.errorRoboHooverResponse(
                    Arrays.asList(new RoboHooverError(false, "Request details are not valid.", e.getMessage())));
            return new ResponseEntity<>(roboHooverResp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(roboHooverResp, HttpStatus.OK);
    }

}
