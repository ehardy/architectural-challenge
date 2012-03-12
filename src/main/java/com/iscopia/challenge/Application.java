package com.iscopia.challenge;

/**
 * @author ehardy
 */
public class Application {
    
    private final Transformer transformer;

    public Application(Transformer transformer) {
        this.transformer = transformer;
    }

    public void process(ContentSource source, TransformationOutput output) {
        String content;
        
        while ((content = source.get()) != null) {
            transformer.transform(content, output);
        }

        source.close();
        output.close();
    }
}