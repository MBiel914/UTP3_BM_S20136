package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {	
	public XList() {
		super();
	}
	
	public XList(T...items) {
		super(Arrays.asList(items));
	}
	
	public XList(Collection<T> items) {
		super(items);
	}

	public static<T> XList<T> of(T... items) {
		return new XList<T>(items);
	}
	
	public static<T> XList<T> of(Collection<T> items) {
		return new XList<T>(items);
	}

	public static <T> XList<T> charsOf(String input)
	{
		List result  = new ArrayList<>();
		for (int counter = 0; counter < input.length(); counter++) {
			result.add(String.valueOf(input.charAt(counter)));
		}
		return new XList<T>(result);
	}

	public static <T> XList<T> tokensOf(String... inputs)
	{
		List result  = null;
		if(inputs.length == 1)
		{
			result = new ArrayList<String>(Arrays.asList(inputs[0].split("\\s")));
		} else {
			result = new ArrayList<String>(Arrays.asList(inputs[0].split(inputs[inputs.length - 1])));
		}
		return new XList<T>(result);
	}
	
	public XList<T> union(T... items) {
	    List<T> result = this;
	    result.addAll(Arrays.asList(items));
	    return new XList(result);
	}
	
	public XList<T> union(Collection<T> items)
	{
		List<T> result = this;
		result.addAll(items);
		
	    return new XList(result);
	}
	
	public XList<T> diff(Collection<T> items) {
		List<T> result = (List<T>) this.clone();
		result.removeAll(items);
		return new XList<T>(result);
	}
	
	public XList<T> unique()
	{
		List<T> result = this.stream().distinct().collect(Collectors.toList());
		return new XList<T>(result);
	}
	
	public XList<T> combine()
	{
		List<T> result = new ArrayList<>();
		T item;
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; j < this.size(); j++) {
				item = (T) ((this.get(i).toString()) + (this.get(j).toString()));
				result.add(item);
			}
		}
		return new XList<T>(result);
	}
	
	public void forEachWithIndex(BiConsumer<T,Integer> cunsumer) {
		for (int counter = 0; counter < this.size(); counter++) {
			cunsumer.accept(this.get(counter), counter);
		}
	}
}
