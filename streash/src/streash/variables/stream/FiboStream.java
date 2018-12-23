package streash.variables.stream;

import java.math.BigInteger;
import java.util.stream.Stream;

import streash.variables.*;

public class FiboStream implements VarStream {
	
	private final VarType type = VarType.FiboStream;
	private final int first;
	private final int second;
	private Stream<Variable> stream;
	
	public FiboStream(int first, int second) {
		this.first = first;
		this.second = second;
		this.stream = Stream.iterate(new Rational[]
				{new Rational(BigInteger.valueOf(first)), 
				new Rational(BigInteger.valueOf(second))}, 
				s -> new Rational[]
						{s[1], s[0].add(s[1])})
				.map(n->n[0]);
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " having for first terms : " + this.first + ", " + this.second;
	}

	@Override
	public VarStream getCopy() {
		return new FiboStream(this.first, this.second);
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
