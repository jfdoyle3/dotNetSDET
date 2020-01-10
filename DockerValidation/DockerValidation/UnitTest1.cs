using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using System;

namespace DockerValidation
{
    public class Tests
    {
        [Test]
        public void Test1()
        {
            // Maven with Selenium Automation and Chrome Driver
            // class needed to used is: RemoteWebDriver
            // Declare what browser. (.chrome, .firefox..), What URL and pass thru driver.



            RemoteSessionSettings cap = new RemoteSessionSettings();
            using (RemoteWebDriver driver = new RemoteWebDriver(new Uri("http://localhost:4444/wd/hub"), cap))
            {
                // fetch / get a web site
                driver.Navigate().GoToUrl("http://careerdevs.com");

                // Console out the title of the Web Site
                System.Console.WriteLine(driver.Title);
            }
        }
    }
}