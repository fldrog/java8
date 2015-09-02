package java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lambda {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("a", "x", "b");
		Collections.sort(list, (a, b) -> {
			return b.compareTo(a);
		});

		ConvTest t = new ConvTest();
		t.convTest();
	}
}

///Functionl interface
@FunctionalInterface
interface Converter<F, T> {

	T convert(F from);
}

class ConvTest {

	public void convTest() {
		Converter<String, Integer> converter = (x) -> Integer.valueOf(x);
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
	}

	//referencing static methods
	public void convTest2() {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
	}
}

//referencing methods
class Something {

	String startsWith(String s) {
		return String.valueOf(s.charAt(0));
	}

	void test() {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String converted = converter.convert("Java");
		System.out.println(converted); // "J"
	}
}

//constructor referencing
class Person {

	String firstName;
	String lastName;

	Person() {
	}

	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

interface PersonFactory<P extends Person> {

	P create(String firstName, String lastName);
}

class ConstrTest {

	public void test() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
	}
}

//accessing local vars

class AccessVars {

	public void test1() {
		final int num = 1;
		Converter<Integer, String> stringConverter =
				(from) -> String.valueOf(from + num);

		stringConverter.convert(2); // 3
	}

	public void test2() {
		int num = 1;
		Converter<Integer, String> stringConverter =
				(from) -> String.valueOf(from + num);

		stringConverter.convert(2); // 3
	}

	public void test3() {
		int num = 1;
		Converter<Integer, String> stringConverter =
				(from) -> String.valueOf(from + num);
		//if below not commented does not compile
		//num = 3;
	}

}

//accessing static vars
class Lambda4 {

	static int outerStaticNum;
	int outerNum;

	void testScopes() {
		Converter<Integer, String> stringConverter1 = (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};

		Converter<Integer, String> stringConverter2 = (from) -> {
			outerStaticNum = 72;
			return String.valueOf(from);
		};
	}
}

//interface default methods can't be accessed:
