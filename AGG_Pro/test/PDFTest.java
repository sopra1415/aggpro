

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;
import Data.LiveClasses.*;
import Controller.Exchange.PDF;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;



public class PDFTest {
	
	@Test
	public void test() throws DocumentException, IOException{
		PDF pdf = new PDF();
                pdf.appendParagraph("AnfangsText");
                ArrayList<ArrayList<String>> table = new ArrayList<>();
                table.add(new ArrayList(Arrays.asList((new String[] {"Id","name","punkte"}))));
                table.add(new ArrayList(Arrays.asList((new String[] {"1,","anna","10"}))));
                table.add(new ArrayList(Arrays.asList((new String[] {"2","bernd","20"}))));
                pdf.appendTable("Test123",table);
		
		pdf.appendParagraph("EndText");
	pdf.write("/tmp/aggpro/test.pdf");
        }
        

}
