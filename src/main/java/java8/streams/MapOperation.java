package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class MapOperation {

	public static void main(String[] args) {

		List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<Integer> l2 = Arrays.asList(8, 9, 2, 3);
		List<Integer> l3 = Arrays.asList(1, 2, 3);

		List<List<Integer>> list = Arrays.asList(l1, l2, l3);

		Function<List<Integer>, Stream<Integer>> flatMapper = l -> l.stream();

		list.stream().map(flatMapper).forEach(System.out::println);
		System.out.println("FLAT:");
		list.stream().flatMap(flatMapper).forEach(System.out::println);

	}
}
