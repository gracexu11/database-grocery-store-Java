import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
 public class Shopping_Cart {
       // JDBC driver name and database URL
     static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
     static final String DB_URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
      
     //  Database credentials
     static final String user = "aalsabbagh";
     static final String pass = "misho1995";
      
   public static void ShoppingCartMain(){
     
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
           Scanner sc = new Scanner(System.in);
           System.out.println("please enter your 6 digit ID or 0 to quit");
           int custId=sc.nextInt();
           if (custId==0){
           System.exit(0);
           }
           String sql1 ="Select * from shoppingcart NATURAL JOIN product WHERE CUSTOMERID="+custId;
           ResultSet rs=stmt.executeQuery(sql1);
           ArrayList<Integer> list2 = new ArrayList<Integer>();
           int x= 1;
           while(rs.next()){// this will show all products we have in stock,
            int v = rs.getInt("productid");
            String w= rs.getString("product_name");
            String q= rs.getString("quantity");
            System.out.println(x+"."+w+" "+q);
            x++;
            list2.add(v);
           }
           System.out.println("Please select an item number from the list above");
           int input1= sc.nextInt();
           System.out.println("Please enter one of the following options:\n 1.delete \n 2.modify quantity");
           int choice= sc.nextInt();
           int num = input1 -1 ;
           if (choice==1){
             String sql30="DELETE FROM SHOPPINGCART WHERE productid="+list2.get(num)+ "AND CUSTOMERID="+custId;
             stmt.executeQuery(sql30);
             System.out.println("Deletetion successful");
           }
           else if (choice==2){
             System.out.println("please enter the new desired quanitity");
             int qa = sc.nextInt();
           String sql40="UPDATE SHOPPINGCART SET QUANTITY ="+qa+
             "WHERE productid="+list2.get(num)+ "AND CUSTOMERID="+custId;
           stmt.executeQuery(sql40);
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
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }
  }
 }