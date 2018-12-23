package test;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.Stream;

import streash.variables.Rational;

public class TestStream {
	public static void main(String[] args) {
		Stream<Integer> s = Stream.iterate(1, i -> i + 1);
		s = s.skip(10).limit(100);
		s.forEach(System.out::println);
		// Test du sort pour les ratinalss
		Random r = new Random();
		Stream<Rational> s1 = Stream.iterate(1,  i -> i + 1)
				.map(x -> new Rational(BigInteger.valueOf(x)));
		s1 = s1.skip(0).limit(5);
		// Test de la somme des elements d'un stream de Rational
		Rational val = s1.reduce((a, b) -> a.mul(b)).get();
		System.out.println(val);
		
	}
}
