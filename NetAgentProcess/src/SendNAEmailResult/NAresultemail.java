package SendNAEmailResult;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;;

public class NAresultemail 
{
	static StringBuilder msg = new StringBuilder();	
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	
	public static void main(String[] args) throws InterruptedException 
	{
		  String baseUrl = rb.getString("URL");		
		
		  msg.append("*** Email attached report for last execution ***" + "\n\n");	
		  msg.append("*** This is automated generated email and send through automation script ***" + "\n");
		  msg.append("Process URL : " + baseUrl);
		  
		  String subject = "Automation: NetAgent Processing - Attached Report";
	      try 
	      {        	
	    	  emailsending.sendMail("pdoshi@samyak.com", subject, msg.toString(), "D:\\Netlink_AJ\\TestAutomation\\ConnectDetailTesting\\NewEclipse\\NetAgentProcess\\test-output\\emailable-report.html");
	      } 
	      catch (Exception ex) 
	      {
	      	Logger.getLogger(NAresultemail.class.getName()).log(Level.SEVERE, null, ex);
	      }  	
	      
	      System.out.println("Report Send Successfully");
		  
	  }
}
