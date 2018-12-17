package streash.variables.stream.treatment;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class ShuffleStream implements VarStream {
	
	private final  VarType type = VarType.ShuffleStream;
	private final int rand;
	private VarStream stream;
	
	public ShuffleStream(int rand, VarStream stream) {
		this.rand = rand;
		this.stream = stream;
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.type + " of " + this.stream.getConsoleString();
	}

	@Override
	public VarStream getCopy() {
		return new ShuffleStream(this.rand, this.stream);
	}

	@Override
	public Stream<Variable> getStream() {
		List<Variable> lst = this.stream.getCopy().getStream().collect(Collectors.toList());
		Collections.shuffle(lst, new Random(this.rand));
		return lst.stream();
	}

}
