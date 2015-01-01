package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MapFilterReduce {

	public static void main(String[] args) {

		//MAP
		List<String> arr = Arrays.asList("a", "b", "c", "d", "e");
		Stream<String> stream = arr.stream();
		Consumer<String> s1 = System.out::println;
		Consumer<String> s2 = s -> {
			System.out.println("consumer 2:" + s);
		};
		stream.forEach(s1.andThen(s2));

		//FILTER
		arr.parallelStream().filter(s -> {
			return s.startsWith("a");
		}).forEach(s -> {
			System.out.println("Filter: " + s);
		});
		//
	}
}
