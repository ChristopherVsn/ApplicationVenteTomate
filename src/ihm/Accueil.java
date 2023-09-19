package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modèleEtudiantSAE.Couleur;
import modèleEtudiantSAE.GenerationArticles;
import modèleEtudiantSAE.Panier;
import modèleEtudiantSAE.Tomate;
import modèleEtudiantSAE.Tomates;
import modèleEtudiantSAE.TypeTomate;

public class Accueil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField montantPanier;
	private Tomates tomates;
	private DefaultListModel<String> listModel;
	private DefaultComboBoxModel<String> comboBoxModel;
	private JComboBox<String> comboBoxCouleurs;
	private JComboBox<String> comboBox;
	private DefaultComboBoxModel<String> comboBoxModelClr;
	private JList<String> list;
	private Panier panier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Tomates tomates = GenerationArticles.générationDeBaseDesTomates();
				try {
					Accueil frame = new Accueil(tomates);
					frame.setVisible(true);
					frame.setSize(500, 600);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Accueil(Tomates tomates) {

		this.panier = new Panier();
		this.tomates = tomates;
		this.listModel = new DefaultListModel<String>();

		this.setTitle("Vente de magnifiques tomates");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Accueil.class.getResource("/ihm/tomato.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);

		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(203, 232, 186));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pHead = new JPanel();
		this.contentPane.add(pHead, BorderLayout.NORTH);
		pHead.setLayout(new BorderLayout(0, 0));

		JLabel lblLogo = new JLabel("");
		lblLogo.setBorder(new EmptyBorder(5, 5, 5, 12));
		lblLogo.setBackground(new Color(203, 232, 186));
		lblLogo.setOpaque(true);
		lblLogo.setMaximumSize(new Dimension(100, 100));
		lblLogo.setIcon(new ImageIcon(Accueil.class.getResource("/ihm/tomato.png")));
		lblLogo.setIconTextGap(5);
		pHead.add(lblLogo, BorderLayout.WEST);

		JLabel lblTitle = new JLabel("Nos graines de tomates");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(203, 232, 186));
		lblTitle.setForeground(new Color(8, 64, 15));
		lblTitle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
		pHead.add(lblTitle, BorderLayout.CENTER);

		JPanel pPanier = new JPanel();
		pHead.add(pPanier, BorderLayout.EAST);
		pPanier.setLayout(new GridLayout(0, 2, 0, 0));

		this.montantPanier = new JTextField();
		this.montantPanier.setBorder(new EmptyBorder(0, 0, 0, 10));
		this.montantPanier.setBackground(new Color(203, 232, 186));
		this.montantPanier.setToolTipText("Prix total du panier");
		this.montantPanier.setForeground(new Color(0, 0, 0));
		this.montantPanier.setHorizontalAlignment(SwingConstants.RIGHT);
		this.montantPanier.setMargin(new Insets(0, 0, 0, 0));
		this.montantPanier.setDisabledTextColor(new Color(0, 0, 0));
		this.montantPanier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.montantPanier.setText("0,00€");
		this.montantPanier.setEditable(false);
		pPanier.add(this.montantPanier);
		this.montantPanier.setColumns(5);

		JButton btnPanier = new JButton("");
		btnPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Accueil.this.panier.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Le panier est vide", "Panier vide",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					panier panier = new panier(Accueil.this.panier, Accueil.this.montantPanier);
					panier.setVisible(true);
				}
			}
		});

		btnPanier.setBorder(new LineBorder(new Color(8, 64, 15), 1, true));
		btnPanier.setBackground(new Color(203, 232, 186));
		btnPanier.setIcon(new ImageIcon(Accueil.class.getResource("/ihm/basket.png")));

		pPanier.add(btnPanier);

		for (Tomate tomate : tomates.getLesTomates()) {
			this.listModel.addElement(tomate.getDésignation());
		}
		this.list = new JList<>(this.listModel);

		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String nom = Accueil.this.list.getSelectedValue();
				description description = new description(tomates, tomates.getTomate(nom), Accueil.this.panier,
						Accueil.this.montantPanier);
				description.setVisible(true);
			}
		});
		this.list.setSelectionBackground(new Color(0, 120, 215));
		this.list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		JScrollPane scrollPane = new JScrollPane(this.list);
		scrollPane.setBackground(new Color(203, 232, 186));
		scrollPane.setBorder(new EmptyBorder(5, 5, 10, 5));
		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel pBot = new JPanel();
		pBot.setBackground(new Color(255, 255, 255));
		this.contentPane.add(pBot, BorderLayout.SOUTH);
		pBot.setLayout(new BorderLayout(0, 0));

		JPanel pFilters = new JPanel();
		pFilters.setBorder(new EmptyBorder(5, 5, 5, 5));
		pFilters.setBackground(new Color(255, 255, 255));
		pBot.add(pFilters, BorderLayout.NORTH);
		pFilters.setLayout(new BorderLayout(0, 0));

		JLabel lblLogoFiltre = new JLabel("");
		lblLogoFiltre.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoFiltre.setIcon(new ImageIcon(Accueil.class.getResource("/ihm/logoSearch.png")));
		lblLogoFiltre.setMaximumSize(new Dimension(100, 100));
		lblLogoFiltre.setIconTextGap(5);
		lblLogoFiltre.setBackground(new Color(255, 255, 255));
		pFilters.add(lblLogoFiltre, BorderLayout.WEST);

		JLabel lblFiltre = new JLabel("Filtres");
		lblFiltre.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblFiltre.setHorizontalAlignment(SwingConstants.LEFT);
		pFilters.add(lblFiltre);

		JButton btnTips = new JButton("");
		btnTips.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConseilCulture conseilCulture = new ConseilCulture();
				conseilCulture.setVisible(true);
			}
		});
		btnTips.setIcon(new ImageIcon(Accueil.class.getResource("/ihm/sprout.png")));
		btnTips.setBackground(new Color(203, 232, 186));
		pFilters.add(btnTips, BorderLayout.EAST);

		JPanel pSelecteur = new JPanel();
		pSelecteur.setBackground(new Color(255, 255, 255));
		pBot.add(pSelecteur, BorderLayout.CENTER);
		pSelecteur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.comboBoxModel = new DefaultComboBoxModel<String>();
		this.comboBoxModel.addElement("Toutes les tomates");
		for (TypeTomate typeTomate : TypeTomate.values()) {
			this.comboBoxModel.addElement(typeTomate.getDénomination());
		}
		this.comboBox = new JComboBox<String>(this.comboBoxModel);

		this.comboBoxModelClr = new DefaultComboBoxModel<>();
		pSelecteur.add(this.comboBox);
		this.comboBoxModelClr.addElement("Couleurs");
		for (Couleur c : Couleur.values()) {
			this.comboBoxModelClr.addElement(c.getDénomination());
		}
		this.comboBoxCouleurs = new JComboBox<String>(this.comboBoxModelClr);
		this.filtreCouleurs();
		pSelecteur.add(this.comboBoxCouleurs);

		this.filtreType();

	}

	private void filtreCouleurs() {
		this.comboBoxCouleurs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Accueil.this.tomates.afficherTomates(Accueil.this.listModel,
						(String) Accueil.this.comboBoxCouleurs.getSelectedItem(),
						(String) Accueil.this.comboBox.getSelectedItem());
			}
		});
	}

	private void filtreType() {
		this.comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Accueil.this.tomates.afficherTomates(Accueil.this.listModel,
						(String) Accueil.this.comboBoxCouleurs.getSelectedItem(),
						(String) Accueil.this.comboBox.getSelectedItem());
			}
		});
	}

}
