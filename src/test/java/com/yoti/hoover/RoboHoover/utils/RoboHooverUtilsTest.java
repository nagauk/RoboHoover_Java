package com.yoti.hoover.RoboHoover.utils;

import com.yoti.hoover.RoboHoover.Exceptions.RoboHooverException;
import com.yoti.hoover.RoboHoover.Utils.RoboHooverMessageConstants;
import com.yoti.hoover.RoboHoover.Utils.RoboHooverUtils;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public class RoboHooverUtilsTest extends RoboHooverUtils{

    private RoboHooverUtils utils = new RoboHooverUtils();
    private RoboHooverRequest roboHooverReq;


    @After
    public void tearDown() {
        utils = null;
        roboHooverReq = null;
    }

    /*
    * Null value passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
//    @Test(expected = RoboHooverException.class)
    @Test()
    public void validateInputsWhenNullRoomSize() {
        roboHooverReq = new RoboHooverRequest(null, null, null, null);
        try {
            validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * negative value passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenIncorrectRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {-1, 5}, null, null, null);
        try {
            validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * Only one value passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenInsufficientElementsRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5}, null, null, null);
        try {
            validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * More than two values passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenExcessiveElementsRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5, 5}, null, null, null);
        try {
            validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * Different X/Y values passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenNotRectangularRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 4}, null, null, null);
        try {
            validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * Correct values passed as RoomSize
    *
    * Expected: no exception raised
    */
    @Test
    public void validateInputsWhenEligibleRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, null, null, null);
        try {
            validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    /*
    * Null passed as Coords
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenNullAsInitialPosition() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, null, null, null);
        try {
            validateInitialPositionInput(roboHooverReq.getCoords(), roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INITIAL_POSITION, e.getMessage());
        }
    }

    /*
    * Null passed as Coords
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenOutOfRoomAreaAsInitialPosition() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, new int[] {5, 6}, null, null);
        try {
            validateInitialPositionInput(roboHooverReq.getCoords(), roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INITIAL_POSITION, e.getMessage());
        }
    }

    /*
    * Correct values passed as Coords
    *
    * Expected: no exception raised
    */
    @Test
    public void validateInputsWhenEligibleInitialPosition() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, new int[] {4, 4}, null, null);
        try {
            validateInitialPositionInput(roboHooverReq.getCoords(), roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    /*
    * Null value passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenNullInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, null);
        try {
            validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Unwanted Characters values passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenUnwantedCharactersAsInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NNQWNWTBSN");
        try {
            validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Special Characters values passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenSpecialCharactersAsInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NN?)");
        try {
            validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Numbers values passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenNumbersAsInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NN12");
        try {
            validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Spaces passed as Commands
    *
    * Expected: No exception raised
    */
    @Test
    public void validateInputsWhenEligibleInstructionsWithSpaces() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NN ESW WNW SN");
        try {
            validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    /*+
    * Correct values passed as RoomSize
    *
    * Expected: no exception raised
    */
    @Test
    public void validateInputsWhenEligibleInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NNESWWNWSN");
        try {
            validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

}
