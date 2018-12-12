package streash.variables;

import java.math.BigInteger;
import java.util.Objects;

public class Rational extends Number implements Comparable<Rational>, Variable{
	/*
	 * Classe representant les rationnels
	 * 
	 * Champs pas finaux car on les changes avec les operations
	 * arithmetique
	 */
	
	private BigInteger numerator;
	private BigInteger denominator;
	private VarType type = VarType.Rational;
	private static final long serialVersionUID = 1L;
	
	public Rational(BigInteger numerator, BigInteger denominator) {
		this.numerator = Objects.requireNonNull(numerator);
		if (denominator.equals(BigInteger.valueOf(0))){
			throw new ArithmeticException("Division par zero");
		}
		this.denominator = Objects.requireNonNull(denominator);
	}
	
	public Rational(BigInteger numerator) {
		this(numerator, BigInteger.valueOf(1));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.numerator.hashCode(), this.denominator.hashCode());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		
		if (!(o.getClass().equals(this.getClass()))) return false;
		
		Rational r = (Rational) o;
		
		// On simplifie les deux avant de tester l'egalite
		r = r.simplify();
		Rational b = this.simplify();

		return r.numerator.equals(b.numerator) && r.denominator.equals(b.denominator);
	}
	
	@Override
	public String toString() {
		return this.numerator.toString() + "/" + this.denominator;
	}
	
	public Rational simplify() {
		BigInteger num = this.numerator;
		BigInteger denom = this.denominator;
		BigInteger pgcd = num.gcd(denom);
		return new Rational(this.numerator.divide(pgcd), this.denominator.divide(pgcd));
	}
	
	public Rational mul(Rational a) {
		BigInteger num = this.numerator.multiply(a.numerator);
		BigInteger denom = this.denominator.multiply(a.denominator);
		return new Rational(num, denom).simplify();
	}
	
	public Rational div(Rational a) {
		BigInteger num = this.numerator.multiply(a.denominator);
		BigInteger denom = this.denominator.multiply(a.numerator);
		return new Rational(num, denom).simplify();
	}
	
	public Rational add(Rational a) {
		// On les met si besoin au meme denominateur
		BigInteger n1 = this.denominator;
		BigInteger n2 = a.denominator;
		if (n1.equals(n2)) {
			return new Rational(this.numerator.add(a.numerator), n1).simplify();
		}
		this.numerator = this.numerator.multiply(n2);
		this.denominator = this.denominator.multiply(n2);
		a.numerator = a.numerator.multiply(n1);
		a.denominator = a.denominator.multiply(n1);
		
		return new Rational(this.numerator.add(a.numerator), this.denominator).simplify();
	}
	
	public Rational sub(Rational a) {
		// On les met si besoin au meme denominateur
		BigInteger n1 = this.denominator;
		BigInteger n2 = a.denominator;
		if (n1.equals(n2)) {
			return new Rational(this.numerator.subtract(a.numerator), n1).simplify();
		}
		this.numerator = this.numerator.multiply(n2);
		this.denominator = this.denominator.multiply(n2);
		a.numerator = a.numerator.multiply(n1);
		a.denominator = a.denominator.multiply(n1);	
		return new Rational(this.numerator.subtract(a.numerator), this.denominator).simplify();
	}
	
	public int ceil() {
		return (int) Math.ceil((double) this.numerator.intValue()/this.denominator.intValue());
	}
	
	public boolean isInteger() {
		return this.denominator.equals(BigInteger.valueOf(1));
	}

	@Override
	public int compareTo(Rational r) {
		Double.compare(this.numerator.intValue() / this.denominator.intValue(), r.numerator.intValue() / r.denominator.intValue());
		return 0;
	}

	@Override
	public double doubleValue() {
		
		return this.numerator.intValue() /(double) this.denominator.intValue();
	}

	@Override
	public float floatValue() {
		return this.numerator.intValue() /(float) this.denominator.intValue();
	}

	@Override
	public int intValue() {
		return this.numerator.intValue() / this.denominator.intValue();
	}

	@Override
	public long longValue() {
		return this.numerator.intValue() /(long) this.denominator.intValue();
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.toString();
	}
}
