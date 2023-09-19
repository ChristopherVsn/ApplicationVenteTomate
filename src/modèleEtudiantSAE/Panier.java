package modèleEtudiantSAE;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Panier {

	private List<Tomate> contenu;
	private List<Integer> quantité;

	public Panier() {
		this.contenu = new LinkedList<Tomate>();
		this.quantité = new LinkedList<Integer>();
	}

	public List<Tomate> getContenu() {
		return this.contenu;
	}

	public List<Integer> getQuantité() {
		return this.quantité;
	}

	public void addTomate(Tomate tomate) {
		if (this.contenu.contains(tomate)) {
			int index = this.contenu.indexOf(tomate);
			this.quantité.set(index, this.quantité.get(index) + 1);
		} else {
			this.contenu.add(tomate);
			this.quantité.add(1);
		}
	}

	public float calculerTotalTomate(int index) {
		return this.contenu.get(index).getPrixTTC() * this.quantité.get(index);
	}

	public float calculerSousTotalPanier() {
		float total = 0;
		for (int i = 0; i < this.contenu.size(); i++) {
			total += this.calculerTotalTomate(i);
		}
		return total;
	}

	public float calculerTotalPanier() {
		if (this.isEmpty()) {
			return 0F;
		} else {
			return this.calculerSousTotalPanier() + 4.50F;
		}
	}

	public int length() {
		return this.contenu.size();
	}

	public void afficherPanier(DefaultTableModel model) {
		model.setRowCount(0);
		for (int i = 0; i < this.contenu.size(); i++) {
			model.addRow(new Object[] { this.contenu.get(i).getDésignation(),
					String.format("%.2f€", this.contenu.get(i).getPrixTTC()), this.quantité.get(i),
					String.format("%.2f€", this.quantité.get(i) * this.contenu.get(i).getPrixTTC()) });
		}
		;
	}

	public void viderPanier(DefaultTableModel model) {
		model.setRowCount(0);
		this.contenu.clear();
		this.quantité.clear();
	}

	public boolean isEmpty() {
		return this.contenu.isEmpty();
	}

	public void changerQuantiteTable(int index, int qté) {
		this.quantité.set(index, qté);
		if (this.quantité.get(index) == 0) {
			this.quantité.remove(index);
			this.contenu.remove(index);
		}
	}

}
