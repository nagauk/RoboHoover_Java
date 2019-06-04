package com.yoti.hoover.RoboHoover.model;

import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverResponse;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@Document("roboHooverAudit")
public class RoboHooverAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId roboHooverAuditId;

    private RoboHooverRequest roboHooverRequest;
    private RoboHooverResponse roboHooverResponse;

    private Date startedTime;

    private Date endTime;

    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public RoboHooverRequest getRoboHooverRequest() {
        return roboHooverRequest;
    }

    public void setRoboHooverRequest(RoboHooverRequest roboHooverRequest) {
        this.roboHooverRequest = roboHooverRequest;
    }

    public RoboHooverResponse getRoboHooverResponse() {
        return roboHooverResponse;
    }

    public void setRoboHooverResponse(RoboHooverResponse roboHooverResponse) {
        this.roboHooverResponse = roboHooverResponse;
    }

    public Date getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Date startedTime) {
        this.startedTime = startedTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
