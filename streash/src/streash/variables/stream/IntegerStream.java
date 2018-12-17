package streash.variables.stream;

import java.math.BigInteger;
import java.util.stream.Stream;

import streash.variables.*;

public class IntegerStream implements VarStream {
	
	private final VarType type = VarType.IntegerStream;
	private final int from;
	private Stream<Variable> stream;

	public IntegerStream(int from) {
		this.from = from;
		this.stream = Stream.iterate(from, i -> i + 1).map(i -> new Rational(BigInteger.valueOf(i)));
	}
	
	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " going from " + this.from + " to +inf";
	}

	@Override
	public VarStream getCopy() {
		return new IntegerStream(this.from);
	}

	@Override
	public Stream<Variable> getStream() {
		return this.stream;
	}



}
