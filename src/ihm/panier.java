package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import modèleEtudiantSAE.Panier;

public class panier extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Panier panier;
	private JTextField montantPanier;

	/**
	 * Create the frame.
	 */
	public panier(Panier panier, JTextField montantPanier) {
		this.setTitle("Panier");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(panier.class.getResource("/ihm/basket.png")));

		this.panier = panier;
		this.montantPanier = montantPanier;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 409, 473);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblFacture = new JLabel("Votre panier");
		lblFacture.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblFacture.setIcon(new ImageIcon(panier.class.getResource("/ihm/basket.png")));
		lblFacture.setIconTextGap(6);
		lblFacture.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblFacture.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacture.setForeground(new Color(26, 119, 68));
		lblFacture.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblFacture.setBackground(SystemColor.menu);
		this.contentPane.add(lblFacture, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Produit", "Prix", "Quantit\u00E9", "Total" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, true, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return this.columnEditables[column];
			}
		};

		this.table = new JTable();
		this.table.setModel(model);

		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
					int row = e.getFirstRow();
					int qté = Integer.parseInt(panier.this.table.getValueAt(row, 2).toString());
					panier.changerQuantiteTable(row, qté);
				}
			}
		});

		scrollPane.setViewportView(this.table);

		JPanel panel = new JPanel();
		this.contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(32767, 90000));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);

		JButton btnRecalculer = new JButton("Recalculer le panier");
		btnRecalculer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panier.this.panier.afficherPanier(model);
				panier.this.afficherTotal();
				panier.this.afficherExpedition();
				panier.this.afficherSousTotal();
			}
		});
		panel_3.add(btnRecalculer);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5);

		JLabel lblNewLabel = new JLabel("Sous-total :");
		panel_5.add(lblNewLabel);

		this.textField = new JTextField();
		this.textField.setEditable(false);
		panel_5.add(this.textField);
		this.textField.setColumns(10);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_6);

		JLabel lblNewLabel_1 = new JLabel("Expédition :");
		panel_6.add(lblNewLabel_1);

		this.textField_1 = new JTextField();
		this.textField_1.setEditable(false);
		panel_6.add(this.textField_1);
		this.textField_1.setColumns(10);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);

		JLabel lblNewLabel_2 = new JLabel("Total :");
		panel_7.add(lblNewLabel_2);

		this.textField_2 = new JTextField();
		this.textField_2.setEditable(false);
		panel_7.add(this.textField_2);
		this.textField_2.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JButton btnValider = new JButton("Valider le panier");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coordonnées coor = new Coordonnées(panier.this.panier);
				coor.setVisible(true);
				panier.this.dispose();
			}
		});
		panel_2.add(btnValider);

		JButton btnVider = new JButton("Vider le panier");
		btnVider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane jop = new JOptionPane();
				int option = jop.showConfirmDialog(null, "Voulez-vous vraiment supprimer le panier ?",
						"Vider le panier", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					panier.this.panier.viderPanier(model);
					panier.this.dispose();
					panier.this.montantPanier.setText("0,00€");
				}
			}
		});
		panel_2.add(btnVider);

		JButton btnContinuer = new JButton("Continuer les achats");
		btnContinuer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panier.this.dispose();
			}
		});
		panel_2.add(btnContinuer);

		this.afficherSousTotal();
		this.afficherExpedition();
		this.afficherTotal();
		this.panier.afficherPanier(model);

	}

	private void afficherExpedition() {
		if (this.panier.isEmpty()) {
			this.textField_1.setText("0.00€");
		} else {
			this.textField_1.setText("4.50€");
		}
	}

	private void afficherTotal() {
		this.textField_2.setText(String.format("%.2f€", this.panier.calculerTotalPanier()));
		this.montantPanier.setText(String.format("%.2f€", this.panier.calculerTotalPanier()));
	}

	private void afficherSousTotal() {
		this.textField.setText(String.format("%.2f€", this.panier.calculerSousTotalPanier()));
	}

}
