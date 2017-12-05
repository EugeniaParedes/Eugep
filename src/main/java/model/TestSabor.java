package model;

public class TestSabor {

	public static void main(String[] args) {
		
		Sabor unSabor;
		unSabor = new Sabor(1, "Sambayon", "CREMA", "Huevo y Vino hecho helado", 38, false);
		Sabor t;
		t = new Sabor(2, "Tramontana", "CREMA", "Tiene cosas crocantes", 2, false);
		
		System.out.println( unSabor.getNombre() );
		System.out.println( t.getNombre() );
		

	}

}
