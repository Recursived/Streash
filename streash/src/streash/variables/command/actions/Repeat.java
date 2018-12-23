package streash.variables.command.actions;

import streash.variables.Rational;
import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.RepeatStream;

public class Repeat extends AbstractCommand {

	public Repeat() {
		super(2, "Repeat");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational && arr[1] instanceof VarStream) {
				VarStream s = (VarStream) arr[1];
				Rational r = (Rational) arr[0];
				if (s.isFinite() && r.isPositive()) {
					return new RepeatStream(s, r);
				} else {
					throw new IllegalArgumentException("The stream should be finite (sliced)");
				}
			} else if (arr[0] instanceof VarStream && arr[1] instanceof Rational) {
				VarStream s = (VarStream) arr[0];
				Rational r = (Rational) arr[1];
				if (s.isFinite() && r.isPositive()) {
					return new RepeatStream(s, r);
				} else {
					throw new IllegalArgumentException("The stream should be finite (sliced)");
				}
			} else {
				throw new IllegalArgumentException("Args should be Rational and VarStream");
			}
		}
		return null;
	}

}
