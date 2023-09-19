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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modèleEtudiantSAE.Tomates;

public class ConseilCulture extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ConseilCulture() {
		this.setTitle("Conseils de culture");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ConseilCulture.class.getResource("/ihm/sprout.png")));

		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 380, 488);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(SystemColor.menu);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		this.contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConseilCulture.this.dispose();
			}
		});
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel_2.add(btnNewButton);

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
		textArea.setText(Tomates.CONSEILS_DE_CULTURE);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		textArea_1.setBackground(new Color(203, 232, 186));
		textArea_1.setMargin(new Insets(10, 10, 10, 10));
		textArea_1.setEditable(false);
		panel.add(textArea_1, BorderLayout.NORTH);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setText(Tomates.CONSEILS_DE_CULTURE_TITRE);

		JLabel lblNewLabel = new JLabel("Conseils de culture");
		lblNewLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblNewLabel.setIconTextGap(10);
		lblNewLabel.setBackground(SystemColor.menu);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel.setIcon(new ImageIcon(ConseilCulture.class.getResource("/ihm/tomato.png")));
		lblNewLabel.setForeground(new Color(26, 119, 68));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
		this.contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
