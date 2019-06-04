package com.yoti.hoover.RoboHoover.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoboHooverError {

    private final boolean success;
    private final String error;
    private final String code;

    public RoboHooverError(
            @JsonProperty("success") boolean success,
            @JsonProperty("error") String error,
            @JsonProperty("code") String code) {
        this.success = success;
        this.error = error;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoboHooverError)) return false;

        RoboHooverError that = (RoboHooverError) o;

        if (success != that.success) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoboHooverError{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
