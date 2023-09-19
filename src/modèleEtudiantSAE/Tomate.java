package modèleEtudiantSAE;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Tomate {

	private TypeTomate typeGraine;
	private Couleur couleur;
	private String désignation;
	private String sousTitre;
	private String nomImage;
	private String description;
	private int nombreDeGraines;
	private float prixTTC;
	private boolean disponible;
	private List<Tomate> tomatesApparentées;

	public Tomate(TypeTomate typeGraine, Couleur couleur, String désignation, String sousTitre, String nomImage,
			String description, int nombreDeGraines, float prixTTC) {
		this.typeGraine = typeGraine;
		this.couleur = couleur;
		this.désignation = désignation;
		this.sousTitre = sousTitre;
		this.nomImage = nomImage;
		this.description = description;
		this.nombreDeGraines = nombreDeGraines;
		this.prixTTC = prixTTC;
		this.setDisponible(true);
		this.tomatesApparentées = new LinkedList<Tomate>();
	}

	public TypeTomate getTypeGraine() {
		return this.typeGraine;
	}

	public Couleur getCouleur() {
		return this.couleur;
	}

	public String getDésignation() {
		return this.désignation;
	}

	public String getNomImage() {
		return this.nomImage;
	}

	public String getDescription() {
		return this.description;
	}

	public int getNombreDeGraines() {
		return this.nombreDeGraines;
	}

	public float getPrixTTC() {
		return this.prixTTC;
	}

	public boolean isDisponible() {
		return this.disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getSousTitre() {
		return this.sousTitre;
	}

	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		if (!this.isDisponible()) {
			res.append("EPUISE ! ");
		}
		res.append(this.getTypeGraine().getDénomination() + ',');
		res.append(this.getCouleur().getDénomination() + ',');
		res.append(this.getDésignation() + ',');
		res.append(this.nombreDeGraines + " graines" + ',');
		res.append(String.valueOf(this.getPrixTTC()) + " €" + ',');
		res.append("Image : " + this.getNomImage());
		return res.toString();
	}

	public String toStringAvecDescription() {
		return this.toString() + '\n' + this.getDescription();
	}

	public String toStringAvecTomatesApparentées() {
		StringBuffer res = new StringBuffer(this.toString());
		res.append("\n Tomates apparentées : ");
		for (Tomate graine : this.getTomatesApparentées()) {
			res.append(graine.getDésignation() + " ");
		}
		return res.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Tomate other = (Tomate) obj;
		return Objects.equals(this.désignation, other.désignation);
	}

	public List<Tomate> getTomatesApparentées() {
		return this.tomatesApparentées;
	}

	public void addTomateApparentée(Tomate tomate) {
		this.tomatesApparentées.add(tomate);
	}

}
