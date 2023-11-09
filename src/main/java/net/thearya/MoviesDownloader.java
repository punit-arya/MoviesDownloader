package net.thearya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MoviesDownloader
{
	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver((new ChromeDriverService.Builder()
		{
			@Override
			protected File findDefaultExecutable()
			{
				if (new File("/snap/bin/chromium.chromedriver").exists())
				{
					return new File("/snap/bin/chromium.chromedriver")
					{
						@Override
						public String getCanonicalPath() throws IOException
						{
							return this.getAbsolutePath();
						}
					};
				}
				else
				{
					return super.findDefaultExecutable();
				}
			}
		}).build());

		driver.get("https://thepiratebay.org/browse/201/0/7/0");
		// WebElement element = driver.findElement(By.name("searchResult"));
		// int rowCount = driver.findElements(By.xpath("//*[@id='searchResult']/div[1]/table/tbody/tr")).size();
		// System.out.println(rowCount);
		// System.out.println("Page title is: " + driver.getTitle());
		List<WebElement> searchResult = driver.findElements(By.id("searchResult"));
		for (WebElement webElement : searchResult)
		{
			List<WebElement> webElementElements = webElement.findElements(By.tagName("td"));
			for (WebElement td : webElementElements)
			{
				String cell = td.getText();
				cell = cell.replaceAll("\n", " ");
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
		driver.quit();
	}
}
