package com.nopcommerce.user;

import com.github.javafaker.Faker;

public class Topic_21_Faker {

	public static void main(String[] args) {
		Faker faker = new Faker();

		faker.business().creditCardType();
	}

}
