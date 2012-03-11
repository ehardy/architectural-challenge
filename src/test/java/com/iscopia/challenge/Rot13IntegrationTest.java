package com.iscopia.challenge;

import com.google.common.io.Files;
import com.iscopia.challenge.algorithm.Rot13Transformer;
import com.iscopia.challenge.io.FileContentSource;
import com.iscopia.challenge.io.FileTransformationOutput;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertThat;

/**
 * @author ehardy
 */
public class Rot13IntegrationTest {

    private static final String EXPECTED_OUTPUT = "Gur qbt onexf ng zvqavtug.";

    private Application app = new Application(new Rot13Transformer());
    private String input = "/sample.txt";
    private String output = "out.txt";

    @Test
    public void rotatesInputContentAndWritesResultBackToFile() throws URISyntaxException {
        app.process(new FileContentSource(new File(getClass().getResource(input).toURI())),
                new FileTransformationOutput(new File(System.getProperty("user.dir"), output)));

        assertThat(new File(System.getProperty("user.dir"), output), hasContent(EXPECTED_OUTPUT));
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