package literals;

public class AtomicFormula {
	private char name; /* 
	* The name of formula. for Tautology I use 1 and for contradiction i use 0.
	* Also negative literals are reserved as lowercase characters but printing this litearls
	* will be showed as two characters "~A"
	* thus name is significint to check whether a literal is positive or not
	*/
	
	private boolean value; //Value of Atomic Formula
	
	public AtomicFormula(char letter,boolean isNegate) //Parsing the input
	{
		/*
		if (text.length() == 2 && text.charAt(0) == '~')
		{
			this.name = Character.toLowerCase(text.charAt(1));
			this.value = true; // ~ 0 = 1
		}
		else {
			this.name = text.charAt(0);
			this.value = false; //Assigning 0 to all positive literals. first step of SAT
		}
		*/
		if (isNegate)
		{
			this.name = Character.toLowerCase(letter);
			//this.value = true; // ~ 0 = 1
		}
		else {
			this.name = letter;
			//
		}
		this.value = false; //Assigning 0 to all positive literals. first step of SAT

		
		
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		//changin the value of Uppercase postive literal should change this too.
		this.value = value;
	}
	
	public char getName() {
		return name;
	}
	
	
	public void setPositive()//removing ~ negation. used in implication
	{
		this.name = Character.toUpperCase(this.name);
		
	}
	@Override
	public String toString() {
		return (Character.isLowerCase(this.name) ? 
				"~" : "") + Character.toUpperCase(this.name);
	}
	
	@Override
	public boolean equals(Object e)
	{
		if (e instanceof AtomicFormula &&
				String.valueOf(((AtomicFormula) e).getName()).equalsIgnoreCase(String.valueOf(this.getName())))
			return true;
		return false;
	}
	
	

}
