package streash.variables.stream.treatment;

import java.util.stream.Stream;

import streash.variables.VarStream;
import streash.variables.VarType;
import streash.variables.Variable;

public class SliceStream implements VarStream {
	
	private final int inf;
	private final int sup;
	private VarStream stream;
	private final VarType type = VarType.SliceStream;
	
	public SliceStream(int inf, int sup, VarStream s ) {
		// Verifier si on lance l'erreur
		if (inf > sup ) {
			throw new IllegalArgumentException("left bound cannot be superior to left bound");
		}
		this.inf = inf;
		this.sup = sup;
		this.stream = s;
	}

	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		// TODO Auto-generated method stub
		return this.type + " ranging from " + this.inf + " to " + this.sup + " of " + this.stream.getConsoleString();
	}

	@Override
	public VarStream getCopy() {
		System.out.println("in getcopy of slice");
		return new SliceStream(this.inf, this.sup, this.stream);
	}

	@Override
	public Stream<Variable> getStream() {
		System.out.println("in getstream of slice");
		return this.stream.getCopy().getStream().skip((long) inf).limit(sup-inf);
	}



}
