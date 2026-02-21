package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		System.out.println(firstName + " " + lastName);
		
		String cityName = faker.address().city();
		System.out.println(cityName);
		
		System.out.println(faker.number().digit());
		System.out.println(faker.numerify("437#######"));
		System.out.println(faker.internet().emailAddress());
	}

}
