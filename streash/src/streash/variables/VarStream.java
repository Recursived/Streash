package streash.variables;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public interface VarStream extends Variable {
	
	// Miscelleanous
	VarStream getCopy();
	Stream<Variable> getStream();
	void setStream(Stream<Variable> stream);
	
	// Pit
	default Rational len() {
		return new Rational(BigInteger.valueOf(this.getCopy()
				.getStream()
				.count()));
	}
	
	default Rational sum() {
		return this.getCopy()
				.getStream()
				.map(x -> {return (Rational) x;})
				.reduce(new Rational(BigInteger.valueOf(0)), (a,b) -> a.add(b));
	}
	
	default Rational product() {
		return this.getCopy()
				.getStream()
				.map(x -> { return (Rational) x;})
				.reduce(new Rational(BigInteger.valueOf(1)), (a, b) -> a.mul(b));
	}
	
	default Rational average() {
		return this.sum().div(this.len());
	}
	
	default Variable min() {
		return this.getCopy()
				.getStream()
				.min(null)
				.get();
	}
	
	default Variable max() {
		return this.getCopy()
				.getStream()
				.max(null)
				.get();
	}
	
	default Variable get(int i) {
		return this.getCopy()
				.getStream()
				.collect(Collectors.toList())
				.get(i);
	}
	
	default Chaine print() {
		return new Chaine(this.getCopy()
				.getStream()
				.map(n -> n.toString())
				.collect(Collectors.joining(",")));
	}
	
	// Stream's treatment
	
	default void slice(int inf, int sup) {
		System.out.println("in slice" + inf + " " + sup);
		if (inf > sup ) {
			throw new IllegalArgumentException("left bound cannot be superior to left bound");
		}
		this.setStream(this.getCopy().getStream().skip((long) inf).limit(sup-inf));
	}
	
	default void repeat(Rational rat) {
		// TO DO
	}
	
	default void concat(VarStream s) {
		this.setStream(Stream.concat(this.getCopy().getStream(), s.getCopy().getStream()));
	}
	
	default void inter(VarStream vs) {
		this.setStream(
			this.getCopy()
			.getStream()
			.filter( 
					me -> vs.getStream()
					.anyMatch( vsv -> me.equals(vsv))
					));
	}
	
	default void sort() {
		this.setStream(this.getCopy().getStream().sorted());;
	}
	
	default void shuffle(int seed) {
		List<Variable> lst = this.getCopy().getStream().collect(Collectors.toList());
		Collections.shuffle(lst, new Random(seed));
		this.setStream(lst.stream());
	}
}
