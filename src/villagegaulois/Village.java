package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private int nb_etal;

	public Village(String nom, int nbVillageoisMaximum, int nb_etal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nb_etal=nb_etal;
		this.marche= new Marche(nb_etal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	private static class Marche{
		private Etal[] etals;
		private int nb_etal;
		
		private Marche(int nb_etal) {
			this.etals = new Etal[nb_etal];
			this.nb_etal = nb_etal;
			
			for(int i=0; i<nb_etal; i++) {
				etals[i]=new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			int i=0, indiceLibre=-1;
			
			while(i< etals.length || indiceLibre!=-1) {
				if (!(etals[i].isEtalOccupe())) {
					indiceLibre=i;
				}
			}
			
			return indiceLibre;
				
			}
		
		private Etal[] trouverEtals(String produit) {
			int nb_produit=0, j=0;
			
			for(int i=0; i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					nb_produit++;
				}
			}
			
			Etal[] etal_prod= new Etal[nb_produit];
			
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					etal_prod[j]=etals[i];
					j++;
				}
			}
			
			return etal_prod;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			Etal etal=null;
			int i=0;
			while (i<etals.length || etal!= null) {
				
				if (etals[i].isEtalOccupe()) {
					if(etals[i].getVendeur()==gaulois) {
						etal=etals[i];
					}
				}
			}
			
			return etal;
		}
		
		}
		

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		System.out.println(vendeur.getNom()+ " cherche un endroit pour vendre " + nbProduit + produit);
		int i= marche.trouverEtalLibre(); // trouver un etal libre
		if(i!=-1) {
			
		marche.utiliserEtal(i, vendeur, produit, nbProduit);
		
		}
		return "";
	}
	
}