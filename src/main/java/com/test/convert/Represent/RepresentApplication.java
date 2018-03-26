package com.test.convert.Represent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.convert.Represent.service.NumberToString;

/**
 * @author ritika 
 * Program to convert numbers to String representation
 *
 */
@SpringBootApplication
public class RepresentApplication implements CommandLineRunner {

	@Autowired
	private NumberToString numberToString;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RepresentApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (args.length > 0) {
			System.out.println(numberToString.convertNumberToString(args[0].toString()));
		} else {
			System.out.println("Argument missing. Please enter a number");
		}

	}
}
