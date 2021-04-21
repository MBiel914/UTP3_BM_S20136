/**
 *
 *  @author Bielecki Michał S20136
 *
 */

package zad2;


public class Purchase {
	String id;
	String surname;
	String name;
	String productName;
	double price;
	double qty;
	double cost;

	public Purchase(String id,String surname, String name, String productName, double price, double qty, double cost) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
		this.cost = cost;
	}
	
	public String getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}
	
	public double getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return id + ";" + surname + " " + name + ";" + productName + ";" + cost + ";" + qty;
	}
}
