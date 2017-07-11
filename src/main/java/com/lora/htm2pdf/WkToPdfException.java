/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://opensource.org/licenses/CDDL-1.0
 */
package com.lora.htm2pdf;

/**
 * @author lora
 */
@SuppressWarnings("serial")
public class WkToPdfException extends Exception {

    private final int exitCode;
    private final StringBuilder output;

    public WkToPdfException(StringBuilder output, int exitCode) {
        super("\nOutput:\n" + output + "\n\nExit code: " + exitCode, null);
        this.output = output;
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }

    public StringBuilder getOutput() {
        return output;
    }
}
