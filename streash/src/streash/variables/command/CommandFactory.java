package streash.variables.command;

import streash.variables.command.actions.*;
import streash.variables.command.basics.*;
import streash.variables.command.pits.*;
import streash.variables.command.sources.*;



public class CommandFactory {
	
	public static Command createCommand(String c) {
		switch(c) {
			case "<add>":
				return new Add();
			case "<sub>":
				return new Sub();
			case "<mul>":
				return new Mul();
			case "<div>":
				return new Div();
			case "<concat>":
				return new Concat();
			case "<inter>":
				return new Inter();
			// Il faut ajouter repeat quand il sera fait
			case "<shuffle>":
				return new Shuffle();
			case "<slice>":
				return new Slice();
			case "<sort>":
				return new Sort();
			case "<average>":
				return new Average();
			case "<get>":
				return new Get();
			case "<len>":
				return new Len();
			case "<max>":
				return new Max();
			case "<min>":
				return new Min();
			case "<print>":
				return new Print();
			case "<product>":
				return new Product();
			case "<sum>":
				return new Sum();
			case "<fibo>":
				return new FiboCommand();
			case "<integers>":
				return new IntegerCommand();
			case "<revintegers>":
				return new RevintegerCommand();
			case "<random>":
				return new RandomCommand();
			default:
				System.out.println("no command created");
				return null;
		}
		
	}
}
