package com.yoti.hoover.RoboHoover.service.impl;

import com.yoti.hoover.RoboHoover.Exceptions.RoboHooverException;
import com.yoti.hoover.RoboHoover.Utils.RoboHooverMessageConstants;
import com.yoti.hoover.RoboHoover.Utils.RoboHooverUtils;
import com.yoti.hoover.RoboHoover.model.RoboHooverAudit;
import com.yoti.hoover.RoboHoover.model.Room;
import com.yoti.hoover.RoboHoover.repository.RoboHooverAuditRepository;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverError;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverResponse;
import com.yoti.hoover.RoboHoover.service.RoboHooverComandExecution;
import com.yoti.hoover.RoboHoover.service.RoboHooverService;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Date;

/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@Service
public class RoboHooverServiceImpl implements RoboHooverService {

    Logger logger = LoggerFactory.getLogger(RoboHooverServiceImpl.class);

    @Autowired
    private RoboHooverAuditRepository roboHooverAuditRepository;



    @Override
    public RoboHooverAudit runCleaningDirtPatches(RoboHooverRequest roboHooverReq) throws RoboHooverException {

        logger.info("Start RoboHooverServiceImpl::runCleaningDirtPatches");
        RoboHooverAudit roboHooverAudit = new RoboHooverAudit();
        roboHooverAudit.setRoboHooverRequest(roboHooverReq);
        roboHooverAudit.setStartedTime(new Date());

        if(null != roboHooverReq) {
            logger.info("RoboHooverService::runCleaning[RoboHooverRequestDetails:{}]",roboHooverReq.toString());
            logger.debug("validating robohoover request");
            RoboHooverInputs roboHooverInputs = new RoboHooverUtils().validateInputs(roboHooverReq);
            logger.debug("Measuring room with provided coordinates");
            Room room = new Room(roboHooverInputs.getRoomSize(), roboHooverInputs.getPatchesPosition());
            logger.debug("initilize robo hoover");
            RoboHooverComandExecution roboHooverComandExecution = getRoboHooverExecutionType(roboHooverInputs);
            roboHooverComandExecution.executeCommands(roboHooverInputs.getHooverDirectionCommands());

            final Point finalHooverPosition = roboHooverComandExecution.hooverPosition();

            RoboHooverResponse roboHooverResponse =
             new RoboHooverResponse(
                    new int[] {finalHooverPosition.x, finalHooverPosition.y},
                     roboHooverComandExecution.getDirtPartsRemovalCount(),
                    null);
            roboHooverAudit.setRoboHooverResponse(roboHooverResponse);
            roboHooverAudit.setEndTime(new Date());
            return roboHooverAudit;
        }
        roboHooverAudit.setException(new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_SERVICE));
        roboHooverAudit.setEndTime(new Date());
        RoboHooverResponse roboHooverResponse = new RoboHooverResponse(null,
                0,
                Arrays.asList(new RoboHooverError(
                        false,
                        RoboHooverMessageConstants.EROOR_MESSAGE_SERVICE,
                        "")));
        roboHooverAudit.setRoboHooverResponse(roboHooverResponse);
        return roboHooverAudit;
    }


    private RoboHooverComandExecution getRoboHooverExecutionType(RoboHooverInputs roboHooverInputs) throws RoboHooverException {
            return new RoboHooverUtils().validateInstructionsAndGetRoboHooverCommandExecution(roboHooverInputs);
    }

    @Override
    public void saveRoboHooverAuditDetails(RoboHooverAudit roboHooverAudit){
        roboHooverAuditRepository.save(roboHooverAudit);
    }


}
