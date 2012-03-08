package com.iscopia.challenge;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * @author ehardy
 */
public class Application {
    public void process(String inputFile, String outputFile) {
        try {
            File input = new File(getClass().getResource(inputFile).toURI());
            String content = Files.toString(input, Charset.forName("UTF-8"));

            Files.write(content, new File(System.getProperty("user.dir"), outputFile), Charset.forName("UTF-8"));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}