package com.P.Client.model;

import java.util.HashMap;

import dev.morphia.annotations.Embedded;

@Embedded
public class Command {
    public final String command;
    public final HashMap<String, String> body = new HashMap<>();

    public Command(String command) {
        this.command = command;
    }
}
