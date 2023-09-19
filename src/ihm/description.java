package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modèleEtudiantSAE.Panier;
import modèleEtudiantSAE.Tomate;
import modèleEtudiantSAE.Tomates;

public class description extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Tomate tomateActuelle;
	private Tomates lesTomates;
	private Panier panier;
	private int nbGraines;
	private JTextField montantpanier;

	private JTextField textFieldDisponible;
	private JSpinner spinnerNbGraines;
	private JLabel labelPrixUnitaire;
	private JLabel labelTextePrixTotal;
	private JLabel labelPrixTotal;

	/**
	 * Create the frame.
	 */
	public description(Tomates lesTomates, Tomate tomateActuelle, Panier panier, JTextField montantpanier) {

		this.lesTomates = lesTomates;
		this.tomateActuelle = tomateActuelle;
		this.nbGraines = 0;
		this.panier = panier;
		this.montantpanier = montantpanier;
		this.montantpanier.setText(String.format("%.2f€", this.panier.calculerTotalPanier()));

		this.setTitle("Description de " + tomateActuelle.getDésignation());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Accueil.class.getResource("/ihm/tomato.png")));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 550, 450);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel gauche
		JPanel panelGauche = new JPanel();
		contentPane.add(panelGauche, BorderLayout.WEST);
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));

		JTextField textFieldTypeTomate = new JTextField();
		textFieldTypeTomate.setForeground(new Color(0, 128, 0));
		textFieldTypeTomate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTypeTomate.setEditable(false);
		panelGauche.add(textFieldTypeTomate);
		textFieldTypeTomate.setColumns(10);
		textFieldTypeTomate.setText(this.tomateActuelle.getDésignation());

		JLabel imgTomate = new JLabel();
		imgTomate.setAlignmentX(Component.CENTER_ALIGNMENT);
		imgTomate.setHorizontalAlignment(SwingConstants.CENTER);
		imgTomate.setIcon(new ImageIcon("./src/ihm/Tomates200x200/" + this.tomateActuelle.getNomImage() + ".jpg"));
		panelGauche.add(imgTomate);

		this.textFieldDisponible = new JTextField();
		this.textFieldDisponible.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFieldDisponible.setEditable(false);
		panelGauche.add(this.textFieldDisponible);
		this.textFieldDisponible.setColumns(10);
		this.actualiserDisponible(tomateActuelle);
		textFieldTypeTomate.setText(tomateActuelle.getDésignation());

		// Panel droit
		JPanel panelDroite = new JPanel();
		contentPane.add(panelDroite);
		panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));

		JTextPane textPaneDescription = new JTextPane();
		textPaneDescription.setText(tomateActuelle.getDescription());
		panelDroite.add(textPaneDescription);

		// Panel bas
		JPanel panelBas = new JPanel();
		contentPane.add(panelBas, BorderLayout.SOUTH);
		panelBas.setLayout(new GridLayout(4, 1, 0, 0));

		// Panel Bas (Haut)
		JPanel panelBasHaut = new JPanel();
		panelBas.add(panelBasHaut);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				description.this.dispose();
			}
		});
		panelBasHaut.add(btnAnnuler);
		JButton btnCommander = new JButton();
		if (this.tomateActuelle.isDisponible()) {
			btnCommander.setText("Ajouter au panier");
		} else {
			btnCommander.setText("Indisponible");
			btnCommander.setEnabled(false);
		}
		btnCommander.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < description.this.nbGraines; i++) {
					panier.addTomate(tomateActuelle);
					montantpanier.setText(String.format("%.2f€", panier.calculerTotalPanier()));
				}
			}
		});
		panelBasHaut.add(btnCommander);

		// Panel bas (Milieu)
		JPanel panelBasMilieu = new JPanel();
		panelBas.add(panelBasMilieu);

		JLabel labelTexteGrainesRestantes = new JLabel("Nombre de graines :");
		panelBasMilieu.add(labelTexteGrainesRestantes);

		JLabel labelNbGrainesRestantes = new JLabel(String.valueOf(tomateActuelle.getNombreDeGraines()));
		labelNbGrainesRestantes.setFont(
				labelNbGrainesRestantes.getFont().deriveFont(labelNbGrainesRestantes.getFont().getStyle() | Font.BOLD));
		panelBasMilieu.add(labelNbGrainesRestantes);

		JLabel labelNbGrainesCommande = new JLabel("Nombre de paquets à commander : ");
		panelBasMilieu.add(labelNbGrainesCommande);

		JSpinner spinnerNbGraines = this.actualiserSpinner();
		panelBasMilieu.add(spinnerNbGraines);

		// Panel Bas (Bas)
		JPanel panelBasBas = new JPanel();
		panelBas.add(panelBasBas);

		JLabel labelTextePrixUnitaire = new JLabel("Prix unitaire :");
		panelBasBas.add(labelTextePrixUnitaire);
		labelTextePrixUnitaire.setHorizontalAlignment(SwingConstants.CENTER);

		this.labelPrixUnitaire = new JLabel(String.valueOf(this.tomateActuelle.getPrixTTC()) + "€");
		panelBasBas.add(this.labelPrixUnitaire);

		this.labelTextePrixTotal = new JLabel("Prix total :");
		panelBasBas.add(this.labelTextePrixTotal);

		this.labelPrixTotal = new JLabel("0.00€");
		panelBasBas.add(this.labelPrixTotal);

		// Choix tomate
		JComboBox<String> comboBoxTomate = this.actualiserComboBox(tomateActuelle.getTomatesApparentées());
		panelBas.add(comboBoxTomate);
	}

	private JComboBox<String> actualiserComboBox(List<Tomate> tomatesApparentées) {
		JComboBox<String> comboBoxTomate = new JComboBox<String>();
		comboBoxTomate.addItem("-- Sélectionner --");
		for (Tomate t : tomatesApparentées) {
			if (!t.equals(this.tomateActuelle)) {
				comboBoxTomate.addItem(t.getDésignation());
			}
		}
		comboBoxTomate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxTomate.getSelectedItem().toString() != "-- Sélectionner --") {
					Tomate tomate = description.this.lesTomates.getTomate(comboBoxTomate.getSelectedItem().toString());
					System.out.println(tomate.getDésignation());
					description desc = new description(description.this.lesTomates, tomate, description.this.panier,
							description.this.montantpanier);
					desc.setVisible(true);
				}
			}
		});
		return comboBoxTomate;
	}

	private JSpinner actualiserSpinner() {
		this.spinnerNbGraines = new JSpinner();
		this.spinnerNbGraines.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		if (!this.tomateActuelle.isDisponible()) {
			this.spinnerNbGraines.setEnabled(false);
		}
		this.spinnerNbGraines.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				description.this.actualiserPrixTomate(description.this.tomateActuelle);
				description.this.nbGraines = (int) description.this.spinnerNbGraines.getValue();
			}
		});
		return this.spinnerNbGraines;
	}

	private void actualiserDisponible(Tomate nouvelleTomate) {
		if (nouvelleTomate.isDisponible()) {
			this.textFieldDisponible.setForeground(new Color(0, 255, 0));
			this.textFieldDisponible.setText("En stock !");
		} else {
			this.textFieldDisponible.setForeground(new Color(255, 0, 0));
			this.textFieldDisponible.setText("Indisponible");
		}
	}

	private void actualiserPrixTomate(Tomate tomate) {

		this.labelPrixUnitaire.setText(String.format("%.2f€", tomate.getPrixTTC()));
		if (tomate.isDisponible()) {
			Integer nbGraines = (int) this.spinnerNbGraines.getValue();
			float prixTotal = tomate.getPrixTTC() * nbGraines.floatValue();
			this.labelTextePrixTotal.setEnabled(true);
			this.labelPrixTotal.setEnabled(true);
			this.labelPrixTotal.setText(String.format("%.2f€", prixTotal));
		} else {
			this.labelTextePrixTotal.setEnabled(false);
			this.labelPrixTotal.setEnabled(false);
		}
	}
}
