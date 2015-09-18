package java8.streams;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LazyOperations {

	public static void main(String[] args) {
		Stream<String> stream = Stream.of("a", "b", "c");

		Predicate<String> p1 = Predicate.isEqual("b");
		Predicate<String> p2 = Predicate.isEqual("c");

		ArrayList<String> list = new ArrayList<>();

		stream.peek(System.out::println).filter(p1.or(p2)).forEach(list::add);

		System.out.println("Done!");
	}
}
