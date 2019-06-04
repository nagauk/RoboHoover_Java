package com.yoti.hoover.RoboHoover.service;

import com.yoti.hoover.RoboHoover.Exceptions.RoboHooverException;
import com.yoti.hoover.RoboHoover.model.RoboHooverAudit;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;

public interface RoboHooverService {

    public void saveRoboHooverAuditDetails(RoboHooverAudit roboHooverAudit);

    public RoboHooverAudit runCleaningDirtPatches(RoboHooverRequest roboHooverReq) throws RoboHooverException;

}
