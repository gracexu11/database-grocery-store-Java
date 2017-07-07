
import java.sql.*;
import java.util.Scanner;
public class SearchProduct{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
    static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
     
    //  Database credentials
    static final String user = "aalsabbagh";
    static final String pass = "misho1995";
     
  public static void SearchProductMain(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter the type of the product you want:");
    String input1= sc.next();
   
   
//    sc.close();
   
  Connection conn = null;
  Statement stmt = null;
     try{
 
 
        //STEP 2: Register JDBC driver
         
      Class.forName("oracle.jdbc.driver.OracleDriver");
 
        //STEP 3: Open a connection
        System.out.println("Connecting to a selected database");
        conn = DriverManager.getConnection(DB_URL, user, pass);
        System.out.println("Connected database successfully");
         
      //STEP 4:  display for available the product
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select category_name from product");
        System.out.println("We are searching "+input1+" for all the goodies you want!");
//        rs.next();
        int found=0;
        while( rs.next()){
        String v =rs.getString("CATEGORY_NAME");
        if((input1).equals(v)){
        	found=1;
           break;
          }
        }
        if(found==1){
        String sql ="Select * from stock NATURAL JOIN warehouse NATURAl JOIN address  NATURAL JOIN product WHERE CATEGORY_NAME ='"+input1+"'";
        stmt.executeQuery(sql);
        System.out.println("select records into the table.");
 
         
        System.out.println("doing the search");
 
    
    ResultSet rs1 = stmt.executeQuery(sql);
      //stmt.executeQuery("SELECT product_name from product");
     while( rs1.next()){
    String w =rs1.getString("product_name");
    String c =rs1.getString("CATEGORY_NAME");
    System.out.println(w+" CATEGORY NAME "+ c);
     }
    rs.close();
    rs1.close();
        }else{
        	System.out.println("The type is not found!");
        }
 
         
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
         }
         try{
            if(conn!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      }//end try
      System.out.println("Goodbye!");
 }      
 //}//end the main
}//end the Search_Product