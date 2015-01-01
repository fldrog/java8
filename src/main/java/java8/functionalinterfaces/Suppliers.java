package java8.functionalinterfaces;

import java.util.UUID;
import java.util.function.Supplier;

public class Suppliers {

	public static void main(String[] args) {
		//	Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.

		System.out.println(UUID.randomUUID().toString());
	}
}
