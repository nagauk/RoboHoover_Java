package com.yoti.hoover.RoboHoover.service;

import com.yoti.hoover.RoboHoover.Exceptions.RoboHooverException;
import com.yoti.hoover.RoboHoover.Utils.RoboHooverMessageConstants;
import com.yoti.hoover.RoboHoover.model.RoboHooverAudit;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverError;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverResponse;
import com.yoti.hoover.RoboHoover.service.impl.RoboHooverServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@DataJpaTest
public class RoboHooverServiceTest {

    RoboHooverServiceImpl service;


    RoboHooverRequest request;

    @Before
    public void setUp() throws Exception {
        service = new RoboHooverServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        request = null;
    }

    @Test
    public void runCleaningWithNullAsInputParams() throws Exception {
        final RoboHooverAudit roboHooverAudit = service.runCleaningDirtPatches(null);
        assertEquals(new RoboHooverResponse(null, 0, Arrays.asList(new RoboHooverError(
                false, RoboHooverMessageConstants.EROOR_MESSAGE_SERVICE,""))), roboHooverAudit.getRoboHooverResponse());
    }

    @Test
    public void runCleaningWithWithWrongRoomSize() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 6},
                new int[] {1, 1},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "NSSN");
        try {
            final RoboHooverAudit roboHooverAudit = service.runCleaningDirtPatches(request);
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE, e.getMessage());
        }
    }

    @Test
    public void runCleaningDirtPatchesWithWithWrongInitialPosition() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 5},
                new int[] {1, 6},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "NSSN");
        try {
            final RoboHooverAudit roboHooverAudit = service.runCleaningDirtPatches(request);
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INITIAL_POSITION, e.getMessage());
        }
    }

    @Test
    public void runCleaningDirtPatchesWithSomeInvalidCommandsWithNoDirtParts() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 5},
                new int[] {1, 1},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "NSSN");
        final RoboHooverAudit roboHooverAudit = service.runCleaningDirtPatches(request);
        assertEquals(new RoboHooverResponse(new int[] {1, 1}, 0, null), roboHooverAudit.getRoboHooverResponse());
    }

    @Test
    public void runCleaningDirtPatchesWithSomeInvalidCommandsWith2DirtPartsRemoved() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 5},
                new int[] {1, 1},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "EENNEEE");
        final RoboHooverAudit roboHooverAudit = service.runCleaningDirtPatches(request);
        assertEquals(new RoboHooverResponse(new int[] {5, 3}, 2, null), roboHooverAudit.getRoboHooverResponse());
    }

}
