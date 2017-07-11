/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://opensource.org/licenses/CDDL-1.0
 */
package com.lora.htm2pdf;

import java.io.InputStream;

/**
 *
 * @author lora
 */
public interface CustomResourceLoader {
    public InputStream getResourceAsStream(String resource);
    
}
