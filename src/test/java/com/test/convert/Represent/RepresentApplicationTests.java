package com.test.convert.Represent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.convert.Represent.service.NumberToString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepresentApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(RepresentApplicationTests.class);

	@Autowired
	private NumberToString numberToString;

	@MockBean
	private RepresentApplication representAppln;

	@Test
	public void contextLoads() {
		try {
			representAppln.run("2");
		} catch (Exception e) {
			logger.error("Error occured " + e.getMessage());
		}
		Assert.assertEquals(numberToString.convertNumberToString("1"), "one");
		Assert.assertEquals(numberToString.convertNumberToString("100"), "one hundred");
		Assert.assertEquals(numberToString.convertNumberToString("-118"), "minus one hundred eighteen");
		Assert.assertEquals(numberToString.convertNumberToString("999999"),
				"nine hundred ninety nine thousand nine hundred ninety nine");
		Assert.assertEquals(numberToString.convertNumberToString("-555555555555555555555555555"),
				"Number out of range. Please enter number between -999,999 and 999,999");
		Assert.assertEquals(numberToString.convertNumberToString("999999fd"),
				"Found characters in String. Please enter number between -999,999 and 999,999");
	}

}
