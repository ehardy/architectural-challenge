package com.iscopia.challenge.io;

import com.google.common.io.Files;
import com.iscopia.challenge.ContentSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author ehardy
 */
public class FileContentSource implements ContentSource {

    private final File file;
    private boolean read;

    public FileContentSource(File file) {
        this.file = file;
    }

    public String get() {
        if (read) {
            return null;
        }

        try {
            String content = Files.toString(file, Charset.forName("UTF-8"));

            read = true;
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {}
}
