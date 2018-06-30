package com.Mockaroo;





import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {

	WebDriver driver;
	// ***********your downloads folder path*****************
	String downloadsFolder = "/Users/Jonathanguadron/Downloads/MOCK_DATA.csv";
			

	String actual;
	String expected;
	String format = "CSV";
	String lineEnd = "Unix (LF)";
	
	String line = null;
	ArrayList<String> readDownFile = new ArrayList<String>();
	String firstRowA;
	String firstRowB;
	int removeNumber = 7;
	List<String> cities = new ArrayList<>();
	List<String> countries = new ArrayList<>();

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://mockaroo.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@AfterClass
	public void tearDown() {

		driver.close();
		erasefile();
	}

	@Test(priority = 1)
	public void mDV1() {
		String actual = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String expected = "mockaroo";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 2)
	public void mDV2() {
		actual = driver.findElement(By.xpath("//div[@class='tagline']")).getText();
		expected = "realistic data generator";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 3)
	public void mDV3() {
		Remove();
	}

	@Test(priority = 4)
	public void mDV4() {
		actual = driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText();
		expected = "Field Name";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 5)
	public void mDV5() {
		actual = driver.findElement(By.xpath("//div[@class='column column-header column-type']")).getText();
		expected = "Type";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 6)
	public void mDV6() {
		actual = driver.findElement(By.xpath("//div[@class='column column-header column-options']")).getText();
		expected = "Options";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 7)
	public void mDV7() {
		Select form = new Select(driver.findElement(By.xpath("//select[@id='schema_file_format']")));
		String expectedFormat = form.getFirstSelectedOption().getText();
		Assert.assertEquals(format, expectedFormat);
	}

	@Test(priority = 8)
	public void mDV8() {
		Select form2 = new Select(driver.findElement(By.xpath("//select[@id='schema_line_ending']")));
		String expectedFormat2 = form2.getFirstSelectedOption().getText();
		Assert.assertEquals(lineEnd, expectedFormat2);
	}

	@Test(priority = 9)
	public void mDV9() {
		boolean select = driver.findElement(By.xpath("// input[@id='schema_include_header']")).isSelected();
		Assert.assertTrue(select);
	}

	@Test(priority = 10)
	public void mDV10() {
		boolean select1 = driver.findElement(By.xpath("//input[@id='schema_bom']")).isSelected();
		Assert.assertFalse(select1);
	}
//brian
	@Test(priority = 11)
	public void mDV11() throws InterruptedException {
		addFields();

	}

	@Test(priority = 12)
	public void mDV12() {

		try {
			FileReader fileReader = new FileReader(downloadsFolder);
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				readDownFile.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + downloadsFolder + "'");
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error reading file '" + downloadsFolder + "'");

		}
		firstRowA = "City";
		firstRowB = "Country";

		String rDF = readDownFile.get(0);
		Assert.assertTrue((rDF.contains((firstRowA)) && rDF.contains(firstRowB)));
	}

	@Test(priority = 13)
	public void mDV13() {
		int records = readDownFile.size();
		Assert.assertTrue(records == 1001);
	}

	@Test(priority = 14)
	public void mDV14() {
		try {
			FileReader fileReader = new FileReader(downloadsFolder);
			@SuppressWarnings("resource")
			BufferedReader buffer = new BufferedReader(fileReader);
			// while ((line = buffer.readLine()) != null) {
			// readDownFile.add(line);
			// }
			while ((line = buffer.readLine()) != null) {
				String[] items = line.split(",");
				cities.add(items[0]);
				countries.add(items[1]);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + downloadsFolder + "'");
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error reading file '" + downloadsFolder + "'");

		}
	}

	@Test(priority = 15)
	public void mDV15() {

		String[] cityArray = cities.toArray(new String[cities.size()]);
		Arrays.sort(cityArray, new MinComp());
		 for (String string : cityArray) {
		 System.out.println(string + ",");
		 }
		Arrays.sort(cityArray, new MaxComp());
		// for (String string : cityArray) {
		// System.out.println(string + ",");
		// }

		String[] countriesArray = countries.toArray(new String[countries.size()]);
		Arrays.sort(countriesArray, new MinComp());
		// for (String string : cityArray) {
		// System.out.println(string + ",");
		// }
		Arrays.sort(countriesArray, new MaxComp());
		// for (String string : cityArray) {
		// System.out.println(string + ",");
		// }
	}

	@Test(priority = 16)
	public void mDV16() {
		Set<String> unique = new HashSet<String>(cities);
        for (String key : unique) {
            System.out.println(key + ": " + Collections.frequency(cities, key));
        }}

	@Test(priority = 17)
	public void mDV18() {

	}

	
	 @Test
	    public void m5DV() {
	        //22. Sort all cities and find the city with the longest name and shortest name
	        String[] array = cities.toArray(new String[cities.size()]);
	        Arrays.sort(array, new MinComp());
	        for (String string : array) {
	            System.out.println(string + ",");
	        }
	        Arrays.sort(array, new MaxComp());
	        for (String string : array) {
	            System.out.println(string + ",");
	        }
	        
	        //23.In Countries ArrayList, find how many times each Country is mentioned.
	         int count = 1;
	         ArrayList <String> list0 = new ArrayList<String>();
	        for(int i = 0; i < countries.size(); i++){
	            boolean isDuplicate = false;
	             for (String s: list0){
	                 if (s.contains(countries.get(i).trim()))
	                     isDuplicate =true;
	             }
	            if (!isDuplicate){
	                  for(int j = i + 1; j < countries.size(); j++){
	                    if(countries.get(i).equals(countries.get(j))){
	                      count++;
	                    }
	                  }
	                  if(countries.get(i).equals("/s")){
	                    list0.add("Space" + "-" + count);
	                  }
	                  else{
	                    list0.add(countries.get(i) + "-" + count);
	                  }
	                  count = 1;   
	                 
	            }
	            
	        }
	        for (String a: list0)
	            System.out.println(a);          
	        
	        //24. From file add all Cities to citiesSet HashSet
	        Set<String> unique = new HashSet<String>(cities);
	        
	        //25. Count how many unique cities are in Cities ArrayList and assert that it is matching with the count of citiesSet HashSet.
	        
	     int count2 = 1;
	     ArrayList <String> list00 = new ArrayList<String>();
	    for(int i = 0; i < cities.size(); i++){
	        boolean isDuplicate = false;
	         for (String s: list00){
	             if (s.contains(cities.get(i).trim()))
	                 isDuplicate =true;
	         }
	        if (!isDuplicate){
	              for(int j = i + 1; j < cities.size(); j++){
	                if(cities.get(i).equals(cities.get(j))){
	                  count2++;
	                }
	              }
	              if(cities.get(i).equals("/s")){
	                list00.add("Space" + "-" + count2);
	              }
	              else{
	                list00.add(cities.get(i) + "-" + count2);
	              }
	              count2 = 1;     
	        }
	       
	    }
	    cities=list00;
	        
	        
	        for (String key : unique) {
	            
	            System.out.println(key + ": " + Collections.frequency(countries, key));
	            
	        }
	        assertEquals(cities.size(), unique.size());
	        
	    }
	
	 @Test
	 public void CheckLast() {

		 
		 List<String> uniqueCities = new ArrayList<>();
			for (String city : cities) {
				if(!uniqueCities.contains(city))
					uniqueCities.add(city);
			}
			
			//Iva line 272
			 Set<String> unique = new HashSet<String>(cities);
		        
			Assert.assertEquals(uniqueCities.size(), unique.size());
			
			List<String> uniqueCountries = new ArrayList<>();
			for (String country : countries) {
				if(!uniqueCountries.contains(country))
					uniqueCountries.add(country);
			}
			
			Assert.assertEquals(uniqueCities.size(), unique.size());
		}
	 
	

	class MinComp implements Comparator<String> {
		// shortest to longest
		public int compare(String o1, String o2) {
			return Integer.compare(o1.length(), o2.length());
		}
	}

	class MaxComp implements Comparator<String> {
		public int compare(String o1, String o2) {
			return Integer.compare(o2.length(), o1.length());
		}
	}

	public void Remove() {
		for (int i = 1; i < removeNumber; i++) {
			driver.findElement(By.xpath("(//a[@class='close remove-field remove_nested_fields'])[" + i + "]")).click();
		}
	}

	public void addFields() throws InterruptedException {
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();

		driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[2]/input")).sendKeys("City");

		// 13. Click on Choose type and assert that Choose a Type dialog box is
		// displayed.
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[3]/input[3]")).isDisplayed());

		// 14. Search for “city” and click on City on search results.

		driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[3]/input[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).sendKeys("city");

		driver.findElement(By.xpath("//*[@id=\"type_list\"]/div/div[1]")).click();

		// step 15 Click on ‘Add another field’ and enter name “Country”
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).clear();

		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).sendKeys("Country");
		// Click on Choose type and assert that Choose a Type dialog box is displayed.
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).isDisplayed());

		// Click on ‘Add another field’ and enter name “Country”
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).clear();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).sendKeys("country");

		driver.findElement(By.xpath("(//div[@class='type-name'])[1]")).click();
		Thread.sleep(4000);
		// 16. Click on Download Data.
		driver.findElement(By.xpath("//*[@id=\"download\"]")).click();
		Thread.sleep(8000);

	}

	public void erasefile() {

		File file = new File(downloadsFolder);

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

	}
}