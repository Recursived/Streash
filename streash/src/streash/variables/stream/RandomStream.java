package streash.variables.stream;
import java.math.BigInteger;
import java.util.Random;
import java.util.stream.Stream;
import streash.variables.*;

public class RandomStream implements VarStream {
	
	private final int from;
	private final int to;
	private final int seed;
	private final VarType type = VarType.RandomStream;
	private Stream<Variable> stream;
	
	public RandomStream(int from, int to, int seed) {
		if (from > to) {
			throw new IllegalArgumentException("first int argument has to be inferior to second int argument");
		}
		this.from = from;
		this.to = to;
		this.seed = seed;
		Random r = seed != 1 ? new Random(seed) : new Random();
		this.stream = r.ints(from, to + 1).mapToObj(value -> new Rational(BigInteger.valueOf(value)));
	}
	
	@Override
	public VarType getVariableType() {
		return this.type;
	}
	
	@Override
	public String getConsoleString() {
		return this.type + " going from " + this.from + " to " + this.to + " with seed " + this.seed;
	}
	
	@Override
	public VarStream getCopy() {
		return new RandomStream(this.from, this.to, this.seed);
	}
	
	@Override
	public Stream<Variable> getStream() {
		return this.stream;
	}

	
}
