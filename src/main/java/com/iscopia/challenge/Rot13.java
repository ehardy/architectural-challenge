package com.iscopia.challenge;

import com.iscopia.challenge.algorithm.Rot13Transformer;
import com.iscopia.challenge.io.DelegatingTransformationOutput;
import com.iscopia.challenge.io.FileContentSource;
import com.iscopia.challenge.io.FileTransformationOutput;
import com.iscopia.challenge.ui.ConsoleUI;

import java.io.File;

/**
 * @author ehardy
 */
public class Rot13 {
    
    public static void main(String[] args) {
        if (args.length != 2) {
            usage();
            return;
        }

        Application app = new Application(new Rot13Transformer());
        
        app.process(new FileContentSource(new File(System.getProperty("user.dir"), args[0])),
                new DelegatingTransformationOutput(new FileTransformationOutput(new File(System.getProperty("user.dir"), args[1])), new ConsoleUI(System.out)));
    }

    private static void usage() {
        System.err.println("Usage: rot13 inputFile outputFile");
    }
}