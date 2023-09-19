package ihm;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modèleEtudiantSAE.Couleur;
import modèleEtudiantSAE.Panier;
import modèleEtudiantSAE.Tomate;
import modèleEtudiantSAE.TypeTomate;

public class testFacture {

	@Before
	public void setUp() {

	}

	@Test
	public void testAffichageInformations() {

	}

	@Test
	public void testTotalTTC() {
		String[] tableau = new String[10];
		Panier panier = new Panier();
		panier.addTomate(new Tomate(TypeTomate.TOMATES_CERISES, Couleur.JAUNE, "Tomate Poire Jaune", null,
				"Tomate-poire-jaune.TEMP_",
				"Grappes de mini-tomates en forme de poire William, légèrement acidulées, productive, ne se taille pas.\r\n"
						+ "\r\n" + "En garniture, brochettes, cocktail. à croquer nature !",
				50, 3.85F));
		panier.addTomate(new Tomate(TypeTomate.TOMATES, Couleur.NOIR, "Tomate Noire de Crimée", null, "Noire_Crimee-1",
				"Résistante à la sécheresse. Beaux fruits de 120-150 g pouvant même atteindre 500 g. \r\n" + "\r\n"
						+ "Peau lisse devenant rouge trés sombre à pourpre à maturité. Sa chair est dense, son goût puissant et sucré.\r\n"
						+ "\r\n" + "Une variété locale lui ressemble beaucoup : La Charbonnière du Berry, extra !",
				50, 3.85F));
		facture fac = new facture(tableau, panier);
		assertEquals(Float.toString(fac.totalTTC), "7.7");
	}

	public void testInformations() {

	}
}