package streash.variables.stream.treatment;

import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class InterStream implements VarStream {
	
	private final VarType type = VarType.InterStream;
	private final VarStream s;
	private final VarStream s1;
	
	public InterStream(VarStream s, VarStream s1) {
		this.s = s;
		this.s1 = s1;
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " : " + s.getConsoleString() + " intersected with " + s1.getConsoleString();
	}

	@Override
	public VarStream getCopy() {
		return new InterStream(this.s, this.s1);
	}

	@Override
	public Stream<Variable> getStream() {
		return s.getCopy()
				.getStream()
				.filter(
						me -> s1.getCopy()
						.getStream()
						.anyMatch( vsv -> me.equals(vsv))
						);
	}

	@Override
	public boolean isFinite() {
		return true;
	}
	
}
