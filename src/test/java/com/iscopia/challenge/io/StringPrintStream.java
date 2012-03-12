package com.iscopia.challenge.io;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author ehardy
 */
public class StringPrintStream extends PrintStream {
    
    private StringBuilder printed = new StringBuilder();

    public StringPrintStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void println(String s) {
        printed.append(s);
        super.println(s);
    }

    @Override
    public void print(String s) {
        printed.append(s);
        super.print(s);
    }

    public String getPrintedContent() {
        return printed.toString();
    }
}
