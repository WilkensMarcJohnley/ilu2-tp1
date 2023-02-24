package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Gaulois obelix = null;
		
		Etal etal = new Etal();
		
		try {
			etal.libererEtal();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test liberer etal");
		
		String text;
		try {
			text= etal.acheterProduit(5, obelix);
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test acheter produit");
		
		obelix= new Gaulois("Obélix", 25);
		etal.occuperEtal(obelix, "fleurs", 5);
		try {
			etal.acheterProduit(-1, obelix);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test quantite negative");
		
		Village village = new Village("le village des irréductibles", 10, 5);
		
		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin du test afficher villageois");
		
		}
	
		
}
