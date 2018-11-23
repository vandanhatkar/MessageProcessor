package main.test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import main.java.MessageProcessor;
import main.java.ProductInfo;


public class MessageProcessorTest {

	MessageProcessor msgProcessor = new MessageProcessor();
	
	@Test
	public void testFirstMessageType(){
		String line = "apple at 10p";
		msgProcessor.evaluate(line);
		assertTrue(MessageProcessor.productMap.containsKey("apples"));
	}
	
	@Test
	public void testSecondMessageType(){
		String line = "35 sales of apples at 20p";
		msgProcessor.evaluate(line);
		ProductInfo product = MessageProcessor.productMap.get("apples");
		assertNotNull(product);
		assertThat(product.getProductPrice(), is(new BigDecimal(20.0)));
	}
	
	@Test
	public void testThirdMessageType(){
		String line = "35 sales of apples at 20p";
		msgProcessor.evaluate(line);
		line = "Add 10p apples";
		msgProcessor.evaluate(line);
		ProductInfo product = MessageProcessor.productMap.get("apples");
		assertNotNull(product);
		assertThat(product.getProductPrice(), is(new BigDecimal(30.0)));
	}
}
