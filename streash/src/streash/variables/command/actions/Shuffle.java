package streash.variables.command.actions;

import streash.variables.Rational;
import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Shuffle extends AbstractCommand {

	public Shuffle() {
		super(2, "Shuffle");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream && arr[1] instanceof Rational) {
				VarStream s = (VarStream) arr[0];
				Rational r = (Rational) arr[1];
				if (r.isInteger()) {
					s.shuffle(r.intValue());
					return s;
				} else {
					throw new IllegalStateException("Arg should be an int");
				}
			} else if (arr[0] instanceof Rational && arr[1] instanceof VarStream) {
				VarStream s = (VarStream) arr[1];
				Rational r = (Rational) arr[0];
				if (r.isInteger()) {
					s.shuffle(r.intValue());
					return s;
				} else {
					throw new IllegalStateException("Arg should be an int");
				}
			} else {
				throw new IllegalArgumentException("args should be of type rational x1 and VarStream x1");
			}
		}
		return null;
	}

}
