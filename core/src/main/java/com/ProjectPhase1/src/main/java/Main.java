package com.ProjectPhase1.src.main.java;


import org.slf4j.LoggerFactory;
import view.AppView;

public class Main {
    public static void main(String[] args) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger("org.mongodb.driver").setLevel(Level.OFF);
        context.getLogger("ROOT").setLevel(Level.OFF);
        AppView appView = new AppView();
        appView.run();
    }
}
