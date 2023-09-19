package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modèleEtudiantSAE.Panier;
import modèleEtudiantSAE.Tomate;

public class facture extends JFrame {

	private JPanel contentPane;
	public float totalTTC;
	String informations_string;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public facture(String[] informations, Panier panier) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(facture.class.getResource("/ihm/tomato.png")));
		this.setTitle("Votre facture");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 770, 550);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblFacture = new JLabel("Facture");
		lblFacture.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblFacture.setIconTextGap(6);
		lblFacture.setIcon(new ImageIcon(facture.class.getResource("/ihm/tomato.png")));
		lblFacture.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblFacture.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacture.setForeground(new Color(26, 119, 68));
		lblFacture.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
		lblFacture.setBackground(SystemColor.menu);
		this.contentPane.add(lblFacture, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		this.contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		JTextArea textArea = new JTextArea();
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
		Date date = new Date();

		// for (int x = 0; x < panier.length(); x++) {
		// content += Integer.toString(x+1) + String.format(" | %-"+
		// Integer.toString(panier.getContenu().get(x).getDésignation().length())+"s |",
		// panier.getContenu().get(x).getDésignation()) + "\n";
		// }

		int maxLength = 0;
		for (Tomate produit : panier.getContenu()) {
			int length = produit.getDésignation().length();
			if (length > maxLength) {
				maxLength = length;
			}
		}

		int taille_1_ligne = 6 + maxLength + 3 + 18 + 3 + 13 + 3 + 22;
		// | 1 | <désignation> | Prix TTC : xx.xx € | Quantité : xx | Sous-Total : xx.xx
		// € |
		// ---------------------------------------------------------------------------------
		// 6 maxLength 3 18 3 13 3 22

		String informations_string = "Vos informations :\n\t- M./Mme : " + informations[0] + " " + informations[1]
				+ "\n\t- Addresse : " + informations[2] + "\n\t- Suite adresse : " + informations[3] + "\n\t- Ville : "
				+ informations[4] + " " + informations[5] + "\n\t- Téléphone : " + informations[6] + "\n\t- Mail : "
				+ informations[7] + "\n\t- Moyen de paiement : " + informations[8] + "\n\nNote: Vous serez "
				+ informations[9] + " à notre newsletter.";

		String content = "o' Tomatique, facture datant du " + formatter.format(date) + "\n\n" + informations_string
				+ "\n\n\n";

		for (int x = 0; x < taille_1_ligne / 2 - 5; x++) {
			content += "_";
		}
		content += " COMMANDE ";
		for (int x = 0; x < taille_1_ligne / 2 - 5; x++) {
			content += "_";
		}
		content += "_\n";

		for (int x = 0; x < panier.length(); x++) {
			content += "| " + Integer.toString(x + 1)
					+ String.format(" | %-" + maxLength + "s", panier.getContenu().get(x).getDésignation())
					+ " | Prix TTC : " + String.format("%-5s", Float.toString(panier.getContenu().get(x).getPrixTTC()))
					+ " € | Quantité : " + String.format("%-2s", Integer.toString(panier.getQuantité().get(x)))
					+ " | Sous-Total : "
					+ String.format("%-5s", Float.toString(
							Math.round(panier.getQuantité().get(x) * panier.getContenu().get(x).getPrixTTC() * 100.0f)
									/ 100.0f))
					+ " € |\n";
		}

		for (int x = 0; x < taille_1_ligne; x++) {
			content += "_";
		}

		this.totalTTC = 0F;
		for (int x = 0; x < panier.length(); x++) {
			this.totalTTC += panier.getQuantité().get(x) * panier.getContenu().get(x).getPrixTTC();
		}

		float expedition_france = 4.5F;
		this.totalTTC = Math.round(this.totalTTC * 100.0f) / 100.0f;
		content += "\n\nVotre commande            : " + Float.toString(this.totalTTC)
				+ " €\nExpédition forfait France : " + Float.toString(expedition_france)
				+ " €\nTotal TTC                 : " + Float.toString(this.totalTTC + expedition_france) + " €";

		textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		textArea.setText(content);

		JLabel lblNewLabel = new JLabel("Merci de votre visite !");
		lblNewLabel.setBorder(new EmptyBorder(3, 3, 3, 3));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBackground(new Color(203, 232, 186));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		this.contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnNewButton = new JButton("Imprimer");
		this.impression(btnNewButton);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Quitter");
		this.quitter(btnNewButton_1);
		panel_1.add(btnNewButton_1);
	}

	private void quitter(JButton btnNewButton_1) {
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

	private void impression(JButton btnNewButton) {
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				if (job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

}