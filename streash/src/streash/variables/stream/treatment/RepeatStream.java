package streash.variables.stream.treatment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import streash.variables.Rational;
import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class RepeatStream implements VarStream{
	
	private final VarStream stream;
	private final Rational r;
	private final VarType type = VarType.RepeatStream;
	
	public RepeatStream(VarStream s, Rational r) {
		this.stream = s;
		this.r = r;
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " : " + stream.getConsoleString() + " (x" + this.r.toString() + ")"; 
	}

	@Override
	public VarStream getCopy() {
		return new RepeatStream(this.stream, this.r);
	}

	@Override
	public Stream<Variable> getStream() {
		double val = this.r.doubleValue();
		BigDecimal bd = new BigDecimal(val % 1).setScale(1, RoundingMode.DOWN);
		double aftercomma = bd.doubleValue();
		int beforecomma = (int) val;
		int limit = (int) (stream.getCopy().getStream().count() * aftercomma);
		
		Stream<Variable> s = this.stream.getCopy().getStream();
		
		for (int i = 1; i < beforecomma; i++) {
			s = Stream.concat(s, this.stream.getCopy().getStream());
		}
		
		s = Stream.concat(s, this.stream.getCopy().getStream().limit(limit));
		
		return s;
	}

	@Override
	public boolean isFinite() {
		return true;
	}

}
