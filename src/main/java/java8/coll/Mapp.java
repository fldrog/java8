package java8.coll;

import java.util.HashMap;
import java.util.Map;

public class Mapp {

	static class N {

		private Integer x;
	}
	public static void main(String[] args) {
		Map<String, String> m = new HashMap<>();
		m.put("a", "b");
		m.compute("a", (k, v) -> {
			System.out.println(k);
			System.out.println(v);
			return "x";
		});
		System.out.println(m.get("a"));
		m.computeIfAbsent("b", v -> {
			System.out.println(v);
			return "y";
		});
		System.out.println(m.get("b"));

		m.merge("a", "b", (k, v) -> "z");
		System.out.println(m.get("a"));

		m.merge("a", "c", (k, v) -> "Y");
		System.out.println(m.get("a"));
	}
}
