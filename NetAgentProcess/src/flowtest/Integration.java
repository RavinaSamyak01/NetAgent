package flowtest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Integration {
	
	static WebDriver driver, driver2, driver3;
	static String readytime;
	static String totalmile, pumile, dlmile, stemmile, actrate;
	static JavascriptExecutor js, executor;

		public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.get("http://192.168.117.22:8075/");
		
		//js=(JavascriptExecutor)driver; 
				//WebElement username= driver.findElement(By.id("inputUsername"));
				//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", username); 
				
				driver.findElement(By.id("inputUsername")).sendKeys("samyak");
				
				//js=(JavascriptExecutor)driver; 
				//WebElement password= driver.findElement(By.id("inputPassword"));
				//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", password); 
				driver.findElement(By.id("inputPassword")).sendKeys("samyak");

				//js=(JavascriptExecutor)driver; 
				//WebElement btn= driver.findElement(By.xpath("//button"));
				//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", btn); 
				driver.findElement(By.xpath("//button")).click();
				Thread.sleep(4000);
				//click on Ship Package
				
				File src = new File("D:\\Netlink_AJ\\TestAutomation\\NetAgentAutomation\\NetAgentProcess\\OrderCreation.xlsx");
				FileInputStream fis = new FileInputStream(src);
				Workbook workbook = WorkbookFactory.create(fis);
				Sheet sh1 = workbook.getSheet("Sheet1");
				DataFormatter formatter = new DataFormatter();
				for(int i=1; i<2; i++)
				{
						Thread.sleep(8000);
						
						//js=(JavascriptExecutor)driver; 
						//WebElement pkg= driver.findElement(By.id("ShipPackageTab"));
						//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", pkg); 
						
						
						driver.findElement(By.id("ShipPackageTab")).click();
						Thread.sleep(12000);
						//System.out.println(driver.findElement(By.xpath("//*[@id='btnPuDelInfo']")).getText());
					
						
						driver.findElement(By.xpath("//*[@id='btnPuDelInfo']")).click();
						Thread.sleep(5000);
						
						String PUCompany = formatter.formatCellValue(sh1.getRow(i).getCell(0));
						driver.findElement(By.id("txtPUCompanyName")).clear();
						driver.findElement(By.id("txtPUCompanyName")).sendKeys(PUCompany);
						driver.findElement(By.id("txtaddressline")).clear();
						String PUAddressLine1 = formatter.formatCellValue(sh1.getRow(i).getCell(1));
						driver.findElement(By.id("txtaddressline")).sendKeys(PUAddressLine1);
						String PUZip = formatter.formatCellValue(sh1.getRow(i).getCell(3));
						driver.findElement(By.id("txtPUZipCode")).clear();
						driver.findElement(By.id("txtPUZipCode")).sendKeys(PUZip);
						Thread.sleep(5000);
						driver.findElement(By.id("txtPUZipCode")).sendKeys(Keys.ENTER);
						
						String PUContactName = formatter.formatCellValue(sh1.getRow(i).getCell(4));
						driver.findElement(By.id("txtpertosee")).clear();
						driver.findElement(By.id("txtpertosee")).sendKeys(PUContactName);
						String PUPhone = formatter.formatCellValue(sh1.getRow(i).getCell(5));
						driver.findElement(By.id("txtphone")).clear();
						driver.findElement(By.id("txtphone")).sendKeys(PUPhone);
						
						String DLCompany = formatter.formatCellValue(sh1.getRow(i).getCell(6));
						driver.findElement(By.id("txtDLCompanyName")).clear();
						driver.findElement(By.id("txtDLCompanyName")).sendKeys(DLCompany);
						String DLAddressLine1 = formatter.formatCellValue(sh1.getRow(i).getCell(7));
						driver.findElement(By.id("txtaddresslinedel")).clear();
						driver.findElement(By.id("txtaddresslinedel")).sendKeys(DLAddressLine1);
						String DLZip = formatter.formatCellValue(sh1.getRow(i).getCell(9));
						driver.findElement(By.id("txtDLZipCode")).clear();
						driver.findElement(By.id("txtDLZipCode")).sendKeys(DLZip);
						Thread.sleep(4000);
						driver.findElement(By.id("txtDLZipCode")).sendKeys(Keys.ENTER);
						Thread.sleep(5000);
						String DLContactName = formatter.formatCellValue(sh1.getRow(i).getCell(10));
						driver.findElement(By.id("txtpertoseedel")).clear();
						driver.findElement(By.id("txtpertoseedel")).sendKeys(DLContactName);
						String DLPhone = formatter.formatCellValue(sh1.getRow(i).getCell(11));
						driver.findElement(By.id("txtphonedel")).clear();
						driver.findElement(By.id("txtphonedel")).sendKeys(DLPhone);
						
						Thread.sleep(8000);
						
						//driver.findElement(By.id("cmbdefualtservice")).click();
						driver.findElement(By.xpath("//*[@id=\"dvPuDelShipment\"]/div/div[3]/div/div[1]/div[2]/div/ul/li[1]/label[2]/span")).click();
						Thread.sleep(10000);
						String Service = formatter.formatCellValue(sh1.getRow(i).getCell(12));
						if (Service.equals("LOC")) 
						{
							WebElement ele = driver.findElement(By.id("cmbdefualtservice"));
							Select opt = new Select(ele);
							opt.selectByVisibleText("Rush Drive - LOC");
						}
						
						else if (Service.equals("SD")) 
						{
							WebElement ele = driver.findElement(By.id("cmbdefualtservice"));
							Select opt = new Select(ele);
							opt.selectByVisibleText("Next Flight Out - SD");
						}

						Thread.sleep(10000);
						
						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,450)", "");
						driver.findElement(By.xpath("//*[@id='btnPlaceOrder']")).click();
						
						Thread.sleep(8000);
						Actions builder = new Actions(driver);
						WebElement ele1 = driver.findElement(By.xpath("html/body/div[8]/div/div"));		
						builder.moveToElement(ele1).build().perform();
						WebElement ele2 = driver.findElement(By.xpath("html/body/div[8]/div/div/div[2]"));		
						builder.moveToElement(ele2).build().perform();		
						
						String pickup = driver.findElement(By.xpath("html/body/div[8]/div/div/div[2]/div[1]")).getText();
						
						//String result = pickup.substring(94, 101);
						Thread.sleep(8000);
						driver.findElement(By.xpath("/html/body/div[8]/div/div/div[3]/button[1]")).click();		
						Thread.sleep(6000);
						Pattern pattern = Pattern.compile("\\w+([0-9]+)\\w+([0-9]+)");
						Matcher matcher = pattern.matcher(pickup);
						matcher.find();
						String  pck = matcher.group();
						System.out.println(pck);
						
						  File src1 = new File("D:\\Netlink_AJ\\TestAutomation\\NetAgentAutomation\\NetAgentProcess\\OrderCreation.xlsx");
						  FileOutputStream fis1 = new FileOutputStream(src1);
						  Sheet sh2 = workbook.getSheet("Sheet1");
						  sh2.getRow(i).createCell(14).setCellValue(pck);
						  workbook.write(fis1);
						  fis1.close();
				}
				connectApplication();
				netAgent();
				driver.close();
				driver.quit();
				driver2.close();
				driver2.quit();
				driver3.close();
				driver3.quit();
			}
			
			public static void connectApplication() throws Exception
			{
				System.setProperty("webdriver.chrome.driver","E:\\Ketan\\Tools\\Selenium\\chromedriver_win32\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				driver2 = new ChromeDriver(options);
				driver2.manage().window().maximize();
				driver2.get("http://192.168.117.22:8072/");
				
				
				//js=(JavascriptExecutor)driver2; 
				//WebElement uname= driver2.findElement(By.id("inputUsername"));
				//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", uname); 
				
				driver2.findElement(By.id("inputUsername")).clear();
				driver2.findElement(By.id("inputUsername")).sendKeys("abhishek");
				
				//js=(JavascriptExecutor)driver2; 
				//WebElement pass= driver2.findElement(By.id("inputPassword"));
				//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", pass); 
				
				driver2.findElement(By.id("inputPassword")).clear();
				driver2.findElement(By.id("inputPassword")).sendKeys("Assipl45");
				
				//js=(JavascriptExecutor)driver2; 
				//WebElement btn= driver2.findElement(By.xpath("//button[@id='btnLogin']"));
				//js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", btn); 
				
				driver2.findElement(By.xpath("//button[@id='btnLogin']")).click();
				
				Thread.sleep(10000);
				
				
				for(int i=1; i<2; i++)
				{
				
				WebDriverWait wait = new WebDriverWait(driver2, 600);
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(@id='headerDiv')]//div[(@id='MainMenu')]//span[contains(text(),'Operations')]")));
				element.click();
				
				driver2.findElement(By.xpath("//div[(@id='headerDiv')]//div[(@id='MainMenu')]//span[contains(text(),'Operations')]")).click();
				Thread.sleep(7000);

				driver2.findElement(By.xpath("//div[(@id='headerDiv')]//div[(@id='MainMenu')]//a[(@id='a_TaskLog')]//span[contains(text(),'Task Log')]")).click();
				Thread.sleep(8000);
								
				driver2.findElement(By.id("hlkOrderSearch")).click();
				
				Thread.sleep(6000);
				
				File src = new File("D:\\Netlink_AJ\\TestAutomation\\NetAgentAutomation\\NetAgentProcess\\OrderCreation.xlsx");
				FileInputStream fis = new FileInputStream(src);
				Workbook workbook = WorkbookFactory.create(fis);
				Sheet sh1 = workbook.getSheet("Sheet1");
				DataFormatter formatter = new DataFormatter();
				
						Thread.sleep(8000);
										
						String pickup = formatter.formatCellValue(sh1.getRow(i).getCell(14));
						driver2.findElement(By.id("txtPickup")).clear();
						driver2.findElement(By.id("txtPickup")).sendKeys(pickup);
						Thread.sleep(3000);
						
						driver2.findElement(By.id("btnSearch")).click();
						Thread.sleep(7000);
						driver2.findElement(By.id("lblJobIdValue_0")).click();
						
						Thread.sleep(10000);
						
						totalmile = driver2.findElement(By.id("txtMileage")).getAttribute("value");
						Thread.sleep(3000);
						
						pumile = driver2.findElement(By.id("txtEditPUMiles")).getAttribute("value");
						Thread.sleep(3000);
						
						dlmile = driver2.findElement(By.id("txtEditDelMiles")).getAttribute("value");
						Thread.sleep(3000);
						
						stemmile = driver2.findElement(By.id("txtTmlStem")).getAttribute("value");
						Thread.sleep(3000);
						
						actrate = driver2.findElement(By.id("txtTmlStem")).getAttribute("value");
						Thread.sleep(3000);
						
						
						Thread.sleep(10000);
						driver2.findElement(By.xpath(".//*[@id='idJobOverview']")).click();
						
						Thread.sleep(9000);
						readytime = driver2.findElement(By.id("txtReadyforPickupTime")).getAttribute("value");
						//System.out.println(readytime);
						driver2.findElement(By.id("GreenTick")).click();
						Thread.sleep(7000);
						driver2.findElement(By.id("GreenTick")).click();
						
						Thread.sleep(10000);
						
						//agentSelection();
						//Thread.sleep(8000);
						
						Select Contacttype = new Select(driver2.findElement(By.id("cmbContacted")));
						Contacttype.selectByVisibleText("Email");
						Thread.sleep(3000);
						
						driver2.findElement(By.id("lblContactedValue1")).clear();
						driver2.findElement(By.id("lblContactedValue1")).sendKeys("asharma@samyak.com");			
						
						driver2.findElement(By.id("txtSpokeWith")).clear();
						driver2.findElement(By.id("txtSpokeWith")).sendKeys("Abhishek");
						Thread.sleep(5000);
						
						driver2.findElement(By.id("GreenTickAlertPickup")).click();
						Thread.sleep(8000);	
						
						File src1 = new File("E:\\ABHISHEK SHARMA\\workspace\\NetShipAJ\\src\\TestFiles\\OrderCreation.xlsx");
						FileOutputStream fis1 = new FileOutputStream(src1);
						Sheet sh2 = workbook.getSheet("Sheet1");
						sh2.getRow(i).createCell(16).setCellValue(totalmile);
						sh2.getRow(i).createCell(18).setCellValue(pumile);
						sh2.getRow(i).createCell(20).setCellValue(dlmile);
						sh2.getRow(i).createCell(22).setCellValue(stemmile);
						sh2.getRow(i).createCell(24).setCellValue(actrate);
						sh2.getRow(i).createCell(25).setCellValue(readytime);
						workbook.write(fis1);
						fis1.close();
				}
				
			}

			public static void netAgent() throws Exception
			{
				
				System.setProperty("webdriver.chrome.driver","E:\\Ketan\\Tools\\Selenium\\chromedriver_win32\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				driver3 = new ChromeDriver(options);
				driver3.manage().window().maximize();
				driver3.get("http://192.168.117.22:8073");
				
				//Robot r = new Robot();
				//r.keyPress(KeyEvent.VK_F11);
				
				driver3.findElement(By.id("inputUsername")).clear();
				driver3.findElement(By.id("inputUsername")).sendKeys("automation");
				
				driver3.findElement(By.id("inputPassword")).clear();
				driver3.findElement(By.id("inputPassword")).sendKeys("automation");
				Thread.sleep(2000);
				driver3.findElement(By.id("idsigninbutton")).click();
				Thread.sleep(10000);
				
				Actions builder = new Actions(driver3);
				WebElement ele = driver3.findElement(By.id("idOperations"));		
				builder.moveToElement(ele).build().perform();
				ele.click();
								
				WebElement ele2 = driver3.findElement(By.id("idTask"));
				ele2.click();
					
				
				Thread.sleep(10000);
				puDRVConfirm();
				pickupConfirm();
				deliveryConfirm();
				
				
				
			}
			public static void puDRVConfirm() throws Exception
			{
				Thread.sleep(8000);
				
				File src = new File("E:\\ABHISHEK SHARMA\\workspace\\NetShipAJ\\src\\TestFiles\\OrderCreation.xlsx");
				FileInputStream fis = new FileInputStream(src);
				Workbook workbook = WorkbookFactory.create(fis);
				Sheet sh1 = workbook.getSheet("Sheet1");
				DataFormatter formatter = new DataFormatter();
				for(int i=1; i<2; i++)
				{
						Thread.sleep(8000);
										
						String pickup = formatter.formatCellValue(sh1.getRow(i).getCell(14));
						driver3.findElement(By.id("txtBasicSearch")).clear();
						driver3.findElement(By.id("txtBasicSearch")).sendKeys(pickup);
						Thread.sleep(7000);
				
						
						driver3.findElement(By.id("btnSearch3")).click();
						Thread.sleep(9000);
				
						driver3.findElement(By.id("lnkConfPick")).click();
						Thread.sleep(7000);
						boolean process = driver3.findElement(By.id("errorid")).isDisplayed();
					if(process == true)
					{
						Thread.sleep(5000);
						WebElement ab = driver3.findElement(By.id("drpDriverName"));
						Select opt = new Select(ab);
						opt.selectByVisibleText("ABHISHEK  - automation");
						driver3.findElement(By.id("lnkConfPick")).click();
						Thread.sleep(4000);
					}

					else {
					Thread.sleep(8000);
					
					driver3.findElement(By.id("txtBasicSearch")).clear();
					driver3.findElement(By.id("txtBasicSearch")).sendKeys(pickup);
					driver3.findElement(By.id("btnSearch3")).click();
					Thread.sleep(8000);

					}
				}
				
			}
			
			public static void pickupConfirm() throws Exception
			{
				Thread.sleep(10000);
				for(int i=1; i<2; i++)
				{
				
				File src = new File("E:\\ABHISHEK SHARMA\\workspace\\NetShipAJ\\src\\TestFiles\\OrderCreation.xlsx");
				FileInputStream fis = new FileInputStream(src);
				Workbook workbook = WorkbookFactory.create(fis);
				Sheet sh1 = workbook.getSheet("Sheet1");
				DataFormatter formatter = new DataFormatter();
						Thread.sleep(8000);
									
						String pickup = formatter.formatCellValue(sh1.getRow(i).getCell(14));
						/*	driver3.findElement(By.id("txtBasicSearch")).clear();
						driver3.findElement(By.id("txtBasicSearch")).sendKeys(pickup);
						Thread.sleep(7000);
						driver3.findElement(By.id("btnSearch3")).click();
				
						Thread.sleep(8000);
				
						driver3.findElement(By.xpath("//*[@id=\"lnksave\"]/span")).click();
						
						Thread.sleep(9000);*/
						driver3.findElement(By.id("txtBasicSearch")).clear();
						driver3.findElement(By.id("txtBasicSearch")).sendKeys(pickup);
						Thread.sleep(4000);
						driver3.findElement(By.id("btnSearch3")).click();
						Thread.sleep(8000);
						//driver3.findElement(By.id("txtActualPickUpTime")).sendKeys("05:35");
						String putime = formatter.formatCellValue(sh1.getRow(i).getCell(25));
						driver3.findElement(By.id("txtActualPickUpTime")).sendKeys(putime);
						
						Thread.sleep(5000);
						driver3.findElement(By.xpath("//*[@id=\"lnksave\"]/span")).click();
						
						
						Thread.sleep(8000);
				}
			}
			
			public static void deliveryConfirm() throws Exception
			{
				Thread.sleep(10000);
				for(int i=1; i<2; i++)
				{
				File src = new File("E:\\ABHISHEK SHARMA\\workspace\\NetShipAJ\\src\\TestFiles\\OrderCreation.xlsx");
				FileInputStream fis = new FileInputStream(src);
				Workbook workbook = WorkbookFactory.create(fis);
				Sheet sh1 = workbook.getSheet("Sheet1");
				DataFormatter formatter = new DataFormatter();
				
						Thread.sleep(8000);
									
						String pickup = formatter.formatCellValue(sh1.getRow(i).getCell(14));
						/*	driver3.findElement(By.id("txtBasicSearch")).clear();
						driver3.findElement(By.id("txtBasicSearch")).sendKeys(pickup);
						Thread.sleep(7000);
						driver3.findElement(By.id("btnSearch3")).click();
				
						Thread.sleep(8000);
				
						driver3.findElement(By.xpath("//*[@id=\"lnksave\"]/span")).click();
						
						Thread.sleep(9000);*/
						driver3.findElement(By.id("txtBasicSearch")).clear();
						driver3.findElement(By.id("txtBasicSearch")).sendKeys(pickup);
						Thread.sleep(2000);
						driver3.findElement(By.id("btnSearch3")).click();
						Thread.sleep(8000);
						//driver3.findElement(By.id("txtActualDeliveryTme")).sendKeys("05:40");
						String podtime = formatter.formatCellValue(sh1.getRow(i).getCell(25));
						driver3.findElement(By.id("txtActualDeliveryTme")).sendKeys(podtime);
						Thread.sleep(5000);
						driver3.findElement(By.id("txtSignature")).sendKeys("asharma");
						Thread.sleep(6000);
						driver3.findElement(By.xpath("//*[@id=\"btnsavedelivery\"]/span")).click();
						Thread.sleep(8000);
				}
				
			}
			/*
			public static void agentSelection() throws Exception
			{
				Thread.sleep(8000);
				
				driver2.findElement(By.id("hlkedit")).click();
				Thread.sleep(8000);
				driver2.findElement(By.id("txtCourierId")).clear();
				driver2.findElement(By.id("txtCourierId")).sendKeys("34627");
				Thread.sleep(1000);
				driver2.findElement(By.id("txtCourierId")).sendKeys(Keys.TAB);
				Thread.sleep(2000);
				
				WebElement agent =  driver2.findElement(By.id("AgentId_34627"));
				
				executor.executeScript("arguments[0].scrollIntoView(true);", agent);
				executor.executeScript("arguments[0].click();", agent);
				try {
					Thread.sleep(25000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(2000);
			}
			*/
			public static void waitForVisibilityOfElement(By objLocator, long lTime) {
				try {
					WebDriverWait wait = new WebDriverWait(driver, lTime);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(objLocator));
				} catch (Exception e) {
				}
			}

			public static void waitForInVisibilityOfElement(By objLocator, long lTime) {

				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(objLocator));
			}

		}
		
