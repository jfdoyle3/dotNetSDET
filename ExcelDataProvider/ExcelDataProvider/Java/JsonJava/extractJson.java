import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class extractJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException
	{
		// json will the response of API
		//Jackson API
		ObjectMapper customerObj=new ObjectMapper();
		CustomerDetailsAppium appiumData=customerObj.readValue(new File("D:\\repository\\SDET\\javaSDET\\JsonJava\\customerInfo0.json"), CustomerDetailsAppium.class);	
		
		System.out.println(appiumData.getCourseName());
		
		// validate records in Web table are correct or not
		// getText() Appium
	}

}
