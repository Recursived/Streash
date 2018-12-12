package streash.variables;

public class Chaine implements Variable {
	
	private final String str;
	private final VarType type = VarType.Chaine;
	
	public Chaine(String str) {
		this.str = str;
	}
	
	@Override
	public VarType getVariableType() {
		return this.type;
	}

	@Override
	public String getConsoleString() {
		return this.str;
	}

}
