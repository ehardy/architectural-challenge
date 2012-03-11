package com.iscopia.challenge.algorithm;

import com.iscopia.challenge.TransformationOutput;
import com.iscopia.challenge.Transformer;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author ehardy
 */
public class Rot13TransformerTest {

    private static final String INPUT = "The dog barks at midnight.";
    private static final String EXPECTED_OUTPUT = "Gur qbt onexf ng zvqavtug.";

    private TransformationOutput output = mock(TransformationOutput.class);

    private Transformer rot13 = new Rot13Transformer();

    @Test
    public void rotatesCharactersBy13Positions() {
        rot13.transform(INPUT, output);

        verify(output).write(EXPECTED_OUTPUT);
    }
}