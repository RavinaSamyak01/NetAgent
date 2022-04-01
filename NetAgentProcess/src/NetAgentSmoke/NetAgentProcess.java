package NetAgentSmoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestReportListener.class)

public class NetAgentProcess extends BaseInit {

	@Test
	public void Courier() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// --Go to Courier screen
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Operations")));
		Driver.findElement(By.partialLinkText("Operations")).click();

		Driver.findElement(By.linkText("Courier")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("panel-body")));

		String Courier = Driver.findElement(By.id("txtCourierId")).getAttribute("value");
		System.out.println(Courier);

		if (!Courier.equals(Agent)) {
			System.out.println("Status : Agent Id is wrong.");
		} else {
			System.out.println("Status : Agent Id is right.");
		}

		// Check all fields
		// Courier
		// 1. Id
		Boolean AgentId = Driver.findElement(By.id("txtCourierId")).isDisplayed();

		if (AgentId == false) {
			System.out.println("Status : AgentId field is not display.");
		} else {
			System.out.println("Status : AgentId field is display.");
		}

		// 2. Agent Code
		Boolean AgentCode = Driver.findElement(By.id("txtAgentcode")).isDisplayed();

		if (AgentCode == false) {
			System.out.println("Status : AgentCode field is not display.");
		} else {
			System.out.println("Status : AgentCode field is display.");
		}

		// 3. Vendor
		Boolean Vendor = Driver.findElement(By.id("txtVendorid")).isDisplayed();

		if (Vendor == false) {
			System.out.println("Status : Vendor field is not display.");
		} else {
			System.out.println("Status : Vendor field is display.");
		}

		// 4. Name
		Boolean Name = Driver.findElement(By.id("txtname")).isDisplayed();

		if (Name == false) {
			System.out.println("Status : Courier Name field is not display.");
		} else {
			System.out.println("Status : Courier Name field is display.");
		}

		// 5. Valid From
		Boolean ValidFrom = Driver.findElement(By.id("txtValidfrom")).isDisplayed();

		if (ValidFrom == false) {
			System.out.println("Status : Valid From  field is not display.");
		} else {
			System.out.println("Status : Valid From  field is display.");
		}

		// 6. Valid To
		Boolean ValidTo = Driver.findElement(By.id("txtValidto")).isDisplayed();

		if (ValidTo == false) {
			System.out.println("Status : Valid To field is not display.");
		} else {
			System.out.println("Status : Valid To field is display.");
		}

		// 7. Country
		Boolean Country = Driver.findElement(By.id("txtCountry")).isDisplayed();

		if (Country == false) {
			System.out.println("Status : Country field is not display.");
		} else {
			System.out.println("Status : Country field is display.");
		}

		// 8. Zip
		Boolean Zip = Driver.findElement(By.id("txtZipCode")).isDisplayed();

		if (Zip == false) {
			System.out.println("Status : Zipcode field is not display.");
		} else {
			System.out.println("Status : Zipcode field is display.");
		}

		// 9. City
		Boolean City = Driver.findElement(By.id("txtCity")).isDisplayed();

		if (City == false) {
			System.out.println("Status : City field is not display.");
		} else {
			System.out.println("Status : City field is display.");
		}

		// 10. State
		Boolean State = Driver.findElement(By.id("txtState")).isDisplayed();

		if (State == false) {
			System.out.println("Status : State field is not display.");
		} else {
			System.out.println("Status : State field is display.");
		}

		// 11. Address 1
		Boolean add1 = Driver.findElement(By.id("txtAddrLine1")).isDisplayed();

		if (add1 == false) {
			System.out.println("Status : Address Line 1 field is not display.");
		} else {
			System.out.println("Status : Address Line 1 field is display.");
		}

		// 12. Dept
		Boolean Dept = Driver.findElement(By.id("txtAddrLine2")).isDisplayed();

		if (Dept == false) {
			System.out.println("Status : Dept/Suite field is not display.");
		} else {
			System.out.println("Status : Dept/Suite field is display.");
		}

		// 13. Phone
		Boolean Phone = Driver.findElement(By.id("txtPhoneNum")).isDisplayed();

		if (Phone == false) {
			System.out.println("Status : Phone field is not display.");
		} else {
			System.out.println("Status : Phone field is display.");
		}

		Boolean PhoneExt = Driver.findElement(By.xpath(".//*[@name='txtExtensionNum']")).isDisplayed();

		if (PhoneExt == false) {
			System.out.println("Status : Phone Extension field is not display.");
		} else {
			System.out.println("Status : Phone Extension field is display.");
		}

		// 14. Fax
		Boolean Fax = Driver.findElement(By.id("txtFaxum")).isDisplayed();

		if (Fax == false) {
			System.out.println("Status : Fax field is not display.");
		} else {
			System.out.println("Status : Fax field is display.");
		}

		// 15. Email
		Boolean Email = Driver.findElement(By.id("txtEmailAddr")).isDisplayed();

		if (Email == false) {
			System.out.println("Status : Email field is not display.");
		} else {
			System.out.println("Status : Email field is display.");
		}

		// 16. AfterFname
		Boolean AfterFname = Driver.findElement(By.id("txtCourierAfterhrName")).isDisplayed();

		if (AfterFname == false) {
			System.out.println("Status : After Hr FName field is not display.");
		} else {
			System.out.println("Status : After Hr FName field is display.");
		}

		// 17. AfterLname
		Boolean AfterLname = Driver.findElement(By.id("txtCourierAfterhrLName")).isDisplayed();

		if (AfterLname == false) {
			System.out.println("Status : After Hr LName field is not display.");
		} else {
			System.out.println("Status : After Hr LName field is display.");
		}

		// 18. AfterPhone
		Boolean AfterPhone = Driver.findElement(By.id("txtCourierAfterhrPhone")).isDisplayed();

		if (AfterPhone == false) {
			System.out.println("Status : After Hr Phone field is not display.");
		} else {
			System.out.println("Status : After Hr Phone field is display.");
		}

		Boolean AfterPhoneExt = Driver.findElement(By.id("txtCourierAfterhrExt")).isDisplayed();

		if (AfterPhoneExt == false) {
			System.out.println("Status : After Hr Phone Extension field is not display.");
		} else {
			System.out.println("Status : After Hr Phone Extension field is display.");
		}

		// 19. EmrgFname
		Boolean EmrgFname = Driver.findElement(By.id("txtEmrgFname")).isDisplayed();

		if (EmrgFname == false) {
			System.out.println("Status : Emrg FName field is not display.");
		} else {
			System.out.println("Status : Emrg FName field is display.");
		}

		// 20. EmrgLname
		Boolean EmrgLname = Driver.findElement(By.id("txtEmrgLname")).isDisplayed();

		if (EmrgLname == false) {
			System.out.println("Status : Emrg LName field is not display.");
		} else {
			System.out.println("Status : Emrg LName field is display.");
		}

		// 21. EmrgPhone
		Boolean EmrgPhone = Driver.findElement(By.xpath(".//*[@name='txtCOurierEmergPhone']")).isDisplayed();

		if (EmrgPhone == false) {
			System.out.println("Status : Emrg Phone field is not display.");
		} else {
			System.out.println("Status : Emrg Phone field is display.");
		}

		Boolean EmrgPhoneExt = Driver.findElement(By.xpath(".//*[@name='txtCourierEmergExt']")).isDisplayed();

		if (EmrgPhoneExt == false) {
			System.out.println("Status : Emrg Phone Ext field is not display.");
		} else {
			System.out.println("Status : Emrg Phone Ext field is display.");
		}

		// 22. APAcct
		Boolean APAcct1 = Driver.findElement(By.xpath(".//*[@name='txtApAcctNo']")).isDisplayed();

		if (APAcct1 == false) {
			System.out.println("Status : A/P Acct No field is not display.");
		} else {
			System.out.println("Status : A/P Acct No field is display.");
		}

		Boolean APAcct2 = Driver.findElement(By.xpath(".//*[@name='txtApDeptNo']")).isDisplayed();

		if (APAcct2 == false) {
			System.out.println("Status : A/P Dept No field is not display.");
		} else {
			System.out.println("Status : A/P Dept No field is display.");
		}

		// 23. CashAcct
		Boolean CashAcct1 = Driver.findElement(By.xpath(".//*[@name='txtCashAcctNo']")).isDisplayed();

		if (CashAcct1 == false) {
			System.out.println("Status : Cash Acct No field is not display.");
		} else {
			System.out.println("Status : Cash Acct No field is display.");
		}

		Boolean CashAcct2 = Driver.findElement(By.xpath(".//*[@name='txtCashDeptNo']")).isDisplayed();

		if (CashAcct2 == false) {
			System.out.println("Status : Cash Dept No field is not display.");
		} else {
			System.out.println("Status : Cash Dept No field is display.");
		}

		// 24. ExpAcct
		Boolean ExpAcct1 = Driver.findElement(By.xpath(".//*[@name='txtExpAcctNo']")).isDisplayed();

		if (ExpAcct1 == false) {
			System.out.println("Status : Exp Acct No field is not display.");
		} else {
			System.out.println("Status : Exp Acct No field is display.");
		}

		Boolean ExpAcct2 = Driver.findElement(By.xpath(".//*[@name='txtExpDeptNo']")).isDisplayed();

		if (ExpAcct2 == false) {
			System.out.println("Status : Exp Dept No field is not display.");
		} else {
			System.out.println("Status : Exp Dept No field is display.");
		}

		// 25. Type
		Boolean Type = Driver.findElement(By.xpath(".//*[@name='txtCourierTypename']")).isDisplayed();

		if (Type == false) {
			System.out.println("Status : Type field is not display.");
		} else {
			System.out.println("Status : Type field is display.");
		}

		// 26. BillingPOC
		Boolean BillingPOC = Driver.findElement(By.id("txtBilligDoc")).isDisplayed();

		if (BillingPOC == false) {
			System.out.println("Status : Billing POC field is not display.");
		} else {
			System.out.println("Status : Billing POC field is display.");
		}

		// 27. OperationsPOC
		Boolean OperationsPOC = Driver.findElement(By.id("txtOperationsPOC")).isDisplayed();

		if (OperationsPOC == false) {
			System.out.println("Status : Operations POC field is not display.");
		} else {
			System.out.println("Status : Operations POC field is display.");
		}

		// 28. ManagementPOC
		Boolean MangPOC = Driver.findElement(By.id("txtManagementPOC")).isDisplayed();

		if (MangPOC == false) {
			System.out.println("Status : Management POC field is not display.");
		} else {
			System.out.println("Status : Management POC field is display.");
		}

		// 29. Compliance POC
		Boolean CompPOC = Driver.findElement(By.id("txtCompliancePOC")).isDisplayed();

		if (CompPOC == false) {
			System.out.println("Status : Compliance POC field is not display.");
		} else {
			System.out.println("Status : Compliance POC field is display.");
		}

		// 30. Driver Required
		Boolean DrvReq = Driver.findElement(By.id("chkIsDrvRequired")).isDisplayed();

		if (DrvReq == false) {
			System.out.println("Status : Driver Required check-box is not display.");
		} else {
			System.out.println("Status : Driver Required check-box is display.");
		}

		// Miscellaneous Information
		// 1. AlertType
		Boolean AlertType = Driver.findElement(By.id("txtDeptStop")).isDisplayed();

		if (AlertType == false) {
			System.out.println("Status : Alert Type field is not display.");
		} else {
			System.out.println("Status : Alert Type field is display.");
		}

		// 2. ReceivedOn1
		Boolean ReceivedOn1 = Driver.findElement(By.id("txtRcvdSrvAgrDttm")).isDisplayed();

		if (ReceivedOn1 == false) {
			System.out.println("Status : Received On 1 field is not display.");
		} else {
			System.out.println("Status : Received On 1 field is display.");
		}

		// 3. ReceivedOn2
		Boolean ReceivedOn2 = Driver.findElement(By.id("txtRcvdW9Dttm")).isDisplayed();

		if (ReceivedOn2 == false) {
			System.out.println("Status : Received On 2 field is not display.");
		} else {
			System.out.println("Status : Received On 2 field is display.");
		}

		// 4. GeneralLiability
		Boolean GenLiab = Driver.findElement(By.id("txtInsrGenExpDttm")).isDisplayed();

		if (GenLiab == false) {
			System.out.println("Status : General Liability field is not display.");
		} else {
			System.out.println("Status : General Liability field is display.");
		}

		// 5. Auto
		Boolean Auto = Driver.findElement(By.id("txtInsrAutoExpDttm")).isDisplayed();

		if (Auto == false) {
			System.out.println("Status : Auto field is not display.");
		} else {
			System.out.println("Status : Auto field is display.");
		}

		// 6. WareHouse
		Boolean WareHouse = Driver.findElement(By.id("txtInsrWhsExpDttm")).isDisplayed();

		if (WareHouse == false) {
			System.out.println("Status : WareHouse field is not display.");
		} else {
			System.out.println("Status : WareHouse field is display.");
		}

		// 7. Cargo
		Boolean Cargo = Driver.findElement(By.id("txtInsrCargExpDttm")).isDisplayed();

		if (Cargo == false) {
			System.out.println("Status : Cargo field is not display.");
		} else {
			System.out.println("Status : Cargo field is display.");
		}

		// 8. Worker Compensation
		Boolean WorkerCo = Driver.findElement(By.id("txtInsrWkCompExpDttm")).isDisplayed();

		if (WorkerCo == false) {
			System.out.println("Status : Worker Compensation field is not display.");
		} else {
			System.out.println("Status : Worker Compensation field is display.");
		}

		// 9. ReceivedOn3
		Boolean ReceivedOn3 = Driver.findElement(By.id("txtRcvdTSARosterDttm")).isDisplayed();

		if (ReceivedOn3 == false) {
			System.out.println("Status : Received On 3 field is not display.");
		} else {
			System.out.println("Status : Received On 3 field is display.");
		}

		// 10. ReceivedOn4
		Boolean ReceivedOn4 = Driver.findElement(By.id("txtRcvdAcknDttm")).isDisplayed();

		if (ReceivedOn4 == false) {
			System.out.println("Status : Received On 4 field is not display.");
		} else {
			System.out.println("Status : Received On 4 field is display.");
		}

		// 11. After Hr Start
		Boolean AftHrStrt = Driver.findElement(By.id("txtAfHrStartTime")).isDisplayed();

		if (AftHrStrt == false) {
			System.out.println("Status : After Hr Start field is not display.");
		} else {
			System.out.println("Status : After Hr Start field is display.");
		}

		// 12. After Hr End
		Boolean AftHrEnd = Driver.findElement(By.id("txtAfHrEndTime")).isDisplayed();

		if (AftHrEnd == false) {
			System.out.println("Status : After Hr End field is not display.");
		} else {
			System.out.println("Status : After Hr End field is display.");
		}

		// 13. Received Service Agreement
		Boolean RecSerAgr = Driver.findElement(By.id("chkRcvdSrvAgr")).isDisplayed();

		if (RecSerAgr == false) {
			System.out.println("Status : Received Service Agreement check-box is not display.");
		} else {
			System.out.println("Status : Received Service Agreement check-box is display.");
		}

		// 14. Received W9
		Boolean Rec9 = Driver.findElement(By.id("chkRcvdW9")).isDisplayed();

		if (Rec9 == false) {
			System.out.println("Status : Received W9 check-box is not display.");
		} else {
			System.out.println("Status : Received W9 check-box is display.");
		}

		// 15. Received Proof of Insurance
		Boolean RecPrfIns = Driver.findElement(By.id("chkRcvdProfInsr")).isDisplayed();

		if (RecPrfIns == false) {
			System.out.println("Status : Received Proof of Insurance check-box is not display.");
		} else {
			System.out.println("Status : Received Proof of Insurance check-box is display.");
		}

		// 16. Received TSA Roster
		Boolean RecTSARos = Driver.findElement(By.id("chkRcvdTSARoster")).isDisplayed();

		if (RecTSARos == false) {
			System.out.println("Status : Received TSA Roster check-box is not display.");
		} else {
			System.out.println("Status : Received TSA Roster check-box is display.");
		}

		// 17. Received TSA Ackn
		Boolean RecTSAAck = Driver.findElement(By.id("chkRcvdAckn")).isDisplayed();

		if (RecTSAAck == false) {
			System.out.println("Status : Received TSA Ackn check-box is not display.");
		} else {
			System.out.println("Status : Received TSA Ackn check-box is display.");
		}

		// Other Information
		// 1. Note
		Boolean Note = Driver.findElement(By.id("txtOpsnote")).isDisplayed();

		if (Note == false) {
			System.out.println("Status : Note field is not display.");
		} else {
			System.out.println("Status : Note field is display.");
		}

		// 2. Company Type
		Boolean CompType = Driver.findElement(By.id("txtCompanyTypename")).isDisplayed();

		if (CompType == false) {
			System.out.println("Status : Company Type field is not display.");
		} else {
			System.out.println("Status : Company Type field is display.");
		}

		// 3. Ship Lable Key
		Boolean ShipLableKey = Driver.findElement(By.id("txtShipLabelKey")).isDisplayed();

		if (ShipLableKey == false) {
			System.out.println("Status : Ship Lable Key field is not display.");
		} else {
			System.out.println("Status : Ship Lable Key field is display.");
		}

		// 4. FDX TSA
		Boolean FDXTSA = Driver.findElement(By.id("chkIsFdxTsa")).isDisplayed();

		if (FDXTSA == false) {
			System.out.println("Status : FDX TSA check-box is not display.");
		} else {
			System.out.println("Status : FDX TSA check-box is display.");
		}

		// 5. TSA Certified
		Boolean TSACert = Driver.findElement(By.id("chkIsTsaCert")).isDisplayed();

		if (TSACert == false) {
			System.out.println("Status : TSA Certified check-box is not display.");
		} else {
			System.out.println("Status : TSA Certified check-box is display.");
		}

		// 6. HAZ Certified
		Boolean HAZCert = Driver.findElement(By.id("chkIsHAZCertified")).isDisplayed();

		if (HAZCert == false) {
			System.out.println("Status : HAZ Certified check-box is not display.");
		} else {
			System.out.println("Status : HAZ Certified check-box is display.");
		}

		// 7. CCFS Trans Certified
		Boolean CCFS = Driver.findElement(By.id("chkCCSFTransCertified")).isDisplayed();

		if (CCFS == false) {
			System.out.println("Status : CCFS Trans Certified check-box is not display.");
		} else {
			System.out.println("Status : CCFS Trans Certified check-box is display.");
		}

		// 8. Elevated Risk Certified
		Boolean Elev = Driver.findElement(By.id("chkElevateRiskTrained")).isDisplayed();

		if (Elev == false) {
			System.out.println("Status : Elevated Risk Certified check-box is not display.");
		} else {
			System.out.println("Status : Elevated Risk Certified check-box is display.");
		}

		// 9. 3P Courier
		Boolean PC = Driver.findElement(By.id("chkIsCourier3p")).isDisplayed();

		if (PC == false) {
			System.out.println("Status : 3P Courier check-box is not display.");
		} else {
			System.out.println("Status : 3P Courier check-box is display.");
		}

		getScreenshot(Driver, "Courier");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	// --Updated By Ravina
	@Test
	public void OrderSearch() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idOperations")));
		Driver.findElement(By.id("idOperations")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idOrder")));
		Driver.findElement(By.id("idOrder")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ordersearch")));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idValidation")));
		String VMessages = Driver.findElement(By.id("idValidation")).getText();
		System.out.println("Validation message:-" + VMessages);

		Driver.findElement(By.id("btnReset")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtPickup")));
		Driver.findElement(By.id("txtPickup")).clear();
		String PickUpID = rb.getString("PickUpID");
		Driver.findElement(By.id("txtPickup")).sendKeys(PickUpID);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --need to ask below code is for what
		Driver.findElement(By.xpath("//*[@id=\"OrderSearchGD\"]/div/div[6]/span")).getText();

		String pageCount = Driver.findElement(By.xpath("//*[@class=\"dx-info\"]")).getText();
		System.out.println("No of Record found=" + pageCount);

		Driver.findElement(By.id("btnReset")).click();
		String JobID = rb.getString("JobID");
		Driver.findElement(By.id("txtJob")).clear();
		Driver.findElement(By.id("txtJob")).sendKeys(JobID);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --need to ask below code is for what
		Driver.findElement(By.xpath("//*[@id=\"OrderSearchGD\"]/div/div[6]/span")).getText();

		pageCount = Driver.findElement(By.xpath("//*[@class=\"dx-info\"]")).getText();
		System.out.println("No of Record found=" + pageCount);

		Driver.findElement(By.id("txtJob")).clear();

		// Search with pickup
		Driver.findElement(By.id("txtPickup")).clear();
		Driver.findElement(By.id("txtPickup")).sendKeys(PUId);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --Searching SPL Order
		String SPLPickUpID = rb.getString("SPLPickUpID");
		Driver.findElement(By.id("txtPickup")).clear();
		Driver.findElement(By.id("txtPickup")).sendKeys(SPLPickUpID);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --Handling new window- new window ID is not unique so we can not handle it

		try {
			// --Click on Print button
			Driver.findElement(By.linkText("Print")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
			// --Transfer to new window
			String winHandleBefore = Driver.getWindowHandle();
			System.out.println(winHandleBefore);

			for (String winHandle : Driver.getWindowHandles()) {
				Driver.switchTo().window(winHandle);
				Thread.sleep(2000);
				System.out.println(winHandle);

			}

			Driver.close();

			// Switch back to original browser (first window)
			Driver.switchTo().window(winHandleBefore);
			Thread.sleep(2000);

			String pagecntOS = "";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dx-info")));
			pagecntOS = Driver.findElement(By.className("dx-info")).getText();
			System.out.println(pagecntOS);
			Thread.sleep(2000);

			if (pagecntOS.contains("Page 1 of 1 (0 items)")) {
				System.out.println(
						"Status : Order Search is not Working after Search with Date Range.(May be there is no Order.)");
			} else {
				System.out.println("Status : Order Search is Working after Search with Date Range.");
			}

			// --Click on Upload button
			wait.until(ExpectedConditions.elementToBeClickable(By.id("hrfAct")));
			Driver.findElement(By.id("hrfAct")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			getScreenshot(Driver, "OrderSearch Document");

			// --Save&Close
			Driver.findElement(By.id("btnOk")).click();
		} catch (Exception e) {
			System.out.println("SPL order is not available");
		}

		// --Reset button
		Driver.findElement(By.id("btnReset")).click();

		// Search with job
		Driver.findElement(By.id("txtJob")).clear();
		Driver.findElement(By.id("txtJob")).sendKeys(JobId);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("btnReset")).click();

		// Search with date range
		// Enter from date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdt = new Date();
		System.out.println(frmdt);
		Date frmdt1 = addDays(frmdt, -20);
		System.out.println(frmdt1);
		String FromDate = dateFormat.format(frmdt1);
		System.out.println(FromDate);

		Driver.findElement(By.id("orderRangeFrom")).clear();
		Driver.findElement(By.id("orderRangeFrom")).sendKeys(FromDate);

		// Enter to date
		Date todt = new Date();
		String ToDate = dateFormat.format(todt);

		Driver.findElement(By.id("orderRangeTo")).clear();
		Driver.findElement(By.id("orderRangeTo")).sendKeys(ToDate);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dx-info")));
		String pagecntOS = Driver.findElement(By.className("dx-info")).getText();
		System.out.println(pagecntOS);

		if (pagecntOS.contains("Page 1 of 1 (0 items)")) {
			System.out.println(
					"Status : Order Search is not Working after Search with Date Range.(May be there is no Order.)");
		} else {
			System.out.println("Status : Order Search is Working after Search with Date Range.");
			// Click on column title for sorting
			// --storing all the columns of the table
			List<WebElement> Columns = Driver.findElements(By.xpath("//td[@role=\"columnheader\"]"));
			System.out.println("total No. of columns of the table is=" + Columns.size());

			// --Clicking on all the columns one by one for sorting
			for (int col = 0; col < Columns.size() - 4; col++) {
				WebElement Cols = Columns.get(col);
				String ColName = Columns.get(col).getAttribute("aria-label");
				System.out.println("column name is=" + ColName);

				// --Check the sorting value before sorting applied
				String ColSortBefore = Columns.get(col).getAttribute("aria-sort");
				System.out.println("Sorting for " + ColName + " is==" + ColSortBefore);

				// --Clicking on column
				Cols = Columns.get(col);
				wait.until(ExpectedConditions.elementToBeClickable(Cols));
				Cols.click();
				System.out.println("Clicked on column for sorting");

				// --Check the sorting value after sorting applied
				String ColSortAsc = Columns.get(col).getAttribute("aria-sort");
				System.out.println("after Sorting value of sort for " + ColName + " is==" + ColSortAsc);

				// --Checking sorting is applied or not
				if (ColSortAsc.equals(ColSortBefore)) {
					System.out.println("Sorting is not applied");
				} else {
					System.out.println("Sorting is applied and sorting is applied on " + ColSortAsc + " Order");
				}

				// --Clicking on column
				Columns.get(col).click();
				System.out.println("Clicked on column for sorting");

				// --Check the sorting value after sorting applied
				String ColSortDesc = Columns.get(col).getAttribute("aria-sort");
				System.out.println("after Sorting value of sort for " + ColName + " is==" + ColSortDesc);

				// --Checking sorting is applied on desc order or not
				if (ColSortDesc.equals("descending")) {
					System.out.println("Sorting is applied and sorting is applied on " + ColSortAsc + " Order");
				} else {
					System.out.println("Sorting is not applied");

				}
			}

		}

		// --Clicking on reset button
		Driver.findElement(By.id("btnReset")).click();

		// --Clicking on MNX logo for main screen
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void replenish() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// Go to replenish screen
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idOperations")));
		Driver.findElement(By.id("idOperations")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idReplenish")));
		Driver.findElement(By.id("idReplenish")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "Replenish");

		// Select Account number
		Select AccNo = new Select(Driver.findElement(By.id("ddlClient")));
		AccNo.selectByVisibleText(Client);
		Thread.sleep(2000);

		// add part
		wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkPartDtl")));
		Driver.findElement(By.id("lnkPartDtl")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Search with part# and name

		Driver.findElement(By.id("txtF1lable")).clear();
		Driver.findElement(By.id("txtF1lable")).sendKeys(Part1);

		Driver.findElement(By.id("txtPartName")).clear();
		Driver.findElement(By.id("txtPartName")).sendKeys(Part1Name);

		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select part
		Driver.findElement(By.xpath(".//*[@title='Add']")).click();
		Thread.sleep(2000);

		getScreenshot(Driver, "Replenish-Part Popup");

		// delete part
		Driver.findElement(By.xpath(".//*[@title='Delete']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select part
		Driver.findElement(By.xpath(".//*[@title='Add']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// save part
		Driver.findElement(By.xpath(".//*[@title='Save']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// click on + icon for expand

		Driver.findElement(By.id("lnkexpandlnkexpand_0")).click();

		// Click on add line

		Driver.findElement(By.xpath(".//*[@id='lnklinedtl_0']")).click();

		Driver.findElement(By.id("ReplanishQty_0")).clear();
		Driver.findElement(By.id("ReplanishQty_0")).sendKeys("2");

		// click on add location
		Driver.findElement(By.linkText("Add Location")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idlocationname")));

		Driver.findElement(By.id("idsavelocationprocess")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@compile=\"ErrorMsg\"]//li")));

		String Text = Driver.findElement(By.id("idValidation")).getText();
		System.out.println(Text);

		// --Cancel Add Location
		Driver.findElement(By.id("idAddLocationProcess")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("idlocationname")));

		// Click on expand and collapse
		Driver.findElement(By.xpath("//a[contains(.,'Collapse All')]")).click();
		Driver.findElement(By.xpath("//a[contains(.,'Expand All')]")).click();

		// Process Save for replenish
		Driver.findElement(By.id("hlksaveReplenish")).click();

		// Success message
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("success")));
		SuccMsgReplnsh = Driver.findElement(By.id("success")).getText();
		System.out.println(SuccMsgReplnsh);

		String winHandleBefore = Driver.getWindowHandle();

		// Open Print label
		Driver.findElement(By.id("idlabelgenerate")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
			Thread.sleep(2000);
			getScreenshot(Driver, "Replenish-Print Label");
		}

		Driver.close();

		// Switch back to original browser (first window)
		Driver.switchTo().window(winHandleBefore);
		Thread.sleep(2000);

		Driver.findElement(By.id("txtOrderNo")).click();

		WOID = Driver.findElement(By.id("txtOrderNo")).getAttribute("value");
		System.out.println("WorkOrder # " + WOID);
		Thread.sleep(2000);

		WOTP = Driver.findElement(By.id("txtOrderType")).getAttribute("value");
		System.out.println("WorkOrder Type : " + WOTP);
		Thread.sleep(2000);

		Driver.findElement(By.id("hlkCreateReplenish")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"scroll_div\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void CycleCount() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// Go to CycleCount screen
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idOperations")));
		Driver.findElement(By.id("idOperations")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idCycle")));
		Driver.findElement(By.id("idCycle")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Start Cycle

		// --if Action column is not empty for 1st row
		if (!Driver.findElements(By.xpath("//a[@class=\"dx-link\"]")).isEmpty()) {
			// System.out.println("Click on Start");
			// --Click on start button of first row
			Driver.findElement(By.xpath("//a[@class=\"dx-link\"]")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		}

		// --if Reset button is exist
		if (!Driver.findElements(By.xpath("//a[@class='dx-link' and text()='Reset']")).isEmpty()) {
			// System.out.println("Click on Reset");
			// --Click on Reset button
			Driver.findElement(By.xpath("//a[@class='dx-link' and text()='Reset']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			String CCWO = Driver.findElement(By.id("woid")).getText();
			System.out.println(CCWO);
			Thread.sleep(2000);
		}

		getScreenshot(Driver, "CycleCount");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	// --Already covered in Courier method
	/*
	 * @Test public void CourierInfo() throws Exception { WebDriverWait wait = new
	 * WebDriverWait(Driver, 50);
	 * 
	 * Thread.sleep(5000);
	 * 
	 * Driver.findElement(By.id("idOperations")).click();
	 * Driver.findElement(By.id("idCourier")).click(); Thread.sleep(5000);
	 * 
	 * File scrFile1 = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
	 * FileUtils.copyFile(scrFile1, new
	 * File("D:\\Automation\\NA_Screenshot\\CourierInfo.png"));
	 * 
	 * Driver.findElement(By.id("imgNGLLogo")).click();
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
	 * "//*[@class=\"ajax-loadernew\"]")));
	 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(
	 * "welcomecontent")));
	 * 
	 * }
	 */
	@Test
	public void Userprofile() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		// -- Go to admin
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idAdmin")));
		// WebElement Admin=Driver.findElement(By.id("idAdmin"));
		// act.moveToElement(Admin).build().perform();
		Driver.findElement(By.partialLinkText("Admin")).click();

		// --Click on UserProfile
		Driver.findElement(By.linkText("User Profile")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("panel-body")));
		Thread.sleep(2000);
		// Check all fields

		// --UserDetails
		// --1.Login ID
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtLoginId")));
		Boolean LognID = Driver.findElement(By.id("txtLoginId")).isDisplayed();

		if (LognID == false) {
			System.out.println("Error: Login ID field is not display");
		}

		// --2.Email
		Boolean Email = Driver.findElement(By.id("txtEmail")).isDisplayed();

		if (Email == false) {
			System.out.println("Error: Email field is not display");
		}
		// --3. Password
		Boolean paswd = Driver.findElement(By.id("txtPwd")).isDisplayed();

		if (paswd == false) {
			System.out.println("Error: Password field is not display");
		}

		// --4. Confirm Password
		Boolean Confpaswd = Driver.findElement(By.id("txtConfpassword")).isDisplayed();

		if (Confpaswd == false) {
			System.out.println("Error: Confirm Password field is not display");
		}

		// --UserContact

		// 5. Title
		Boolean Title = Driver.findElement(By.id("txtTitle")).isDisplayed();

		if (Title == false) {
			System.out.println("Error: Title  field is not display");
		}

		// 6. First Name
		Boolean fname = Driver.findElement(By.id("txtFirstname")).isDisplayed();

		if (fname == false) {
			System.out.println("Error: First Name field is not display");
		}

		// 7. Middle Name
		Boolean mname = Driver.findElement(By.id("txtMiddleName")).isDisplayed();

		if (mname == false) {
			System.out.println("Error: Middle Name field is not display");
		}

		// 8. Last Name
		Boolean lname = Driver.findElement(By.id("txtLastName")).isDisplayed();

		if (lname == false) {
			System.out.println("Error: Last Name field is not display");
		}

		// 9. Address Line 1
		Boolean Add1UP = Driver.findElement(By.id("txtAddrline1")).isDisplayed();

		if (Add1UP == false) {
			System.out.println("Error: Address Line 1 field is not display");
		}

		// 10. Dept/Suite
		Boolean deptUP = Driver.findElement(By.id("txtAddrline2")).isDisplayed();

		if (deptUP == false) {
			System.out.println("Error: Dept/Suite  field is not display");
		}

		// 11. CityUP
		Boolean CityUP = Driver.findElement(By.id("txtCity")).isDisplayed();

		if (CityUP == false) {
			System.out.println("Error: City field is not display");
		}

		// 12. StateUP
		Boolean StateUP = Driver.findElement(By.id("txtState")).isDisplayed();

		if (StateUP == false) {
			System.out.println("Error: State field is not display");
		}

		// 13. Zip/Postal Code
		Boolean zippost = Driver.findElement(By.id("txtZipcode")).isDisplayed();

		if (zippost == false) {
			System.out.println("Error: Zip/Postal Code field is not display");
		}

		// 14. Country
		Boolean Country = Driver.findElement(By.id("txtCountryId")).isDisplayed();

		if (Country == false) {
			System.out.println("Error: Country field is not display");
		}

		// 15. Main Phone
		Boolean MainPhone = Driver.findElement(By.id("txtUserMainphone")).isDisplayed();

		if (MainPhone == false) {
			System.out.println("Error: Main Phone field is not display");
		}

		// 16. Work Phone
		Boolean WorkPhone = Driver.findElement(By.id("txtUserworkphone")).isDisplayed();

		if (WorkPhone == false) {
			System.out.println("Error: Work Phone field is not display");
		}

		// 17. Cell Phone
		Boolean CellPhone = Driver.findElement(By.id("txtCallphone")).isDisplayed();

		if (CellPhone == false) {
			System.out.println("Error: Cell Phone field is not display");
		}

		// 18. Home Phone
		Boolean HomePhone = Driver.findElement(By.id("txtHomephone")).isDisplayed();

		if (HomePhone == false) {
			System.out.println("Error: Home Phone field is not display");
		}

		getScreenshot(Driver, "UserProfile");

		// --Click on MNX Logo
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void Userlist() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		// -- Go to admin
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idAdmin")));
		Driver.findElement(By.id("idAdmin")).click();

		// --UserList
		Driver.findElement(By.linkText("User List")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("UserlistSearchGD")));

		// search with first name
		Driver.findElement(By.id("txtFirstName")).clear();

		File src0 = new File(".\\NA_STG.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("Sheet1");
		// int rcount = sh0.getLastRowNum();

		DataFormatter formatter = new DataFormatter();

		Driver.findElement(By.id("txtFirstName")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(36)));

		// --Click on Search
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String FNexp = formatter.formatCellValue(sh0.getRow(2).getCell(36));
		String FNact = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'First Name')]"))
				.getText();
		System.out.println(FNexp);
		System.out.println(FNact);

		if (FNexp.contentEquals(FNact)) {
			System.out.println("First Name Search Compare is PASS");
		} else {
			System.out.println("First Name Search Compare is FAIL");
		}

		Driver.findElement(By.id("btnReset")).click();

		// search with last name
		Driver.findElement(By.id("txtLastName")).clear();
		Driver.findElement(By.id("txtLastName")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(37)));

		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String LNexp = Driver.findElement(By.id("txtLastName")).getText();
		String LNact = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Last Name')]"))
				.getText();
		System.out.println(LNexp);
		System.out.println(LNact);

		if (LNexp.contentEquals(LNact)) {
			System.out.println("Last Name Search Compare is PASS");
		} else {
			System.out.println("Last Name Search Compare is FAIL");
		}

		Driver.findElement(By.id("btnReset")).click();

		Driver.findElement(By.id("txtFirstName")).sendKeys("Test1234");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String s1 = Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-nodata\"]")).getText();
		System.out.println(s1);

		Driver.findElement(By.id("btnReset")).click();

		Driver.findElement(By.id("txtLastName")).sendKeys("Test1234");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String s2 = Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-nodata\"]")).getText();
		System.out.println(s2);

		Driver.findElement(By.id("btnReset")).click();

		Driver.findElement(By.id("txtLoginId")).sendKeys("Test1234");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String s3 = Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-nodata\"]")).getText();
		System.out.println(s3);

		Driver.findElement(By.id("btnReset")).click();

		// search with login id
		Driver.findElement(By.id("txtLoginId")).clear();
		Driver.findElement(By.id("txtLoginId")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(38)));

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearch")));
		Driver.findElement(By.id("btnSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String Loginidexp = Driver.findElement(By.id("txtLoginId")).getText();
		String Loginidact = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Login Id')]"))
				.getText();
		System.out.println(Loginidexp);
		System.out.println(Loginidact);

		if (Loginidexp.contentEquals(Loginidact)) {
			System.out.println("Login ID Search Compare is PASS");
		} else {
			System.out.println("Login ID Search Compare is FAIL");
		}

		// Click on Edit
		Driver.findElement(By.id("imgUserListEdit_1")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'well-sm')]")));

		// check all editable fields
		// 1. Login Type
		Boolean logintype = Driver.findElement(By.id("txtLoginType")).isEnabled();

		if (logintype == true) {
			throw new Error("Error: Login Type field is enable");
		}

		// 2. Reporting To
		Boolean RepoTo = Driver.findElement(By.id("txtReportingTo")).isEnabled();

		if (RepoTo == false) {
			throw new Error("Error: Reporting To field is disable");
		}

		// 3. Login ID
		Boolean loginid = Driver.findElement(By.id("txtLoginId")).isEnabled();

		if (loginid == true) {
			throw new Error("Error: Login ID field is enable");
		}

		// 4. Password
		Boolean pwd = Driver.findElement(By.id("txtPwd")).isEnabled();

		if (pwd == true) {
			throw new Error("Error: Password field is enable");
		}

		// 5. Confirm Password
		Boolean cnfpwd = Driver.findElement(By.id("txtConfPwd")).isEnabled();

		if (cnfpwd == true) {
			throw new Error("Error: Confirm Password field is enable");
		}

		// 6. First Name
		Boolean fname = Driver.findElement(By.id("txtFirstName")).isEnabled();

		if (fname == false) {
			throw new Error("Error: First Name field is disable");
		}

		// 7. Middle Name
		Boolean mname = Driver.findElement(By.id("txtMiddleName")).isEnabled();

		if (mname == false) {
			throw new Error("Error: Middle Name field is disable");
		}

		// 8. Last Name
		Boolean lname = Driver.findElement(By.id("txtLastName")).isEnabled();

		if (lname == false) {
			throw new Error("Error: Last Name field is disable");
		}

		// 9. Title
		Boolean title = Driver.findElement(By.id("txtTitle")).isEnabled();

		if (title == false) {
			throw new Error("Error: Title field is disable");
		}

		// 10. Portal Type
		Boolean porttype = Driver.findElement(By.id("txtPortalType")).isEnabled();

		if (porttype == true) {
			throw new Error("Error: Portal Type  field is enable");
		}

		// 11. Password Last Set
		Boolean pwdlastset = Driver.findElement(By.id("txtPwdLastSet")).isEnabled();

		if (pwdlastset == true) {
			throw new Error("Error: Password Last Set field is enable");
		}

		// 12. Valid From
		Boolean vfrom = Driver.findElement(By.id("txtvalidfrom")).isEnabled();

		if (vfrom == false) {
			throw new Error("Error: Valid From field is disable");
		}

		// 13. Valid To
		Boolean vto = Driver.findElement(By.id("txtValidto")).isEnabled();

		if (vto == false) {
			throw new Error("Error: Valid To field is disable");
		}

		// 14. Description
		Boolean desc = Driver.findElement(By.id("txtDescription")).isEnabled();

		if (desc == false) {
			throw new Error("Error: Description field is disable");
		}

		// User Contact grid
		// 1. Country
		Boolean asdasd = Driver.findElement(By.id("drpCountry")).isEnabled();

		if (asdasd == false) {
			throw new Error("Error: Country field is disable");
		}

		// 2. Zip/Postal Code
		Boolean Zip = Driver.findElement(By.id("txtZipCode")).isEnabled();

		if (Zip == false) {
			throw new Error("Error: Zip/Postal Code field is disable");
		}

		// 3. City
		Boolean City = Driver.findElement(By.id("txtCity")).isEnabled();

		if (City == false) {
			throw new Error("Error: City field is disable");
		}

		// 4. State
		Boolean State = Driver.findElement(By.id("txtState")).isEnabled();

		if (State == true) {
			throw new Error("Error: State field is enable");
		}

		// 5. Address Line 1
		Boolean add1 = Driver.findElement(By.id("txtAddr1")).isEnabled();

		if (add1 == false) {
			throw new Error("Error: Address Line 1 field is disable");
		}

		// 6. Dept/Suite
		Boolean Dept = Driver.findElement(By.id("txtDept")).isEnabled();

		if (Dept == false) {
			throw new Error("Error: Dept/Suite field is disable");
		}

		// 7. Main Phone
		Boolean mphone = Driver.findElement(By.id("txtMain")).isEnabled();

		if (mphone == false) {
			throw new Error("Error: Main Phone field is disable");
		}

		// 8. Main Phone ext
		Boolean mphoneext = Driver.findElement(By.id("txtExt")).isEnabled();

		if (mphoneext == false) {
			throw new Error("Error: Main Phone ext field is disable");
		}

		// 9. Fax
		Boolean Fax = Driver.findElement(By.id("txtFax")).isEnabled();

		if (Fax == false) {
			throw new Error("Error: Fax field is disable");
		}

		// 10. Email
		Boolean Email = Driver.findElement(By.id("txtEmail")).isEnabled();

		if (Email == false) {
			throw new Error("Error: Email field is disable");
		}

		// 11. Work Phone
		Boolean wphone = Driver.findElement(By.id("txtUserWorkphone")).isEnabled();

		if (wphone == false) {
			throw new Error("Error: Work Phone field is disable");
		}

		// 12. Work Phone Ext
		Boolean wphoneext = Driver.findElement(By.id("txtWorkphoneExt")).isEnabled();

		if (wphoneext == false) {
			throw new Error("Error: Work Phone Ext field is disable");
		}

		// 13. Call Phone
		Boolean cphone = Driver.findElement(By.id("txtCallphone")).isEnabled();

		if (cphone == false) {
			throw new Error("Error: Call Phone field is disable");
		}

		// 14. Home Phone
		Boolean hphone = Driver.findElement(By.id("txtHomephone")).isEnabled();

		if (hphone == false) {
			throw new Error("Error: Home Phone field is disable");
		}

		// 15. Web Address
		Boolean wadd = Driver.findElement(By.id("txtWebaddress")).isEnabled();

		if (wadd == false) {
			throw new Error("Error: Web Address field is disable");
		}

		// 16. Security Question
		Boolean sque = Driver.findElement(By.id("txtSecQue")).isEnabled();

		if (sque == false) {
			throw new Error("Error: Security Question field is disable");
		}

		// 17. Response
		// Driver.findElement(By.id("chkShowResponse")).click();
		// Thread.sleep(5000);

		// Boolean Response = Driver.findElement(By.id("txtSecAns")).isEnabled();

		// if(Response == false)
		// {
		// throw new Error("Error: Response field is disable");
		// }

		// Click on save
		// Driver.findElement(By.id("imgSaveUserMaster")).click();
		// Thread.sleep(5000);

		// add and delete user role
		JavascriptExecutor js = ((JavascriptExecutor) Driver);
		js.executeScript("window.scrollTo(2, document.body.scrollHeight);");

		// select all
		Driver.findElement(By.id("chkRoleItemsSelectAll")).click();

		// -Delete
		Driver.findElement(By.id("imgCancelIcon")).click();

		Driver.switchTo().alert();
		Driver.switchTo().alert().accept();

		Driver.findElement(By.id("imgSaveUserMaster")).click();

		getScreenshot(Driver, "UserList_Validation");

		String Message1 = Driver.findElement(By.id("idValidation")).getText();

		if (Message1.equals("Please assign atleast one Role to User.")) {
			Message1 = "*****Validation message is matched*****";
			System.out.println(Message1);
		}

		// --Assign Role
		Driver.findElement(By.id("imgPlusIcon")).click();
		js.executeScript("window.scrollTo(2, document.body.scrollHeight);");

		// --Select Role
		Driver.findElement(By.id("drpRole")).click();
		Thread.sleep(2000);
		Select Rolename = new Select(Driver.findElement(By.id("drpRole")));
		Rolename.selectByVisibleText("NETAGENT W/Inventory");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgSaveUserMaster")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("UserlistSearchGD")));

		// enter valid to for newly added
		/*
		 * DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); Date Todate = new
		 * Date(); String ValidToDate = dateFormat.format(Todate);
		 * 
		 * Driver.findElement(By.id("txtCalValidTo")).clear();
		 * Driver.findElement(By.id("txtCalValidTo")).sendKeys(ValidToDate);
		 * Thread.sleep(7000);
		 */

		// Select added record and click on delete
		/*
		 * Driver.findElement(By.xpath(
		 * "//*[@id=\"RoleDetailsTable\"]/tbody/tr[7]/td[1]/input")).click();
		 * Thread.sleep(7000);
		 * 
		 * Driver.findElement(By.id("imgCancelIcon")).click(); Thread.sleep(7000);
		 * 
		 * Driver.switchTo().alert(); Driver.switchTo().alert().accept();
		 * Thread.sleep(7000);
		 */

		getScreenshot(Driver, "UserList");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("imgNGLLogo")));
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void parts() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Inventory")));
		Driver.findElement(By.partialLinkText("Inventory")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Parts")));
		Driver.findElement(By.linkText("Parts")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		logger.info("Parts Screen: Parts screen opened.");
		System.out.println(Driver.getTitle());

		// parts
		WebElement clientprt = Driver.findElement(By.id("ddlClient"));
		Select optprt = new Select(clientprt);
		optprt.selectByVisibleText(Client);
		logger.info("Parts Screen: Client selected");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// -Search button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		logger.info("Parts Screen: Click on search button.");

		// Select Include Zero Qty
		Driver.findElement(By.id("IncludeZeroQty")).click();
		logger.info("Parts Screen: Tick on Include zero qty checkbox.");
		// --Search
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		logger.info("Parts Screen: Click on search button.");

		// DeSelect Include Zero Qty
		WebElement element1 = Driver.findElement(By.id("IncludeZeroQty"));
		Actions actions1 = new Actions(Driver);
		actions1.moveToElement(element1).click().build().perform();
		logger.info("Parts Screen: UnTick on Include zero qty checkbox.");
		// -search
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		logger.info("Parts Screen: Click on search button.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "Part");

		// partDetail

		// Click on Part# and Return

		File src0 = new File(".\\NA_STG.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("Sheet1");
		// int rcount = sh0.getLastRowNum();

		DataFormatter formatter = new DataFormatter();

		Driver.findElement(By.id("txtField1")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(22)));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Thread.sleep(2000);

		Driver.findElement(By.xpath("//*[@id='PartMasterGD']//tbody//a")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Thread.sleep(2000);

		logger.info("Parts Screen: Click on part and go to part details screen.");
		System.out.println(Driver.getTitle());

		getScreenshot(Driver, "Part_PartEditor");

		Driver.findElement(By.id("idreturntoitem")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		logger.info("Parts Screen: Return from part details screen.");
		Thread.sleep(2000);

		String exp1 = formatter.formatCellValue(sh0.getRow(2).getCell(22));
		System.out.println("Expected Sprint part Number is=" + exp1);
		String act1 = Driver.findElement(By.xpath("//*[@id='PartMasterGD']//tbody//a")).getText();
		System.out.println("Actual Sprint part Number is=" + act1);

		if (act1.contains(exp1)) {
			System.out.println("Field1 Search Compare is - PASS");
		}

		else {
			System.out.println("Field1 Search Compare is - FAIL");
		}

		// --Search with second field
		Driver.findElement(By.id("txtField1")).clear();
		Driver.findElement(By.id("txtField2")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(23)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("txtField2")).clear();

		// --Search with third field
		Driver.findElement(By.id("txtField3")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(24)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --Search with fourth field
		Driver.findElement(By.id("txtField3")).clear();
		Driver.findElement(By.id("txtField4")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(25)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// -Search with fifth field
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtField4")));
		Driver.findElement(By.id("txtField4")).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSrAliasName")));
		Driver.findElement(By.id("txtSrAliasName")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(26)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --Invalid text
		Driver.findElement(By.id("txtSrAliasName")).clear();

		// Search with invalid text
		Driver.findElement(By.id("txtField1")).sendKeys("Test123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("PartNoGrid")).getText();
		Driver.findElement(By.id("txtField1")).clear();

		Driver.findElement(By.id("txtField2")).sendKeys("Test123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("PartNoGrid")).getText();
		Driver.findElement(By.id("txtField2")).clear();

		Driver.findElement(By.id("txtField3")).sendKeys("Test123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("PartNoGrid")).getText();
		Driver.findElement(By.id("txtField3")).clear();

		Driver.findElement(By.id("txtField4")).sendKeys("Test123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("PartNoGrid")).getText();
		Driver.findElement(By.id("txtField4")).clear();

		Driver.findElement(By.id("txtSrAliasName")).sendKeys("Test123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("PartNoGrid")).getText();
		Driver.findElement(By.id("txtSrAliasName")).clear();

		// Select Bin and Search
		Driver.findElement(By.id("btn_cmbLocationBinclass=")).click();
		Thread.sleep(2000);
		logger.info("Parts Screen: User has clicked on Bin dropdown box.");

		Driver.findElement(By.xpath("//label[contains(.,'All')]")).click();
		Thread.sleep(2000);
		logger.info("Parts Screen: User has tick All checkbox.");

		Driver.findElement(By.id("btn_cmbLocationBinclass=")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		logger.info("Parts Screen: User has click on search button.");

		Driver.findElement(By.id("idResetbutton")).click();
		logger.info("Parts Screen: User has click Reset button.");

		WebElement clientstck = Driver.findElement(By.id("ddlClient"));
		Select optstck = new Select(clientstck);
		optstck.selectByVisibleText(Client);
		Thread.sleep(2000);

		// WebElement fslstck = Driver.findElement(By.id("ddlfsl"));
		// Select opt1stck = new Select(fslstck);
		// opt1stck.selectByVisibleText(FSLName);
		// Thread.sleep(10000);

		// Stock Details

		// Click on Stock
		Driver.findElement(By.id("txtField1")).clear();
		Driver.findElement(By.id("txtField1")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(22)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idsearchbutton")));
		Driver.findElement(By.id("idsearchbutton")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Thread.sleep(2000);
		String stock_partDetails = Driver.findElement(By.xpath(".//*[@id=\"PartMasterGD\"]//tr[1]/td[4]/a")).getText();
		String[] list = stock_partDetails.split(" ");
		String a = list[1].replaceAll("[^0-9]", "");
		String stock_partDetails1 = a;
		System.out.println("First : " + stock_partDetails1);

		// System.out.println("First :: "+stock_partDetails);

		Driver.findElement(By.xpath(".//*[@id=\"PartMasterGD\"]//tr[1]/td[4]/a")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String stock_Details = Driver.findElement(By.xpath("//div[@style=\"text-align:right\"]/strong")).getText();
		System.out.println("Second : " + stock_Details);

		if (stock_partDetails1.equals(stock_Details)) {
			System.out.println("Total Records Matched");
		} else {
			System.out.println("Total Records Not Matched");
		}

		logger.info("Parts Screen: Go to stock details screen.");
		System.out.println(Driver.getTitle());

		getScreenshot(Driver, "Part_StockDetails");

		// --Click on PrintLabel button
		Driver.findElement(By.linkText("Print Label")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		logger.info("Parts Screen: User has performed print label.");

		String winHandleBefore = Driver.getWindowHandle();
		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);

		Driver.close();
		Driver.switchTo().window(winHandleBefore);
		Thread.sleep(2000);

		// --Return to Item
		Driver.findElement(By.id("idreturntoitem")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("btnPrint")).click();
		Thread.sleep(10000);
		boolean plabel = Driver.findElement(By.id("PartNoGrid")).getText()
				.contains("Please select atleast one record.");

		if (plabel == true) {
			Driver.findElement(By.xpath("//td[@role=\"gridcell\"]//span[@class=\"dx-checkbox-icon\"]")).click();

			Driver.findElement(By.id("btnPrint")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			String winHandleBefore1 = Driver.getWindowHandle();
			for (String winHandle1 : Driver.getWindowHandles()) {
				Driver.switchTo().window(winHandle1);
			}

			Driver.close();
			Thread.sleep(2000);
			Driver.switchTo().window(winHandleBefore1);
			Thread.sleep(2000);

		}
		getScreenshot(Driver, "PartswithSelection");

		Driver.findElement(By.id("idResetbutton")).click();
		logger.info("Parts Screen: User has click reset button.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		logger.info("Parts Screen: Going to main screen.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void FSLStorage() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// --Click on Inventory
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idInventory")));
		Driver.findElement(By.id("idInventory")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-labelledby=\"idInventory\"]")));

		// --Click on FSL Storage
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idFSLStorage")));
		Driver.findElement(By.id("idFSLStorage")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		System.out.println(Driver.getTitle());

		// --Month dropdown
		Select monthname = new Select(Driver.findElement(By.id("ddlmonth")));
		String selectedComboValue = monthname.getFirstSelectedOption().getText();
		System.out.println("Default Current Month Displayed in Combo : " + selectedComboValue);

		WebElement month = Driver.findElement(By.id("ddlmonth"));
		Select opt = new Select(month);
		opt.selectByVisibleText("August");
		Driver.findElement(By.id("idSearchFsl")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Year dropdown
		Select yrname = new Select(Driver.findElement(By.id("ddlyear")));
		String selectedComboValue1 = yrname.getFirstSelectedOption().getText();
		System.out.println("Default Current Year Displayed in Combo : " + selectedComboValue1);

		getScreenshot(Driver, "FSLStorage");

		WebElement year = Driver.findElement(By.id("ddlyear"));
		Select opt1 = new Select(year);
		opt1.selectByVisibleText("2019");
		Driver.findElement(By.id("idSearchFsl")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --nothing to edit and save in the screen
		/*
		 * Driver.findElement(By.xpath(
		 * "//*[@id=\"gridFSLStorage\"]/div/div[6]/div/div[1]/div/table/tbody/tr[1]/td[3]"
		 * )) .click(); Driver.findElement( By.xpath(
		 * "//*[@id=\"gridFSLStorage\"]/div/div[6]/div/div[1]/div/table/tbody/tr[1]/td[3]/div/div/input"
		 * )) .clear(); Thread.sleep(5000); Driver.findElement(By.xpath(
		 * "//*[@id=\"gridFSLStorage\"]/div/div[6]/div/div[1]/div/table/tbody/tr[1]/td[3]"
		 * )) .click(); Driver.findElement( By.xpath(
		 * "//*[@id=\"gridFSLStorage\"]/div/div[6]/div/div[1]/div/table/tbody/tr[1]/td[3]/div/div/input"
		 * )) .sendKeys("21"); Thread.sleep(5000);
		 * Driver.findElement(By.id("idSaveFSlStorage")).click(); Thread.sleep(5000);
		 * 
		 * Driver.findElement(By.id("success")).getText(); Thread.sleep(5000);
		 */

		getScreenshot(Driver, "FSLStorage");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void FSLSetup() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idInventory")));
		Driver.findElement(By.id("idInventory")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-labelledby=\"idInventory\"]")));

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idFSLSetup")));
		Driver.findElement(By.id("idFSLSetup")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Location Code Search
		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys("DEFAULTBIN");
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Select DefaultBin and Try to edit
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hrfAct")));
		Driver.findElement(By.id("hrfAct")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Boolean locationcode = Driver.findElement(By.id("txtLocationCode")).isEnabled();

		if (locationcode == true) {
			throw new Error("Error: Location Code field is enable");
		}

		Driver.findElement(By.id("idiconsave")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idiconsave")));
		Driver.findElement(By.id("errorid")).getText();

		getScreenshot(Driver, "FSLSetup");

		// Driver.findElement(By.cssSelector(".btn.btn-primary.no-radius")).click();
		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Refresh
		Driver.findElement(By.id("hlkCancleContactsDtls")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Paging
		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys("1");
		Thread.sleep(10000);

		Driver.findElement(By.id("idbtnsearch")).click();
		Thread.sleep(10000);

		String pagecnt = Driver.findElement(By.xpath("//*[@class=\"dx-info\"]")).getText();
		System.out.println(pagecnt);

		if (pagecnt.contains("Page 1 of 1")) {
			Driver.findElement(By.id("idbtnreset")).click();
		} else {
			Driver.findElement(By.xpath(".//*[@aria-label='Page 2']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			Driver.findElement(By.xpath(".//*[@aria-label=' Next page']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			Driver.findElement(By.xpath(".//*[@aria-label='Previous page']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			Driver.findElement(By.id("idbtnreset")).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtFSLbinSearch")));
		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys("1");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys("Test1234");
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.xpath("//*[@id=\"gridFSLSetup\"]/div/div[6]/span")).getText();
		Driver.findElement(By.id("idbtnreset")).click();

		// Edit and Save

		File src0 = new File(".\\NA_STG.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("Sheet1");
		// int rcount = sh0.getLastRowNum();

		DataFormatter formatter = new DataFormatter();

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(27)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hrfAct")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("hrfAct")));
		Driver.findElement(By.id("hrfAct")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtLength")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Driver.findElement(By.id("txtLength")).sendKeys("10");

		Driver.findElement(By.id("txtWidth")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Driver.findElement(By.id("txtWidth")).sendKeys("2");

		Driver.findElement(By.id("txtHeight")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Driver.findElement(By.id("txtHeight")).sendKeys("5");

		Driver.findElement(By.id("hlkSaveASN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Manage FSL
		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys("DEFAULTBIN");
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Manage')]")));
		Driver.findElement(By.xpath("//a[contains(.,'Manage')]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Refresh
		Driver.findElement(By.id("idiconrefresh")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// basic search
		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(28)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String E1 = formatter.formatCellValue(sh0.getRow(2).getCell(28));
		String A1 = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Part')]"))
				.getText();
		System.out.println(E1);
		System.out.println(A1);

		if (A1.equals(E1)) {
			System.out.println("Search Compare PASS");
		} else {
			System.out.println("Search Compare FAIL");
		}

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(29)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String E2 = formatter.formatCellValue(sh0.getRow(2).getCell(29));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Part')]")));
		String A2 = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Part')]"))
				.getText();
		System.out.println(E2);
		System.out.println(A2);

		if (A2.contains(E2)) {
			System.out.println("Search Compare PASS");
		} else {
			System.out.println("Search Compare FAIL");
		}

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(30)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String E3 = formatter.formatCellValue(sh0.getRow(2).getCell(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Part')]")));
		Thread.sleep(2000);
		String A3 = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Part')]"))
				.getText();
		System.out.println(E3);
		System.out.println(A3);

		if (A3.contains(E3)) {
			System.out.println("Search Compare PASS");
		} else {
			System.out.println("Search Compare FAIL");
		}

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(31)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String E4 = formatter.formatCellValue(sh0.getRow(2).getCell(31));
		String A4 = Driver
				.findElement(By.xpath("//*[@class=\"dx-datagrid-content\"]//td[contains(@aria-label,'Part')]"))
				.getText();
		System.out.println(E4);
		System.out.println(A4);

		if (A4.contains(E4)) {
			System.out.println("Search Compare PASS");
		} else {
			System.out.println("Search Compare FAIL");
		}

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(32)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(33)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(34)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(35)));
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtFSLbinSearch")).sendKeys("Test1234");
		Driver.findElement(By.id("idbtnsearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.xpath("//*[@id=\"gridManageFSLSetup\"]/div/div[6]/span")).getText();

		Driver.findElement(By.id("idbtnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// click save
		Driver.findElement(By.id("hlkSaveASN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("idValidation")).getText();

		getScreenshot(Driver, "FSLSetup");

		Driver.findElement(
				By.xpath("//*[@id=\"gridManageFSLSetup\"]/div/div[6]/div/div[1]/div/table/tbody/tr[1]/td[1]/div/div"))
				.click();
		Thread.sleep(10000);

		// Select To Location
		Driver.findElement(By.id("ddlToLocation")).sendKeys("DEFAULTBIN");

		Driver.findElement(By.id("hlkSaveASN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("iderrorcloseicon")).getText();

		Driver.findElement(By.id("ddlToLocation")).clear();

		Driver.findElement(By.id("ddlToLocation")).sendKeys("LAX");
		Driver.findElement(By.id("idbtnAdd")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		System.out.println(Driver.findElement(By.id("idsuccesscloseicon")).getText());

		getScreenshot(Driver, "FSLSetup_Location add");

		Driver.findElement(By.id("idbtnAdd")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		System.out.println(Driver.findElement(By.id("errorid")).getText());

		// Click Back
		Driver.findElement(By.id("idBack")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Add New

		// Click on add
		Driver.findElement(By.id("hlkCreateASN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Random rand = new Random();
		int num1 = rand.nextInt(20);
		if (num1 == 0) {
			num1 = num1 + 2;
		}
		String num2 = Integer.toString(num1);
		LOCCode1 = LOCCode1 + num2;

		// fill information
		Driver.findElement(By.id("txtLocationCode")).clear();
		Driver.findElement(By.id("txtLocationCode")).sendKeys(LOCCode1);

		Driver.findElement(By.id("txtLength")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Driver.findElement(By.id("txtLength")).sendKeys("15.15");

		Driver.findElement(By.id("txtWidth")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Driver.findElement(By.id("txtWidth")).sendKeys("22.22");

		Driver.findElement(By.id("txtHeight")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Driver.findElement(By.id("txtHeight")).sendKeys("33.33");

		// click on save
		Driver.findElement(By.id("hlkSaveASN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "FSLSetup_Save");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void CycleCountBIN() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idInventory")));
		Driver.findElement(By.id("idInventory")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-labelledby=\"idInventory\"]")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cycle Count BIN")));
		Driver.findElement(By.linkText("Cycle Count BIN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Select Client from dropdown
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ddlClient")));
		Driver.findElement(By.id("ddlClient")).click();
		Select client = new Select(Driver.findElement(By.id("ddlClient")));
		client.selectByVisibleText("AUTOMATION - SPRINT #950656");

		// Select Bin name from dropdown list.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_cmbLocationBinclass=")));
		Driver.findElement(By.id("btn_cmbLocationBinclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("idcheckboxInput")).click();
		Driver.findElement(By.id("btn_cmbLocationBinclass=")).click();

		// Click on Start button
		Driver.findElement(By.id("btnstart")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		System.out.println((Driver.findElement(By.id("lblSuccessMsg")).getText()));

		getScreenshot(Driver, "CycleCountBIN");

		Driver.findElement(By.id("btnreset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void ASNLog() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);

		// --Inventory
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idInventory")));
		Driver.findElement(By.id("idInventory")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-labelledby=\"idInventory\"]")));

		// --ASN
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idASN")));
		Driver.findElement(By.id("idASN")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		File src0 = new File(".\\NA_STG.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("Sheet1");
		// int rcount = sh0.getLastRowNum();

		DataFormatter formatter = new DataFormatter();

		/*
		 * // Search with Tracking#
		 * Driver.findElement(By.id("txtTracking")).sendKeys(formatter.formatCellValue(
		 * sh0.getRow(2).getCell(19))); Thread.sleep(2000);
		 * Driver.findElement(By.id("idbtnRunSearch")).click();
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 * 
		 * // -Created ASN Doesnt have tracking ID
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 * System.out.println("Title of the screen is" + Driver.getTitle()); String
		 * Trackexp = formatter.formatCellValue(sh0.getRow(2).getCell(19)); String
		 * Trackact = Driver.findElement(By.
		 * xpath("//td[@role=\"gridcell\" and contains(@aria-label,'Tracking')]"))
		 * .getText(); System.out.println(Trackexp); System.out.println(Trackact);
		 * 
		 * if (Trackexp.equals(Trackact)) {
		 * System.out.println("Tracking Number search result is PASS"); } else {
		 * System.out.println("Tracking Number search result is FAIL"); } // Back to
		 * screen Driver.findElement(By.id("hlkBackToScreen")).click();
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 * Driver.findElement(By.id("txtTracking")).clear();
		 */

		// Search with ASN# and go to ASN Details screen
		Driver.findElement(By.id("txtASN")).sendKeys(formatter.formatCellValue(sh0.getRow(2).getCell(20)));
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		System.out.println("Title of the screen is" + Driver.getTitle());

		String ASNexp = formatter.formatCellValue(sh0.getRow(2).getCell(20));
		String ASNact = Driver.findElement(By.id("txtasnno")).getText();
		System.out.println(ASNexp);
		System.out.println(ASNact);

		if (ASNexp.equals(ASNact)) {
			System.out.println("ASN Number search result is PASS");
		} else {
			System.out.println("ASN Number search result is FAIL");
		}

		// Expand and Collapse in ASN details screen
		Driver.findElement(By.id("expandId")).click();
		Thread.sleep(2000);

		getScreenshot(Driver, "ASN Log Details");

		Driver.findElement(By.id("collapseId")).click();
		Thread.sleep(2000);

		// Go back to ASNLog screen
		Driver.findElement(By.id("hlkBackToScreen")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Driver.findElement(By.id("txtASN")).clear();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Search with ASN Type
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btn_cmbAsnTypeclass=")));
		Driver.findElement(By.id("btn_cmbAsnTypeclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllcmbAsnType")).click();
		Driver.findElement(By.id("btn_cmbAsnTypeclass=")).click();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btn_cmbAsnTypeclass=")));
		Driver.findElement(By.id("btn_cmbAsnTypeclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllcmbAsnType")).click();
		Driver.findElement(By.id("btn_cmbAsnTypeclass=")).click();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Search with ASN Status
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btn_cmbAsnStatusclass=")));
		Driver.findElement(By.id("btn_cmbAsnStatusclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllcmbAsnStatus")).click();
		Driver.findElement(By.id("btn_cmbAsnStatusclass=")).click();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btn_cmbAsnStatusclass=")));
		Driver.findElement(By.id("btn_cmbAsnStatusclass=")).click();
		Thread.sleep(3000);
		Driver.findElement(By.id("chkAllcmbAsnStatus")).click();
		Driver.findElement(By.id("btn_cmbAsnStatusclass=")).click();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Search with Carrier - Fedex
		/*
		 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(
		 * "btn_cmbAsnCarrierclass=")));
		 * Driver.findElement(By.id("btn_cmbAsnCarrierclass=")).click();
		 * Thread.sleep(2000); Driver.findElement(By.id("chkAllcmbAsnCarrier")).click();
		 * Driver.findElement(By.id("btn_cmbAsnCarrierclass=")).click();
		 * Driver.findElement(By.id("idbtnRunSearch")).click();
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 * 
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
		 * "hlkBackToScreen"))); Driver.findElement(By.id("hlkBackToScreen")).click();
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 * 
		 * Driver.findElement(By.id("btn_cmbAsnCarrierclass=")).click();
		 * Thread.sleep(2000); Driver.findElement(By.id("chkAllcmbAsnCarrier")).click();
		 * Thread.sleep(2000); Driver.findElement(By.id("chkAllcmbAsnCarrier")).click();
		 * Driver.findElement(By.id("btn_cmbAsnCarrierclass=")).click();
		 * Driver.findElement(By.id("idbtnRunSearch")).click();
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//*[@class=\"ajax-loadernew\"]")));
		 */

		// Search with Location
		Select Location = new Select(Driver.findElement(By.id("ddlFsl")));
		Location.selectByIndex(1);
		Thread.sleep(2000);
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String Locationexp = formatter.formatCellValue(sh0.getRow(2).getCell(39));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//td[@role=\"gridcell\" and contains(@aria-label,'Location')]")));
		String Locationact = Driver
				.findElement(By.xpath("//td[@role=\"gridcell\" and contains(@aria-label,'Location')]")).getText();
		System.out.println(Locationexp);
		System.out.println(Locationact);

		if (Locationexp.equals(Locationact)) {
			System.out.println("Location search result is PASS");
		} else {
			System.out.println("Location search result is FAIL");
		}

		Select Account = new Select(Driver.findElement(By.id("drpAccount")));
		Account.selectByIndex(1);
		Thread.sleep(2000);
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Select Location1 = new Select(Driver.findElement(By.id("ddlFsl")));
		Location1.selectByIndex(0);
		Thread.sleep(2000);
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// From date and To date selection
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -30);
		String ValiFrom = getDate(cal);
		System.out.println("Valid From Date :- " + ValiFrom);
		String ValiTo = getDate(cal);
		System.out.println("Valid To Date :- " + ValiTo);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtFromEstArrival")));
		Driver.findElement(By.id("txtFromEstArrival")).sendKeys(ValiFrom);
		Driver.findElement(By.id("txtToEstArrival")).sendKeys(ValiTo);

		Driver.findElement(By.id("txtASN")).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idbtnRunSearch")));
		WebElement RSearch = Driver.findElement(By.id("idbtnRunSearch"));
		act.moveToElement(RSearch).build().perform();
		RSearch.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		try {
			Driver.findElement(By.id("hlkBackToScreen")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		} catch (Exception e) {
			System.out.println("There is no data with enetered date");
		}
		Driver.findElement(By.id("txtFromEstArrival")).clear();
		Driver.findElement(By.id("txtToEstArrival")).clear();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("txtAsnFromDate")).sendKeys(ValiFrom);
		Driver.findElement(By.id("txtAsnToDate")).sendKeys(ValiTo);
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		try {
			Driver.findElement(By.id("hlkBackToScreen")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		} catch (Exception e) {
			System.out.println("There is no data with enetered date");
		}
		Driver.findElement(By.id("txtAsnFromDate")).clear();
		Driver.findElement(By.id("txtAsnToDate")).clear();
		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "ASNLog_1");

		Driver.findElement(By.id("txtTracking")).sendKeys("Test1234");
		Driver.findElement(By.id("idbtnRunSearch")).click();
		Driver.findElement(By.xpath("//*[@id=\"ASNLogGD\"]/div/div[6]/span")).getText();
		Thread.sleep(8000);
		Driver.findElement(By.id("txtTracking")).clear();
		Thread.sleep(3000);

		Driver.findElement(By.id("txtWorkOrder")).sendKeys("1234567890");
		Driver.findElement(By.id("idbtnRunSearch")).click();
		Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-nodata\"]")).getText();
		Driver.findElement(By.id("txtWorkOrder")).clear();

		Driver.findElement(By.id("txtASN")).sendKeys("1234567890");
		Driver.findElement(By.id("idbtnRunSearch")).click();
		Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-nodata\"]")).getText();
		Driver.findElement(By.id("txtASN")).clear();

		Driver.findElement(By.id("txtAsnRef")).sendKeys("Test1234");
		Driver.findElement(By.id("idbtnRunSearch")).click();
		Driver.findElement(By.xpath("//*[@class=\"dx-datagrid-nodata\"]")).getText();
		Driver.findElement(By.id("txtAsnRef")).clear();

		Driver.findElement(By.id("idbtnRunSearch")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Check paging
		pagination();

		// Click on ASN No.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hrfAct")));
		Driver.findElement(By.id("hrfAct")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Boolean ASNNo = Driver.findElement(By.id("txtasnno")).getAttribute("readonly").equals("");
		if (ASNNo == true) {
			System.out.println("ASNNo. field is Editable");
		} else {
			System.out.println("ASNNo. field is Non-editable");
		}

		Boolean ASNType = Driver.findElement(By.id("txtASNType")).getAttribute("readonly").equals("");
		if (ASNType == true) {
			System.out.println("ASNType field is Editable");
		} else {
			System.out.println("ASNType field is Non-editable");
		}

		Boolean AccountNum = Driver.findElement(By.id("txtacc")).getAttribute("readonly").equals("");
		if (AccountNum == true) {
			System.out.println("Account Number field is Editable");
		} else {
			System.out.println("Account Number field is Non-editable");
		}

		Boolean Locfield = Driver.findElement(By.id("txtfsl")).getAttribute("readonly").equals("");
		if (Locfield == true) {
			System.out.println("Location field is Editable");
		} else {
			System.out.println("Location field is Non-editable");
		}

		Boolean ASNRef = Driver.findElement(By.id("txtasnref")).getAttribute("readonly").equals("");
		if (ASNRef == true) {
			System.out.println("ASN Reference field is Editable");
		} else {
			System.out.println("ASN Reference field is Non-editable");
		}

		Boolean CarrierName = Driver.findElement(By.id("txtcarriername")).getAttribute("readonly").equals("");
		if (CarrierName == true) {
			System.out.println("Carrier Name field is Editable");
		} else {
			System.out.println("Carrier Name field is Non-editable");
		}

		Boolean TrackingNum = Driver.findElement(By.id("txttrackingno")).getAttribute("readonly").equals("");
		if (TrackingNum == true) {
			System.out.println("Tracking Number field is Editable");
		} else {
			System.out.println("Tracking Number field is Non-editable");
		}

		Boolean RefURL = Driver.findElement(By.id("txtreferenceurl")).getAttribute("readonly").equals("");
		if (RefURL == true) {
			System.out.println("RefURL field is Editable");
		} else {
			System.out.println("RefURL field is Non-editable");
		}

		Boolean Note = Driver.findElement(By.id("txtnotes")).getAttribute("readonly").equals("");
		if (Note == true) {
			System.out.println("Note field is Editable");
		} else {
			System.out.println("Note field is Non-editable");
		}

		Driver.findElement(By.id("hlkBackToScreen")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		WebElement nextpage = Driver.findElement(By.xpath("//*[@aria-label=\" Next page\"]"));
		act.moveToElement(nextpage).click().perform();
		System.out.println("clicked on next page");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String P1 = Driver.findElement(By.xpath("//*[@class=\"dx-info\"]")).getText();
		System.out.println(P1);

		WebElement prevpage = Driver.findElement(By.xpath("//*[@aria-label=\"Previous page\"]"));
		act.moveToElement(prevpage).click().perform();
		System.out.println("clicked on previous page");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String P2 = Driver.findElement(By.xpath("//*[@class=\"dx-info\"]")).getText();
		System.out.println(P2);

		// Export Action
		Driver.findElement(By.id("idbtnexport")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		Thread.sleep(5000);

		getScreenshot(Driver, "ASN Log_2");

		// Go to main screen
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	// --Not exist in new NetAgent app
	/*
	 * @Test public void NetlinkInv() throws Exception { WebDriverWait wait = new
	 * WebDriverWait(Driver, 50);
	 * 
	 * Thread.sleep(10000);
	 * Driver.findElement(By.partialLinkText("Inventory")).click();
	 * 
	 * Driver.findElement(By.linkText("Netlink Inventory")).click();
	 * Thread.sleep(10000);
	 * 
	 * Driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/a/img")).click();
	 * Thread.sleep(10000); }
	 */
	@Test
	public void AgentActivityReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idReports")));
		Driver.findElement(By.id("idReports")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idAgent")));
		Driver.findElement(By.id("idAgent")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select all agent
		Driver.findElement(By.id("btn_ddlCourierclass=")).click();
		//// button[contains(.,'Select')]
		Thread.sleep(2000);
		// --selected all courier
		Driver.findElement(By.id("chkAllddlCourier")).click();
		Driver.findElement(By.id("btn_ddlCourierclass=")).click();

		// from date
		DateFormat dateFormatAgAcRp = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdtAgAcRp = new Date();
		Date frmdt1AgAcRp = addDays(frmdtAgAcRp, -10);
		String FromDateAgAcRp = dateFormatAgAcRp.format(frmdt1AgAcRp);

		Driver.findElement(By.id("txtValidFrom")).clear();
		Driver.findElement(By.id("txtValidFrom")).sendKeys(FromDateAgAcRp);
		WebElement AgAcRpfdate = Driver.findElement(By.id("txtValidFrom"));
		AgAcRpfdate.sendKeys(Keys.TAB);

		// to date
		Date todtAgAcRp = new Date();
		String ToDateAgAcRp = dateFormatAgAcRp.format(todtAgAcRp);

		Driver.findElement(By.id("txtValidTo")).clear();
		Driver.findElement(By.id("txtValidTo")).sendKeys(ToDateAgAcRp);
		WebElement AgAcRptdate = Driver.findElement(By.id("txtValidTo"));
		AgAcRptdate.sendKeys(Keys.TAB);

		// click on view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean AvArRp = Driver.findElement(By.xpath("//iframe[@id=\"myIframe\"]")).isDisplayed();

		if (AvArRp == false) {
			throw new Error("Error: Agent Activity Report grid not display");
		}

		getScreenshot(Driver, "AgentActivityReport");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void AgentActivityChartReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idReports")));
		Driver.findElement(By.id("idReports")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agent Activity Chart")));
		Driver.findElement(By.linkText("Agent Activity Chart")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select all agent
		Driver.findElement(By.id("btn_ddlCourierclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllddlCourier")).click();
		Driver.findElement(By.id("btn_ddlCourierclass=")).click();

		// from date
		DateFormat dateFormatAgAcChrtRp = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdtAgAcChrtRp = new Date();
		Date frmdt1AgAcChrtRp = addDays(frmdtAgAcChrtRp, -10);
		String FromDateAgAcChrtRp = dateFormatAgAcChrtRp.format(frmdt1AgAcChrtRp);

		Driver.findElement(By.id("txtValidFrom")).clear();
		Driver.findElement(By.id("txtValidFrom")).sendKeys(FromDateAgAcChrtRp);
		WebElement AgAcChrtRpfdate = Driver.findElement(By.id("txtValidFrom"));
		AgAcChrtRpfdate.sendKeys(Keys.TAB);

		// to date
		Date todtAgAcChrtRp = new Date();
		String ToDateAgAcChrtRp = dateFormatAgAcChrtRp.format(todtAgAcChrtRp);

		Driver.findElement(By.id("txtValidTo")).clear();
		Driver.findElement(By.id("txtValidTo")).sendKeys(ToDateAgAcChrtRp);
		WebElement AgAcChrtRptdate = Driver.findElement(By.id("txtValidTo"));
		AgAcChrtRptdate.sendKeys(Keys.TAB);

		// click on view report

		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean AvArChrtRp = Driver.findElement(By.xpath("//iframe[@id=\"myIframe\"]")).isDisplayed();

		if (AvArChrtRp == false) {
			throw new Error("Error: Agent Activity Chart Report grid not display");
		}

		getScreenshot(Driver, "AgentActivityChartReport");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	// ---Not exist in NetAGent
	/*
	 * @Test public void RejectQty() throws Exception { WebDriverWait wait = new
	 * WebDriverWait(Driver, 50);
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(By.id("idReports")));
	 * Driver.findElement(By.id("idReports")).click();
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(By.id("Rejected Qty")));
	 * Driver.findElement(By.linkText("Rejected Qty")).click();
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
	 * "//*[@class=\"ajax-loadernew\"]")));
	 * 
	 * // Select Client Select clnt = new
	 * Select(Driver.findElement(By.id("ddlClient")));
	 * clnt.selectByVisibleText(Client); Thread.sleep(10000);
	 * 
	 * // Select FSL Select FSL = new Select(Driver.findElement(By.id("ddlfsl")));
	 * FSL.selectByVisibleText(FSLName); Thread.sleep(10000);
	 * 
	 * // view report
	 * 
	 * Driver.findElement(By.id("btnView")).click(); Thread.sleep(10000);
	 * 
	 * boolean RejectedQtyRp = Driver .findElement(By.xpath(
	 * "/html/body/div[2]/section/div[2]/div/div/div[2]/form/div[4]/iframe"))
	 * .isDisplayed();
	 * 
	 * if (RejectedQtyRp == false) { throw new
	 * Error("Error: Rejected Qty Report grid not display"); } Thread.sleep(10000);
	 * 
	 * // Reset Driver.findElement(By.id("btnReset")).click(); Thread.sleep(10000);
	 * 
	 * File scrFile2 = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
	 * FileUtils.copyFile(scrFile2, new
	 * File("D:\\Automation\\NA_Screenshot\\RejectQty.png"));
	 * 
	 * Driver.findElement(By.id("imgNGLLogo")).click();
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
	 * "//*[@class=\"ajax-loadernew\"]")));
	 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(
	 * "welcomecontent")));
	 * 
	 * }
	 */

	@Test
	public void AgentActivityDetailReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idReports")));
		Driver.findElement(By.id("idReports")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agent Activity Detail")));
		Driver.findElement(By.linkText("Agent Activity Detail")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --Select Agent
		Driver.findElement(By.id("btn_ddlCourierclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("chkAllddlCourier")).click();
		Driver.findElement(By.id("btn_ddlCourierclass=")).click();

		// from date
		DateFormat dateFormatAgAcRp = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdtAgAcRp = new Date();
		Date frmdt1AgAcRp = addDays(frmdtAgAcRp, -10);
		String FromDateAgAcRp = dateFormatAgAcRp.format(frmdt1AgAcRp);

		Driver.findElement(By.id("txtValidFrom")).clear();
		Driver.findElement(By.id("txtValidFrom")).sendKeys(FromDateAgAcRp);
		WebElement AgAcRpfdate = Driver.findElement(By.id("txtValidFrom"));
		AgAcRpfdate.sendKeys(Keys.TAB);

		// to date
		Date todtAgAcRp = new Date();
		String ToDateAgAcRp = dateFormatAgAcRp.format(todtAgAcRp);

		Driver.findElement(By.id("txtValidTo")).clear();
		Driver.findElement(By.id("txtValidTo")).sendKeys(ToDateAgAcRp);
		WebElement AgAcRptdate = Driver.findElement(By.id("txtValidTo"));
		AgAcRptdate.sendKeys(Keys.TAB);

		// click on view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean AvArRp = Driver.findElement(By.xpath("//iframe[@id=\"myIframe\"]")).isDisplayed();

		if (AvArRp == false) {
			throw new Error("Error: Agent Activity Report grid not display");
		}

		getScreenshot(Driver, "AgentActivityDetailReport");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void quarantineReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idReports")));
		Driver.findElement(By.id("idReports")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quarantine Report")));
		Driver.findElement(By.linkText("Quarantine Report")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "QuarantineReportScreen");

		// --client select
		Select client = new Select(Driver.findElement(By.id("ddlClient")));
		client.selectByVisibleText("AUTOMATION - SPRINT #950656");
		System.out.println("Client selected");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --FSL Name select
		Select fsl = new Select(Driver.findElement(By.id("ddlfsl")));
		fsl.selectByVisibleText("AUTOMATION-FSL(F5386)");
		System.out.println("FSL selected");

		// click on view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		getScreenshot(Driver, "QuarantineReport");

		boolean AvArRp = Driver.findElement(By.xpath("//iframe[@id=\"myIframe\"]")).isDisplayed();

		if (AvArRp == false) {
			throw new Error("Error: Agent Activity Report grid not display");
		}

		// --click on Reset button
		Driver.findElement(By.id("btnReset")).click();
		System.out.println("Clicked on Reset button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void ReceiptReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idReports")));

		try {
			WebElement Reports = Driver.findElement(By.id("idReports"));
			act.moveToElement(Reports).build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			WebElement Inventory = Driver.findElement(By.id("idReportInventory"));
			act.moveToElement(Inventory).build().perform();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idReceipt")).click();
		} catch (Exception e) {
			Driver.findElement(By.id("idReports")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			Driver.findElement(By.id("idReportInventory")).click();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idReceipt")).click();
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		/*
		 * 
		 * Driver.findElement(By.partialLinkText("Reports")).click();
		 * Thread.sleep(5000);
		 * Driver.findElement(By.xpath("//*[@id=\"idInventory\"]")).click();
		 * Thread.sleep(5000); Driver.findElement(By.id("idReceipt")).click();
		 * Thread.sleep(10000);
		 */
		// select FSL
		// Driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div[2]/form/div[2]/div[1]/div[1]/div/div/button")).click();

		// --Select FSL Name
		Driver.findElement(By.id("btn_ddlfslclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//div[@id=\"ddlfsl\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlfslclass=")).click();

		// select client
		// Driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div[2]/form/div[2]/div[1]/div[2]/div/div/button")).click();

		Driver.findElement(By.id("btn_ddlClientclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//div[@id=\"ddlClient\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlClientclass=")).click();

		// from date
		DateFormat dateFormatRpln = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdtRpln = new Date();
		Date frmdt1Rpln = addDays(frmdtRpln, -10);
		String FromDateRpln = dateFormatRpln.format(frmdt1Rpln);

		Driver.findElement(By.id("txtValidFrom")).click();
		Driver.findElement(By.id("txtValidFrom")).clear();
		Driver.findElement(By.id("txtValidFrom")).sendKeys(FromDateRpln);
		WebElement Rplnfdate = Driver.findElement(By.id("txtValidFrom"));
		Rplnfdate.sendKeys(Keys.TAB);

		// to date
		Date todtRpln = new Date();
		String ToDateRpln = dateFormatRpln.format(todtRpln);

		Driver.findElement(By.id("txtValidTo")).clear();
		Driver.findElement(By.id("txtValidTo")).sendKeys(ToDateRpln);
		WebElement Rplntdate = Driver.findElement(By.id("txtValidTo"));
		Rplntdate.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		// part name
		Driver.findElement(By.id("txtField1")).clear();
		Driver.findElement(By.id("txtField1")).sendKeys(Part2);

		// view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean Replnsh = Driver.findElement(By.xpath("//*[@id=\"myIframe\"]")).isDisplayed();

		if (Replnsh == false) {
			throw new Error("Error: Replenish Report grid not display");
		}

		getScreenshot(Driver, "ReceiptReport");

		// Reset
		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void PullReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);

		try {
			WebElement Reports = Driver.findElement(By.id("idReports"));
			act.moveToElement(Reports).build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			WebElement Inventory = Driver.findElement(By.id("idReportInventory"));
			act.moveToElement(Inventory).build().perform();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idPull")).click();
		} catch (Exception e) {
			Driver.findElement(By.id("idReports")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			Driver.findElement(By.id("idReportInventory")).click();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idPull")).click();
		}

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select FSL
		// Driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div[2]/form/div[2]/div[1]/div[1]/div/div/button")).click();

		Driver.findElement(By.id("btn_ddlfslclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"ddlfsl\"]//input[@id=\"idcheckboxInput\"]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_ddlfslclass=")));
		Driver.findElement(By.id("btn_ddlfslclass=")).click();

		// select client
		// Driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div[2]/form/div[2]/div[1]/div[2]/div/div/button")).click();

		Driver.findElement(By.id("btn_ddlClientclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"ddlClient\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlClientclass=")).click();

		// from date
		DateFormat dateFormatpull = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdtpull = new Date();
		Date frmdt1pull = addDays(frmdtpull, -10);
		String FromDatepull = dateFormatpull.format(frmdt1pull);

		Driver.findElement(By.id("txtValidFrom")).click();
		Driver.findElement(By.id("txtValidFrom")).clear();
		Driver.findElement(By.id("txtValidFrom")).sendKeys(FromDatepull);
		WebElement pullfdate = Driver.findElement(By.id("txtValidFrom"));
		pullfdate.sendKeys(Keys.TAB);

		// to date
		Date todtpull = new Date();
		String ToDatepull = dateFormatpull.format(todtpull);

		Driver.findElement(By.id("txtValidTo")).clear();
		Driver.findElement(By.id("txtValidTo")).sendKeys(ToDatepull);
		WebElement pulltdate = Driver.findElement(By.id("txtValidTo"));
		pulltdate.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		// part name
		Driver.findElement(By.id("txtField1")).clear();
		Driver.findElement(By.id("txtField1")).sendKeys(Part2);

		// view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean Replnsh = Driver.findElement(By.xpath("//*[@id=\"myIframe\"]")).isDisplayed();

		if (Replnsh == false) {
			throw new Error("Error: Pull Report grid not display");
		}

		getScreenshot(Driver, "PullReport");

		// Reset
		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void TransactionReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);

		try {
			WebElement Reports = Driver.findElement(By.id("idReports"));
			act.moveToElement(Reports).build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			WebElement Inventory = Driver.findElement(By.id("idReportInventory"));
			act.moveToElement(Inventory).build().perform();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idTransaction")).click();
		} catch (Exception e) {
			Driver.findElement(By.id("idReports")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			Driver.findElement(By.id("idReportInventory")).click();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idTransaction")).click();
		}

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select FSL
		Driver.findElement(By.id("btn_ddlfslclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"ddlfsl\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlfslclass=")).click();

		// select client
		Driver.findElement(By.id("btn_ddlClientclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"ddlClient\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlClientclass=")).click();

		// from date
		DateFormat dateFormattrns = new SimpleDateFormat("MM/dd/yyyy");
		Date frmdttrns = new Date();
		Date frmdt1trns = addDays(frmdttrns, -10);
		String FromDatetrns = dateFormattrns.format(frmdt1trns);

		Driver.findElement(By.id("txtValidFrom")).click();
		Driver.findElement(By.id("txtValidFrom")).clear();
		Driver.findElement(By.id("txtValidFrom")).sendKeys(FromDatetrns);
		WebElement trnsfdate = Driver.findElement(By.id("txtValidFrom"));
		trnsfdate.sendKeys(Keys.TAB);

		// to date
		Date todttrns = new Date();
		String ToDatetrns = dateFormattrns.format(todttrns);

		Driver.findElement(By.id("txtValidTo")).clear();
		Driver.findElement(By.id("txtValidTo")).sendKeys(ToDatetrns);
		WebElement trnstdate = Driver.findElement(By.id("txtValidTo"));
		trnstdate.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		// --select WO type
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ddlWOType")));
		Select clnt = new Select(Driver.findElement(By.id("ddlWOType")));
		clnt.selectByVisibleText("Work Order In");

		// view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean Replnsh = Driver.findElement(By.xpath("//*[@id=\"myIframe\"]")).isDisplayed();

		if (Replnsh == false) {
			throw new Error("Error: Transaction Report grid not display");
		}

		Driver.findElement(By.id("txtPart")).clear();
		Driver.findElement(By.id("txtPart")).sendKeys(Part2);

		Driver.findElement(By.id("txtSerial")).clear();
		Driver.findElement(By.id("txtSerial")).sendKeys(P2Field2);

		// --Fields are not exist

		/*
		 * Driver.findElement(By.id("txtTracking")).clear();
		 * Driver.findElement(By.id("txtTracking")).sendKeys(P2Field3);
		 * 
		 * Driver.findElement(By.id("txtRevision")).clear();
		 * Driver.findElement(By.id("txtRevision")).sendKeys(P2Field4);
		 * 
		 * Driver.findElement(By.id("txtField5")).clear();
		 * Driver.findElement(By.id("txtField5")).sendKeys(P2Field5);
		 */

		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean Replnsh1 = Driver.findElement(By.xpath("//*[@id=\"myIframe\"]")).isDisplayed();

		if (Replnsh1 == false) {
			throw new Error("Error: Transaction Report grid not display when no record found");
		}

		getScreenshot(Driver, "TransactionReport");

		// Reset
		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void OnHandReport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);

		try {
			WebElement Reports = Driver.findElement(By.id("idReports"));
			act.moveToElement(Reports).build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			WebElement Inventory = Driver.findElement(By.id("idReportInventory"));
			act.moveToElement(Inventory).build().perform();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idOn")).click();
		} catch (Exception e) {
			Driver.findElement(By.id("idReports")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReports\"]")));
			Driver.findElement(By.id("idReportInventory")).click();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@aria-labelledby=\"idReportInventory\"]")));
			Driver.findElement(By.id("idOn")).click();
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// select FSL
		Driver.findElement(By.id("btn_ddlfslclass=")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"ddlfsl\"]//input[@id=\"idcheckboxInput\"]")));
		Driver.findElement(By.xpath("//*[@id=\"ddlfsl\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlfslclass=")).click();

		// select client
		Driver.findElement(By.id("btn_ddlClientclass=")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"ddlClient\"]//input[@id=\"idcheckboxInput\"]")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("btn_ddlClientclass=")).click();

		// part num
		Driver.findElement(By.id("txtField1")).clear();
		Driver.findElement(By.id("txtField1")).sendKeys(Part2);

		// view report
		Driver.findElement(By.id("btnView")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --wait to get the notification message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));
		String WaitMsg = Driver.findElement(By.xpath("//label[@id=\"idwait\"]")).getText();
		System.out.println("Wait Message is==" + WaitMsg);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id=\"idwait\"]")));

		boolean Replnsh = Driver.findElement(By.xpath("//*[@id=\"myIframe\"]")).isDisplayed();

		if (Replnsh == false) {
			throw new Error("Error: On Hand Report grid not display");
		} else {
			System.out.println("On Hand Report is displayed");
		}

		getScreenshot(Driver, "OnHandReport");

		// Reset
		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void AgentConsole() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// Go to Tools - Agent Console screen
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idTools")));
		Driver.findElement(By.id("idTools")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("idAgent")));
		Driver.findElement(By.id("idAgent")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Select AirlineId
		Driver.findElement(By.id("txtAirlineId")).clear();
		Driver.findElement(By.id("txtAirlineId")).sendKeys("AA");
		Thread.sleep(2000);
		WebElement alid = Driver.findElement(By.id("txtAirlineId"));
		alid.sendKeys(Keys.ENTER);

		// enter FLight No
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtFlightNo")));
		Driver.findElement(By.id("txtFlightNo")).clear();
		Driver.findElement(By.id("txtFlightNo")).sendKeys("1993");

		// Select Arriving Airport
		Driver.findElement(By.id("txtAirportID")).clear();
		Driver.findElement(By.id("txtAirportID")).sendKeys("CLT");
		Thread.sleep(2000);
		WebElement apid = Driver.findElement(By.id("txtAirportID"));
		apid.sendKeys(Keys.ENTER);

		// Click on Check Status
		Driver.findElement(By.id("btCheckStatus")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		boolean map = Driver.findElement(By.id("collFlight")).isEnabled();

		if (map == false) {
			throw new Error("Error: Map not display");
		}

		// Collapse map
		Driver.findElement(By.id("imgFlight")).click();
		Thread.sleep(10000);

		// Weather info
		Driver.findElement(By.id("txtZipCode")).clear();
		Driver.findElement(By.id("txtZipCode")).sendKeys("10019");
		Thread.sleep(10000);

		Driver.findElement(By.id("btnReset")).click();
		Thread.sleep(10000);

		Driver.findElement(By.id("txtZipCode")).clear();
		Driver.findElement(By.id("txtZipCode")).sendKeys("90019");
		Thread.sleep(10000);

		Driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(10000);

		getScreenshot(Driver, "agentconsole");

		Driver.findElement(By.id("imgWeather")).click();
		Thread.sleep(10000);

		// Click on First Doc
		String winHandleBefore1 = Driver.getWindowHandle();

		// CLick on link

		Driver.findElement(By.linkText("Click here")).click();
		Thread.sleep(10000);

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		// Close new window
		Driver.close();

		// Switch back to original browser (first window)
		Driver.switchTo().window(winHandleBefore1);
		Thread.sleep(5000);

//			//Store window
//			String winHandleBefore = Driver.getWindowHandle();
//			
//			//CLick for open new window
//			Driver.findElement(By.xpath("//a[contains(.,'Click here')]")).click();
//			Thread.sleep(10000);
//			
//			for(String winHandle : Driver.getWindowHandles())
//			{
//				Driver.switchTo().window(winHandle);
//			}
//			//Close new window
//			Driver.close();
//			
//			Thread.sleep(10000);	
//			
//			// Switch back to original browser (first window)
//			Driver.switchTo().window(winHandleBefore);
//			Thread.sleep(10000);

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void ContactUs() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// Go to Welcome - Contact Us screen
		Driver.findElement(By.xpath("//div[contains(@class,'userthumb')]")).click();
		Driver.findElement(By.linkText("Contact Us")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Contact Us screen and Add comment and submit.
		System.out.println(Driver.getTitle());
		Driver.findElement(By.name("txtComments")).clear();
		Driver.findElement(By.name("txtComments")).sendKeys("Test Note");
		Driver.findElement(By.id("btnSubmit")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Take screen-shot.
		getScreenshot(Driver, "ContactUs");

		// Go to main screen.
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void AgentTSA() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		// Go to Welcome - AgentTSA Training screen
		Driver.findElement(By.xpath("//div[contains(@class,'userthumb')]")).click();
		Driver.findElement(By.linkText("Agent TSA Training")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// AgentTSA screen.
		System.out.println(Driver.getTitle());

		// Take screen-shot.
		getScreenshot(Driver, "AgentTSA");

		String strParentWindowHandle = Driver.getWindowHandle();

		// CLick on link
		Driver.findElement(By.linkText("Annual TSA Training Presentation(Authorized Representative Version)")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		// Close new window
		Driver.close();

		Thread.sleep(2000);

		// Switch back to original browser (first window)
		Driver.switchTo().window(strParentWindowHandle);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Go to main screen.
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

		// Go to Welcome - AgentTSA Training screen
		Driver.findElement(By.xpath("//div[contains(@class,'userthumb')]")).click();
		Driver.findElement(By.linkText("Agent TSA Training")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// AgentTSA screen.
		System.out.println(Driver.getTitle());

		String strParentWindowHandle1 = Driver.getWindowHandle();

		// CLick on link
		Driver.findElement(By.linkText("Annual TSA Test(Authorized Representative Version)")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "AgentTSA");

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		// Close new window
		Driver.close();

		Thread.sleep(2000);

		// Switch back to original browser (first window)
		Driver.switchTo().window(strParentWindowHandle1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Go to main screen.
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
	}

	@Test
	public void AgentRisk() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		Thread.sleep(15000);

		// Go to Welcome - Agent Risk screen
		Driver.findElement(By.xpath("//div[contains(@class,'userthumb')]")).click();
		Driver.findElement(By.linkText("Agent Elevated Risk Training")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		System.out.println(Driver.getTitle());

		// Take screen-shot.
		getScreenshot(Driver, "AgentRisk");

		String strParentWindowHandle = Driver.getWindowHandle();

		// CLick on link
		Driver.findElement(By.linkText("Elevated Risk Physical Search 2.0A Training Presentation")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		// Close new window
		Driver.close();
		Thread.sleep(2000);

		// Switch back to original browser window
		Driver.switchTo().window(strParentWindowHandle);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Go to main screen.
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

		// Go to Welcome - Agent Risk screen
		Driver.findElement(By.xpath("//div[contains(@class,'userthumb')]")).click();
		Driver.findElement(By.linkText("Agent Elevated Risk Training")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		System.out.println(Driver.getTitle());

		String strParentWindowHandle1 = Driver.getWindowHandle();

		// CLick on link
		Driver.findElement(By.linkText("Elevated Risk Physical Search 2.0A Test")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		// Close new window
		Driver.close();
		Thread.sleep(2000);

		// Switch back to original browser window
		Driver.switchTo().window(strParentWindowHandle1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Go to main screen.
		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void MNXDoc() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		// Go to Tools - NGL Doc screen
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Tools")));
		Driver.findElement(By.partialLinkText("Tools")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("MNX Documents")));
		Driver.findElement(By.linkText("MNX Documents")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "MNXDoc");

//			//Click on First Doc
		String winHandleBefore1 = Driver.getWindowHandle();

//			//CLick on doc link
		Driver.findElement(By.xpath("//a[@ng-click=\"NglDocData(doc)\"]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		for (String winHandle : Driver.getWindowHandles()) {
			Driver.switchTo().window(winHandle);
		}
		// Close new window
		Driver.close();

		Thread.sleep(5000);

		// Switch back to original browser (first window)
		Driver.switchTo().window(winHandleBefore1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void MileageCalc() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Tools")));
		Driver.findElement(By.partialLinkText("Tools")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("MileageCalc")));
		Driver.findElement(By.linkText("MileageCalc")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		System.out.println("Title of the screen is==" + Driver.getTitle());

		// For Record not Found Testing.
		// PUId = "1234567";
		// JobId = "123456789";

		// Search with Pickup

		Driver.findElement(By.id("txtpickupid")).clear();
		Driver.findElement(By.id("txtpickupid")).sendKeys("1234567");

		Driver.findElement(By.id("btngetdetails")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String RNF = Driver.findElement(By.id("errorid")).getText();
		System.out.println("RNF : " + RNF);

		String CalMsg = Driver.findElement(By.xpath("//label/strong")).getText();
		/// html/body/div[2]/section/div[2]/div/div/div[2]/div[1]/form/div[2]/div/div[3]/div/div/label/strong

		// Reset
		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// Search with JobId
		Driver.findElement(By.id("txtJobid")).clear();
		Driver.findElement(By.id("txtJobid")).sendKeys(JobId);

		Driver.findElement(By.id("btngetdetails")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String CalMsg2 = Driver.findElement(By.xpath("//label/strong")).getText();

		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String RNF2 = Driver.findElement(By.id("errorid")).getText();
		System.out.println("RNF2 : " + RNF2);

		Driver.findElement(By.id("txtpickupid")).clear();
		Driver.findElement(By.id("txtpickupid")).sendKeys(PUId);

		Driver.findElement(By.id("btngetdetails")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		String act = Driver.findElement(By.xpath("//label/strong/span")).getText();
		System.out.println(act);

		File src0 = new File(".\\NA_DEV.xls");
		FileInputStream fis0 = new FileInputStream(src0);
		Workbook workbook = WorkbookFactory.create(fis0);
		Sheet sh0 = workbook.getSheet("Sheet1");
		// int rcount = sh0.getLastRowNum();

		DataFormatter formatter = new DataFormatter();

		String Exp = formatter.formatCellValue(sh0.getRow(2).getCell(21));

		if (act.contains(Exp)) {
			System.out.println("Miles Comparison is PASS");
		}

		else {
			System.out.println("Miles Comparison is FAIL");
		}

		// Click on Calculate
		// Driver.findElement(By.id("btCalculate")).click();
		// Thread.sleep(10000);
		System.out.println("Mileage Calculation : " + CalMsg);
		System.out.println("Mileage Calculation : " + CalMsg2);
		// Click on Get Direction
		Driver.findElement(By.id("btnDirection")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		boolean mapcheck = Driver.findElement(By.xpath(".//*[@id='mapLoad']")).isDisplayed();

		if (mapcheck == false) {
			throw new Error("Error: Map Not Display on Get Direction");
		}

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		getScreenshot(Driver, "MileageCalc");

		// Expand and Collapse Note
		Driver.findElement(By.id("imgData")).click();
		Thread.sleep(2000);

		Driver.findElement(By.id("imgData")).click();

		// Reset
		Driver.findElement(By.id("btnReset")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public void UserPreference() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);

		// --Clicked on Preferences
		wait.until(ExpectedConditions.elementToBeClickable(By.id("idPreferences")));
		WebElement Preference = Driver.findElement(By.id("idPreferences"));
		act.moveToElement(Preference).click().perform();
		Thread.sleep(2000);

		// --UserPreference
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("User Preferences")));
		Driver.findElement(By.linkText("User Preferences")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("panel-body")));

		// Check all fields
		// User Pref
		// 1. User Name
		Boolean uname = Driver.findElement(By.id("txtUsername")).isEnabled();

		if (uname == true) {
			throw new Error("Error: User Name field is enable");
		}

		// Currency Pref
		// 1. Currency
		Boolean Currency = Driver.findElement(By.id("txtCurrency")).isEnabled();

		if (Currency == true) {
			throw new Error("Error: Currency field is enable");
		}

		// 2. Currency Symbol
		Boolean currsym = Driver.findElement(By.id("txtCurrencySymbol")).isEnabled();

		if (currsym == true) {
			throw new Error("Error: Currency Symbol field is enable");
		}

		// 3. Currency Separator
		Boolean currsep = Driver.findElement(By.id("ddlCurrencySeparator")).isEnabled();

		if (currsep == true) {
			throw new Error("Error: Currency Separator field is enable");
		}

		// Regional Preferences
		// 1. Country
		Boolean Country = Driver.findElement(By.id("ddlCountry")).isEnabled();

		if (Country == false) {
			throw new Error("Error: Country field is disable");
		}

		// 2. Culture
		Boolean Culture = Driver.findElement(By.id("ddlCULTURE")).isEnabled();

		if (Culture == false) {
			throw new Error("Error: Culture field is disable");
		}

		// 4. Time Zone
		Boolean TZ = Driver.findElement(By.id("ddlTimeZone")).isEnabled();

		if (TZ == false) {
			throw new Error("Error: Time Zone field is disable");
		}

		// 3. Date/Time Format
		Boolean dttmfor = Driver.findElement(By.id("ddlDateTimeFormat")).isEnabled();

		if (dttmfor == false) {
			throw new Error("Error: Date/Time Format field is disable");
		}

		// 2. FSL
		Boolean FSL = Driver.findElement(By.id("ddlCourierFSL")).isEnabled();

		if (FSL == false) {
			throw new Error("Error: FSL field is disable");
		}

		// --Click on save
		Driver.findElement(By.id("btnSave")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		getScreenshot(Driver, "UserPreference");

		Driver.findElement(By.id("imgNGLLogo")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

	}

	@Test
	public static void TaskLog()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		TaskLog.taskLog();
	}

	@Test
	public static void orderProcessing()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		OrderProcessing.orderProcess();
	}

	@Test
	public static void putaway()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		PutAway.putAway();
	}
}
