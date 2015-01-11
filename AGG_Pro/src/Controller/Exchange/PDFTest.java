package Controller.Exchange;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;



public class PDFTest {
	
	//@Test
	public void test() throws DocumentException, IOException{
		PDF pdf = new PDF();
		pdf.pdfWriteStrStart("/tmp/examplepdf2.pdf");
		pdf.appendParagraph("p1");
		pdf.appendParagraph("p2");
		pdf.appendParagraph("Vorgegebene	Technologien	• • • • • Java 1.7 grafische Benutzeroberfläche mit Swing H2 mit JDBC iText Verwendung zusätzlicher externer Bibliotheken muss von den Betreuern vorher genehmigt werden (Antrag per Beitrag im Ilias-Forum) •  eigenständige Wahl der Werkzeuge");
		pdf.closeDocument();
		//pdf.createExamplePdf("/tmp/examplepdf.pdf");
	}

}
