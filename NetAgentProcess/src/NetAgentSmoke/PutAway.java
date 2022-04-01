package NetAgentSmoke;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PutAway extends BaseInit {

	public static void putAway() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		// Actions act = new Actions(Driver);

		// --Inventory
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idInventory")));
		Driver.findElement(By.id("idInventory")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-labelledby=\"idInventory\"]")));

		// --PutAway
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idPutaway")));
		Driver.findElement(By.id("idPutaway")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

		// --FSL Dropdown
		Select FSL = new Select(Driver.findElement(By.id("drpFSLList")));
		FSL.selectByVisibleText("RAVINA FSL (F5491)");
		System.out.println("Selected FSL");
		Thread.sleep(3000);

		// --Search
		Driver.findElement(By.id("idbtnsearch")).click();
		System.out.println("Clicked on Search button");
		Thread.sleep(5000);

		WebElement NoData = Driver.findElement(By.className("dx-datagrid-nodata"));
		if (NoData.isDisplayed()) {
			System.out.println("Data is not present related search parameter");

		} else {
			System.out.println("Data is present related search parameter");
			// Bin
			Driver.findElement(By.id("txtBinBarcode")).clear();
			Driver.findElement(By.id("txtBinBarcode")).sendKeys("DEFAULTBIN");
			Driver.findElement(By.id("txtBinBarcode")).sendKeys(Keys.TAB);

			// get the STock IDs
			List<WebElement> stockIDS = Driver.findElements(By.xpath("//*[contains(@aria-label,'Stock ID,')]/span"));
			System.out.println("Total records are==" + stockIDS.size());
			for (int sID = 0; sID < stockIDS.size(); sID++) {
				String stockID = stockIDS.get(sID).getText();
				System.out.println("Stock ID is==" + stockID + "\n");

				// --Enter in stockID
				Driver.findElement(By.id("txtPartBarcode")).sendKeys(stockID);
				System.out.println("Entered stock ID");
				Driver.findElement(By.id("txtPartBarcode")).sendKeys(Keys.TAB);
				Thread.sleep(2000);
				getScreenshot(Driver, "PartMoved_" + stockID);

				// --check Success Message
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-bind=\"SuccessMsg\"]")));
					String SuccMsg = Driver.findElement(By.xpath("//*[@ng-bind=\"SuccessMsg\"]")).getText();
					System.out.println("Success message==" + SuccMsg + "\n");

					// --Check Green sign for selected part
					// *[contains(@class,'showGreenTick ')]
					stockID = stockIDS.get(sID).getText();
					String xpath = "//*[@id=\"gridProcessPutaway\"]//tbody/tr[" + sID + 1 + "]//i";
					WebElement GreenTick = Driver.findElement(By.xpath(xpath));
					if (GreenTick.isDisplayed()) {
						System.out.println("Part is moved to DEFAULTBIN");
					}

				} catch (Exception wrong) {
					System.out.println("Something unexpected happened");
				}

			}
		}

	}

}
