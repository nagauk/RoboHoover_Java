package com.yoti.hoover.RoboHoover.service.impl;

import com.yoti.hoover.RoboHoover.model.Room;
import com.yoti.hoover.RoboHoover.service.RoboHooverComandExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

/**
 * *Author  : 30620
 * *Date    : 5/24/2019
 **/
public class RoboHooverCommandExecutionImpl implements RoboHooverComandExecution {

    private final Logger logger = LoggerFactory.getLogger(RoboHooverCommandExecutionImpl.class);

    private final Point initialHooverPosition;

    private final Room roomToClean;

    public RoboHooverCommandExecutionImpl(Point initialHooverPosition, Room roomToClean) {
        this.initialHooverPosition = initialHooverPosition;
        this.roomToClean = roomToClean;
        initHoover(initialHooverPosition);
    }

    /***
     *
     * @param commands
     */

    int dirtPartsRemovalCount;
    @Override
    public void executeCommands(List<Character> commands) {
        logger.info("Executing hoover direction commands: " + commands.toString());
        commands.stream().forEach(command -> {
            switch(Character.toUpperCase(command)) {
                case 'N':
                    moveHoover(moveNorth());
                    break;
                case 'E':
                    moveHoover(moveEast());
                    break;
                case 'S':
                    moveHoover(moveSouth());
                    break;
                case 'W':
                    moveHoover(moveWest());
                    break;
                default:
                    logger.error("Command not Executed: " + command);
            }
            if(roomToClean.hasCoveredAnyDirtParts()) {
                dirtPartsRemovalCount = roomToClean.incrementCountAndRemoveDirtPartsRecord();
            }
        });
    }

    /***
     *
     * @return Point
     */
    public Point getInitialHooverPosition() {
        return initialHooverPosition;
    }

    /***
     *
     * @return Point
     */
    @Override
    public Point hooverPosition() {
        return this.roomToClean.getHooverPosition();
    }

    @Override
    public int getDirtPartsRemovalCount() {
        return dirtPartsRemovalCount;
    }

    public Point moveNorth() {
        Point currentPosition = hooverPosition();
        int upperRoomEdge = this.roomToClean.getRoomEdges().y;
        if(currentPosition.y < upperRoomEdge) {
            return new Point(currentPosition.x, currentPosition.y+1);
        }
        return currentPosition;
    }

    public Point moveSouth() {
        Point currentPosition = hooverPosition();
        if(currentPosition.y > 0) {
            return new Point(currentPosition.x, currentPosition.y-1);
        }
        return currentPosition;
    }

    public Point moveWest() {
        Point currentPosition = hooverPosition();
        if(currentPosition.x > 0) {
            return new Point(currentPosition.x-1, currentPosition.y);
        }
        return currentPosition;
    }

    public Point moveEast() {
        Point currentPosition = hooverPosition();
        int easternRoomEdge = this.roomToClean.getRoomEdges().x;
        if(currentPosition.x < easternRoomEdge) {
            return new Point(currentPosition.x+1, currentPosition.y);
        }
        return currentPosition;
    }

    private void initHoover(Point initialHooverPosition) {
        this.roomToClean.initHoover(initialHooverPosition);
    }

    private void moveHoover(Point nextPosition) {
        this.roomToClean.moveHoover(nextPosition);
    }
}
