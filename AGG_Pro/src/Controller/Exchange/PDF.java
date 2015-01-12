package Controller.Exchange;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.ArrayList;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class PDF {
    ArrayList<Element> content = new ArrayList<>();
	public void appendParagraph(String paragraph) throws DocumentException{
            content.add( new Paragraph(paragraph));
	}
        
 
        
        public void appendTable(String caption,ArrayList<ArrayList<String>> table) throws DocumentException{
          PdfPTable pdfTable = new PdfPTable(3);
        //PdfPCell cell;
        //cell = new PdfPCell(new Phrase(caption));
        //cell = new PdfPCell(new Phrase(caption));
        //cell.setColspan(table.get(0).size()+1);
        //cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        //cell.setRowspan(table.size());
        //pdfTable.addCell(cell);
       
          ArrayList<String> legend = table.remove(0);
            for (String legend1 : legend) {
                pdfTable.addCell(new Paragraph(legend1,FontFactory.getFont("Times-Roman", 12, Font.BOLD)));
            }
          content.add(new Paragraph(legend.toString()));
            for (ArrayList<String> line : table) {
                for (String line1 : line) {
                    pdfTable.addCell(line1);
                }
                
                //content.add((Element) new Paragraph(line.toString()));
            }
            content.add(pdfTable);
        }
        
        public void write (String pathAndFile) throws DocumentException, FileNotFoundException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathAndFile));
        document.open();

            for (Element e : content) {
                document.add((com.itextpdf.text.Element) e);
           }
        document.close();
        } 
        

   }