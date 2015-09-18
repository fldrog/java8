package java8.streams;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collector {

	public static void main(String[] args) {

		Stream<String> stream = Stream.of("a", "b", "c");

		System.out.println(stream.filter(a -> (a.length() == 1)).map(a -> "-" + a.length()).collect(Collectors.joining()));
	}
}
