package com.yoti.hoover.RoboHoover.repository;

import com.yoti.hoover.RoboHoover.model.RoboHooverAudit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public interface RoboHooverAuditRepository extends MongoRepository<RoboHooverAudit,Long> {
}
