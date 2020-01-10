package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class chromeStandAloneTest {

	public static void main(String[] args) throws MalformedURLException
	{
		// Maven with Selenium Automation and Chrome Driver
		// class needed to used is: RemoteWebDriver
		// Declare what browser. (.chrome, .firefox..), What URL and pass thru driver.
				
		DesiredCapabilities cap=DesiredCapabilities.chrome();
	
		URL url=new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver=new RemoteWebDriver(url,cap);
		
		// fetch / get a web site
		driver.get("http://careerdevs.com");

		// Console out the title of the Web Site
		System.out.println(driver.getTitle());
	}

}
