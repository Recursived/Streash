package streash.variables.stream.treatment;

import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class ConcatStream implements VarStream {
	
	private final VarType type = VarType.ConcatStream;
	private final VarStream s;
	private final VarStream s1;
	
	public ConcatStream(VarStream s, VarStream s1) {
		this.s = s;
		this.s1 = s1;
	}
	
	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " : " + s.getConsoleString() + " concatened with "  + s1.getConsoleString();
	}

	@Override
	public VarStream getCopy() {
		return new ConcatStream(this.s, this.s1);
	}

	@Override
	public Stream<Variable> getStream() {
		return Stream.concat(this.s.getCopy().getStream(), this.s1.getCopy().getStream());
	}

	@Override
	public boolean isFinite() {
		return true;
	}

}
