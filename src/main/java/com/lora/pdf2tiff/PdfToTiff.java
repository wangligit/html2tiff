package com.lora.pdf2tiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.GraphicsMagickCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

/**
 * PDF convert tiff and compress fax
 * 
 * @author lora
 *
 */
public class PdfToTiff {

    /**
     * imageMagick compress tiff
     * @param f
     * @param outImagePath
     * @param tiffFileName
     * @return
     */
    public static String imCompressTiff(File f, String outImagePath, String tiffFileName) {
        File path = new File(outImagePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        if (f.exists()) {
            try {
                File file = File.createTempFile(tiffFileName, ".tiff", path);
                IMOperation op = new IMOperation(); // imageMagic
                op.addImage(f.getAbsolutePath());
                op.density(204, 196); // input-file
                op.units("PixelsPerInch");
                op.quality(95.0);
                op.resize(1728, 1728, '!');
                op.monochrome();
                op.compress("Fax");
                op.addImage(file.getAbsolutePath());
                ConvertCmd cmd = new ConvertCmd();
                cmd.setSearchPath("C:\\Program Files\\ImageMagick-6.9.3-Q16");
                cmd.run(op);
            } catch (IOException | InterruptedException | IM4JavaException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * GraphicsMagick compress tiff
     * @param f
     * @param outImagePath
     * @param tiffFileName
     * @return
     */
    public static String gmCompressTiff(File f, String outImagePath, String tiffFileName) {
        File path = new File(outImagePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        if (f.exists()) {
            try {
                File file = File.createTempFile(tiffFileName, ".tiff", path);
                GMOperation op = new GMOperation();
                op.addImage(f.getAbsolutePath());
                op.resize(1728, 2291, '!');// 压缩图片
                // op.monochrome();
                op.type("Bilevel");
                op.type("TrueColor");
                op.type("Grayscale");
                // op.compress("Fax");
                op.compress("LZW");
                op.quality(100.0);
                op.units("PixelsPerInch");
                op.density(204, 196); // input-file
                // op.endian("lsb");
                op.depth(1);
                op.addImage(file.getAbsolutePath());
                GraphicsMagickCmd cmd = new GraphicsMagickCmd("convert");
                cmd.setSearchPath("c:/Program Files/GraphicsMagick-1.3.24-Q8");
                cmd.run(op);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IM4JavaException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    /**
     * GraphicsMagick compress tiff
     * 
     * @param f
     * @param outImagePath
     * @param tiffFileName
     * @return
     */
    public static String gmCompressTiffCmd(File f, String outImagePath, String tiffFileName) {
        File path = new File(outImagePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        if (f.exists()) {

            try {
                File file = File.createTempFile(tiffFileName, ".tiff", path);
                String commands = "gm convert -resize 1728x2291! -type Bilevel -type truecolor -type grayscale -compress LZW -quality 100 -units PixelsPerInch -density 204x196 -depth 1  "
                        + f.getAbsolutePath() + "   " + file.getAbsolutePath();
                Runtime exe = Runtime.getRuntime();
                Process p = exe.exec(commands);
                InputStream stdout = p.getInputStream();
                InputStreamReader isr = new InputStreamReader(stdout);
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
