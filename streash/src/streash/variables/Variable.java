package streash.variables;

import java.io.Serializable;

public interface Variable {
	
	VarType getVariableType();
	String getConsoleString();
	static int compare(Variable a, Variable b) {
		if (a instanceof Rational && b instanceof Rational) {
			Rational a1 = (Rational) a;
			Rational b1 = (Rational) b;
			return Rational.compare(a1, b1);
		} else if (a instanceof Chaine && b instanceof Chaine) {
			Chaine a1 = (Chaine) a;
			Chaine b1 = (Chaine) b;
			return Chaine.compare(a1, b1);
		} else {
			throw new IllegalArgumentException("You should compare Chaine with Chaine or Rational with Rational");
		}
	}
	
}
