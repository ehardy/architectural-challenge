package com.iscopia.challenge;

import java.io.Closeable;

/**
 * @author ehardy
 */
public interface ContentSource {

    String get();

    void close();
}
