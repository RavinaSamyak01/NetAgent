package NetAgentSmoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderProcessing extends BaseInit {

	public static void orderProcess()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) Driver;
		// Actions act = new Actions(Driver);
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

		// --Basic Search

		for (int row1 = 1; row1 < rowNum; row1++) {
			// --Search with PickUP ID
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
			String PUID = formatter.formatCellValue(sh0.getRow(row1).getCell(1));
			String ServiceID = formatter.formatCellValue(sh0.getRow(row1).getCell(0));
			System.out.println("Service ID is==" + ServiceID);
			Driver.findElement(By.id("txtBasicSearch")).clear();
			Driver.findElement(By.id("txtBasicSearch")).sendKeys(PUID);
			Driver.findElement(By.id("btnSearch3")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			// try {
			WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
			if (PickuPBox.isDisplayed()) {
				System.out.println("Searched Job is displayed in edit mode");
				getScreenshot(Driver, "OrderEditor_" + PUID);

				// --Get current stage of the order
				String Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
				System.out.println("Current stage of the order is=" + Orderstage);

				// --Memo
				// memo(PUID);

				// -Notification
				// notification(PUID);

				// Upload
				// upload(PUID);

				// Map
				// map(PUID);

				Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();

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

				} else {
					System.out.println("Unknown stage found");
					Orderstage = Driver.findElement(By.xpath("//strong/span[@class=\"ng-binding\"]")).getText();
					System.out.println("Current stage of the order is=" + Orderstage);

				}
			}

			/*
			 * } catch (Exception e) { System.out.println("Job is not exist"); }
			 */
		}

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
