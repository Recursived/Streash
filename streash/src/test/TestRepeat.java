package test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import streash.variables.Rational;

public class TestRepeat {
	public static void main(String[] args) {
		Rational r = new Rational(BigInteger.valueOf(10), BigInteger.valueOf(3));
		double val = r.doubleValue();
		System.out.println(r);
		System.out.println(val);
		BigDecimal bd = new BigDecimal(val % 1).setScale(1, RoundingMode.DOWN);
		System.out.println( bd.doubleValue());
		System.out.println((int)12 * 0.7);
//		Stream<Integer> s = null;
//		Stream<Integer> s2 = Stream.iterate(1, i -> i + 1);
//		s2 = s2.skip(0).limit(10);
//		Stream<Integer> conc = Stream.concat(s, s2);
//		conc.forEach(System.out::println);
		String expr = "/print ";
		System.out.println(expr.matches("^/[a-z][a-z]* .*"));
	}
}
