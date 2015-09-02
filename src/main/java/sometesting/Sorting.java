package sometesting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Sorting {

	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				} else {
					return 1;
				}
			}

		});

		set.add(1);
		set.add(3);
		set.add(2);
		System.out.println(set);

		System.out.println(new ArrayList<>(set));

		Map<Long, String> map = new HashMap<>();
		map.put(1214124L, "One");
		map.put(22141421224L, "Two");
		map.put(3333214124L, "Three");
		map.put(44431214124L, "Four");
		map.put(53231214124L, "Five");
		System.out.println(map.values());

		map.remove(3333214124L);
		System.out.println(map.values());

	}
}
