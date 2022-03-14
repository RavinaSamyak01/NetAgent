package NetAgentSmoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TaskLog extends BaseInit {

	public static void taskLog()
			throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) Driver;
		// Actions act = new Actions(Driver);
		File src0 = new File(".\\NA_STG.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("TaskLog");
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

		for (int col = 0; col < colNum; col++) { // --Search with PickUP ID
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch")));
			Driver.findElement(By.id("txtBasicSearch")).clear();
			Driver.findElement(By.id("txtBasicSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(1).getCell(col)));
			Driver.findElement(By.id("btnSearch3")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			try {
				WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
				if (PickuPBox.isDisplayed()) {
					System.out.println("Searched Job is displayed in edit mode");
					getScreenshot(Driver, "OrderEditor_" + col);
					// --Click on Close button
					Driver.findElement(By.id("idclosetab")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				}
			} catch (Exception e) {

				System.out.println("There is no job exist with the entered value");

			}
		}

		// --Advance Search
		Driver.findElement(By.id("AdvancedASNSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("AdvancesSearch")));
		getScreenshot(Driver, "AdvanceSearchTab");

		// --Search by Order Type
		for (int OType = 1; OType < 3; OType++) {
			Select Ordertype = new Select(Driver.findElement(By.id("cmbOrderType1")));
			Ordertype.selectByIndex(OType);
			Driver.findElement(By.id("btnSearch1")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			try {
				WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
				if (PickuPBox.isDisplayed()) {
					System.out.println("Searched Job is displayed in edit mode");
					// --Click on Close button
					Driver.findElement(By.id("idclosetab")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					// --Go to Advance Tab
					Driver.findElement(By.id("AdvancedASNSearch")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("AdvancesSearch")));
				}
			} catch (Exception e) {
				System.out.println("Data is not exist related search parameters");

			}

		}
		// --Search by Next Task
		Driver.findElement(By.id("idddlnexttask")).click();
		Thread.sleep(2000);

		// -Select All
		Driver.findElement(By.id("chkAllidddlnexttask")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllidddlnexttask")).isSelected())

		{
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		} // --Click on Search
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}
		Thread.sleep(2000);

		// Unselect All
		Driver.findElement(By.id("idddlnexttask")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllidddlnexttask")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllidddlnexttask")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}

		// --Search by Service
		Driver.findElement(By.id("txtServiceId1")).sendKeys("LOC");
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}

		// --Search by Expected From and Expected To
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdt = new Date();
		System.out.println(frmdt);
		Date frmdt1 = addDays(frmdt, -20);
		System.out.println(frmdt1);
		String FromDate = dateFormat.format(frmdt1);
		System.out.println(FromDate);

		// --Expected From
		Driver.findElement(By.id("txtExpCompFromDate1")).sendKeys(FromDate);
		// --expected To
		Driver.findElement(By.id("txtExpCompToDate1")).sendKeys(FromDate);
		Driver.findElement(By.id("txtServiceId1")).click();
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		} // --Clear Expected From
		Driver.findElement(By.id("txtExpCompFromDate1")).clear();
		// --Clear expectedTo
		Driver.findElement(By.id("txtExpCompToDate1")).clear();
		Driver.findElement(By.id("txtServiceId1")).click();

		// --Search by Customer

		Driver.findElement(By.id("txtCustCode1")).click();
		Driver.findElement(By.id("txtCustCode1")).sendKeys("950654");
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		try {
			WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
			if (PickuPBox.isDisplayed()) {
				System.out.println("Searched Job is displayed in edit mode");
				// --Click on Close button
				Driver.findElement(By.id("idclosetab")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				// --Go to Advance Tab
				Driver.findElement(By.id("AdvancedASNSearch")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("AdvancesSearch")));
			}
		} catch (Exception e) {
			System.out.println("Data is not exist related search parameters");

		} // --Search by PickUp
		Driver.findElement(By.id("txtPickup1")).sendKeys(formatter.formatCellValue(sh0.getRow(1).getCell(0)));
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		try {
			WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
			if (PickuPBox.isDisplayed()) {
				System.out.println("Searched Job is displayed in edit mode");
				// --Click on Close button
				Driver.findElement(By.id("idclosetab")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				// --Go to Advance Tab
				Driver.findElement(By.id("AdvancedASNSearch")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("AdvancesSearch")));
			}
		} catch (Exception e) {
			System.out.println("Data is not exist related search parameters");

		}

		// --Search by ASN Type
		Driver.findElement(By.id("cmbASNType1")).click();
		Thread.sleep(2000);
		// -Select All
		Driver.findElement(By.id("chkAllcmbASNType1")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllcmbASNType1")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		} // --Click on Search
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}

		// Unselect All
		Driver.findElement(By.id("cmbASNType1")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllcmbASNType1")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllcmbASNType1")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}

		/*
		 * // --Search by Carrier
		 * wait.until(ExpectedConditions.elementToBeClickable(By.id("cmbCarrier1")));
		 * Driver.findElement(By.id("cmbCarrier1")).click(); Thread.sleep(2000); //
		 * -Select All Driver.findElement(By.id("chkAllcmbCarrier1")).click();
		 * Thread.sleep(2000); if
		 * (Driver.findElement(By.id("chkAllcmbCarrier1")).isSelected()) {
		 * System.out.println("Select All checkbox is checked"); } else {
		 * System.out.println("Select All checkbox is not checked"); } // --Click on
		 * Search Driver.findElement(By.id("btnSearch1")).click();
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 * 
		 * NoData = Driver.findElement(By.className("dx-datagrid-nodata")); if
		 * (NoData.isDisplayed()) {
		 * System.out.println("Data is not present related search parameter"); } else {
		 * System.out.println("Data is present related search parameter"); }
		 * 
		 * // Unselect All Driver.findElement(By.id("cmbCarrier1")).click();
		 * Thread.sleep(2000); Driver.findElement(By.id("chkAllcmbCarrier1")).click();
		 * Thread.sleep(2000); if
		 * (Driver.findElement(By.id("chkAllcmbCarrier1")).isSelected()) {
		 * System.out.println("Select All checkbox is checked"); } else {
		 * System.out.println("Select All checkbox is not checked"); }
		 */

		// --Search by Reference

		Driver.findElement(By.id("txtReference1")).sendKeys(formatter.formatCellValue(sh0.getRow(1).getCell(4)));
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		try {
			WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
			if (PickuPBox.isDisplayed()) {
				System.out.println("Searched Job is displayed in edit mode");
				// --Click on Close button
				Driver.findElement(By.id("idclosetab")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				// --Go to Advance Tab
				Driver.findElement(By.id("AdvancedASNSearch")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("AdvancesSearch")));
			}
		} catch (Exception e) {
			System.out.println("Data is not exist related search parameters");

		}

		// --Clear Reference
		Driver.findElement(By.id("txtReference1")).clear();
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		String TotalJob = Driver.findElement(By.xpath("//*[@ng-bind=\"TotalJob\"]")).getText();
		System.out.println("Total No of job is/are==" + TotalJob);

		// --Quarantine Window
		Driver.findElement(By.linkText("Quarantine")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idAddQuarantinePopup")));
		getScreenshot(Driver, "QuarantineWindow");

		// --FSL Default selection
		// boolean FSL = Driver.findElement(By.id("cmbFSL")).isSelected(); //
		// Assert.assertTrue(FSL);
		// --Account
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cmbAccount\"]")));
		WebElement ACC = Driver.findElement(By.xpath("//*[@id=\"cmbAccount\"]"));
		js.executeScript("arguments[0].click();", ACC);
		Select Account = new Select(Driver.findElement(By.xpath("//*[@id=\"cmbAccount\"]")));
		Thread.sleep(2000);
		Account.selectByVisibleText("AUTOMATION - SPRINT #950656");
		Thread.sleep(5000); // --Sprint Part Number
		Driver.findElement(By.id("UnExpField1")).sendKeys("123");
		// --IMN
		Driver.findElement(By.id("UnExpField2")).sendKeys("123");
		// --MFG Part Number
		Driver.findElement(By.id("UnExpField3")).sendKeys("123");
		// --SP Number
		Driver.findElement(By.id("UnExpField4")).sendKeys("123");
		// --MFG Serial
		Driver.findElement(By.id("UnExpSerialNo")).sendKeys("123");

		// --FSL Default selection

		/*
		 * boolean BLoc = Driver.findElement(By.id("cmbLocation")).isSelected();
		 * Assert.assertTrue(BLoc);
		 */

		// -Quantity
		// Driver.findElement(By.id("txtAddQuarantineQty")).sendKeys("");
		// --Category
		Select Category = new Select(Driver.findElement(By.id("cmbCategory")));
		Category.selectByVisibleText("(select)");
		// --Carrier
		Select Carrier = new Select(Driver.findElement(By.id("cmbCarrier")));
		Carrier.selectByVisibleText("NGL");
		// --Carrier Tracking
		Driver.findElement(By.id("txtTrackingNo")).sendKeys("123");
		// --Reference
		Driver.findElement(By.id("txtAsnRef")).sendKeys("RV123");
		// --Quarantine Reason
		Select QuarReas = new Select(Driver.findElement(By.id("cmbQuarantineReasons")));
		QuarReas.selectByVisibleText("Expected Parts Not Received");
		// --Notes
		Driver.findElement(By.id("txtNotes"))
				.sendKeys("Automation Testing:-This part is quarantine due to expected parts not Received");
		// --Upload files
		// --click on Upload files
		Driver.findElement(By.id("hlkUploadDocument")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"DocDetailsForm\"]")));
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

		// --Submit button
		Driver.findElement(By.id("btnAddToQuarantineDetails")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblSuccessMsg")));

		String ActSuccMsg = Driver.findElement(By.id("lblSuccessMsg")).getText();
		String ExpSucMsg = "Direct Quarantine is successfully created with";
		if (ActSuccMsg.contains(ExpSucMsg)) {
			System.out.println("Part is quarantine successfully");
		} else {
			System.out.println("Part is not quarantine");
		}

		getScreenshot(Driver, "PartQuarantined");

		// --Close button
		WebElement close = Driver.findElement(By.id("idanchorclose"));
		js.executeScript("arguments[0].click()", close);
		Thread.sleep(2000);

		// --Refresh
		Driver.findElement(By.id("refreshicon")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		getScreenshot(Driver, "AdvanceSearch_Refresh");

		// --MERGE RTE Tab

		Driver.findElement(By.id("btnMerge3")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ngdialog-message")));
		getScreenshot(Driver, "MergeRTETab");
		// --Get the Messages
		String Warning = Driver.findElement(By.xpath("//*[@ng-show=\"isWarningRTE\"]//div[contains(@class,'well-sm')]"))
				.getText();
		System.out.println("Warning==" + Warning);
		// --Get all the conditions for merge RTE
		List<WebElement> Conditons = Driver.findElements(By.xpath("//*[@ng-show=\"isWarningRTE\"]//ul"));
		for (int i = 0; i < Conditons.size(); i++) {
			String Condition = Conditons.get(i).getText();
			System.out.println(Condition);
		}
		// --Close the window

		WebElement MergeClose = Driver.findElement(By.className("ngdialog-close"));
		js.executeScript("arguments[0].click()", MergeClose);
		Thread.sleep(2000);

		// --Merge RTE with Selection of Job
		// --Search by Service
		Driver.findElement(By.id("txtServiceId1")).sendKeys("LOC");
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");

			// --Descending the 1st column
			Driver.findElement(By.xpath("//*[@aria-label=\"Task & Timing Column\"]")).click();
			Thread.sleep(2000);

			try {
				// --select 1st row
				Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//tr[1]//div")).click();
				String FstRow = Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//tr[1]//div"))
						.getAttribute("aria-checked");
				Thread.sleep(2000);
				Assert.assertEquals(FstRow, "true");
				Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//tr[2]//div")).click();
				String SndRow = Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//tr[2]//div"))
						.getAttribute("aria-checked");
				Thread.sleep(2000);
				Assert.assertEquals(SndRow, "true");
				// --Merge RTE
				Driver.findElement(By.id("btnMerge3")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ngdialog-message")));

				// --Merge Button
				// Driver.findElement(By.id("btnsave")).click();

				// Cancel button
				Driver.findElement(By.id("btnCancel")).click();
				Thread.sleep(2000);
			} catch (Exception e) {
				System.out.println("Warning message is displayed");
				MergeClose = Driver.findElement(By.className("ngdialog-close"));
				js.executeScript("arguments[0].click()", MergeClose);
				Thread.sleep(2000);
			}

		}

		// --Operation Tab
		Driver.findElement(By.id("operation")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("divTable")));

		getScreenshot(Driver, "OperationTab");
		// --Search
		for (int col = 0; col < colNum; col++) {
			// --Search
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearch2")));
			Driver.findElement(By.id("txtBasicSearch2")).clear();
			Driver.findElement(By.id("txtBasicSearch2"))
					.sendKeys(formatter.formatCellValue(sh0.getRow(1).getCell(col)));
			Driver.findElement(By.id("btnGXNLSearch2")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			try {
				WebElement PickuPBox = Driver.findElement(By.xpath("//*[contains(@class,'pickupbx')]"));
				if (PickuPBox.isDisplayed()) {
					System.out.println("Searched Job is displayed in edit mode");
					// --Click on Close button
					Driver.findElement(By.id("idclosetab")).click();
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				}
			} catch (Exception e) {
				System.out.println("There is no job exist with the entered value");
			}

		}

		// --Inventory Tab
		Driver.findElement(By.id("inventory")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("WO")));
		getScreenshot(Driver, "InventoryTab");
		TotalJob = Driver.findElement(By.xpath("//*[@ng-bind=\"TotalJob\"]")).getText();
		System.out.println("Total No of job in Inventory Tab is/are==" + TotalJob);

		// --Combined Tab
		Driver.findElement(By.id("combined")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("GXNLWOBOTH")));
		getScreenshot(Driver, "CombinedTab");
		TotalJob = Driver.findElement(By.xpath("//*[@ng-bind=\"TotalJob\"]")).getText();
		System.out.println("Total No of job in Combined Tab is/are==" + TotalJob);

		// --RTE Tab
		Driver.findElement(By.xpath("//*[@ng-model=\"RTE\"]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("RTE")));
		getScreenshot(Driver, "RTETab");
		TotalJob = Driver.findElement(By.xpath("//*[@ng-bind=\"TotalJob\"]")).getText();
		System.out.println("Total No of job in RTE Tab is/are==" + TotalJob);
		// --Search
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtBasicSearchRTE")));
		Driver.findElement(By.id("txtBasicSearchRTE")).clear();
		Driver.findElement(By.id("txtBasicSearchRTE")).sendKeys("");
		Driver.findElement(By.id("btnRTESearch2")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

	}

}
