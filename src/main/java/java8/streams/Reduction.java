package java8.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Reduction {

	public static void main(String[] args) {
		List<Integer> array = Arrays.asList(1, 2, 3, 4, 5, 6);
		System.out.println(array.parallelStream().reduce(0, (a, b) -> a + b));
		System.out.println(Stream.empty().reduce(0, (a, b) -> a + "" + b));
		System.out.println(Stream.of(1).reduce(0, (a, b) -> a + b));

		BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
		System.out.println(Stream.of(1, 2, 3, 4, 5, 1, 23, 123, 1, 3, 3).reduce(max).get());
		System.out.println(Stream.of(1, 2, 3, 4, 5, 1, 23, 123, 1, 3, 3).max(Comparator.naturalOrder()));

		int sum = Stream.of(1, 2, 3, 4, 5, 1, 23, 123, 1, 3, 3).reduce(0, Integer::max);
		System.out.println(sum);
	}
}
