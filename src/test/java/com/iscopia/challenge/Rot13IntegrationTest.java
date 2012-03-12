package com.iscopia.challenge;

import com.google.common.io.Files;
import com.iscopia.challenge.algorithm.Rot13Transformer;
import com.iscopia.challenge.io.DelegatingTransformationOutput;
import com.iscopia.challenge.io.FileContentSource;
import com.iscopia.challenge.io.FileTransformationOutput;
import com.iscopia.challenge.io.StringPrintStream;
import com.iscopia.challenge.ui.ConsoleUI;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ehardy
 */
public class Rot13IntegrationTest {

    @Rule
    public TemporaryFolder directory = new TemporaryFolder();

    private static final String EXPECTED_OUTPUT = "Gur qbt onexf ng zvqavtug.";

    private Application app = new Application(new Rot13Transformer());
    private StringPrintStream consoleOutput;

    @Test
    public void rotatesInputContentAndWritesResultBackToFile() throws Exception {
        app.process(new FileContentSource(inputFile()), output());

        assertThat(outputFile(), hasContent(EXPECTED_OUTPUT));
    }

    @Test
    public void writesRotatedContentToConsole() throws Exception {
        app.process(new FileContentSource(inputFile()), output());
        
        assertThat(consoleOutput(), is(equalTo(EXPECTED_OUTPUT)));
    }

    private TransformationOutput output() throws IOException {
        return new DelegatingTransformationOutput(new FileTransformationOutput(outputFile()), new ConsoleUI(consoleOutput));
    }

    @Before
    public void replaceConsoleOutput() {
        consoleOutput = new StringPrintStream(System.out);
        System.setOut(consoleOutput);
    }

    private String consoleOutput() {
        return consoleOutput.getPrintedContent();
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