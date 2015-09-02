package java8.functionalinterfaces;

import java.util.function.Function;

public class Functions {

	//Functions accept one argument and produce a result. 
	public static void main(String[] args) {
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);

		backToString.apply("123"); // "123"
	}
}
