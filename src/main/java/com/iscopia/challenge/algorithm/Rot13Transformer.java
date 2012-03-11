package com.iscopia.challenge.algorithm;

import com.iscopia.challenge.TransformationOutput;
import com.iscopia.challenge.Transformer;

/**
 * @author ehardy
 */
public class Rot13Transformer implements Transformer {

    private static final String STANDARD = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CONVERTED = "nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM";
    
    public void transform(String content, TransformationOutput output) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i != content.length(); ++i) {
            if (!Character.isLetter(content.charAt(i))) {
                result.append(content.charAt(i));
                continue;
            }

            int conversionIndex = STANDARD.indexOf(content.substring(i, i + 1));
            
            if (conversionIndex != -1) {
                result.append(CONVERTED.charAt(conversionIndex));
            }
        }
        
        output.write(result.toString());
    }
}