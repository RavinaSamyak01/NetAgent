package NetAgentSmoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderProcessing extends BaseInit {

	public static void orderProcess()
			throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
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
		for (int col = 0; col < colNum; col++) {
			// --Search with PickUP ID
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

				}
			} catch (Exception e) {

				System.out.println("There is no job exist with the entered value");

			}
			// --Click on Close button
			Driver.findElement(By.id("idclosetab")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		}

		// --Advance Search
		Driver.findElement(By.id("AdvancedASNSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("AdvancesSearch")));

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
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("iddropdownmenuform")));
		// -Select All
		Driver.findElement(By.id("chkAllidddlnexttask")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllidddlnexttask")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}
		// --Click on Search
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}

		// Unselect All
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
		Driver.findElement(By.id("txtExpCompToDate1")).sendKeys("FromDate");

		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}
		// --Clear Expected From
		Driver.findElement(By.id("txtExpCompFromDate1")).clear();
		// --Clear expected To
		Driver.findElement(By.id("txtExpCompToDate1")).clear();

		// --Search by Customer
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

		}
		// --Search by PickUp
		Driver.findElement(By.id("txtPickup1")).sendKeys(formatter.formatCellValue(sh0.getRow(1).getCell(0)));
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
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
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("iddropdownmenuform")));
		// -Select All
		Driver.findElement(By.id("chkAllcmbASNType1")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllcmbASNType1")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}
		// --Click on Search
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}

		// Unselect All
		Driver.findElement(By.id("chkAllcmbASNType1")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllcmbASNType1")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}

		// --Search by Carrier
		Driver.findElement(By.id("cmbASNType1")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("iddropdownmenuform")));
		// -Select All
		Driver.findElement(By.id("chkAllcmbASNType1")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllcmbASNType1")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}
		// --Click on Search
		Driver.findElement(By.id("btnSearch1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");
		} else {
			System.out.println("Data is present related search parameter");
		}

		// Unselect All
		Driver.findElement(By.id("chkAllcmbASNType1")).click();
		Thread.sleep(2000);
		if (Driver.findElement(By.id("chkAllcmbASNType1")).isSelected()) {
			System.out.println("Select All checkbox is checked");
		} else {
			System.out.println("Select All checkbox is not checked");
		}
	}

}
