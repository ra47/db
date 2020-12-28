package com.alli.backend.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Role {
    AGENT("AGENT"),
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

//    @JsonCreator
//    public static Role decode(final String role) {
//        return Stream.of(Role.values()).filter(targetEnum -> targetEnum.role.equals(role)).findFirst().orElse(null);
//    }
//
//    @JsonValue
//    public String getRole() {
//        return role;
//    }

}