package java8.functionalinterfaces;

import java.util.Objects;
import java.util.function.Predicate;

public class Predicates {

	//Predicates are boolean-valued functions of one argument
	private void test() {
		Predicate<String> predicate = (s) -> s.length() > 0;

		predicate.test("foo"); // true
		predicate.negate().test("foo"); // false

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
	}
}
