package com.zzz.clob;

import java.sql.*;
import java.io.*;

//import oracle specific classes
import oracle.jdbc.*;

class GetClob
{

	static Connection conn = null;
    static String stmtString = 
    		  
  	"	  SELECT X.blob FROM clob_table x where x.clob_id = '" ;
    
  public static void main(String args[]) throws SQLException, IOException
  {
    
 
   
    try
    {
      DriverManager.registerDriver( new OracleDriver());

      String thinDriverURL = "jdbc:oracle:thin:@server1:1521:dev1";
      conn = DriverManager.getConnection ( thinDriverURL, "usr_id1",
          "passcode1" );

            
    } finally {
    	
    }
    
    

    getCrd("SFDC_500A000000UXSzA.xml");
    
    
    if( conn != null)  conn.close();
}
    
   public static void getCrd(String fileName) throws SQLException, IOException{
	   
	   PreparedStatement pstmt = conn.prepareStatement( stmtString + fileName + "'");
	      pstmt.execute();
	      ResultSet rset = pstmt.getResultSet();

	      while( rset.next() )
	      {
	        //Clob clob = rset.getClob( 1 );
	        
	        File data = new File("C:\\DATA\\" + fileName);
	        Reader dataReader = rset.getCharacterStream(1);
	        FileWriter writer = new FileWriter(data);
	        char[] buffer = new char[1];
	        while (dataReader.read(buffer) > 0) {
	          writer.write(buffer);
	        }
	        writer.close();
	        dataReader.close();

	      }
      
	      rset.close();
	      pstmt.close();
	      
  }

private static void usage() {
	System.out.println("Please pass the input file name");
	
}
}