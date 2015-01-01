package java8.optionals;

import java.util.Optional;

public class OptionalTst {

	public static void main(String[] args) {
		Optional<String> optional = Optional.of("bam");

		optional.isPresent(); // true
		optional.get(); // "bam"
		optional.orElse("fallback"); // "bam"
		optional.filter(s -> {
			return s.startsWith("s");
		});

		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
	}
}
