//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
public class ModifyProduct {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
   static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
 
   //  Database credentials
   static final String USER = "aalsabbagh";
   static final String PASS = "misho1995";
    
   public static void ModifyProductMain() {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Please enter product ID : ");
    String prodId= keyboard.next();
    System.out.print("Please enter product Name : ");
    String prodName= keyboard.next();
    System.out.print("Please enter product's category: ");
    String prodCat = keyboard.next();
    System.out.print("Please enter product size in Cubic Feet: ");
    String prodSize = keyboard.next();
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
      System.out.println(prodName+","+prodCat+","+prodSize);
      stmt = conn.createStatement();
      String sql = "UPDATE PRODUCT SET CATEGORY_NAME='"+prodCat+"',PRODUCT_NAME='"+prodName+"',SIZE_CUBIFEET="+prodSize+"WHERE PRODUCTID="+prodId;
      stmt.executeUpdate(sql);
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
}//end Modify_Product