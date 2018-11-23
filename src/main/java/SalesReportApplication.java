package main.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* This SalesReportApplication class processes the sales
*  messages and print sales report
*
* @author  Vandan Hatkar
* @version 1.0
* @since   2018-11-22 
*/

public class SalesReportApplication {

	public static void main(String[] args) {
		
		String notificationLine = new String();
		MessageProcessor messageProcessor = new MessageProcessor();
		SalesReportService salesReportService = new SalesReportServiceImpl();

		try (BufferedReader inputSalesFile = new BufferedReader(
				new FileReader("src/main/resources/salesInput.txt"));) {

			int messageCounter = 0; 
			
			while ((notificationLine = inputSalesFile.readLine()) != null) {
				
				messageProcessor.evaluate(notificationLine); 
				
				messageCounter++;
				
				if (messageCounter % 10 == 0) {
					salesReportService.salesReport(); 
				}
				
				if (messageCounter == 50) {
					
					System.out.println("      ----- PAUSING FOR WHILE -----     ");
					Thread.sleep(2000);
					
					salesReportService.adjustmentReport();
					
					System.out.println("----- Exiting application after 50 messages processing completed ------");
					
					System.exit(1);
				}
			}
			
		} catch (IOException | InterruptedException ie) {

			System.out.println(ie.getMessage());
		}
	}
}
