package com.iscopia.challenge;

import com.google.common.io.Files;
import com.iscopia.challenge.algorithm.Rot13Transformer;
import com.iscopia.challenge.io.FileContentSource;
import com.iscopia.challenge.io.FileTransformationOutput;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertThat;

/**
 * @author ehardy
 */
public class Rot13IntegrationTest {

    @Rule
    public TemporaryFolder directory = new TemporaryFolder();

    private static final String EXPECTED_OUTPUT = "Gur qbt onexf ng zvqavtug.";

    private Application app = new Application(new Rot13Transformer());

    @Test
    public void rotatesInputContentAndWritesResultBackToFile() throws Exception {
        app.process(new FileContentSource(inputFile()), new FileTransformationOutput(outputFile()));

        assertThat(outputFile(), hasContent(EXPECTED_OUTPUT));
    }

    private File outputFile() throws IOException {
        return directory.newFile("out.txt");
    }

    private File inputFile() throws URISyntaxException {
        return new File(getClass().getResource("/sample.txt").toURI());
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