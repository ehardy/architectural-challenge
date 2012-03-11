package com.iscopia.challenge.io;

import com.iscopia.challenge.ContentSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ehardy
 */
public class FileContentSourceTest {

    private static final String CONTENT = "The dog barks at midnight.";

    private ContentSource source;

    @Before
    public void initializeFile() throws URISyntaxException {
        source = new FileContentSource(new File(getClass().getResource("/sample.txt").toURI()));
    }
    
    @After
    public void closeFile() {
        source.close();
    }

    @Test
    public void readsAllContentFromFile() {
        StringBuilder content = new StringBuilder();
        String partialContent;
        
        while ((partialContent = source.get()) != null) {
            content.append(partialContent);
        }

        assertThat(content.toString(), is(equalTo(CONTENT)));
    }
}