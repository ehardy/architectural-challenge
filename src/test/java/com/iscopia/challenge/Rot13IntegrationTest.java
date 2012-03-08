package com.iscopia.challenge;

import com.google.common.io.Files;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertThat;

/**
 * @author ehardy
 */
public class Rot13IntegrationTest {

    private Application app = new Application();
    private String input = "/sample.txt";
    private String output = "out.txt";
    
    private String expectedOutput = "The dog barks at midnight.";

    @Test
    public void readsAndWritesSampleFile() {
        app.process(input, output);

        assertThat(new File(output), hasContent(expectedOutput));
    }

    private Matcher<File> hasContent(final String expectedOutput) {
        return new TypeSafeMatcher<File>() {
            @Override
            protected boolean matchesSafely(File file) {
                if (!file.exists()) {
                    return false;
                }

                try {
                    String fileContent = Files.toString(file, Charset.forName("UTF-8"));
                    
                    return expectedOutput.equals(fileContent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a file containing text ").appendValue(expectedOutput);
            }
        };
    }

}