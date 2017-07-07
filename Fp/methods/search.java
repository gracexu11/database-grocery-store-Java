import java.sql.*;
import java.util.Scanner;
public class search{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
    static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
    
    //  Database credentials
    static final String user = "aalsabbagh";
    static final String pass = "misho1995";
    
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter your current state (delivery address)");
    String input1= sc.next();
  
  
    sc.close();
  
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
        ResultSet rs = stmt.executeQuery("select state from address");
        System.out.print(input1);
//        rs.next();
        while( rs.next()){
        String v =rs.getString("STATE");
        System.out.print(v);
        System.out.println("hey");
        if((input1).equals(v)){
           System.out.println("hi");
           break;
          }
        }
         
        String sql ="Select * from stock NATURAL JOIN warehouse NATURAl JOIN address  NATURAL JOIN product WHERE STATE ='"+input1+"'";
        stmt.executeUpdate(sql);
        System.out.println("select records into the table.");

        
        System.out.println("doing the search");
        Statement prep = conn.createStatement();
        

        String state = rs.getString("state");
        
   System.out.println(state);
   System.out.println("the sate print out successful");
   int addressid = rs.getInt("addressid");
   System.out.println(addressid);
   
   prep.executeQuery("SELECT STATE  FROM ADDRESS WHERE ="+state);
   
   ResultSet rs1 = stmt.executeQuery("SELECT product_name from product");
   rs1.next();
   String w =rs1.getString("product_name");
    System.out.print(w);
    rs.close();
    rs1.close();
 // prep.executeQuery("select state  FROM address table WHERE ="+state);

        
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
}//end the search 
