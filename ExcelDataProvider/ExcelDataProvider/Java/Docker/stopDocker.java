package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;


public class stopDocker {

	
	public void ShutDownDocker() throws IOException, InterruptedException
	{
		boolean flag=false;
		Runtime runTime=Runtime.getRuntime();
		runTime.exec("cmd /c start dockerDown.bat");
		
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
			BufferedReader reader=new BufferedReader(new FileReader(fileName));
			String currentLine=reader.readLine();
			
			while(currentLine!=null && !flag)
			{
				if (currentLine.contains("selenium-hub exited"))
				{
					System.out.println("Selenium Hub is running");
					flag=true;
					break;
				}
			 currentLine=reader.readLine();
			}
		  reader.close();
		}
		
		Assert.assertTrue(flag);
		
//    Didn't work in project, worked when running on it's own.
//    Kept it here need to work her or paste it else where to work.
//		File fileDel=new File(fileName);
//		if (fileDel.delete())
//		{
//			System.out.println("Log file deleted");
//		}
		
	}
}
