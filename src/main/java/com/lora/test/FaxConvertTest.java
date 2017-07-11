package com.lora.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.lora.htm2pdf.HtmlToPdf;
import com.lora.htm2pdf.Parameters;
import com.lora.htm2pdf.WkToPdfException;
import com.lora.pdf2tiff.PdfToTiff;
import com.lora.util.DateUtil;


public class FaxConvertTest {

    public static void main(String[] args) {
        String url = "https://www.baidu.com" ;
        Parameters p = new Parameters();
        p.setDpi(600);
        p.setPageSize("A4");
        p.setPortrait(true);
        String currentDate = DateUtil.datetoStr(new Date());
        String pdfDirectory = "d:/111/Fax/PDF/".concat(currentDate).concat("/") ;
        try {
            File f = HtmlToPdf.htmlToPdf(url, p, new File(pdfDirectory));
            PdfToTiff.gmCompressTiff(f, pdfDirectory.replace("PDF", "TIFF"), "fax");
        } catch (IOException | WkToPdfException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
