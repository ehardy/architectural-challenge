package com.iscopia.challenge;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author ehardy
 */
public class ApplicationTest {

    private ContentSource source = mock(ContentSource.class);
    private TransformationOutput output = mock(TransformationOutput.class);
    private Transformer transformer = mock(Transformer.class);
    
    private Application app = new Application(transformer);

    @Test
    public void transformsInput() {
        when(source.get()).thenReturn("abc").thenReturn("def").thenReturn(null);

        app.process(source, output);
        
        verify(transformer).transform("abc", output);
        verify(transformer).transform("def", output);
    }
    
    @Test
    public void closesCharacterSourceWhenDone() {
        when(source.get()).thenReturn(null);

        app.process(source, output);
        
        verify(source).close();
    }

    @Test
    public void closesTransformationOutputWhenDone() {
        when(source.get()).thenReturn(null);

        app.process(source, output);

        verify(output).close();
    }
}