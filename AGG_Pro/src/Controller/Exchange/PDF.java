package Controller.Exchange;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

public class PDF {

    ArrayList<String> content = new ArrayList<>();
	public void appendParagraph(String paragraph) throws DocumentException{
            content.add(paragraph);
	}
        
        public void appendTable(){
            
        }
        
        public void create (String pathAndFile) throws DocumentException, FileNotFoundException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("/tmp/aggpro/pdfnetbeans.pdf"));
        document.open();
            for (String string : content) {
        document.add(new Paragraph(string));	
           }
        document.close();
        }        
    public void test_example() throws DocumentException, FileNotFoundException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("/tmp/aggpro/pdfnetbeans.pdf"));
        document.open();
        document.add(new Paragraph("test"));	
        document.close();
        
    }
        public void test_example1() throws DocumentException, FileNotFoundException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("/tmp/aggpro/pdfnetbeans.pdf"));
                document.open();
        document.add(new Paragraph("test\ntest"));
        document.add(new Paragraph("test"));
        document.add(new Paragraph("test"));	
        document.close();
        }
    


}