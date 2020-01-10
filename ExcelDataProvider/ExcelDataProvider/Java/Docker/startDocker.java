package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;


public class startDocker {

	
	public void StartDocker() throws IOException, InterruptedException
	{
		boolean flag=false;
		
		// Run an external file
		Runtime runTime=Runtime.getRuntime();
		runTime.exec("cmd /c start dockerUp.bat");
		
		String fileName="DockerGridLog.txt";
		
		// Gets Current Time and stores the time 30 seconds ahead.
		Calendar time=Calendar.getInstance();
		time.add(Calendar.SECOND, 45); // change number amount of seconds ahead.
		long stopTime=time.getTimeInMillis();
		Thread.sleep(3000);
		
		while(System.currentTimeMillis()<stopTime)
		{
			if(flag)
			{
				break;
			}
			
			// Read external file called: fileName
			BufferedReader reader=new BufferedReader(new FileReader(fileName));
			String currentLine=reader.readLine();
			
			while(currentLine!=null && !flag)
			{
				if (currentLine.contains("registered to the hub and ready to use"))
				{
					System.out.println("Found it.");
					flag=true;
					break;
				}
			 currentLine=reader.readLine();
			}
		  reader.close();
		}
		
		Assert.assertTrue(flag);
		runTime.exec("cmd /c start ScaleGrid.bat"); 
		Thread.sleep(5000);
	}
}
