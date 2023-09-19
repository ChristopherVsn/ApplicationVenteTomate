package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modèleEtudiantSAE.Panier;

public class Coordonnées extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PlaceholderTextField textFieldNom;
	private PlaceholderTextField textFieldPrenom;
	private PlaceholderTextField textFieldAdresse1;
	private PlaceholderTextField textFieldAdresse2;
	private PlaceholderTextField textFieldCodePostal;
	private PlaceholderTextField textFieldVille;
	private PlaceholderTextField textFieldTelephone;
	private PlaceholderTextField textFieldMail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox checkBoxNewsletter;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Coordonnées frame = new Coordonnées();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Coordonnées(Panier panier) {
		this.setTitle("Coordonnées");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Coordonnées.class.getResource("/ihm/tomato.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_bottom_ok_annuler = new JPanel();
		FlowLayout fl_panel_bottom_ok_annuler = (FlowLayout) panel_bottom_ok_annuler.getLayout();
		fl_panel_bottom_ok_annuler.setAlignment(FlowLayout.RIGHT);
		this.contentPane.add(panel_bottom_ok_annuler, BorderLayout.SOUTH);

		JButton btnOk = new JButton("Ok");
		this.confirmAndSendInformations(btnOk, panier);
		btnOk.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_bottom_ok_annuler.add(btnOk);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Coordonnées.this.dispose();
			}
		});
		btnAnnuler.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_bottom_ok_annuler.add(btnAnnuler);

		JLabel labelTitre = new JLabel("Vos coordonnées");
		labelTitre.setBorder(new EmptyBorder(5, 5, 5, 5));
		labelTitre.setForeground(new Color(0, 128, 0));
		labelTitre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
		this.contentPane.add(labelTitre, BorderLayout.NORTH);

		JPanel panel_informations = new JPanel();
		panel_informations.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_informations.setFont(new Font("Calibri", Font.PLAIN, 11));
		panel_informations.setBackground(new Color(240, 240, 240));
		this.contentPane.add(panel_informations, BorderLayout.CENTER);
		panel_informations.setLayout(new GridLayout(8, 0, 0, 0));

		JPanel panel_nomprenom = new JPanel();
		panel_informations.add(panel_nomprenom);
		panel_nomprenom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelNom = new JLabel("Nom");
		labelNom.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_nomprenom.add(labelNom);

		this.textFieldNom = new PlaceholderTextField();
		this.textFieldNom.setPlaceholder("Voisin");
		this.textFieldNom.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_nomprenom.add(this.textFieldNom);
		this.textFieldNom.setColumns(11);

		JLabel labelPrenom = new JLabel("Prénom");
		labelPrenom.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_nomprenom.add(labelPrenom);

		this.textFieldPrenom = new PlaceholderTextField();
		this.textFieldPrenom.setPlaceholder("Christopher");
		this.textFieldPrenom.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_nomprenom.add(this.textFieldPrenom);
		this.textFieldPrenom.setColumns(11);

		JPanel panel_adresse1 = new JPanel();
		panel_informations.add(panel_adresse1);
		panel_adresse1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelAdresse1 = new JLabel("Adresse 1");
		labelAdresse1.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_adresse1.add(labelAdresse1);

		this.textFieldAdresse1 = new PlaceholderTextField();
		this.textFieldAdresse1.setPlaceholder("9 rue du vide");
		this.textFieldAdresse1.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_adresse1.add(this.textFieldAdresse1);
		this.textFieldAdresse1.setColumns(25);

		JPanel panel_adresse2 = new JPanel();
		panel_informations.add(panel_adresse2);
		panel_adresse2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelAdresse2 = new JLabel("Adresse 2");
		labelAdresse2.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_adresse2.add(labelAdresse2);

		this.textFieldAdresse2 = new PlaceholderTextField();
		this.textFieldAdresse2.setPlaceholder("Appartement 9 bâtiment 2");
		this.textFieldAdresse2.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_adresse2.add(this.textFieldAdresse2);
		this.textFieldAdresse2.setColumns(25);

		JPanel panel_5 = new JPanel();
		panel_informations.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelCodePostal = new JLabel("Code postal");
		labelCodePostal.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_5.add(labelCodePostal);

		this.textFieldCodePostal = new PlaceholderTextField();
		this.textFieldCodePostal.setHorizontalAlignment(SwingConstants.CENTER);
		this.textFieldCodePostal.setPlaceholder("31400");
		this.textFieldCodePostal.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_5.add(this.textFieldCodePostal);
		this.textFieldCodePostal.setColumns(5);

		JLabel labelVille = new JLabel("Ville");
		labelVille.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_5.add(labelVille);

		this.textFieldVille = new PlaceholderTextField();
		this.textFieldVille.setPlaceholder("Toulouse");
		this.textFieldVille.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_5.add(this.textFieldVille);
		this.textFieldVille.setColumns(15);

		JPanel panel_6 = new JPanel();
		panel_informations.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelTelephone = new JLabel("Téléphone");
		labelTelephone.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_6.add(labelTelephone);

		this.textFieldTelephone = new PlaceholderTextField();
		this.textFieldTelephone.setPlaceholder("0624034232");
		this.textFieldTelephone.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_6.add(this.textFieldTelephone);
		this.textFieldTelephone.setColumns(10);

		JPanel panel_7 = new JPanel();
		panel_informations.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelMail = new JLabel("Mail");
		labelMail.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_7.add(labelMail);

		this.textFieldMail = new PlaceholderTextField();
		this.textFieldMail.setPlaceholder("christopher.voisin@gmail.com");
		this.textFieldMail.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_7.add(this.textFieldMail);
		this.textFieldMail.setColumns(20);

		JPanel panel_8 = new JPanel();
		panel_informations.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel labelMoyenPaiement2 = new JLabel("Paiement :");
		labelMoyenPaiement2.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_8.add(labelMoyenPaiement2);

		JRadioButton radioButtonChoix1 = new JRadioButton("Carte");
		radioButtonChoix1.setActionCommand("Carte");
		radioButtonChoix1.setSelected(true);
		radioButtonChoix1.setFont(new Font("Verdana", Font.PLAIN, 10));
		this.buttonGroup.add(radioButtonChoix1);
		radioButtonChoix1.setOpaque(false);
		panel_8.add(radioButtonChoix1);

		JLabel labelRadioButtonChoix1 = new JLabel("");
		labelRadioButtonChoix1.setIcon(new ImageIcon(new ImageIcon("C:\\projet_eclipse\\src\\mastercard.png").getImage()
				.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		labelRadioButtonChoix1.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_8.add(labelRadioButtonChoix1);

		JRadioButton radioButtonChoix2 = new JRadioButton("Paypal");
		radioButtonChoix2.setActionCommand("Paypal");
		radioButtonChoix2.setFont(new Font("Verdana", Font.PLAIN, 10));
		this.buttonGroup.add(radioButtonChoix2);
		radioButtonChoix2.setOpaque(false);
		panel_8.add(radioButtonChoix2);

		JLabel labelRadioButtonChoix2 = new JLabel("");
		labelRadioButtonChoix2.setIcon(new ImageIcon(new ImageIcon("C:\\projet_eclipse\\src\\paypal.png").getImage()
				.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		labelRadioButtonChoix2.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_8.add(labelRadioButtonChoix2);

		JRadioButton radioButtonChoix3 = new JRadioButton("Chèque");
		radioButtonChoix3.setActionCommand("Chèque");
		radioButtonChoix3.setFont(new Font("Verdana", Font.PLAIN, 10));
		this.buttonGroup.add(radioButtonChoix3);
		radioButtonChoix3.setOpaque(false);
		panel_8.add(radioButtonChoix3);

		JLabel labelRadioButtonChoix3 = new JLabel("");
		labelRadioButtonChoix3.setIcon(new ImageIcon(new ImageIcon("C:\\projet_eclipse\\src\\cheque.png").getImage()
				.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		labelRadioButtonChoix3.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_8.add(labelRadioButtonChoix3);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new EmptyBorder(0, 0, 5, 0));
		panel_informations.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.checkBoxNewsletter = new JCheckBox("Voulez-vous vous abonner à notre Newsletter ?");
		this.checkBoxNewsletter.setFont(new Font("Verdana", Font.PLAIN, 10));
		this.checkBoxNewsletter.setOpaque(false);
		panel_9.add(this.checkBoxNewsletter);
	}

	private void confirmAndSendInformations(JButton btnOk, Panier panier) {
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String[] tableau = new String[10];

				tableau[0] = Coordonnées.this.textFieldNom.getText();
				tableau[1] = Coordonnées.this.textFieldPrenom.getText();
				tableau[2] = Coordonnées.this.textFieldAdresse1.getText();
				tableau[3] = Coordonnées.this.textFieldAdresse2.getText();
				tableau[4] = Coordonnées.this.textFieldCodePostal.getText();
				tableau[5] = Coordonnées.this.textFieldVille.getText();
				tableau[6] = Coordonnées.this.textFieldTelephone.getText();
				tableau[7] = Coordonnées.this.textFieldMail.getText();
				tableau[8] = Coordonnées.this.buttonGroup.getSelection().getActionCommand();
				tableau[9] = Coordonnées.this.checkBoxNewsletter.isSelected() ? "abonné" : "non abonné";

				facture facture = new facture(tableau, panier);
				facture.setVisible(true);
			}
		});
	}

}
