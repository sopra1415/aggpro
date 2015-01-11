package Controller.Exchange;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF {
	
	Document document = new Document();

	
	
	public void pdfWriteStrStart(String filename) throws FileNotFoundException, DocumentException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
	}
	
	public void appendParagraph(String paragraph) throws DocumentException{
	document.add(new Paragraph(paragraph));	
	}
    
    public void closeDocument(){
    	document.close();
    }
    public void test_example() throws DocumentException, FileNotFoundException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("/tmp/pdfnetbeans"));
        document.open();
        document.add(new Paragraph("test"));	
        document.close();
        
	
    }
    
}