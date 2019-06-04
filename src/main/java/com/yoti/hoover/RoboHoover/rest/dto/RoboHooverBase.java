package com.yoti.hoover.RoboHoover.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public abstract class RoboHooverBase {

    private final int[] coords;

    protected RoboHooverBase(
            @JsonProperty("coords") int[] coords) {
        this.coords = coords;
    }

    public int[] getCoords() {
        return coords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoboHooverBase)) return false;

        RoboHooverBase that = (RoboHooverBase) o;

        return Arrays.equals(coords, that.coords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    @Override
    public String toString() {
        return "RoboHooverBase{" +
                "coords=" + Arrays.toString(coords) +
                '}';
    }

}
