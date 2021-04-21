/**
 *
 *  @author Bielecki Michał S20136
 *
 */

package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
	private List<Purchase> _purchaseList = null;
	private File _file =  null; 
	private Scanner _scanner = null;
	private Purchase _purchase = null;
	
	public void readFile(String fname) {
		_purchaseList = new ArrayList<Purchase>();
		try {
			_file = new File(fname);
			_scanner = new Scanner(_file);
			
			while (_scanner.hasNextLine())
			{
				String[] splited = _scanner.nextLine().split(";| ");
				
				_purchase = new Purchase(splited[0],
											splited[1], 
											splited[2], 
											splited[3], 
											Double.parseDouble(splited[4]), 
											Double.parseDouble(splited[5]),
											Double.parseDouble(splited[4]) * Double.parseDouble(splited[5])
											);
			 
				_purchaseList.add(_purchase); 
			} 
		} catch (FileNotFoundException ex) {
			System.err.println("Read file error: " + ex.getMessage());
		}	
	}

	public void showSortedBy(String sortType) {
		Comparator<Purchase> sortBySurname = Comparator.comparing(Purchase::getSurname).thenComparing(Purchase::getId);
		Comparator<Purchase> sortByCost = Comparator.comparingDouble(Purchase::getCost).reversed().thenComparing(Purchase::getId);
		
		switch (sortType) {
			case "Nazwiska":
				System.out.println(sortType);
				
				_purchaseList.stream().sorted(sortBySurname).forEach(System.out::println);
				break;
	
			case "Koszty":
				System.out.println(sortType);
				
				_purchaseList.stream().sorted(sortByCost).forEach(e -> System.out.println(e + " (koszt: " + e.cost + ")"));
				break;
		}

		System.out.println();
	}

	public void showPurchaseFor(String id) {
		System.out.println("Klient " + id);
		_purchaseList.stream().filter(e -> id.equals(e.getId())).forEach(System.out::println);
		
		System.out.println();
	}
}
