package streash.variables;

public enum VarType {
	
	Rational("Rational", ""),
	Chaine("Chaine", ""),
	IntegerStream("IntegerStream", "<integer>"),
	FiboStream("FiboStream", "<fibo>"),
	RandomStream("RandomStream", "<random>"),
	RevInteger("RevIntegerStream", "<revinteger"),
	SliceStream("SliceStream", "<slice>"),
	ConcatStream("ConcatStream", "<concat>"),
	InterStream("InterStream", "<inter>"),
	RepeatStream("RepeatStream", "<repeat>"),
	ShuffleStream("ShuffleStream", "<shuffle>"),
	SortStream("SortStream","<sort>");
	// Rajouter les manquants quand vous les faites
	
	
	private String name = "";
	private String commandSyntax = "";
	
	VarType(String name, String commandSyntax){
		this.name = name;
		this.commandSyntax= commandSyntax;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getCommandSyntax() {
		return this.commandSyntax;
	}
}
