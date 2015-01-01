package java8.functionalinterfaces;

import java.util.Objects;
import java.util.function.Predicate;

public class Predicates {

	public static void main(String[] args) {
		Predicate<String> p1 = s -> {
			return s.length() > 0;
		};
		Predicate<String> p2 = s -> {
			return s.startsWith("x");
		};

		Predicate<String> combined = p1.and(p2);
		Predicate<String> negate = p1.negate();

	}

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
