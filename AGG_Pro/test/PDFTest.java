

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;
import Data.LiveClasses.*;
import Controller.Exchange.PDF;
import java.io.FileNotFoundException;



public class PDFTest {
	
	@Test
	public void test() throws DocumentException, IOException{
		PDF pdf = new PDF();
                pdf.appendParagraph("das ist ein test");
		pdf.appendParagraph("Vorgegebene	Technologien	• • • • • Java 1.7 grafische Benutzeroberfläche mit Swing H2 mit JDBC iText Verwendung zusätzlicher externer Bibliotheken muss von den Betreuern vorher genehmigt werden (Antrag per Beitrag im Ilias-Forum) •  eigenständige Wahl der Werkzeuge");
	pdf.create("/tmp/aggpro/test.pdf");
        }
        

}
