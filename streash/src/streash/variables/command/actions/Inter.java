package streash.variables.command.actions;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.InterStream;

public class Inter extends AbstractCommand {

	public Inter() {
		super(2, "Inter");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream && arr[1] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				VarStream s1 = (VarStream) arr[1];
				if (s.isFinite() && s1.isFinite()) {
					return new InterStream(s, s1);
				} else {
					throw new IllegalArgumentException("Those stream should be finite (sliced)");
				}
			} else {
				throw new IllegalArgumentException("Args should be of type VarStream");
			}
		}
		return null;
	}

}
