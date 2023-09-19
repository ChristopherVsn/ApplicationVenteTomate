package modèleEtudiantSAE;

import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Tomates {

	public static final String CONSEILS_DE_CULTURE_TITRE = "Conseils de culture\r\n" + "Semis : mars-avril\r\n"
			+ "Repiquage : après les gelées\r\n" + "Récolte : juillet à septembre, voire octobre";

	public static final String CONSEILS_DE_CULTURE = new String("Les tomates sont frileuses !\r\n" + "\r\n"
			+ "Exposition : Les tomates sont originaires d’Amérique du Sud…et leurs gènes s’en souviennent !\r\n"
			+ "Réservez-leur l’endroit le mieux ensoleillé de votre jardin.\r\n" + "\r\n"
			+ "Semis : Démarrez vos semis en petite terrine dès mars / avril (15/20° nuit et jour) dans du terreau à semis, "
			+ "couvrez vos graines de 0,5 cm, tassez doucement et maintenez humide. "
			+ "Repiquez vos semis lorsqu’ils font 5 cm, dans des godets avec du terreau 1/3 de fumier ou du compost , enterrez jusqu’au première feuilles.\r\n"
			+ "\r\n" + "Après les Saints de glaces, plantez vos pieds de tomates dès qu’ils auront atteint 15 cm, "
			+ "enterrez-les jusqu’aux premières feuilles en pleine terre dans un trou avec du fumier, du compost ou quelques feuilles d’ortie si besoin, "
			+ "installez vos tuteurs espacés de 70 cm.\r\n"
			+ "Arrosez abondamment les 3 premiers jours, arrêtez les 15 jours suivants puis arrosez régulièrement.\r\n"
			+ "\r\n"
			+ "Pensez à pailler ! ainsi vous garderez beaucoup plus facilement la terre humide et espacerez les désherbages.\r\n"
			+ "\r\n"
			+ "Maladies pouvant toucher les tomates: mildiou (ne pas arroser les feuilles et supprimer celles qui touchent le sol).\r\n"
			+ "\r\n" + "Plantez du basilic entre vos pieds de tomates, ils s’entraident l’un l’autre…");

	private List<Tomate> lesTomates;

	public Tomates() {
		this.lesTomates = new LinkedList<Tomate>();
	}

	public void addTomates(List<Tomate> tomates) {
		this.lesTomates.addAll(tomates);
	}

	public List<Tomate> getLesTomates() {
		return this.lesTomates;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("Liste des graines de tomates : \n\n");
		for (Tomate graine : this.lesTomates) {
			res.append(graine.toString() + '\n');
		}
		return res.toString();
	}

	public List<Tomate> tomatesDeType(TypeTomate typeTomate) {
		return this.tomatesDeTypeAvecListe(typeTomate, this.lesTomates);
	}

	private List<Tomate> tomatesDeTypeAvecListe(TypeTomate typeTomate, List<Tomate> lesTomates) {
		List<Tomate> lTomate = new LinkedList<>();
		for (Tomate t : lesTomates) {
			if (t.getTypeGraine() == typeTomate) {
				lTomate.add(t);
			}
		}
		return lTomate;
	}

	public List<Tomate> tomatesDeCouleur(Couleur couleur) {
		return this.tomatesDeCouleurAvecListe(couleur, this.lesTomates);
	}

	private List<Tomate> tomatesDeCouleurAvecListe(Couleur couleur, List<Tomate> lesTomates) {
		List<Tomate> lTomate = new LinkedList<>();
		for (Tomate t : lesTomates) {
			if (t.getCouleur() == couleur) {
				lTomate.add(t);
			}
		}
		return lTomate;
	}

	public List<Tomate> tomatesDetypeDeCouleur(TypeTomate typeTomate, Couleur couleur) {
		List<Tomate> lTomate = new LinkedList<>();
		for (Tomate t : this.lesTomates) {
			if (t.getCouleur() == couleur && t.getTypeGraine() == typeTomate) {
				lTomate.add(t);
			}
		}
		return lTomate;
	}

	public Tomate getTomate(String designation) {
		for (Tomate graine : this.lesTomates) {
			if (graine.getDésignation().equals(designation))
				return graine;
		}
		return null;
	}

	public void afficherTomates(DefaultListModel<String> liste, String couleur, String type) {
		liste.removeAllElements();
		for (Tomate tomate : this.getLesTomates()) {
			if ((tomate.getCouleur().getDénomination() == couleur || couleur == "Couleurs")
					&& (tomate.getTypeGraine().getDénomination() == type || type == "Toutes les tomates")) {
				liste.addElement(tomate.getDésignation());
			}
		}
	}
}
