package streash.variables.stream.treatment;

import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class SortStream implements VarStream{
	private final VarType type = VarType.SortStream;
	private VarStream stream;
	
	public SortStream(VarStream stream) {
		this.stream = stream.getCopy();
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " of stream : " + stream.getConsoleString();
	}

	@Override
	public VarStream getCopy() {
		return new SortStream(this.stream);
	}

	@Override
	public Stream<Variable> getStream() {
		return this.getCopy().getStream().sorted();
	}
}
