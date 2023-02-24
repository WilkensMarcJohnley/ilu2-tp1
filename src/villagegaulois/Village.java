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
	
	
	public int getNb_etal() {
		return nb_etal;
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
			int i=0;
			
			while(i< etals.length) {
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
				i+=1;
			}
			
			return -1;
				
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
		
		private Etal trouverVendeur(Gaulois gaulois) {  
			int i=0;
			while (i<etals.length ) {
				
				if (etals[i].isEtalOccupe()) {
					if((etals[i].getVendeur()).equals(gaulois)) {
						return etals[i];
					}
				}
				i+=1;
			}
			
			return null;
		}
		
		private String afficheMarche() {
			StringBuilder chaine= new StringBuilder();
			int nb_etal_vide=0;
			
			for(int i=0; i<etals.length;i++) {
				
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nb_etal_vide+=1;
				}
			}
			
			if (nb_etal_vide>0) {
				chaine.append("Il reste "+nb_etal_vide+ " étals non utilisés dans le marché.\n");
			}
			
			return chaine.toString();
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

	public String afficherVillageois() throws VillageSansChefException{
		if(chef==null) {
			throw new VillageSansChefException("Un village ne peut pas exister sans un chef");
		}
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
		StringBuilder chaine =new StringBuilder();
		chaine.append(vendeur.getNom()+ " cherche un endroit pour vendre " + nbProduit +" "+ produit+".\n");
		int i= marche.trouverEtalLibre(); // trouver un etal libre
		
		if(i!=-1) {
			marche.utiliserEtal(i, vendeur, produit, nbProduit);
			chaine.append("Le vendeur "+ vendeur.getNom()+" vend des fleurs a l'etal numero "+(i+1)+".\n");
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des fleurs sont :\n");
		for (int i = 0; i < marche.etals.length; i++) {
			if (marche.etals[i].isEtalOccupe()) {
				if (marche.etals[i].contientProduit(produit)) {
					chaine.append("- "+marche.etals[i].getVendeur().getNom()+"\n");
				}
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		for (int i = 0; i < marche.etals.length; i++) {
			if (marche.etals[i].getVendeur().equals(vendeur)) {
				return marche.etals[i];
			}
		}
		
		return null;
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine= new StringBuilder();
		Etal etal= rechercherEtal(vendeur);
		chaine.append(etal.libererEtal());
		
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine= new StringBuilder();
		chaine.append("Le marché du village "+nom+" possède plusieurs étals :\n");
		chaine.append(marche.afficheMarche());
		
		return chaine.toString();
	}
}