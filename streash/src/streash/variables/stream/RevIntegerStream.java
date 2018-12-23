package streash.variables.stream;

import java.math.BigInteger;
import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;
import streash.variables.Rational;;

public class RevIntegerStream implements VarStream{
	
	private final VarType type = VarType.RevInteger;
	private final int from;
	private Stream<Variable> stream;
	
	public RevIntegerStream(int from) {
		this.from = from;
		this.stream = Stream.iterate(from, i -> i -1).map(i -> new Rational(BigInteger.valueOf(i)));
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type.toString() + " going from " + this.from + " to -inf";
	}

	@Override
	public VarStream getCopy() {
		return new RevIntegerStream(this.from);
	}

	@Override
	public Stream<Variable> getStream() {
		return this.stream;
	}

	@Override
	public boolean isFinite() {
		return false;
	}



}
