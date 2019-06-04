package com.yoti.hoover.RoboHoover.model;

import com.yoti.hoover.RoboHoover.service.RoboHooverComandExecution;
import com.yoti.hoover.RoboHoover.service.impl.RoboHooverCommandExecutionImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public class RoboHooverCommandExecutionTest {

    private Room room;
    private RoboHooverCommandExecutionImpl roboHooverComandExecution;
    private List<Character> commands;

    @Before
    public void setUp() throws Exception {
        room = new Room(new Point(5, 5),
                new HashSet<>(Arrays.asList(
                        new Point(2, 3),
                        new Point(3, 2),
                        new Point(3, 3)
                )));
    }

    @After
    public void tearDown() throws Exception {
        room = null;
        roboHooverComandExecution = null;
        commands = null;
    }

    @Test
    public void testHooverInitialPosition() {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(5, 5), room);
        assertEquals(new Point(5, 5), this.room.getHooverPosition());
    }

    @Test
    public void executeCommandsWithSomeInvalidMovesWithNoDirtParts() {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(5, 5), room);
        assertEquals(new Point(5, 5), this.room.getHooverPosition());
        assertEquals(3, room.getDirtParts().size());
        commands = new LinkedList<>(Arrays.asList('N', 'S', 'S', 'N'));
        roboHooverComandExecution.executeCommands(commands);
        assertEquals(new Point(5, 4), this.room.getHooverPosition());
        assertEquals(3, room.getDirtParts().size());
        assertEquals(0, this.roboHooverComandExecution.getDirtPartsRemovalCount());
    }

    @Test
    public void executeCommandsWithSomeInvalidMovesWith2DirtPatchesRemoved() {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        assertEquals(3, room.getDirtParts().size());
        commands = new LinkedList<>(Arrays.asList('E', 'E', 'N', 'N', 'E', 'E', 'E'));
        roboHooverComandExecution.executeCommands(commands);
        assertEquals(new Point(5, 3), this.room.getHooverPosition());
        assertEquals(1, room.getDirtParts().size());
        assertEquals(2, this.roboHooverComandExecution.getDirtPartsRemovalCount());
    }

    @Test
    public void executeCommandsWithSomeInvalidMovesWith2DirtPartsSamePosition() {
        room = new Room(new Point(5, 5),
                new HashSet<>(Arrays.asList(
                        new Point(2, 3),
                        new Point(3, 2),
                        new Point(3, 3),
                        new Point(3, 3)
                )));
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        assertEquals(3, room.getDirtParts().size());
        commands = new LinkedList<>(Arrays.asList('E', 'E', 'N', 'N', 'E', 'E', 'E'));
        roboHooverComandExecution.executeCommands(commands);
        assertEquals(new Point(5, 3), this.room.getHooverPosition());
        assertEquals(1, room.getDirtParts().size());
        assertEquals(2, this.roboHooverComandExecution.getDirtPartsRemovalCount());
    }

    @Test
    public void testMoveNorthWithNotValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(1, 5), room);
        assertEquals(new Point(1, 5), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveNorth();
        assertEquals(new Point(1, 5), retVal);
    }

    @Test
    public void testMoveNorthWithValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(4, 1), room);
        assertEquals(new Point(4, 1), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveNorth();
        assertEquals(new Point(4, 2), retVal);
    }

    @Test
    public void testMoveEastWithNotValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(5, 5), room);
        assertEquals(new Point(5, 5), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveEast();
        assertEquals(new Point(5, 5), retVal);
    }

    @Test
    public void testMoveEastWithValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(4, 1), room);
        assertEquals(new Point(4, 1), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveEast();
        assertEquals(new Point(5, 1), retVal);
    }

    @Test
    public void testMoveSouthWithNotValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(1, 0), room);
        assertEquals(new Point(1, 0), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveSouth();
        assertEquals(new Point(1, 0), retVal);
    }

    @Test
    public void testMoveSouthWithValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveSouth();
        assertEquals(new Point(1, 0), retVal);
    }

    @Test
    public void testMoveWestWithNotValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(0, 1), room);
        assertEquals(new Point(0, 1), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveWest();
        assertEquals(new Point(0, 1), retVal);
    }

    @Test
    public void testMoveWestWithValidValue() throws Exception {
        roboHooverComandExecution = new RoboHooverCommandExecutionImpl(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        Point retVal = roboHooverComandExecution.moveWest();
        assertEquals(new Point(0, 1), retVal);
    }

}
