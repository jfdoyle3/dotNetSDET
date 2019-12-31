package remoteTesting.dockerValidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class chromeNode1Test 
{
	
	@BeforeTest
	public void startDockerGrid() throws IOException, InterruptedException 
	{
		File fileDel=new File("DockerGridLog.txt");
 		if (fileDel.delete())
		{
			System.out.println("Log file deleted");
		}
		startDocker startUpGrid=new startDocker();
		startUpGrid.StartDocker();
	}
	
	@AfterTest
	public void stopDockerGrid() throws IOException, InterruptedException
	{
		stopDocker shutdownGrid=new stopDocker();
		shutdownGrid.ShutDownDocker();
	}
	
	@Test
	public void test1() throws MalformedURLException
	{			
		DesiredCapabilities cap=DesiredCapabilities.chrome();
	
		URL url=new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver=new RemoteWebDriver(url,cap);
		
		driver.get("http://careerdevs.com");

		System.out.println(driver.getTitle());

	}
}

