package modèleEtudiantSAE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ihm.facture;

public class test {

	private Tomates tomates;

	@Before
	public void setUp() throws Exception {
		this.tomates = GenerationArticles.générationDeBaseDesTomates();
	}

	@After
	public void tearDown() throws Exception {
		this.tomates = null;
	}

	@Test
	public void testFiltreTypeCouleur() {
		List<Tomate> listeTomatesBleu = new ArrayList<>();
		List<Tomate> listeTomatesCerises = new ArrayList<>();
		List<Tomate> listeTomatesCerisesRouge = new ArrayList<>();
		Boolean mauvaiseTomate = false;

		for (Tomate tomate : this.tomates.getLesTomates()) {
			if (tomate.getCouleur() == Couleur.BLEU) {
				listeTomatesBleu.add(tomate); // Ajoute toutes les tomates bleu à la liste,
			}
		}
		for (Tomate t : listeTomatesBleu) {
			if (t.getCouleur() != Couleur.BLEU) {
				mauvaiseTomate = true; // Si une des tomates n'est pas bleu on met true à notre boolean
			}
		}

		for (Tomate tomate : this.tomates.getLesTomates()) {
			if (tomate.getTypeGraine() == TypeTomate.TOMATES_CERISES) {
				listeTomatesCerises.add(tomate);
			}
		}
		for (Tomate t : listeTomatesCerises) {
			if (t.getTypeGraine() != TypeTomate.TOMATES_CERISES) {
				mauvaiseTomate = true;
			}
		}

		for (Tomate tomate : this.tomates.getLesTomates()) {
			if (tomate.getTypeGraine() == TypeTomate.TOMATES_CERISES && tomate.getCouleur() == Couleur.ROUGE) {
				listeTomatesCerisesRouge.add(tomate);
			}
		}
		for (Tomate t : listeTomatesCerisesRouge) {
			if (t.getTypeGraine() != TypeTomate.TOMATES_CERISES || t.getCouleur() != Couleur.ROUGE) {
				mauvaiseTomate = true;
			}
		}

		assertFalse(mauvaiseTomate); // Si une des boucles à trouvé une tomate qui n'était pas censé être là,
										// mauvaiseTomate sera true donc le test ne passera pas.

	}

	@Test
	public void testPanierAjout() {
		Panier panier = new Panier();
		Tomate tomate = this.tomates.getTomate("Tomate Green Grape");
		panier.addTomate(tomate);
		assertFalse(panier.isEmpty());
		assertEquals((int) panier.getQuantité().get(0), 1);
		assertEquals(panier.getContenu().get(0).getDésignation(), "Tomate Green Grape");
	}

	@Test
	public void testPanierModification() {
		Panier panier = new Panier();
		Tomate tomate = this.tomates.getTomate("Tomate Green Grape");
		panier.addTomate(tomate);
		panier.addTomate(tomate);
		assertEquals((int) panier.getQuantité().get(0), 2);
	}

	@Test
	public void testPanierSuppression() {
		Panier panier = new Panier();
		Tomate tomate = this.tomates.getTomate("Tomate Green Grape");
		panier.addTomate(tomate);
		panier.changerQuantiteTable(0, 0);
		assertTrue(panier.isEmpty());
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

}
