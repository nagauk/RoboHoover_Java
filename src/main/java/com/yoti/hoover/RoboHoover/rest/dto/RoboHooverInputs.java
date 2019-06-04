package com.yoti.hoover.RoboHoover.rest.dto;

import java.awt.*;
import java.util.List;
import java.util.Set;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public class RoboHooverInputs {

    private final Point roomSize;
    private final Point hooverInitialPosition;
    private final Set<Point> patchesPosition;
    private final List<Character> hooverDirectionCommands;

    public RoboHooverInputs(Point roomSize,
                            Point hooverInitialPosition,
                            Set<Point> patchesPosition,
                            List<Character> hooverDirectionCommands) {
        this.roomSize = roomSize;
        this.hooverInitialPosition = hooverInitialPosition;
        this.patchesPosition = patchesPosition;
        this.hooverDirectionCommands = hooverDirectionCommands;
    }

    public Point getRoomSize() {
        return roomSize;
    }

    public Point getHooverInitialPosition() {
        return hooverInitialPosition;
    }

    public Set<Point> getPatchesPosition() {
        return patchesPosition;
    }

    public List<Character> getHooverDirectionCommands() {
        return hooverDirectionCommands;
    }

}
