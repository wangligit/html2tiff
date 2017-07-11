/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://opensource.org/licenses/CDDL-1.0
 */
package com.lora.htm2pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author lora
 */
public class BinaryCore {

    private static File wkToPdf;
    private static boolean initialized;

    public static void init() throws IOException {
        init(new CustomResourceLoader() {
            public InputStream getResourceAsStream(String resource) {
                InputStream is = null;
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                if (classLoader != null) {
                    is = classLoader.getResourceAsStream(resource);
                }
                if (is == null) {
                    classLoader = BinaryCore.class.getClassLoader();
                    if (classLoader != null) {
                        is = classLoader.getResourceAsStream(resource);
                    }

                    if (is == null && !resource.startsWith("/")) {
                        is = getResourceAsStream("/" + resource);
                    }
                }

                return is;
            }
        });
    }

    public static void init(CustomResourceLoader customResourceLoader) throws IOException {
        if (initialized) {
            return;
        }
        
        boolean amd64 = System.getProperty("os.arch").contains("64");

        String resWkToPdf;

        switch (OSType.getOSType()) {
            case Linux:
                resWkToPdf = "com/lora/resource/linux/wkhtmltopdf" + 
                        (amd64 ? "-amd64" : "");
                break;

            case Windows:
                resWkToPdf = "com/lora/resource/windows/wkhtmltopdf.exe";

                break;

            case MacOS:
                resWkToPdf = "com/lora/resource/mac/wkhtmltopdf";

                break;

            default:
                throw new UnsupportedOperationException("Your Operation System is not supported yet.");
        }

        wkToPdf = File.createTempFile("wkToPdf", "exe");

        wkToPdf.setExecutable(true, true);

        wkToPdf.deleteOnExit();

        byte[] buffer = new byte[4096 * 4]; //16KiB

        InputStream is = customResourceLoader == null
                ? ClassLoader.getSystemClassLoader().getResourceAsStream(resWkToPdf)
                : customResourceLoader.getResourceAsStream(resWkToPdf);
        FileOutputStream fos = new FileOutputStream(wkToPdf);
        try {
            int count;
            while ((count = is.read(buffer)) != -1) {
                fos.write(buffer, 0, count);
            }
        } finally {
            is.close();
            fos.close();
        }

        initialized = true;
    }

    /**
     * @param executable Executable file. Ex:
     * <code>BinaryCore.getWkToPdf()</code>
     * @param args Executable arguments
     * @param ignoreExitCode If <code>true</code>, no exception will be thrown
     * when exit code is not zero
     * @return Output printed by executable
     */
    public static StringBuilder runExecutable(File executable, Collection<String> args, boolean ignoreExitCode) throws IOException, WkToPdfException, InterruptedException {
        StringBuilder output = new StringBuilder();

        ArrayList<String> commands = new ArrayList<String>(args.size() + 1);
        commands.add(executable.getAbsolutePath());
        commands.addAll(args);

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process process = processBuilder.start();
        InputStream isProcess = process.getErrorStream();
        try {
            int character;
            while ((character = isProcess.read()) != -1) {
                output.append((char) character);
            }
            if (!ignoreExitCode) {
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    throw new WkToPdfException(output, exitCode);
                }
            }
        } finally {
            isProcess.close();
        }

        return output;
    }

    public static File getWkToPdf() throws IOException {
        init();
        return wkToPdf;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
