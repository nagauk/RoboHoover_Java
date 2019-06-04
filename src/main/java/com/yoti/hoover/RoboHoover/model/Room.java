package com.yoti.hoover.RoboHoover.model;

import java.awt.*;
import java.util.Optional;
import java.util.Set;

/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public class Room {

    private final Point roomEdges;
    private final Set<Point> dirtParts;
    private Optional<Point> hooverPosition;
    private int dirtPartsRemovalCount = 0;

    public Room(Point roomEdges, Set<Point> dirtParts) {
        this.roomEdges = roomEdges;
        this.dirtParts = dirtParts;
        this.hooverPosition = Optional.empty();
    }


    public Point getRoomEdges() {
        return roomEdges;
    }

    public Set<Point> getDirtParts() {
        return dirtParts;
    }

    public Point getHooverPosition() {
        return hooverPosition.orElseThrow(IllegalStateException::new);
    }

    public void initHoover(Point initialHooverPosition) {
        hooverPosition = Optional.of(initialHooverPosition);
    }

    public void moveHoover(Point newPosition) {
        hooverPosition = Optional.of(newPosition);
    }

    /****
     *
     * @return boolean
     *
     * it will find out any dirt patches available or not
     */
    public boolean hasCoveredAnyDirtParts() {
        return dirtParts.stream()
                .anyMatch(dirtPart -> dirtPart.equals(hooverPosition.get()));
    }

    /***
     *  It will counts the cleaned dirt parts
     */
    public int incrementCountAndRemoveDirtPartsRecord() {
        dirtPartsRemovalCount += 1;
        dirtParts.remove(hooverPosition.get());
        return dirtPartsRemovalCount;
    }


}
