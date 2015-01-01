package java8.functionalinterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerForEach {

	public static void main(String[] args) {
		List<String> array = Arrays.asList("a", "b", "c", "d", "e");
		Consumer<String> printConsumer = System.out::println;
		array.forEach(printConsumer);
		//add each element to a second list
		List<String> a2 = new ArrayList<>();
		Consumer<String> addConsumer = a2::add;
		array.forEach(addConsumer);

		//both
		array.forEach(printConsumer.andThen(addConsumer));

	}
}
