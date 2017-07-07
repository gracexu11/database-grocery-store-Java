
//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
public class DeleteProduct {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
   static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
 
   //  Database credentials
   static final String USER = "aalsabbagh";
   static final String PASS = "misho1995";
    
   public static void DeleteProductMain() {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Please enter product ID : ");
    int prodId= keyboard.nextInt();
//    keyboard.close();
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("oracle.jdbc.driver.OracleDriver");
 
      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
       
      //STEP 4: Execute a query
      System.out.println("Updating records in the table...");
      System.out.println(prodId);
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select PRODUCTID from PRICE");
      int found = 0;
      while( rs.next()){
        int v =rs.getInt("PRODUCTID");
        if((prodId==v)){
          String sql = "DELETE FROM PRICE WHERE PRODUCTID="+prodId;
          found =1;
          stmt.executeUpdate(sql);
           break;
          }
        }
      if(found==0){
        System.out.println("such product doesn't exist in the first place so I can't delete");
      }
      rs = stmt.executeQuery("select PRODUCTID from STOCK");
      while( rs.next()){
        int v =rs.getInt("PRODUCTID");
        if((prodId==v)){
          String sql = "DELETE FROM STOCK WHERE PRODUCTID="+prodId;
          stmt.executeUpdate(sql);
           break;
          }
        }
      rs = stmt.executeQuery("select PRODUCTID from SUPPLY");
      while( rs.next()){
        int v =rs.getInt("PRODUCTID");
        if((prodId==v)){
          String sql = "DELETE FROM SUPPLY WHERE PRODUCTID="+prodId;
          stmt.executeUpdate(sql);
           break;
          }
        }
      rs = stmt.executeQuery("select PRODUCTID from GETORDER");
      while( rs.next()){
        int v =rs.getInt("PRODUCTID");
        if((prodId==v)){
          String sql = "DELETE FROM GETORDER WHERE PRODUCTID="+prodId;
          stmt.executeUpdate(sql);
           break;
          }
        }
      rs = stmt.executeQuery("select PRODUCTID from PRODUCT");
      while( rs.next()){
        int v =rs.getInt("PRODUCTID");
        if((prodId==v)){
          String sql = "DELETE FROM PRODUCT WHERE PRODUCTID="+prodId;
          stmt.executeUpdate(sql);
           break;
          }
        }
      System.out.println("Updated records in the table...");
 
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end Delete_Product
