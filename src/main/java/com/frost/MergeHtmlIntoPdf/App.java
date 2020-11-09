package com.frost.MergeHtmlIntoPdf;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;



@SpringBootApplication
public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SpringApplication.run(App.class, args);
//        final String html = "<h1>Hello</h1>"
//    			+ "<p>This was created using iText</p>"
//    			+ "<a href='hmkcode.com'>hmkcode.com</a>";
//        final String html1 = "<h1>Hello Ayush</h1>"
//    			+ "<p>This was created using iText</p>"
//    			+ "<a href='hmkcode.com'>hmkcode.com</a>";
////HtmlConverter.convertToPdf(html, new FileOutputStream("string-to-pdf.pdf"));
//  
//        String src[] = {html,html1};
//        String dest = "string-merge-pdf";
//        createPdf(null,src,dest);
//        System.out.println( "PDF Created!" );
        
        LocalDateTime time = LocalDateTime.now();
        //System.out.println(time);
        ZonedDateTime time1 = ZonedDateTime.of(time,ZoneId.of("Europe/Paris"));
        System.out.println(time);

        ZonedDateTime time2 = time1.withZoneSameLocal(ZoneId.of("Asia/Kolkata"));
        System.out.println(time2);
        time2.plusHours(3);
        ZonedDateTime time3 =  time2.plusHours(3);
        System.out.println(time3);
        // new Method
    

}
    
    public static void createPdf(String baseUri, String[] src, String dest) throws IOException {
        ConverterProperties properties = new ConverterProperties();
        //properties.setBaseUri(baseUri);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        PdfMerger merger = new PdfMerger(pdf);
        for (String html : src) {
            ConvertibleOutputStream baos = new ConvertibleOutputStream();
            PdfDocument temp = new PdfDocument(new PdfWriter(baos));
            HtmlConverter.convertToPdf(html, temp, properties);
            temp = new PdfDocument(
                new PdfReader(new ByteArrayInputStream(baos.toByteArray())));
            merger.merge(temp, 1, temp.getNumberOfPages());
            temp.close();
        }
        pdf.close();
       

}
}