package com.iscopia.challenge.io;

import com.google.common.io.Closeables;
import com.iscopia.challenge.TransformationOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author ehardy
 */
public class FileTransformationOutput implements TransformationOutput {

    private final Writer writer;

    public FileTransformationOutput(File file) {
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String content) {
        try {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Closeables.closeQuietly(writer);
    }
}
