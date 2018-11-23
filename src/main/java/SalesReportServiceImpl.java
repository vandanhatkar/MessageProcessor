package main.java;

import java.math.BigDecimal;

/**
* This SalesReport implementation prints report on console output
*
* @author  Vandan Hatkar
* @version 1.0
* @since   2018-11-22 
*/
public class SalesReportServiceImpl implements SalesReportService {


	private static final BigDecimal DIVISOR = new BigDecimal(100);
	/* (non-Javadoc)
	 * @see SalesReportService#AdjustmentReport()
	 * This method prints a report for all the prices adjusted during sales processing.
	 */
	@Override
	public void adjustmentReport() {
		System.out.println("*********************REPORT OF ADJUSTMENT *****************************");
		for (String message : MessageProcessor.adjustments) {
			System.out.println(message);
		}
	}
	
	/* (non-Javadoc)
	 * @see SalesReportService#GeneralReport()
	 * Method prints a sales report for 10 messages processed.
	 */
	@Override
	public void salesReport() {
		System.out.println("*********************SALES APPLICATION REPORT*****************************");
		
		for (ProductInfo ProductInfo : MessageProcessor.productMap.values()) {
			
			BigDecimal productPrice = ProductInfo.getProductPrice().divide(DIVISOR); 
			BigDecimal productTotal = ProductInfo.getTotalAmount().divide(DIVISOR);
			
			System.out.printf("%d quantities of %s were sold at a base price of %.2f. Total sale is %.2f",
			
			ProductInfo.getProductQuantity(), ProductInfo.getProductType(), productPrice, productTotal);
			
			System.out.println("\n----------------------------------------------------------");
		}

		System.out.println("**********Consumed 10 messages successfully***************");
		
	}

}
