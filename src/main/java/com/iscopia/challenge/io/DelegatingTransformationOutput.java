package com.iscopia.challenge.io;

import com.iscopia.challenge.TransformationOutput;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author ehardy
 */
public class DelegatingTransformationOutput implements TransformationOutput {

    private final List<TransformationOutput> outputs = new ArrayList<TransformationOutput>();

    public DelegatingTransformationOutput(TransformationOutput...outputs) {
        this.outputs.addAll(asList(outputs));
    }

    public void write(String content) {
        for (TransformationOutput output : outputs) {
            output.write(content);
        }
    }

    public void close() {
        for (TransformationOutput output : outputs) {
            output.close();
        }    
    }
}
