package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import streash.variables.Rational;

public class TestRational {
	private Rational r1 = new Rational(BigInteger.valueOf(14), BigInteger.valueOf(18));
	private Rational r2 = new Rational(BigInteger.valueOf(6), BigInteger.valueOf(7));
	private Rational r3 = new Rational(BigInteger.valueOf(2), BigInteger.valueOf(198));

	@Test
	public void testEquality() {
		assertEquals(r1, r1.simplify());
	}
	
	@Test
	public void testAdd() {
		assertEquals(r1.add(r2), new Rational(BigInteger.valueOf(103), BigInteger.valueOf(63)));
	}
	
	 @Test
	public void testSubtract() {
		assertEquals(r2.sub(r1), new Rational(BigInteger.valueOf(5), BigInteger.valueOf(63)));
	}
	
	@Test
	public void testMul() {
		assertEquals(r2.mul(r1), new Rational(BigInteger.valueOf(2), BigInteger.valueOf(3))); 
	}
	
	@Test
	public void testDiv() {
		assertEquals(r2.div(r1), new Rational(BigInteger.valueOf(54), BigInteger.valueOf(49)));
	}

	@Test
	public void testFloor() {
		assertEquals(r1.ceil(), 1);
		assertEquals(r3.ceil(), 1);
	}
	
	@Test
	public void testError() {
		assertThrows(ArithmeticException.class, () ->new Rational(BigInteger.valueOf(10), BigInteger.valueOf(0)));
	}
}
