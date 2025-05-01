package model;

import java.util.HashMap;

public class Command {
    public final String command;
    public final HashMap<String, String> body = new HashMap<>();

    public Command(String command) {
        this.command = command;
    }
}
