package com.yoti.hoover.RoboHoover.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverBase;

import java.util.Arrays;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public class RoboHooverRequest extends RoboHooverBase {

    private final int[] roomSize;
    private final int[][] patches;
    private final String instructions;

    public RoboHooverRequest(
            @JsonProperty("roomSize") int[] roomSize,
            @JsonProperty("coords") int[] coords,
            @JsonProperty("patches") int[][] patches,
            @JsonProperty("instructions") String instructions) {
        super(coords);
        this.roomSize = roomSize;
        this.patches = patches;
        this.instructions = instructions;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public int[][] getPatches() {
        return patches;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "RoboHooverRequest{" +
                "roomSize=" + ((null != roomSize) ? Arrays.toString(roomSize):null) +
                ", patches=" + ((null != patches) ? Arrays.toString(patches):null) +
                ",instructions='" + instructions + '\'' +
                '}';
    }
}
