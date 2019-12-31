import java.sql.DriverManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.sql.ResultSet;

public class oneSingleJson {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws JsonMappingException, JsonGenerationException, ClassNotFoundException, SQLException,IOException {
  
	   // Initialize JDBC Driver
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   Connection connctn=null;
	   
	   // Setup Array Lists: customerList: Object Array of DB records, jsonArr: Array of json objects.
	   ArrayList<CustomerDetails> customerList=new ArrayList<CustomerDetails>();
	   JSONArray jsonArr=new JSONArray();
	   
	   // Connect to mySQL Database called business
	   connctn=DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "DBadmin");
	   System.out.println("Connected to Database");
	   
	   //object of statement class will help us to execute queries
	   Statement ask=connctn.createStatement();
	   ResultSet queryResult=ask.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' and purchasedDate=curdate();");
	   System.out.println("Executed the Query");
	   
 
	   while (queryResult.next()) /* boolean: the while True until it reaches the last record*/
	  {

		   CustomerDetails customer=new CustomerDetails();
		   customer.setCourseName(queryResult.getString(1));
		   customer.setPurchasedDate(queryResult.getString(2));
		   customer.setAmount(queryResult.getInt(3));
		   customer.setLocation(queryResult.getString(4));

		   customerList.add(customer);
	  }
	   for (int index=0; index<customerList.size(); index++)
	   {
		   
		 //Jackson API : maps DB tables fields to json
	   ObjectMapper customerObj=new ObjectMapper();
	   customerObj.writeValue(new File("C:\\repository\\SDET\\javaSDET\\JsonJava\\customerInfo"+index+".json"), customerList.get(index));
	   
	   // Gson: Create json String from Java Object/json
	   Gson gson=new Gson();
	   String jsonString=gson.toJson(customerList.get(index));
	   jsonArr.add(jsonString);
	   }
	   
	   System.out.println(customerList.size()+" Files Created");
	   

	   // Json Simple: .put("data",jsonArr) .  "data" is json header /array name. jsonArray is the DB json data
	   JSONObject jsonObj=new JSONObject();
	   jsonObj.put("data",jsonArr);
	   
	   // Apache Commons Text : cleans out unwanted '\' esc char. and removes bugged plugin '"' using replace
	   String jsonEscRemoved=StringEscapeUtils.unescapeJava(jsonObj.toJSONString());
	   String stringMod1=jsonEscRemoved.replace("\"{","{");
	   String jsonResult=stringMod1.replace("}\"", "}");
	   System.out.println(jsonResult);
	   
	   // Write CustomerData.json file
	   try (FileWriter jsonFile=new FileWriter("C:\\repository\\SDET\\javaSDET\\JsonJava\\CustomerData.json"))
	   {
		   jsonFile.write(jsonResult);
		   
	   }
	   System.out.println("CustomerData.json written");
	   
	   
	  connctn.close();
	   System.out.println("Connection Closed");
	 }

}
