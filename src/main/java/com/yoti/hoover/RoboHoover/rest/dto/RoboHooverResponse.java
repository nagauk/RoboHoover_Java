package com.yoti.hoover.RoboHoover.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoboHooverResponse extends RoboHooverBase {

    private final int patches;
    private final List<RoboHooverError> errors;

    public RoboHooverResponse(
            @JsonProperty("coords") int[] coords,
            @JsonProperty("patches") int patches,
            List<RoboHooverError> errors) {
        super(coords);
        this.patches = patches;
        this.errors = errors;
    }

    public static RoboHooverResponse errorRoboHooverResponse(List<RoboHooverError> errors) {
        return new RoboHooverResponse(null, 0, errors);
    }

    public int getPatches() {
        return patches;
    }

    public List<RoboHooverError> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoboHooverResponse)) return false;
        if (!super.equals(o)) return false;

        RoboHooverResponse that = (RoboHooverResponse) o;

        if (patches != that.patches) return false;
        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + patches;
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoboHooverResponse{" +
                "coords=" + Arrays.toString(getCoords()) +
                "patches=" + patches +
                ", errors=" + errors +
                '}';
    }
}
