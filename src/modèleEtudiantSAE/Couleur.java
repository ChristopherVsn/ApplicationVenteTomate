package modèleEtudiantSAE;

public enum Couleur {

	BLEU ("Bleu"), VERT("Vert"), ROUGE("Rouge"), ORANGE("Orange"), JAUNE("Jaune"), NOIR("Noir"), MULTICOLORE("Multicolore");
	private final String dénomination;

	private Couleur(String dénomination) {
		this.dénomination  = dénomination;
	}

	public String getDénomination() {
		return this.dénomination;
	}
	
	public static Couleur getCouleur(String dénomination) {
		for (Couleur c : Couleur.values()) {
			if (c.getDénomination().equals(dénomination)) {
				return c;
			}
		}
		return null;
	}

}
