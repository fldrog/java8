package java8.functionalinterfaces;

import java.util.function.Supplier;

public class Suppliers {

	public static void main(String[] args) {
		//	Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.

		Supplier<Person> personSupplier = Person::new;
		personSupplier.get(); // new Person
	}
}
