package com.iscopia.challenge.ui;

import com.iscopia.challenge.TransformationOutput;

import java.io.PrintStream;

/**
 * @author ehardy
 */
public class ConsoleUI implements TransformationOutput {
    
    private final PrintStream console;

    public ConsoleUI(PrintStream console) {
        this.console = console;
    }


    public void write(String content) {
        console.print(content);
    }

    public void close() {
        console.flush();
    }
}