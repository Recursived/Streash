
package streash.variables;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public interface VarStream extends Variable {
	
	// Miscelleanous
	VarStream getCopy();
	Stream<Variable> getStream();
	boolean isFinite();
	
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
				.min(Variable::compare)
				.get();
	}
	
	default Variable max() {
		return this.getCopy()
				.getStream()
				.max(Variable::compare)
				.get();
	}
	
	default Variable get(int i) {
		System.out.println(this.getCopy().getStream().collect(Collectors.toList()));
		return this.getCopy()
				.getStream()
				.collect(Collectors.toList())
				.get(i);
	}
	
	default Chaine print() {
		return new Chaine(this.getCopy()
				.getStream()
				.map(n -> n.toString())
				.collect(Collectors.joining(", ")));
	}
	
}
