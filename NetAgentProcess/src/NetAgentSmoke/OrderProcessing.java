package NetAgentSmoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrderProcessing extends BaseInit {

	@Test
	public static void orderProcess()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) Driver;
		Actions act = new Actions(Driver);
		File src0 = new File(".\\NA_STG.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("OrderCreation");
		DataFormatter formatter = new DataFormatter();

		Row row = sh0.getRow(0);

		int rowNum = sh0.getLastRowNum() + 1;
		System.out.println("total No of Rows=" + rowNum);

		int colNum = row.getLastCellNum();
		System.out.println("total No of Columns=" + colNum);

		// Go To TaskLog
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Operations")));
		Driver.findElement(By.partialLinkText("Operations")).click();

		Driver.findElement(By.linkText("Task Log")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("panel-body")));

		getScreenshot(Driver, "TaskLog_Operations");
		Driver.findElement(By.id("operation")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --Basic Search

		for (int row1 = 1; row1 < rowNum; row1++) {
			// --Search with PickUP ID
			String PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
			String ServiceID = formatter.formatCellValue(sh0.getRow(row1).getCell(0));
			System.out.println("==========PickUp ID is==" + PUID + "==========");
			System.out.println("==========Service ID is==" + ServiceID + "==========");

			// try {

			if (ServiceID.contains("LOC") || ServiceID.contains("SD")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
				Driver.findElement(By.id("txtBasicSearch2")).clear();
				Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
				Driver.findElement(By.id("btnGXNLSearch2")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
				if (PickuPBox.isDisplayed()) {
					System.out.println("Searched Job is displayed in edit mode");
					getScreenshot(Driver, "OrderEditor_" + PUID);
				}
				// --Memo
				// memo(PUID);

				// -Notification
				// notification(PUID);

				// Upload
				// upload(PUID);

				// Map
				// map(PUID);

				// --Get current stage of the order
				String Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
				System.out.println("Current stage of the order is=" + Orderstage);
				if (Orderstage.equalsIgnoreCase("Confirm Pu Alert")) {
					// --Confirm button
					Driver.findElement(By.id("lnkConfPick")).click();
					System.out.println("Clicked on CONFIRM button");

					// --Click on Close button //
					Driver.findElement(By.id("idclosetab")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					// --Search again
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
					PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
					Driver.findElement(By.id("txtBasicSearch")).clear();
					Driver.findElement(By.id("txtBasicSearch")).sendKeys(PUID);
					Driver.findElement(By.id("btnSearch3")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				} else if (Orderstage.equalsIgnoreCase("PICKUP")) {
					// --Pickup
					Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
					System.out.println("Current stage of the order is=" + Orderstage);
					// --Enter PickUp Time

					String ZOneID = Driver.findElement(By.id("spanTimezoneId")).getText();
					System.out.println("ZoneID of is==" + ZOneID);
					if (ZOneID.equalsIgnoreCase("EDT")) {
						ZOneID = "America/New_York";
					} else if (ZOneID.equalsIgnoreCase("CDT")) {
						ZOneID = "CST";

					}

					WebElement PUPTime = Driver.findElement(By.id("txtActualPickUpTime"));
					PUPTime.clear();
					Date date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("HH:mm");
					dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
					System.out.println(dateFormat.format(date));
					PUPTime.sendKeys(dateFormat.format(date));
					Driver.findElement(By.id("lnksave")).click();
					System.out.println("Clicked on PICKUP button");
					Thread.sleep(2000);
					try {
						wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("modal-dialog")));
						Driver.findElement(By.id("iddataok")).click();
						System.out.println("Clicked on Yes button");

					} catch (Exception e) {
						System.out.println("Dialogue is not exist");
					}
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
					PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
					Driver.findElement(By.id("txtBasicSearch")).clear();
					Driver.findElement(By.id("txtBasicSearch")).sendKeys(PUID);
					wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch3")));
					Driver.findElement(By.id("btnSearch3")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

					ServiceID = formatter.formatCellValue(sh0.getRow(row1).getCell(0));
					System.out.println("Service ID is==" + ServiceID);
					if (ServiceID == "LOC") {

						// --Deliver Stage
						Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);

						// --Deliver Time

						ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
						System.out.println("ZoneID of is==" + ZOneID);
						if (ZOneID.equalsIgnoreCase("EDT")) {
							ZOneID = "America/New_York";
						} else if (ZOneID.equalsIgnoreCase("CDT")) {
							ZOneID = "CST";

						}

						WebElement DelTime = Driver.findElement(By.id("txtActualDeliveryTme"));
						DelTime.clear();
						date = new Date();
						dateFormat = new SimpleDateFormat("HH:mm");
						dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
						System.out.println(dateFormat.format(date));
						DelTime.sendKeys(dateFormat.format(date));

						// --Signature
						Driver.findElement(By.id("txtSignature")).sendKeys("Ravina Prajapati");
						System.out.println("Entered signature");

						// --Click on Deliver
						Driver.findElement(By.id("btnsavedelivery")).click();
						System.out.println("Clicked on Deliver button");
						try {
							wait.until(
									ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("modal-dialog")));
							Driver.findElement(By.id("iddataok")).click();
							System.out.println("Clicked on Yes button");

						} catch (Exception e) {
							System.out.println("Dialogue is not exist");
						}
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is not delivered yet");
						}

					} else if (ServiceID == "SD") {

						Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);

						// --Drop Time

						ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
						System.out.println("ZoneID of is==" + ZOneID);
						if (ZOneID.equalsIgnoreCase("EDT")) {
							ZOneID = "America/New_York";
						} else if (ZOneID.equalsIgnoreCase("CDT")) {
							ZOneID = "CST";
						}

						WebElement DelTime = Driver.findElement(By.id("txtActualDeliveryTme"));
						DelTime.clear();
						date = new Date();
						dateFormat = new SimpleDateFormat("HH:mm");
						dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
						System.out.println(dateFormat.format(date));
						DelTime.sendKeys(dateFormat.format(date));

						// --Click on Drop
						WebElement Drop = Driver.findElement(By.id("btnsavedelivery"));
						Drop.click();
						System.out.println("Clicked on Deliver button");
						WebElement ErrorID = Driver.findElement(By.id("errorid"));
						if (ErrorID.getText().contains("The Air Bill is required")) {
							System.out.println("Message:-" + ErrorID.getText());
							// --Add Airbill
							WebElement AirBill = Driver.findElement(By.id("lnkAddAWB"));
							js.executeScript("arguments[0].scrollIntoView();", AirBill);

							WebElement AddAirBill = Driver.findElement(By.id("btnAddAWB"));
							js.executeScript("arguments[0].click();", AddAirBill);
							wait.until(ExpectedConditions
									.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"tableairbill\"]/tbody/tr")));
							System.out.println("AirBill editor is opened");

							/// --Enter AirBill
							Driver.findElement(By.id("txtAWBNum_0")).sendKeys("11111111");
							System.out.println("Entered AirBill");
							/// --Enter Description
							Driver.findElement(By.id("txtAWBDec_0")).sendKeys("SD Service Automation");
							System.out.println("Entered Description");
							/// --Enter NoOFPieces
							Driver.findElement(By.id("txtNoOfPieces_0")).sendKeys("2");
							System.out.println("Entered NoOFPieces");
							/// --Enter Total Weight
							Driver.findElement(By.id("txtTotalweight_0")).sendKeys("10");
							System.out.println("Entered Total Weight");
							// --Track
							wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Track")));
							WebElement Track = Driver.findElement(By.linkText("Track"));
							js.executeScript("arguments[0].click();", Track);
							System.out.println("Clicked on Track button");
							// --AIrbill new window
							String WindowHandlebefore = Driver.getWindowHandle();
							for (String windHandle : Driver.getWindowHandles()) {
								Driver.switchTo().window(windHandle);
								System.out.println("Switched to Airbill window");
								Thread.sleep(5000);
								getScreenshot(Driver, "AirBill_" + PUID);

							}
							Driver.close();
							System.out.println("Closed Airbill window");

							Driver.switchTo().window(WindowHandlebefore);
							System.out.println("Switched to main window");

							// --Click on Drop
							Drop = Driver.findElement(By.id("btnsavedelivery"));
							Drop = Driver.findElement(By.id("btnsavedelivery"));
							js.executeScript("window.scrollBy(0,-250)");
							Thread.sleep(1000);
							Drop.click();
							System.out.println("Clicked on Drop button");

							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
							PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
							try {
								WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
								if (NoData.isDisplayed()) {
									System.out.println("Job is Delivered successfully");
								}
							} catch (Exception e1) {
								System.out.println("Job is not delivered yet");
							}

						} else {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
							PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
							try {
								WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
								if (NoData.isDisplayed()) {
									System.out.println("Job is Delivered successfully");
								}
							} catch (Exception e1) {
								System.out.println("Job is not delivered yet");
							}
						}
					} else {
						System.out.println("Service is not SD or LOC");

					}
				} else if (Orderstage.equalsIgnoreCase("Deliver")) {
					if (ServiceID.equalsIgnoreCase("LOC")) {

						// --Deliver Stage
						Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);

						// --Deliver Time
						String ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
						System.out.println("ZoneID of is==" + ZOneID);
						if (ZOneID.equalsIgnoreCase("EDT")) {
							ZOneID = "America/New_York";
						} else if (ZOneID.equalsIgnoreCase("CDT")) {
							ZOneID = "CST";

						}

						WebElement DelTime = Driver.findElement(By.id("txtActualDeliveryTme"));
						DelTime.clear();
						Date date = new Date();
						DateFormat dateFormat = new SimpleDateFormat("HH:mm");
						dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
						System.out.println(dateFormat.format(date));
						DelTime.sendKeys(dateFormat.format(date));

						// --Signature
						Driver.findElement(By.id("txtSignature")).sendKeys("Ravina Prajapati");
						System.out.println("Entered signature");

						// --Click on Deliver
						Driver.findElement(By.id("btnsavedelivery")).click();
						System.out.println("Clicked on Deliver button");
						try {
							wait.until(
									ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("modal-dialog")));
							Driver.findElement(By.id("iddataok")).click();
							System.out.println("Clicked on Yes button");

						} catch (Exception e) {
							System.out.println("Dialogue is not exist");
						}
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is not delivered yet");
						}

					} else {
						System.out.println("Service is not LOC");
					}
				} else if (Orderstage.equalsIgnoreCase("Drop @ Origin")) {
					Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
					System.out.println("Current stage of the order is=" + Orderstage);

					// --Drop Time

					String ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
					System.out.println("ZoneID of is==" + ZOneID);
					if (ZOneID.equalsIgnoreCase("EDT")) {
						ZOneID = "America/New_York";
					} else if (ZOneID.equalsIgnoreCase("CDT")) {
						ZOneID = "CST";

					}

					WebElement DelTime = Driver.findElement(By.id("txtActualDeliveryTme"));
					DelTime.clear();
					Date date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("HH:mm");
					dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
					System.out.println(dateFormat.format(date));
					DelTime.sendKeys(dateFormat.format(date));

					// --Click on Drop
					WebElement Drop = Driver.findElement(By.id("btnsavedelivery"));
					Drop.click();
					System.out.println("Clicked on Drop button");
					WebElement ErrorID = Driver.findElement(By.id("errorid"));
					if (ErrorID.getText().contains("The Air Bill is required")) {
						System.out.println("Message:-" + ErrorID.getText());
						// --Add Airbill
						WebElement AirBill = Driver.findElement(By.id("lnkAddAWB"));
						js.executeScript("arguments[0].scrollIntoView();", AirBill);

						WebElement AddAirBill = Driver.findElement(By.id("btnAddAWB"));
						js.executeScript("arguments[0].click();", AddAirBill);
						wait.until(ExpectedConditions
								.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"tableairbill\"]/tbody/tr")));
						System.out.println("AirBill editor is opened");

						/// --Enter AirBill
						Driver.findElement(By.id("txtAWBNum_0")).sendKeys("11111111");
						System.out.println("Entered AirBill");
						/// --Enter Description
						Driver.findElement(By.id("txtAWBDec_0")).sendKeys("SD Service Automation");
						System.out.println("Entered Description");
						/// --Enter NoOFPieces
						Driver.findElement(By.id("txtNoOfPieces_0")).sendKeys("2");
						System.out.println("Entered NoOFPieces");
						/// --Enter Total Weight
						Driver.findElement(By.id("txtTotalweight_0")).sendKeys("10");
						System.out.println("Entered Total Weight");
						// --Track
						wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Track")));
						WebElement Track = Driver.findElement(By.linkText("Track"));
						js.executeScript("arguments[0].click();", Track);
						System.out.println("Clicked on Track button");
						// --AIrbill new window
						String WindowHandlebefore = Driver.getWindowHandle();
						for (String windHandle : Driver.getWindowHandles()) {
							Driver.switchTo().window(windHandle);
							System.out.println("Switched to Airbill window");
							Thread.sleep(5000);
							getScreenshot(Driver, "AirBill_" + PUID);
						}
						Driver.close();
						System.out.println("Closed Airbill window");

						Driver.switchTo().window(WindowHandlebefore);
						System.out.println("Switched to main window");

						// --Click on Drop
						Drop = Driver.findElement(By.id("btnsavedelivery"));
						js.executeScript("window.scrollBy(0,-250)");
						Thread.sleep(1000);
						Drop.click();
						System.out.println("Clicked on Drop button");

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is not delivered yet");
						}

					} else {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is not delivered yet");
						}
					}

				}
			} else if (ServiceID.contains("H3P")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
				Driver.findElement(By.id("txtBasicSearch2")).clear();
				Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
				Driver.findElement(By.id("btnGXNLSearch2")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

				// --Memo
				// memo(PUID);

				// -Notification
				// notification(PUID);

				// Upload
				// upload(PUID);

				// Map
				// map(PUID);
				String Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
				System.out.println("Current stage of the order is=" + Orderstage);
				if (Orderstage.contains("Confirm Pull and Apply Return Pack(s)")
						|| Orderstage.contains("CONF PULL ALERT")) {
					WebElement Form = Driver.findElement(By.name("ConfPullAlertForm"));
					if (Form.isDisplayed()) {
						System.out.println("Searched Job is displayed in edit mode");
						getScreenshot(Driver, "OrderEditor_" + PUID);
					}
					// --Confirm Pull and Apply Return Pack(s) stage
					String stage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
					System.out.println("stage=" + stage);
					getScreenshot(Driver, "ConfirmPullandApplyReturnPack(s)_" + PUID);
					// --Ship Label Services
					Driver.findElement(By.linkText("Ship Label Services")).click();
					System.out.println("Clicked on Ship Label Services");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					wait.until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"SLForm\"]")));
					getScreenshot(Driver, "ShipLabelServices_" + PUID);

					// --Send Email
					Driver.findElement(By.id("txtEmailLabelto")).sendKeys("Ravina.prajapati@samyak.com");
					System.out.println("Enetered EmailID");
					Driver.findElement(By.id("btnSend")).click();
					System.out.println("Clicked on Send button");
					// ErrorMsg
					try {
						wait.until(
								ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-bind=\"ErrorMsg\"]")));
						System.out.println("ErroMsg is Displayed="
								+ Driver.findElement(By.xpath("//*[@ng-bind=\"ErrorMsg\"]")).getText());

						// -- check the checkbox
						Driver.findElement(By.id("chkbShip_0")).click();
						System.out.println("Checked the shiplabel");
						Thread.sleep(2000);
						// --Send Email
						Driver.findElement(By.id("txtEmailLabelto")).clear();
						Driver.findElement(By.id("txtEmailLabelto")).sendKeys("Ravina.prajapati@samyak.com");
						System.out.println("Enetered EmailID");
						Driver.findElement(By.id("btnSend")).click();
						System.out.println("Clicked on Send button");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success")));
						System.out.println(
								"Success Message is Displayed=" + Driver.findElement(By.id("success")).getText());

					} catch (Exception e) {
						System.out.println("Error Message is not displayed");
					}
					// --Print button
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"scrollboxIframe\"]//button[@id=\"btnPrint\"]")));
					String TrackingNo = Driver
							.findElement(By.xpath("//*[contains(@ng-bind,'Your tracking number is')]")).getText();
					System.out.println("Tracking No==" + TrackingNo);
					Driver.findElement(By.xpath("//*[@id=\"scrollboxIframe\"]//button[@id=\"btnPrint\"]")).click();
					System.out.println("Clicked on Print button");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					wait.until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"SLForm\"]")));

					// Handle Print window
					String WindowHandlebefore = Driver.getWindowHandle();
					for (String windHandle : Driver.getWindowHandles()) {
						Driver.switchTo().window(windHandle);
						System.out.println("Switched to Print window");
						Thread.sleep(5000);
						getScreenshot(Driver, "PrintShipLabelService");
					}
					Driver.close();
					System.out.println("Closed Print window");

					Driver.switchTo().window(WindowHandlebefore);
					System.out.println("Switched to main window");

					// --Close Ship Label Service pop up
					WebElement memoClose = Driver.findElement(By.id("idanchorclose"));
					js.executeScript("arguments[0].click();", memoClose);
					System.out.println("Clicked on Close button of Memo");
					Thread.sleep(2000);

					// --Print pull Ticket
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idprintpull")));
					Driver.findElement(By.id("idprintpull")).click();
					System.out.println("Clicked on Print Pull Ticket");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					// Handle pull Print window
					WindowHandlebefore = Driver.getWindowHandle();
					for (String windHandle : Driver.getWindowHandles()) {
						Driver.switchTo().window(windHandle);
						System.out.println("Switched to Print Pull Ticket window");
						Thread.sleep(5000);
						getScreenshot(Driver, "PrintPullTicket_" + PUID);
					}
					Driver.close();
					System.out.println("Closed Print Pull Ticket window");

					Driver.switchTo().window(WindowHandlebefore);
					System.out.println("Switched to main window");
					Thread.sleep(2000);

					// --Click on Accept
					Driver.findElement(By.id("idiconaccept")).click();
					System.out.println("Clicked on Accept button");
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//*[@ng-message=\"required\"]")));
						System.out.println("Validation Message is=="
								+ Driver.findElement(By.xpath("//*[@ng-message=\"required\"]")).getText());
						// --Spoke with
						Driver.findElement(By.id("idConfPullAlertForm")).sendKeys("Ravina Oza");
						System.out.println("Entered spoke with");
						// --Click on Accept
						Driver.findElement(By.id("idiconaccept")).click();
						System.out.println("Clicked on Accept button");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
						Driver.findElement(By.id("txtBasicSearch2")).clear();
						Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
						Driver.findElement(By.id("btnGXNLSearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is not moved to Confirm Pull stage successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is moved to Confirm Pull stage successfully");

							// --Pull Inventory and Apply Return Pack(s) stage
							stage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
							System.out.println("stage=" + stage);
							getScreenshot(Driver, "PullInventoryandApplyReturnPack(s)_" + PUID);
							// --Label generation
							Driver.findElement(By.id("idiconprint")).click();
							System.out.println("Clicked on Label Generation");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							// Handle Label Generation window
							WindowHandlebefore = Driver.getWindowHandle();
							for (String windHandle : Driver.getWindowHandles()) {
								Driver.switchTo().window(windHandle);
								System.out.println("Switched to Label generation window");
								Thread.sleep(5000);
								getScreenshot(Driver, "Labelgeneration" + PUID);
							}
							Driver.close();
							System.out.println("Closed Label generation window");

							Driver.switchTo().window(WindowHandlebefore);
							System.out.println("Switched to main window");

							// --Save button
							Driver.findElement(By.id("idiconsave")).click();
							System.out.println("Clicked on Save button");
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.id("idPartPullDttmValidation")));
								String ValMsg = Driver.findElement(By.id("idPartPullDttmValidation")).getText();
								System.out.println("Validation Message=" + ValMsg);
								// --Part Pull Date
								WebElement PartPullDate = Driver.findElement(By.id("idtxtPartPullDate"));
								PartPullDate.clear();
								Date date = new Date();
								DateFormat dateFormat = new SimpleDateFormat("mm:dd:yy");
								PartPullDate.sendKeys(dateFormat.format(date));
								// --Part Pull Time
								String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'TimezoneId')]"))
										.getText();
								System.out.println("ZoneID of is==" + ZOneID);
								if (ZOneID.equalsIgnoreCase("EDT")) {
									ZOneID = "America/New_York";
								} else if (ZOneID.equalsIgnoreCase("CDT")) {
									ZOneID = "CST";
								}
								WebElement PartPullTime = Driver.findElement(By.id("txtPartPullTime"));
								PartPullTime.clear();
								date = new Date();
								dateFormat = new SimpleDateFormat("HH:mm");
								dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
								System.out.println(dateFormat.format(date));
								PartPullTime.sendKeys(dateFormat.format(date));

								// --Save button
								Driver.findElement(By.id("idiconsave")).click();
								System.out.println("Clicked on Save button");
							} catch (Exception e) {
								System.out.println("Validation Message is not displayed");
							}

							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//label[contains(@class,'error-messages')]")));
								System.out.println("ErroMsg is Displayed=" + Driver
										.findElement(By.xpath("//label[contains(@class,'error-messages')]")).getText());
								Thread.sleep(2000);

								// --Get the serial NO
								String SerialNo = Driver.findElement(By.xpath("//*[@ng-bind=\"segment.SerialNo\"]"))
										.getText();
								System.out.println("Serial No of Part is==" + SerialNo + "\n");
								// enter serial number in scan
								Driver.findElement(By.id("txtBarcode")).clear();
								Driver.findElement(By.id("txtBarcode")).sendKeys(SerialNo);
								Driver.findElement(By.id("txtBarcode")).sendKeys(Keys.TAB);
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
								System.out.println("Entered serial No in scan barcode");

								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//label[contains(@class,'error-messages')]")));
									System.out.println("ErroMsg is Displayed="
											+ Driver.findElement(By.xpath("//label[contains(@class,'error-messages')]"))
													.getText());
									Thread.sleep(2000);
									// --Part Pull Time
									String ZOneID = Driver
											.findElement(By.xpath("//span[contains(@ng-bind,'TimezoneId')]")).getText();
									System.out.println("ZoneID of is==" + ZOneID);
									if (ZOneID.equalsIgnoreCase("EDT")) {
										ZOneID = "America/New_York";
									} else if (ZOneID.equalsIgnoreCase("CDT")) {
										ZOneID = "CST";
									}
									WebElement PartPullTime = Driver.findElement(By.id("txtPartPullTime"));
									PartPullTime.clear();
									Date date = new Date();
									SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
									dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
									System.out.println(dateFormat.format(date));
									PartPullTime.sendKeys(dateFormat.format(date));

								} catch (Exception Time) {
									System.out.println("Time validation is not displayed-Time is as per timeZone");
								}

								// --Save button
								Driver.findElement(By.id("idiconsave")).click();
								System.out.println("Clicked on Save button");
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
								PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
								Driver.findElement(By.id("txtBasicSearch2")).clear();
								Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
								Driver.findElement(By.id("btnGXNLSearch2")).click();
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
								try {
									WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
									if (NoData.isDisplayed()) {
										System.out.println("Job is not moved to TENDER TO 3P stage successfully");
									}
								} catch (Exception e) {
									System.out.println("Job is moved to TENDER TO 3P stage successfully");

									// --Enter Drop Time
									String ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
									System.out.println("ZoneID of is==" + ZOneID);
									if (ZOneID.equalsIgnoreCase("EDT")) {
										ZOneID = "America/New_York";
									} else if (ZOneID.equalsIgnoreCase("CDT")) {
										ZOneID = "CST";
									}
									WebElement DropTime = Driver.findElement(By.id("txtActualDeliveryTme"));
									DropTime.clear();
									Date date = new Date();
									SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
									dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
									System.out.println(dateFormat.format(date));
									DropTime.sendKeys(dateFormat.format(date));

									// --Click on Tender To 3P
									Driver.findElement(By.id("btnsavedelivery")).click();
									System.out.println("Clicked on Tender to 3P");

									try {
										wait.until(ExpectedConditions
												.visibilityOfAllElementsLocatedBy(By.className("modal-content")));
										Driver.findElement(By.id("iddataok")).click();
										System.out.println("Click on OK of Dialogue box");

										wait.until(ExpectedConditions.invisibilityOfElementLocated(
												By.xpath("//*[@class=\"ajax-loadernew\"]")));

									} catch (Exception SPickup) {
										System.out.println("Dialogue is not present");
									}
									wait.until(ExpectedConditions
											.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
									PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
									Driver.findElement(By.id("txtBasicSearch2")).clear();
									Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
									Driver.findElement(By.id("btnGXNLSearch2")).click();
									wait.until(ExpectedConditions
											.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
									try {
										WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
										if (NoData.isDisplayed()) {
											System.out.println("Job is Delivered successfully");
										}
									} catch (Exception Deliver) {
										System.out.println("Job is not Delivered successfully");
									}

								}
							} catch (Exception errmsg) {
								System.out.println("Validation message is not displayed");
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
								PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
								Driver.findElement(By.id("txtBasicSearch2")).clear();
								Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
								Driver.findElement(By.id("btnGXNLSearch2")).click();
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
								try {
									WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
									if (NoData.isDisplayed()) {
										System.out.println("Job is not moved to TENDER TO 3P stage successfully");
									}
								} catch (Exception e) {
									System.out.println("Job is moved to TENDER TO 3P stage successfully");
								}
							}
						}

					} catch (Exception e) {
						System.out.println("Spoke with validation is not displayed");
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						Driver.findElement(By.id("txtBasicSearch2")).clear();
						Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
						Driver.findElement(By.id("btnGXNLSearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is not moved to Confirm Pull stage successfully");
							}
						} catch (Exception Data) {
							System.out.println("Job is moved to Confirm Pull successfully");
						}
					}

				} else if (Orderstage.contains("Pull Inventory and Apply Return Pack(s)")) {
					// --Pull Inventory and Apply Return Pack(s) stage
					String stage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
					System.out.println("stage=" + stage);
					getScreenshot(Driver, "PullInventoryandApplyReturnPack(s)_" + PUID);
					// --Label generation
					Driver.findElement(By.id("idiconprint")).click();
					System.out.println("Clicked on Label Generation");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					// Handle Label Generation window
					String WindowHandlebefore = Driver.getWindowHandle();
					for (String windHandle : Driver.getWindowHandles()) {
						Driver.switchTo().window(windHandle);
						System.out.println("Switched to Label generation window");
						Thread.sleep(5000);
						getScreenshot(Driver, "Labelgeneration" + PUID);
					}
					Driver.close();
					System.out.println("Closed Label generation window");

					Driver.switchTo().window(WindowHandlebefore);
					System.out.println("Switched to main window");

					// --Save button
					Driver.findElement(By.id("idiconsave")).click();
					System.out.println("Clicked on Save button");
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idPartPullDttmValidation")));
						String ValMsg = Driver.findElement(By.id("idPartPullDttmValidation")).getText();
						System.out.println("Validation Message=" + ValMsg);
						// --Part Pull Date
						WebElement PartPullDate = Driver.findElement(By.id("idtxtPartPullDate"));
						PartPullDate.clear();
						Date date = new Date();
						DateFormat dateFormat = new SimpleDateFormat("mm:dd:yy");
						PartPullDate.sendKeys(dateFormat.format(date));
						// --Part Pull Time
						String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'TimezoneId')]"))
								.getText();
						System.out.println("ZoneID of is==" + ZOneID);
						if (ZOneID.equalsIgnoreCase("EDT")) {
							ZOneID = "America/New_York";
						} else if (ZOneID.equalsIgnoreCase("CDT")) {
							ZOneID = "CST";
						}
						WebElement PartPullTime = Driver.findElement(By.id("txtPartPullTime"));
						PartPullTime.clear();
						date = new Date();
						dateFormat = new SimpleDateFormat("HH:mm");
						dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
						System.out.println(dateFormat.format(date));
						PartPullTime.sendKeys(dateFormat.format(date));

						// --Save button
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on Save button");
					} catch (Exception e) {
						System.out.println("Validation Message is not displayed");
					}

					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//label[contains(@class,'error-messages')]")));
						System.out.println("ErroMsg is Displayed="
								+ Driver.findElement(By.xpath("//label[contains(@class,'error-messages')]")).getText());
						Thread.sleep(2000);

						// --Get the serial NO
						String SerialNo = Driver.findElement(By.xpath("//*[@ng-bind=\"segment.SerialNo\"]")).getText();
						System.out.println("Serial No of Part is==" + SerialNo + "\n");
						// enter serial number in scan
						Driver.findElement(By.id("txtBarcode")).clear();
						Driver.findElement(By.id("txtBarcode")).sendKeys(SerialNo);
						Driver.findElement(By.id("txtBarcode")).sendKeys(Keys.TAB);
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						System.out.println("Entered serial No in scan barcode");

						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//label[contains(@class,'error-messages')]")));
							System.out.println("ErroMsg is Displayed=" + Driver
									.findElement(By.xpath("//label[contains(@class,'error-messages')]")).getText());
							Thread.sleep(2000);
							// --Part Pull Time
							String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'TimezoneId')]"))
									.getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement PartPullTime = Driver.findElement(By.id("txtPartPullTime"));
							PartPullTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							PartPullTime.sendKeys(dateFormat.format(date));

						} catch (Exception Time) {
							System.out.println("Time validation is not displayed-Time is as per timeZone");
						}

						// --Save button
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on Save button");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						Driver.findElement(By.id("txtBasicSearch2")).clear();
						Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
						Driver.findElement(By.id("btnGXNLSearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is not moved to TENDER TO 3P stage successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is moved to TENDER TO 3P stage successfully");

							// --Enter Drop Time
							String ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement DropTime = Driver.findElement(By.id("txtActualDeliveryTme"));
							DropTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							DropTime.sendKeys(dateFormat.format(date));

							// --Click on Tender To 3P
							Driver.findElement(By.id("btnsavedelivery")).click();
							System.out.println("Clicked on Tender to 3P");

							try {
								wait.until(ExpectedConditions
										.visibilityOfAllElementsLocatedBy(By.className("modal-content")));
								Driver.findElement(By.id("iddataok")).click();
								System.out.println("Click on OK of Dialogue box");

								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

							} catch (Exception SPickup) {
								System.out.println("Dialogue is not present");
							}
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
							PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
							Driver.findElement(By.id("txtBasicSearch2")).clear();
							Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
							Driver.findElement(By.id("btnGXNLSearch2")).click();
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							try {
								WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
								if (NoData.isDisplayed()) {
									System.out.println("Job is Delivered successfully");
								}
							} catch (Exception Deliver) {
								System.out.println("Job is not Delivered successfully");
							}

						}
					} catch (Exception errmsg) {
						System.out.println("Validation message is not displayed");
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
						PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
						Driver.findElement(By.id("txtBasicSearch2")).clear();
						Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
						Driver.findElement(By.id("btnGXNLSearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is not moved to TENDER TO 3P stage successfully");
							}
						} catch (Exception e1) {
							System.out.println("Job is moved to TENDER TO 3P stage successfully");
						}
					}
				} else if (Orderstage.contains("Tender to 3P")) {
					// --Enter Drop Time
					String ZOneID = Driver.findElement(By.id("lblactdltz")).getText();
					System.out.println("ZoneID of is==" + ZOneID);
					if (ZOneID.equalsIgnoreCase("EDT")) {
						ZOneID = "America/New_York";
					} else if (ZOneID.equalsIgnoreCase("CDT")) {
						ZOneID = "CST";
					}
					WebElement DropTime = Driver.findElement(By.id("txtActualDeliveryTme"));
					DropTime.clear();
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
					dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
					System.out.println(dateFormat.format(date));
					DropTime.sendKeys(dateFormat.format(date));

					// --Click on Tender To 3P
					Driver.findElement(By.id("btnsavedelivery")).click();
					System.out.println("Clicked on Tender to 3P");
					Thread.sleep(2000);

					try {
						wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("modal-content")));
						Driver.findElement(By.id("iddataok")).click();
						System.out.println("Click on OK of Dialogue box");

						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

					} catch (Exception SPickup) {
						System.out.println("Dialogue is not present");
					}
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
					PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
					Driver.findElement(By.id("txtBasicSearch2")).clear();
					Driver.findElement(By.id("txtBasicSearch2")).sendKeys(PUID);
					Driver.findElement(By.id("btnGXNLSearch2")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					try {
						WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
						if (NoData.isDisplayed()) {
							System.out.println("Job is Delivered successfully");
						}
					} catch (Exception Deliver) {
						System.out.println("Job is not Delivered successfully");
					}
				} else {
					System.out.println("unknown stage found for H3P service");
				}
			} else if (ServiceID.contains("RTE")) {
				// --Click on RTE tab
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-model=\"RTE\"]")));
				Driver.findElement(By.xpath("//*[@ng-model=\"RTE\"]")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("RTE")));
				getScreenshot(Driver, "RTETab");
				// --Search
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearchRTE")));
				Driver.findElement(By.id("txtBasicSearchRTE")).clear();
				Driver.findElement(By.id("txtBasicSearchRTE")).sendKeys(PUID);
				Driver.findElement(By.id("btnRTESearch2")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
				if (NoData.isDisplayed()) {
					System.out.println("Record is not available with search parameters");
				} else {
					System.out.println("Record is available with search parameters");
					// --click on record
					Driver.findElement(By.xpath("//*[@id=\"idRTEList\"]//span/strong")).click();
					System.out.println("Clicked on the record");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblRWID")));
					String Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
					System.out.println("Current stage of the order is=" + Orderstage);
					getScreenshot(Driver, Orderstage + PUID);

					if (Orderstage.contains("Confirm Alert")) {
						// --Confirm Alert stage
						// --Click on Accept
						Driver.findElement(By.id("idiconaccept")).click();
						System.out.println("Clicked on Accept");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

						// ---Pickup@Stop 1 of 2 stage
						// --Again click on Record
						Driver.findElement(By.xpath("//*[@id=\"idRTEList\"]//span/strong")).click();
						System.out.println("Clicked on the record");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblRWID")));
						Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);
						getScreenshot(Driver, Orderstage + PUID);
						// --Click on save
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on the Save");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
							String Errmsg = Driver.findElement(By.id("errorid")).getText();
							System.out.println("validation message=" + Errmsg);
							// --Enter Actual PickupTime
							String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'PUTimeZone')]"))
									.getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement ActPUTime = Driver.findElement(By.id("txtActPuTime"));
							ActPUTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							ActPUTime.sendKeys(dateFormat.format(date));
							// --Click on save
							Driver.findElement(By.id("idiconsave")).click();
							System.out.println("Clicked on the Save");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						} catch (Exception e) {
							System.out.println("validation message is not displayed");
						}

						// ---DEL@Stop 2 of 2 stage
						// --Again click on Record
						Driver.findElement(By.xpath("//*[@id=\"idRTEList\"]//span/strong")).click();
						System.out.println("Clicked on the record");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblRWID")));
						Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);
						getScreenshot(Driver, Orderstage + PUID);
						// --Click on save
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on the Save");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
							String Errmsg = Driver.findElement(By.id("errorid")).getText();
							System.out.println("validation message=" + Errmsg);
							// --Enter Actual DeliverTime
							String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'PUTimeZone')]"))
									.getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement ActDelTime = Driver.findElement(By.id("txtActDlTime"));
							ActDelTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							ActDelTime.sendKeys(dateFormat.format(date));
							// --Click on save
							Driver.findElement(By.id("idiconsave")).click();
							System.out.println("Clicked on the Save");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
								Errmsg = Driver.findElement(By.id("errorid")).getText();
								System.out.println("validation message=" + Errmsg);
								// --Enter Signature
								Driver.findElement(By.id("txtsign")).sendKeys("RV");
								System.out.println("Entered Signature");
								// --Click on save
								Driver.findElement(By.id("idiconsave")).click();
								System.out.println("Clicked on the Save");
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							} catch (Exception e) {
								System.out.println("validation message is not displayed");
							}
						} catch (Exception e) {
							System.out.println("validation message is not displayed");
						}
						// --Search the job
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearchRTE")));
						Driver.findElement(By.id("txtBasicSearchRTE")).clear();
						Driver.findElement(By.id("txtBasicSearchRTE")).sendKeys(PUID);
						Driver.findElement(By.id("btnRTESearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered");
							}
						} catch (Exception e) {
							System.out.println("Job is not delivered yet");

						}

					} else if (Orderstage.contains("Pickup@Stop 1 of 2")) {
						// ---Pickup@Stop 1 of 2 stage
						// --Again click on Record
						Driver.findElement(By.xpath("//*[@id=\"idRTEList\"]//span/strong")).click();
						System.out.println("Clicked on the record");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblRWID")));
						Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);
						getScreenshot(Driver, Orderstage + PUID);
						// --Click on save
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on the Save");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
							String Errmsg = Driver.findElement(By.id("errorid")).getText();
							System.out.println("validation message=" + Errmsg);
							// --Enter Actual PickupTime
							String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'PUTimeZone')]"))
									.getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement ActPUTime = Driver.findElement(By.id("txtActPuTime"));
							ActPUTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							ActPUTime.sendKeys(dateFormat.format(date));
							// --Click on save
							Driver.findElement(By.id("idiconsave")).click();
							System.out.println("Clicked on the Save");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						} catch (Exception e) {
							System.out.println("validation message is not displayed");
						}

						// ---DEL@Stop 2 of 2 stage
						// --Again click on Record
						Driver.findElement(By.xpath("//*[@id=\"idRTEList\"]//span/strong")).click();
						System.out.println("Clicked on the record");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblRWID")));
						Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);
						getScreenshot(Driver, Orderstage + PUID);
						// --Click on save
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on the Save");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
							String Errmsg = Driver.findElement(By.id("errorid")).getText();
							System.out.println("validation message=" + Errmsg);
							// --Enter Actual DeliverTime
							String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'PUTimeZone')]"))
									.getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement ActDelTime = Driver.findElement(By.id("txtActDlTime"));
							ActDelTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							ActDelTime.sendKeys(dateFormat.format(date));
							// --Click on save
							Driver.findElement(By.id("idiconsave")).click();
							System.out.println("Clicked on the Save");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
								Errmsg = Driver.findElement(By.id("errorid")).getText();
								System.out.println("validation message=" + Errmsg);
								// --Enter Signature
								Driver.findElement(By.id("txtsign")).sendKeys("RV");
								System.out.println("Entered Signature");
								// --Click on save
								Driver.findElement(By.id("idiconsave")).click();
								System.out.println("Clicked on the Save");
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							} catch (Exception e) {
								System.out.println("validation message is not displayed");
							}
						} catch (Exception e) {
							System.out.println("validation message is not displayed");
						}
						// --Search the job
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearchRTE")));
						Driver.findElement(By.id("txtBasicSearchRTE")).clear();
						Driver.findElement(By.id("txtBasicSearchRTE")).sendKeys(PUID);
						Driver.findElement(By.id("btnRTESearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered");
							}
						} catch (Exception e) {
							System.out.println("Job is not delivered yet");

						}

					} else if (Orderstage.contains("DEL@Stop 2 of 2")) {
						// ---DEL@Stop 2 of 2 stage
						// --Again click on Record
						Driver.findElement(By.xpath("//*[@id=\"idRTEList\"]//span/strong")).click();
						System.out.println("Clicked on the record");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblRWID")));
						Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
						System.out.println("Current stage of the order is=" + Orderstage);
						getScreenshot(Driver, Orderstage + PUID);
						// --Click on save
						Driver.findElement(By.id("idiconsave")).click();
						System.out.println("Clicked on the Save");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
							String Errmsg = Driver.findElement(By.id("errorid")).getText();
							System.out.println("validation message=" + Errmsg);
							// --Enter Actual DeliverTime
							String ZOneID = Driver.findElement(By.xpath("//span[contains(@ng-bind,'PUTimeZone')]"))
									.getText();
							System.out.println("ZoneID of is==" + ZOneID);
							if (ZOneID.equalsIgnoreCase("EDT")) {
								ZOneID = "America/New_York";
							} else if (ZOneID.equalsIgnoreCase("CDT")) {
								ZOneID = "CST";
							}
							WebElement ActDelTime = Driver.findElement(By.id("txtActDlTime"));
							ActDelTime.clear();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
							dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));
							System.out.println(dateFormat.format(date));
							ActDelTime.sendKeys(dateFormat.format(date));
							// --Click on save
							Driver.findElement(By.id("idiconsave")).click();
							System.out.println("Clicked on the Save");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
								Errmsg = Driver.findElement(By.id("errorid")).getText();
								System.out.println("validation message=" + Errmsg);
								// --Enter Signature
								Driver.findElement(By.id("txtsign")).sendKeys("RV");
								System.out.println("Entered Signature");
								// --Click on save
								Driver.findElement(By.id("idiconsave")).click();
								System.out.println("Clicked on the Save");
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
							} catch (Exception e) {
								System.out.println("validation message is not displayed");
							}
						} catch (Exception e) {
							System.out.println("validation message is not displayed");
						}
						// --Search the job
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearchRTE")));
						Driver.findElement(By.id("txtBasicSearchRTE")).clear();
						Driver.findElement(By.id("txtBasicSearchRTE")).sendKeys(PUID);
						Driver.findElement(By.id("btnRTESearch2")).click();
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						try {
							NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
							if (NoData.isDisplayed()) {
								System.out.println("Job is Delivered");
							}
						} catch (Exception e) {
							System.out.println("Job is not delivered yet");

						}

					} else {
						System.out.println("Unknown stage found");
					}

				}
			} else if (ServiceID.contains("Replenish")) {
				// --Click on Inventory tab
				Driver.findElement(By.id("inventory")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("WO")));
				getScreenshot(Driver, "InventoryTab");
				String TotalJob = Driver.findElement(By.xpath("//*[@ng-bind=\"TotalJob\"]")).getText();
				System.out.println("Total No of job in Inventory Tab is/are==" + TotalJob);
				// --Search
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
				Driver.findElement(By.id("txtBasicSearch")).clear();
				Driver.findElement(By.id("txtBasicSearch")).sendKeys(PUID);
				Driver.findElement(By.id("btnSearch2")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				try {
					WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
					if (NoData.isDisplayed()) {
						System.out.println("Record is not available with search parameters");
					}
				} catch (Exception NoData) {
					System.out.println("Record is available with search parameters");
					// --click on record
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idparttable")));
					String Orderstage = Driver.findElement(By.xpath("//h3[contains(@class,'panel-title')]")).getText();
					System.out.println("Current stage of the order is=" + Orderstage);
					getScreenshot(Driver, Orderstage + PUID);
					// --Click on Update
					Driver.findElement(By.id("idupdateicon")).click();
					System.out.println("Clicked on the Update");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					try {
						wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("fslavailableloclist")));
						System.out.println("Alert is displayed for create sub ASN");
						// --ALert
						// --Close the pop up
						Driver.findElement(By.id("btnCancel")).click();
						System.out.println("Click on Close PopUp");
					} catch (Exception e) {
						System.out.println("Alert is not displayed for create sub ASN");
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
						String ValMsg = Driver.findElement(By.id("errorid")).getText();
						System.out.println("Validation Msg==" + ValMsg);
					}
					// --Click on Save
					Driver.findElement(By.id("idsaveicon")).click();
					System.out.println("Clicked on the Save");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

					// --table
					WebElement parttable = Driver.findElement(By.xpath("//*[@id=\"parttable\"]/tbody"));
					List<WebElement> Partrow = parttable.findElements(By.tagName("tr"));
					System.out.println("total No of rows in part table are==" + Partrow.size());

					for (int part = 0; part < Partrow.size(); part++) {
						// --Find SerialNo column
						try {
							WebElement SerialNo = Partrow.get(part).findElement(By.id("txtSerialNo"));
							act.moveToElement(SerialNo).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(SerialNo));
							SerialNo.clear();
							SerialNo.sendKeys("SerialNo" + part);
							System.out.println("Enetered serial Number in " + part + " part");

							// --Enter Accepted Quantity
							WebElement AccQty = Partrow.get(part).findElement(By.id("txtReceivedQty"));
							act.moveToElement(AccQty).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(AccQty));
							AccQty.clear();
							AccQty.sendKeys("1");
							AccQty.sendKeys(Keys.TAB);
							System.out.println("Enetered Accepted Quantity in " + part + " part");
							// --Click on Save
							wait.until(ExpectedConditions.elementToBeClickable(By.id("idsaveicon")));
							WebElement Save = Driver.findElement(By.id("idsaveicon"));
							act.moveToElement(Save).click().perform();
							System.out.println("Clicked on the Save");
							wait.until(ExpectedConditions
									.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						} catch (Exception staleelement) {
							try {
								WebElement parttable1 = Driver.findElement(By.xpath("//*[@id=\"parttable\"]/tbody"));
								List<WebElement> Partrow1 = parttable1.findElements(By.tagName("tr"));
								System.out.println("total No of rows in part table are==" + Partrow.size());
								for (int partR = part; partR < Partrow1.size();) {
									WebElement SerialNo = Partrow1.get(partR).findElement(By.id("txtSerialNo"));
									act.moveToElement(SerialNo).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(SerialNo));
									SerialNo.clear();
									SerialNo.sendKeys("SerialNo" + part);
									System.out.println("Enetered serial Number in " + part + " part");

									// --Enter Accepted Quantity
									WebElement AccQty = Partrow1.get(partR).findElement(By.id("txtReceivedQty"));
									act.moveToElement(AccQty).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(AccQty));
									AccQty.clear();
									AccQty.sendKeys("1");
									AccQty.sendKeys(Keys.TAB);
									System.out.println("Enetered Accepted Quantity in " + part + " part");
									// --Click on Save
									wait.until(ExpectedConditions.elementToBeClickable(By.id("idsaveicon")));
									WebElement Save = Driver.findElement(By.id("idsaveicon"));
									act.moveToElement(Save).click().perform();
									System.out.println("Clicked on the Save");
									wait.until(ExpectedConditions
											.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
									break;
								}
							} catch (Exception StaleElement) {
								WebElement parttable1 = Driver.findElement(By.xpath("//*[@id=\"parttable\"]/tbody"));
								List<WebElement> Partrow1 = parttable1.findElements(By.tagName("tr"));
								System.out.println("total No of rows in part table are==" + Partrow.size());
								for (int partRw = part; partRw < Partrow1.size();) {
									WebElement SerialNo = Partrow1.get(partRw).findElement(By.id("txtSerialNo"));
									act.moveToElement(SerialNo).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(SerialNo));
									SerialNo.clear();
									SerialNo.sendKeys("SerialNo" + part);
									System.out.println("Entered serial Number in " + part + " part");

									// --Enter Accepted Quantity
									WebElement AccQty = Partrow1.get(partRw).findElement(By.id("txtReceivedQty"));
									act.moveToElement(AccQty).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(AccQty));
									AccQty.clear();
									AccQty.sendKeys("1");
									AccQty.sendKeys(Keys.TAB);
									System.out.println("Enetered Accepted Quantity in " + part + " part");
									// --Click on Save
									wait.until(ExpectedConditions.elementToBeClickable(By.id("idsaveicon")));
									WebElement Save = Driver.findElement(By.id("idsaveicon"));
									act.moveToElement(Save).click().perform();
									System.out.println("Clicked on the Save");
									wait.until(ExpectedConditions
											.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
									break;
								}
							}
						}
					}
					// --Click on Update

					wait.until(ExpectedConditions.elementToBeClickable(By.id("idupdateicon")));
					WebElement update = Driver.findElement(By.id("idupdateicon"));
					act.moveToElement(update).click().perform();
					System.out.println("Clicked on the update");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successid")));
						String SuccMsg = Driver.findElement(By.id("successid")).getText();
						System.out.println("Success Message==" + SuccMsg);

						// --Binless label
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idBinlessLabelGenerate")));
						Driver.findElement(By.id("idBinlessLabelGenerate")).click();
						System.out.println("Clicked on BinLess Label");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorid")));
						String ErrMsg = Driver.findElement(By.id("errorid")).getText();
						System.out.println("Error Message==" + ErrMsg);

						// --Print Label
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idLabelGenerate")));
						Driver.findElement(By.id("idLabelGenerate")).click();
						System.out.println("Clicked on Print Label");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
						// --Handle Print label window
						String WindowHandlebefore = Driver.getWindowHandle();
						for (String windHandle : Driver.getWindowHandles()) {
							Driver.switchTo().window(windHandle);
							System.out.println("Switched to Prit Label window");
							Thread.sleep(5000);
							getScreenshot(Driver, "PrintLabel_" + PUID);

						}
						Driver.close();
						System.out.println("Closed Print Label window");

						Driver.switchTo().window(WindowHandlebefore);
						System.out.println("Switched to main window");

						// --Close button
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idiconclose")));
						Driver.findElement(By.id("idiconclose")).click();
						System.out.println("Clicked on Close button");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

					} catch (Exception sMsg) {
						System.out.println(" Data is not Saved Successfully");
						// --Close button
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idiconclose")));
						Driver.findElement(By.id("idiconclose")).click();
						System.out.println("Clicked on Close button");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

					}
					// --Search
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
					Driver.findElement(By.id("txtBasicSearch")).clear();
					Driver.findElement(By.id("txtBasicSearch")).sendKeys(PUID);
					Driver.findElement(By.id("btnSearch2")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					try {
						WebElement NoData1 = Driver.findElement(By.className("dx-datagrid-nodata"));
						if (NoData1.isDisplayed()) {
							System.out.println("Record is not available with search parameters");
						}
					} catch (Exception Data) {
						System.out.println("Record is available with search parameters");
					}
				}

			} else {
				System.out.println("Unknown Service found");

			}
		}

	}

	public static boolean retryingFindClick(WebElement webElement) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				webElement.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public static void memo(String PID) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) Driver;

		Driver.findElement(By.id("hlkMemo")).click();
		System.out.println("Clicked on Memo");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		getScreenshot(Driver, "Memo_" + PID);

		// --Total no of existing memo
		String Memoheader = Driver.findElement(By.xpath("//*[contains(@class,'popupheadeing')]/strong")).getText();
		String NoOfMemo = Memoheader.split(" ")[1];

		System.out.println("Total no of memo is/are=" + NoOfMemo);

		// --Enter value in memo
		Driver.findElement(By.id("txtMemoNA")).sendKeys("Confirm Pu Alert stage from NetAgent");
		System.out.println("Entered value in memo");
		// --Save
		Driver.findElement(By.id("btnAgentMemoNA")).click();
		System.out.println("Clicked on Save button");

		// --Close
		WebElement memoClose = Driver.findElement(By.id("idanchorclose"));
		js.executeScript("arguments[0].click();", memoClose);
		System.out.println("Clicked on Close button of Memo");
		Thread.sleep(2000);
	}

	public static void notification(String PID) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) Driver;

		Driver.findElement(By.id("hlkNotification")).click();
		System.out.println("Clicked on Notification");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		getScreenshot(Driver, "Notification_" + PID);

		// --Close
		WebElement memoClose = Driver.findElement(By.id("idanchorclose"));
		js.executeScript("arguments[0].click();", memoClose);
		System.out.println("Clicked on Close button of Notification");
		Thread.sleep(2000);
	}

	public static void upload(String PID) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Driver.findElement(By.id("hlkUploadDocument")).click();
		System.out.println("Clicked on Upload");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		getScreenshot(Driver, "Upload_" + PID);

		// --Click on Plus sign
		Driver.findElement(By.id("hlkaddUpload")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtDocName")));
		// --Enter Doc name
		Driver.findElement(By.id("txtDocName")).sendKeys("AutoDocument");
		Driver.findElement(By.id("btnSelectFile")).click();
		Thread.sleep(2000);

		String Fpath = "C:\\Users\\rprajapati\\git\\NetAgent\\NetAgentProcess\\Job Upload Doc STG.xls";
		WebElement InFile = Driver.findElement(By.id("inputfile"));
		InFile.sendKeys(Fpath);
		Thread.sleep(2000);
		// --Click on Upload btn
		Driver.findElement(By.id("btnUpload")).click();
		Thread.sleep(2000);
		try {
			String ErrorMsg = Driver.findElement(By.xpath("ng-bind=\"RenameFileErrorMsg\"")).getText();
			if (ErrorMsg.contains("already exists.Your file was saved as")) {
				System.out.println("File already exist in the system");
			}
		} catch (Exception e) {
			System.out.println("File is uploaded successfully");
		}
		Driver.findElement(By.id("btnOk")).click();
		Thread.sleep(2000);

	}

	public static void map(String PID) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) Driver;

		Driver.findElement(By.id("hlkMap")).click();
		System.out.println("Clicked on Map");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Thread.sleep(5000);
		getScreenshot(Driver, "Map_" + PID);

		// --Close
		WebElement memoClose = Driver.findElement(By.id("idMapClose"));
		js.executeScript("arguments[0].click();", memoClose);
		System.out.println("Clicked on Close button of Map");
		Thread.sleep(2000);
	}

}
