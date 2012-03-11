package com.iscopia.challenge;

/**
 * @author ehardy
 */
public interface TransformationOutput {

    void write(String content);
    void close();
}