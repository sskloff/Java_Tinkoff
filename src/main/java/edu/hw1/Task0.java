package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task0 {

    private Task0() {
    }

    public final static Logger LOGGER = LogManager.getLogger();

    public static void sayHelloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
