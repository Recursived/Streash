package streash.variables.stream.treatment;

import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class SliceStream implements VarStream {
	
	private final int inf;
	private final int sup;
	private VarStream stream;
	private VarType type = VarType.SliceStream;
	
	public SliceStream(int inf, int sup, VarStream s ) {
		// Verifier si on lance l'erreur
		this.inf = inf;
		this.sup = sup;
		this.stream = s.getCopy();
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		// TODO Auto-generated method stub
		return this.type + " ranging from " + this.inf + " to " + this.sup;
	}

	@Override
	public VarStream getCopy() {
		return new SliceStream(this.inf, this.sup, this.stream.getCopy());
	}

	@Override
	public Stream<Variable> getStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStream(Stream<Variable> stream) {
		// TODO Auto-generated method stub
		
	}

}
