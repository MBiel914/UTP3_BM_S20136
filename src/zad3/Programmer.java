package zad3;

import java.util.Set;
import java.util.HashSet;

public class Programmer {
	public String name;
	public Set languages = new HashSet<String>();
	
	public Programmer(String name, Set<String> langs) {
		this.name = name;
		this.languages = langs;
	}
	
	public Integer getLangsSize(){
		return languages.size();
	}

	@Override
	public String toString() {
		return "" + languages;
	}
}