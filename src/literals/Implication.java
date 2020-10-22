package literals;

import java.io.InputStream;
import java.util.LinkedList;

import sat.Horn;

public class Implication {
	
	private LinkedList<AtomicFormula> antecedents = new LinkedList<AtomicFormula>(); //final?
	private AtomicFormula consequentFormula = null;
	public Implication(LinkedList<AtomicFormula> inputFormulas) //making the implication
	{
		//if there is no consequent then consequent is a new atomic formula having the name of 0
		//same goes for antecedents which maybe 1
		
		if (inputFormulas.size() == 1 && inputFormulas.peek().toString().length() == 1) //is positive
		{
				//inputFormulas.get(0).setValue(true);
				consequentFormula = inputFormulas.pop();
				antecedents.add(new AtomicFormula('1', false));
		}
		else {
			for (AtomicFormula atomicFormula : inputFormulas) {
				if (atomicFormula.toString().charAt(0) == '~')
				{
					atomicFormula.setPositive();
					antecedents.add(atomicFormula);
				}
				else {
					consequentFormula = atomicFormula;
				}
			}
		}
		if (consequentFormula == null) //meaning no positive literal found
		{
			consequentFormula = new AtomicFormula('0', true);
		}
	}
	
	public boolean evaluateAntecedents()
	{
		for (AtomicFormula atomicFormula : Horn.atomics) {
			if (antecedents.contains(atomicFormula) && !atomicFormula.getValue())
				return false;
		}
		return true;
	}
	
	
	public void evaulate()
	{
		if (antecedents.peek().getName() == '1')
		{
			Horn.atomics.get(Horn.atomics.indexOf(consequentFormula)).setValue(true);
			//consequentFormula.setValue(true);
		}
		if (evaluateAntecedents() == true && consequentFormula.getName() == '0') {
			throw new IllegalArgumentException(this.toString());
		}	
		if (evaluateAntecedents() && !Horn.atomics.get(Horn.atomics.indexOf(consequentFormula)).getValue()) {
			Horn.atomics.get(Horn.atomics.indexOf(consequentFormula)).setValue(true);
		}
		
	}
	
	public boolean validateFor() //Check if the implication is in the form that makes the main FOR LOOP valid
	{
		if (evaluateAntecedents() &&
				consequentFormula.getName() != '1' &&
				consequentFormula.getValue() == false)
			return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		output.append("(");
		for (int i = 0; i < antecedents.size(); i++) {
			output.append(antecedents.get(i).toString() + ((i != antecedents.size() - 1) ? "^" : ""));
		}
		output.append("->" + consequentFormula.toString() + ")");
		return output.toString();
	}
	
	
}
