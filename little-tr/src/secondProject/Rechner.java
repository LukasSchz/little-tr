package secondProject;

import java.util.LinkedList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Rechner {

	private String rechnung;
	private char[] zeichen = {'+', '-', '*', '/', '(', ')'};
    private LinkedList<Character> bestandteile = new LinkedList<Character>();
	private int ergebnis;
	private ScriptEngineManager mgr;
	private ScriptEngine engine;
	
	public Rechner(String rechnung) {
		this.rechnung = rechnung;
		try{
			convert();
		} catch(ScriptException s) {
			s.getMessage();
		}
	}
	
	public void convert() throws ScriptException{
		if(convertCheck()) {
			mgr = new ScriptEngineManager();
			engine = mgr.getEngineByName("JavaScript");
			System.out.println("Die Rechnung lautet: " + rechnung);
			if(engine.eval(rechnung) instanceof Integer) {
				ergebnis = (Integer) engine.eval(rechnung);
			}
		}
	}
	
	public void ausgabeZulassen() {
		if(check()) {
			System.out.println("Das Ergebnis der Rechnung lautet: " + ergebnis);
		} else {
			System.out.println("Die Rechnung ist nicht zulässig!");
		}
	}
	
	public boolean convertCheck() {
		char[] bestandteil = rechnung.toCharArray();
		for(char x: bestandteil) {
			bestandteile.add(x);
		}
		anpassen();
		Character[] array = bestandteile.toArray(new Character[bestandteile.size()]);
		rechnung = arrayToString(array);
		System.out.println(bestandteile.size());
		return check();
	}
	
	public String arrayToString(Character[] array) {
		StringBuilder sb = new StringBuilder();
		for(Character c: array) {
			if(c.equals('[') || c.equals(']') || c.equals(',')) {
				break;
			}
			sb.append(c);
		}
		return sb.toString();
	}
	
	public boolean check() {
		if(!(Character.isDigit(bestandteile.getLast())) && bestandteile.getLast() != ')') {
			return false;
		}
		for(char teil: bestandteile) {
			if(Character.isLetter(teil)) {
				return false;
			}
		}
		return true;
	}
	
	public void anpassen() {
		for(int i = 0; i < bestandteile.size() - 1; i++) {
			if(bestandteile.get(i) == '0') {;
					if(i == 0) {
						if(counter(i+1) == 0) {
								bestandteile.remove(i);
						}
					} else{
						if(counter(i-1) > 0) {
								if(counter(i+1) == 0) {
										bestandteile.remove(i);
								}
						} 
					}
			}
		}
	}
	
	public int counter(int position) {
		int count = 0;
		for(char wert: zeichen) {
			if(bestandteile.get(position) == wert) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public String toString() {
		if(check()) {
			return Integer.toString(ergebnis);
		} else {
			return "Diese Rechnung ist nicht zulässig";
		}
	}	
}
