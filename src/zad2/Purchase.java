/**
 *
 *  @author Bielecki Michał S20136
 *
 */

package zad2;


public class Purchase {
	String id;
	String nazwisko;
	String imie;
	String nazwaTw;
	double cena;
	double ilosc;
	double koszt;

	public Purchase(String id,String nazwisko, String imie, String nazwaTw, double cena, double ilosc, double koszt) {
		this.id = id;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.nazwaTw = nazwaTw;
		this.cena = cena;
		this.ilosc = ilosc;
		this.koszt = koszt;
	}
	
	public String getId_klienta() {
		return id;
	}

	public String getNazwisko() {
		return nazwisko;
	}
	
	public double getKoszt() {
		return koszt;
	}
	
	@Override
	public String toString() {
		return id + ";" + nazwisko + " " + imie + ";" + nazwaTw + ";" + cena + ";" + ilosc;
	}
}
