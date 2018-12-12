package streash.variables.command.actions;

import streash.variables.Rational;
import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Slice extends AbstractCommand {

	public Slice(int maxArg) {
		super(3);
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream && arr[1] instanceof Rational && arr[2] instanceof Rational) {
				VarStream s = (VarStream) arr[0];
				Rational r = (Rational) arr[1];
				Rational r2 = (Rational) arr[2];
				if (r.isInteger() && r2.isInteger()) {
					s.slice(r.intValue(), r2.intValue());
					return s;
				} else {
					throw new IllegalArgumentException("Args should be Integer");
				}
			} else if (arr[0] instanceof Rational && arr[1] instanceof VarStream && arr[2] instanceof Rational) {
				Rational r = (Rational) arr[0];
				VarStream s = (VarStream) arr[1];
				Rational r2 = (Rational) arr[2];
				if (r.isInteger() && r2.isInteger()) {
					s.slice(r.intValue(), r2.intValue());
					return s;
				} else {
					throw new IllegalArgumentException("Args should be Integer");
				}
			} else if (arr[0] instanceof Rational && arr[1] instanceof Rational && arr[2] instanceof VarStream) {
				Rational r = (Rational) arr[0];
				Rational r2 = (Rational) arr[1];
				VarStream s = (VarStream) arr[2];
				if (r.isInteger() && r2.isInteger()) {
					s.slice(r.intValue(), r2.intValue());
					return s;
				} else {
					throw new IllegalArgumentException("Args should be Integer");
				}
			} else {
				throw new IllegalArgumentException("Args should be of type VarStream x1 and Rational x2");
			}
		}
		return null;
	}

}
