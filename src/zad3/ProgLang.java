package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang<K, V> {
private Map<K,V> _langMap;
private Map<String, Set<String>> _progMap;
private Map<String,Programmer> _progManMap;

public ProgLang(String fileName) throws NoSuchFieldException, SecurityException {
	_langMap = new LinkedHashMap<K,V>();
	
	try {
		BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));
		String line;
	
		while ((line = bufferReader.readLine()) != null)
		{
		    String[] elements = line.split("\\t");
			List<String> elementsList = new ArrayList<String>();
			
		    for(int i = 1; i < elements.length; i ++)
		    	elementsList.add(elements[i]);
	
		    elementsList = elementsList.stream().distinct().collect(Collectors.toList());
		    		
		    _langMap.put((K)elements[0],(V)elementsList);
		}
		bufferReader.close();
	} catch (IOException ex) {
		System.err.println("ProgLang constructor error: " + ex.getMessage());
	}
}

public <K2, V2> Map<K,V> getLangsMap() {      
	return (Map<K, V>) _langMap;
}

public Map<String, Set<String>> getProgsMap() {
	_progMap = new LinkedHashMap<String,Set<String>>();
	_progManMap = new LinkedHashMap<String,Programmer>();
	
	for (K itemKey : _langMap.keySet()) 
	    for (V itemName : (List<V>)_langMap.get(itemKey)) {
	    	if(_progMap.containsKey(itemName))
	    	{
	    		_progMap.get(itemName).add((String) itemKey);
	    	} else
	    	{
        	    Set<String> langs = new HashSet<>();
        	    
            	langs.add((String) itemKey);
            	
            	_progMap.put((String) itemName, langs);
            	_progManMap.put((String) itemName, new Programmer((String) itemName,langs));
            }
		}
	
	return _progMap;
}

public Map<K,V> getLangsMapSortedByNumOfProgs() {
	Map<K, V> result = (LinkedHashMap<K, V>) this._langMap.entrySet().stream()
			.sorted((e1,e2) -> {
								List<String> l1 = (List<String>) e1.getValue();
								List<String> l2 = (List<String>) e2.getValue();
								Integer s1 = l1.size();
								Integer s2 = l2.size();
								return s2.compareTo(s1);
								})
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	
	return result;
}

public Map<K,V> getProgsMapSortedByNumOfLangs() {
	Map<K, V> result = (LinkedHashMap<K, V>) this._progManMap.entrySet().stream()
			.sorted((e1,e2) -> e2.getValue().getLangsSize().compareTo(e1.getValue().getLangsSize()))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	
	return result;
}

public Map<K,V> getProgsMapForNumOfLangsGreaterThan(int i) {
	Map<K, V> result = (LinkedHashMap<K, V>) this._progManMap.entrySet().stream()
			.filter(e -> e.getValue().getLangsSize() > i)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
	
	return result;
}

public static <K,V> Map <K,V> sorted(Map<K,V> mapArg, Comparator<Map.Entry<K,V>> comp) {
	Map<K,V> result = mapArg.entrySet().stream()
			.sorted(comp)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e2, LinkedHashMap::new));
	
	return result;
}

public static <K,V> Map <K,V> filtered(Map<K,V> mapArg, Predicate<Map.Entry<K,V>> pred) {
	Map<K,V> result = mapArg.entrySet().stream()
			.filter(pred)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
	
	return result;
}
}
