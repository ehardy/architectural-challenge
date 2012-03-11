package com.iscopia.challenge.io;

import com.google.common.io.Files;
import com.iscopia.challenge.TransformationOutput;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ehardy
 */
public class FileTransformationOutputTest {

    private static final String CONTENT = "testing one two";

    @Rule
    public TemporaryFolder directory = new TemporaryFolder();
    private File file;

    private TransformationOutput output;

    @Test
    public void writesContentToFile() throws IOException {
        output.write(CONTENT);
        output.close();

        assertThat(fileContent(), is(equalTo(CONTENT)));
    }

    private String fileContent() throws IOException {
        return Files.toString(file, Charset.forName("UTF-8"));
    }
    
    @Before
    public void initializeOutput() throws IOException {
        file = directory.newFile();
        output = new FileTransformationOutput(file);
    }
}