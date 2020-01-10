import java.sql.DriverManager;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.ResultSet;

public class jsonToJava {

	public static void main(String[] args) throws JsonMappingException, JsonGenerationException, ClassNotFoundException, SQLException,IOException {
  
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   Connection connctn=null;
	   
	   ArrayList<CustomerDetails> customerList=new ArrayList<CustomerDetails>();
	   // Connect to Database
	   connctn=DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "DBadmin");
	   System.out.println("Connected to Database");
	   
	   //object of statement class will help us to execute queries
	   Statement ask=connctn.createStatement();
	   ResultSet queryResult=ask.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' and purchasedDate=curdate();");
	   System.out.println("Executed the Query");
	   
		/*
		 * Manually select record items 
		 * queryResult.next(); // set pointer to First Record
		 * queryResult.getString(1); 
		 * queryResult.getString(2);
		 * queryResult.getInt(3);
		 * queryResult.getString(4);
		 * queryResult.next(); // set  pointer to Second Record
		 * 
		 */	  
	   while (queryResult.next()) /* boolean: the while True until it reaches the last record*/
	  {
			/*  w/o getter / setters:
			 * System.out.println(queryResult.getString(1));
			 * System.out.println(queryResult.getString(2));
			 * System.out.println(queryResult.getInt(3));
			 * System.out.println(queryResult.getString(4));
			 */
		   // With getters | setters: Using Setters:
		   CustomerDetails customer=new CustomerDetails();
		   customer.setCourseName(queryResult.getString(1));
		   customer.setPurchasedDate(queryResult.getString(2));
		   customer.setAmount(queryResult.getInt(3));
		   customer.setLocation(queryResult.getString(4));
		   
			/* Console Output with getters:
			 * System.out.println(customer.getCourseName());
			 * System.out.println(customer.getPurchasedDate());
			 * System.out.println(customer.getAmount());
			 * System.out.println(customer.getLocation());
			 */
		   customerList.add(customer);
	  }
	   for (int index=0; index<customerList.size(); index++)
	   {
		   //Jackson API : maps DB tables fields to json 
	   ObjectMapper customerObj=new ObjectMapper();
	   customerObj.writeValue(new File("C:\\repository\\SDET\\javaSDET\\JsonJava\\customerInfo"+index+".json"), customerList.get(index));
	   }
	   System.out.println(customerList.size()+" Files Created");
	  connctn.close();
	 }

}
