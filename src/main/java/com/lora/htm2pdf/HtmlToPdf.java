package com.lora.htm2pdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * @author lora
 */
public class HtmlToPdf {
    // This compression type may be wpecific to JAI ImageIO Tools 
    public static final String COMPRESSION_TYPE_GROUP4FAX = "CCITT T.4";
    
    /**
     * @param url URL of page to be converted to PDF (can be a absolute file
     * name)
     * @param p The WkToPed parameters
     * @return File of PDF generated
     */
    public static File htmlToPdf(String url, Parameters p, File directory) throws IOException, WkToPdfException, InterruptedException {
        ArrayList<String> objects = new ArrayList<String>();
        objects.add(url);
        return htmlToPdf(objects, p, directory);
    }    

    /**
     * @param objects A page objects puts the content of a singe webpage into
     * the output document. input url/file name [PAGE OPTION]...
     * @param p The WkToPed parameters
     * @return File of PDF generated
     */
    public static  File htmlToPdf(Collection<String> objects, Parameters p, File directory) throws IOException, WkToPdfException, InterruptedException {
		if (!directory.exists()) {
			directory.mkdirs();
		}
    	File output = File.createTempFile("HTMLToPDF", ".pdf", directory);

        ArrayList<String> args = new ArrayList<String>();

        if (p.getCollate() != null) {
            if (p.getCollate()) {
                args.add("--collate");
            } else {
                args.add("--no-collate");
            }
        }

        if (p.getCopies() != null) {
            args.add("--copies");
            args.add(p.getCopies().toString());
        }

        if (p.getGrayScale() != null && p.getGrayScale()) {
            args.add("--grayscale");
        }

        if (p.getLowQuality() != null && p.getLowQuality()) {
            args.add("--lowquality");
        }

        if (p.getPortrait() != null) {
            args.add("--orientation");
            if (p.getPortrait()) {
                args.add("Portrait");
            } else {
                args.add("Landscape");
            }
        }

        if (p.getPageSize() != null) {
            args.add("--page-size");
            args.add(p.getPageSize());
        }

        args.add("--quiet");

        if (p.getPrintMediaType() != null) {
            if (p.getPrintMediaType()) {
                args.add("--print-media-type");
            } else {
                args.add("--no-print-media-type");
            }
        }

        if (p.getZoom() != null) {
            args.add("--zoom");
            args.add(p.getZoom().toString());
        }

        if (p.getJavascriptDelay() != null) {
            args.add("--javascript-delay");
            args.add(p.getJavascriptDelay().toString());
        }

        if (p.getWindowStatus() != null) {
            args.add("--window-status");
            args.add(p.getWindowStatus());
        }

        if (p.getDisableSmartShrinking() != null && p.getDisableSmartShrinking()) {
            args.add("--disable-smart-shrinking");
        }
        
        if (p.getDpi() != null) {
            args.add("--dpi");
            args.add(p.getDpi().toString());
        }
        
        if (p.getTopMargin() != null) {
            args.add("-T");
            args.add(p.getTopMargin());
        }
        
        if (p.getRightMargin() != null) {
            args.add("-R");
            args.add(p.getRightMargin());
        }
        
        if (p.getBottomMargin() != null) {
            args.add("-B");
            args.add(p.getBottomMargin());
        }
        
        if (p.getLeftMargin() != null) {
            args.add("-L");
            args.add(p.getLeftMargin());
        }
        
        if (p.getCookies() != null) {
            for (String key : p.getCookies().keySet()) {
                String value = p.getCookies().get(key);
                
                args.add("--cookie");
                args.add(key);
                args.add(value);
            }
        }
        
        if (p.getProxy() != null) {
            args.add("--proxy");        
            args.add(p.getProxy());
        }
        args.addAll(objects);
        args.add(output.getAbsolutePath());
        try {
			BinaryCore.runExecutable(BinaryCore.getWkToPdf(), args, p.isIgnoreErrors());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return output;
    }

}