package sat;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import literals.AtomicFormula;
import literals.Implication;

public class Horn {
	public static LinkedList<AtomicFormula> atomics = new LinkedList<AtomicFormula>(); // Will contain all the Atomic Formuals in the Horn CNF
	public static void main(String[] args) {
		
			
			var implications = new LinkedList<Implication>();
			/*
			 * 
			 * Parsing the input into Atomic Formulas as well as Normal Forms
			 * 
			 */
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			var formFormula = new LinkedList<AtomicFormula>();//will contain the atomic fomulas in a clause
			Implication currentImplication;//for the loop
			for (int i = 1; i < input.length() - 1; i++) {
				//checking if the literal exist
				if (i < input.length() - 2 && (input.charAt(i) == ')' && input.charAt(i+1) == '^' && input.charAt(i+2) =='('))
				{
					i += 2;
					currentImplication = new Implication(formFormula);
					implications.add(currentImplication);
					
					//making them empty for the next clause and implication
					formFormula.clear();
					currentImplication = null;
				}
				else if (input.charAt(i) != '~' && input.charAt(i) != 'v') {
					AtomicFormula formula = new AtomicFormula(input.charAt(i),(input.charAt(i-1) == '~'));
					if (!atomics.contains(formula)) // If the formula is new
						atomics.add(formula); //then add it
					formFormula.add(formula); //form must have it wheter it's new or not
					
				}
				
				
			}
			//for the last implication: XXX: making it into a function?
			currentImplication = new Implication(formFormula);
			implications.add(currentImplication);
			System.out.println(implications);
			
			//STEP 2
			for (AtomicFormula atomicFormula : atomics) {
				System.out.println(atomicFormula.getName() + " : " + atomicFormula.getValue());
			}

			while(true)
			{
				boolean validFor = false; //Checks the condition of the loop
				for (Implication implies : implications) {
					if (implies.validateFor())
					{
						validFor = true;
						break;
					}
				}
				if (validFor)
				{
					for (int i = 0; i < 4; i++) {
						System.out.println("new:");
						try {
							for (Implication clause : implications) {
								clause.evaulate();
							}
						} catch (IllegalArgumentException e) {
							System.out.println("UNSAT: " + e.getMessage());
							System.exit(1);
						}
						for (AtomicFormula atomicFormula : atomics) {
							System.out.println(atomicFormula.getName() + " : " + atomicFormula.getValue());
						}
						
						
					}
				}
				else
					break;
			}
			System.out.println("SAT");
			
				
	}
	
}
