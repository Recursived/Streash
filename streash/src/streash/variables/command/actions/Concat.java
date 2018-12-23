package streash.variables.command.actions;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.ConcatStream;

public class Concat extends AbstractCommand {

	public Concat() {
		super(2, "Concat");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream && arr[1] instanceof VarStream ){
				VarStream s = (VarStream) arr[0];
				VarStream s1 = (VarStream) arr[1];
				if (s.isFinite() && s1.isFinite()) {
					return  new ConcatStream(s1, s);
				} else {
					throw new IllegalArgumentException("Those VarStream should be finite (sliced)");
				}
			} else {
				throw new IllegalArgumentException("Args should be of type VarStream");
			}
			
		}
		return null;
	}

}
