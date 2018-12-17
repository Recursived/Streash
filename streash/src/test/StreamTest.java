package test;

import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		Stream<Integer> s = Stream.iterate(1, i -> i + 1);
		s = s.skip(10).limit(100);
		s.forEach(System.out::println);
	}
}
