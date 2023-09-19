package modèleEtudiantSAE;

public enum TypeTomate {
	
	TOMATES_CERISES("Cerises & Cocktails (16)"), TOMATES("Autres Tomates (47)");
	
	private final String dénomination;

	private TypeTomate(String dénomination) {
		this.dénomination  = dénomination;
	}

	public String getDénomination() {
		return this.dénomination;
	}
	
	public static TypeTomate getTypeTomate(String dénomination) {
		for (TypeTomate t : TypeTomate.values()) {
			if (t.getDénomination().equals(dénomination)) {
				return t;
			}
		}
		return null;
	}

}
