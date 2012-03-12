package com.iscopia.challenge.io;

import com.iscopia.challenge.TransformationOutput;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author ehardy
 */
public class DelegatingTransformationOutputTest {
    
    private TransformationOutput first = mock(TransformationOutput.class);
    private TransformationOutput second = mock(TransformationOutput.class);

    private DelegatingTransformationOutput output = new DelegatingTransformationOutput(first, second);

    @Test
    public void sendsWriteMessageToEveryTransformationOutput() {
        output.write("testing");
        
        verify(first).write("testing");
        verify(second).write("testing");
    }

    @Test
    public void sendsCloseMessageToEveryTransformationOutput() {
        output.close();
        
        verify(first).close();
        verify(second).close();
    }
}