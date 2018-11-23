package main.java;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* This MessageProcessor class processes messages and check for product information
*
* @author  Vandan Hatkar
* @version 1.0
* @since   2018-11-22 
*/
public class MessageProcessor {

	public static Map<String, ProductInfo> productMap = new HashMap<>(); 
	public static List<String> adjustments = new ArrayList<>();
	private static final String SPACE = "";
	private static final int ZERO = 0;

	/**
	 * This method filters the messages to determine its type and passes for processing
	 * @param String is a message to be parsed
	 */
	public void evaluate(String line) {

		String[] productInfo = line.split(" ");
		for (String word : productInfo) {
			if (word.matches("Add|Subtract|Multiply")) { 
				thirdMessageType(productInfo);
				break;
			} else if (word.equalsIgnoreCase("sales")) { 
				secondMessageType(productInfo);
				break;
			} else if (word.equalsIgnoreCase("at")) { 
				firstMessageType(productInfo);
				break;
			}
		}
	}

	/**
	 * This method adds the processed product info to the static hashmap
	 * @param productInfo parsed String array
	 */
	private void firstMessageType(String[] productInfo) {
		if (productInfo.length != 3) {
			System.out.println("Invalid input for Message Type 1");
		} else {
			String productType = getProductType(productInfo[0]);
			ProductInfo product = productMap.get(productType);
			if (product == null) { 
				product = new ProductInfo(SPACE,BigDecimal.ZERO,BigDecimal.ZERO,ZERO);
			}
			product.setProductType(productType);
			product.setProductPrice(getPrice(productInfo[2]));
			product.setProductQuantity(product.getProductQuantity());
			product.setTotalAmount(product.getTotalAmount());
			productMap.put(productType, product);
		}
	}

	/**
	 * This method updates the quantity and total sales to the static hashmap
	 * @param productInfo parsed String array
	 */
	private void secondMessageType(String[] productInfo) {
		if (productInfo.length != 6) {
			System.out.println("Invalid input for Message Type 2");
		} else {
			String productType = getProductType(productInfo[3]);
			int quantity = Integer.valueOf(productInfo[0]);
			ProductInfo product = productMap.get(productType);
			if (product == null) {
				product = new ProductInfo(SPACE,BigDecimal.ZERO,BigDecimal.ZERO,ZERO);
			}
			product.setProductPrice(getPrice(productInfo[5]));
			product.setProductQuantity(product.getProductQuantity() + quantity);
			product.setTotalAmount(product.getTotalAmount().add(product.getProductPrice().multiply(new BigDecimal(quantity))));
			productMap.put(productType, product);
		}
	}

	/**
	 * This method to check operation type and updates the product in productMap
	 * @param productInfo parsed String array
	 */
	private void thirdMessageType(String[] productInfo) {
		if (productInfo.length != 3) {
			System.out.println("Invalid input for Message Type 3");
		} else {
			String productType = getProductType(productInfo[2]);
			ProductInfo product = productMap.get(productType);
			if (product == null) {
				product = new ProductInfo(SPACE,BigDecimal.ZERO,BigDecimal.ZERO,ZERO);
			}
			BigDecimal price = product.getProductPrice();

			try {
				if (productInfo[0].equalsIgnoreCase("Add")) {
					product.setProductPrice(product.getProductPrice().add(getPrice(productInfo[1])));
				}
				if (productInfo[0].equalsIgnoreCase("Subtract")) {

					if (price.doubleValue() > 0) { 
						product.setProductPrice(price.subtract(getPrice(productInfo[1])));
					} 
				}
				if (productInfo[0].equalsIgnoreCase("Multiply")) {
					if (price.doubleValue() > 0) { 
						product.setProductPrice(price.multiply(getPrice(productInfo[1])));
					}
				}
				product.setProductQuantity(product.getProductQuantity());
				product.setTotalAmount(product.getTotalAmount());
				productMap.put(product.getProductType(), product);
				adjustments.add(product.getProductType() + " with base value " + price + " has been adjusted to "
						+ product.getProductPrice());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Method to remove p(currency) to get pure number
	 * @param value
	 * @return price without currency info
	 */
	private BigDecimal getPrice(String value) {
		return BigDecimal.valueOf(Integer.valueOf(value.substring(0, value.indexOf('p'))));
	}

	/**
	 * Method to generalise the key for map
	 * @param product type
	 * @return product type
	 */
	private String getProductType(String type) {
		String productType = new String();
		if (type.contains("apple")) { 
			productType = "apples";
		}
		if (type.contains("banana")) {
			productType = "bananas";
		}
		if (type.contains("pear")) {
			productType = "pears";
		}
		if (type.contains("plum")) {
			productType = "plums";
		}
		return productType;
	}

}
