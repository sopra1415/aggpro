package Data.Database;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 
 * @author Jonathan Göggel
 *
 */

public class h2Test {

	@Test
	public void test() {
		try {
			//create empty database
			DatabaseConnector dc = new DatabaseConnector("test1");
			dc.clearDatabase();
			assertEquals(false, dc.checkTable("Tournament"));

			//create table
			dc.createTable("CREATE TABLE Tournament"
					+ " (Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, Name VARCHAR(255))"); 
			assertEquals(true, dc.checkTable("TOURNAMENT"));
			assertEquals(false, dc.checkTable("TOU%AMENT"));
			assertEquals(true, dc.checkTable("tournament"));
			assertEquals(false,dc.checkTable("hugo"));

			//insert records
			assertEquals(1,dc.insert("insert into Tournament(NaMe) values('satz1')"));
			assertEquals(2,dc.insert("insert into Tournament(NaMe) values('satz2');"));
			assertEquals(5,dc.insert("insert into Tournament(id,NaMe) values(5,'satz3');"));
			assertEquals("1,satz1,\n2,satz2,\n5,satz3,\n",dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));

			//delete record
			dc.delete("tournament", 2);
			assertEquals("1,satz1,\n5,satz3,\n",dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));
			dc.delete("tournament", 42);
			assertEquals("1,satz1,\n5,satz3,\n",dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));

			//update record
			dc.update("UPDATE Tournament SET Name = 'satzneu' WHERE id = 1");

			//loop over the rs
			ResultSet rs = dc.select("SELECT * FROM Tournament ORDER BY Id");
			String result="";
			while (rs.next()) {
				result += String.format("%d%s ",rs.getInt(1),rs.getString(2));
			}
			assertEquals("1satzneu 5satz3 ", result);

			//test blanks
			assertEquals(33,dc.insert("insert into Tournament(Id,NaMe) values(33,'   s\tatz33\t');"));
			assertEquals("1,satzneu,\n5,satz3,\n33,   s\tatz33\t,\n",dc.test_selecttostr("SELECT * FROM Tournament ORDER BY Id"));

			//close connection
			dc.finalized();
		}
		catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Test
public void test2(){
			DatabaseConnector bc;
			try {
				bc = new DatabaseConnector("test1");
			bc.clearDatabase();
			bc.createAllTables();
			assertEquals("ENCOUNTER\nEVENTPROPERTIES\nKOSYSTEM\nMODUL\nMODULLIST\nPARTICIPANT\nPARTICIPANTLIST\nPOINTS\nROUND\nSWISSSYSTEM\nTOURNAMENT\n", bc.test_tablelisttostring());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
}

}


/*
    public void beispielMetaData(){
        try {
            DatabaseMetaData md = conn.getMetaData(); 
            String[] types = { "TABLE", "SYSTEM TABLE" }; 
            ResultSet metaRS = md.getTables(null, null, "%", types); 
            while (metaRS.next()) { 

                // Catalog 
                String tableCatalog = metaRS.getString(1); 
                System.out.println("Catalog: " + tableCatalog); 

                // Schemata 
                String tableSchema = metaRS.getString(2); 
                System.out.println("Tabellen-Schema: " + tableSchema); 

                // Tabellennamen 
                String tableName = metaRS.getString(3); 
                System.out.println("Tabellen-Name: " + tableName); 

                // Tabellentyp 
                String tableType = metaRS.getString(4); 
                System.out.println("Tabellen-Typ: " + tableType + "\n"); 
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

    }
*/