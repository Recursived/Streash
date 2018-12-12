package streash.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class ConsoleFrame extends JFrame {  
	/* 
	 * Classe représentant la console streash
	 * Composé d'un input field
	 * et d'un display field 
	 */
	
	
	private ConsoleInput ci;
	private ConsolePanel cp;
	private JScrollPane scrollpane;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem save;
	private JMenuItem importe;
	
	public static void main(String[] args) {
		new ConsoleFrame();
	}
	
	public ConsoleFrame() {
		this.ci = new ConsoleInput();
		this.cp = new ConsolePanel();
		this.scrollpane = new JScrollPane();
		this.menubar = new JMenuBar();
		this.menu = new JMenu("Console");
		this.save = new JMenuItem("Sauvegarder");
		this.importe = new JMenuItem("Importer");
		this.initComponents();
		this.initListeners();
	}
	
	private void initComponents() {
		// Init de la frame
				this.setTitle("Streash Console");
			    this.setSize(650, 400);
			    this.setLocationRelativeTo(null);
			    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
			    this.setVisible(true);
			    this.setResizable(false);
			    this.setLayout(new BorderLayout());
			    
			    // Ajout des composants principaux
			    this.cp.setEditable(false);
			    this.cp.setOpaque(false);
			    this.cp.setForeground(Color.WHITE);
			    this.cp.setMargin(new Insets(5,5,5,5));
		    
			    this.ci.setEditable(true);
			    this.ci.setForeground(Color.WHITE);
			    this.ci.setBackground(new Color(30, 30, 30));
			    this.ci.setCaretColor(Color.WHITE);
			    this.ci.setMargin(new Insets(10,10,10,10));
			    
			    this.scrollpane.setOpaque(false);
			    this.scrollpane.getViewport().setOpaque(false);
			    this.scrollpane.setPreferredSize(new Dimension(200,300));
			    this.scrollpane.getViewport().setView(cp);
			    
			    this.getContentPane().setBackground(new Color(30,30,30));
			    
			    this.add(this.ci, BorderLayout.SOUTH);
			    this.add(this.scrollpane, BorderLayout.CENTER);
			    
			    // Ajout de la menu bar
			    menu.setToolTipText("Importation/Exportation de session");
			    save.setToolTipText("Sauvegarder la session courante du shell ");
			    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.VK_CONTROL));
			    importe.setToolTipText("Importer une session precedente");
			    importe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.VK_CONTROL));
			    menu.add(save);
			    menu.add(importe);
			    menubar.add(menu);
			    this.setJMenuBar(menubar);
			    this.validate();
			    this.repaint();
	}
	
	public void initListeners() {
		this.ci.addKeyListener(new ConsoleInputListener());
		
		//save listeners
		this.save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("save pressed");
				
			}
			
		});
		this.save.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_S && e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK) {
					System.out.println("ctrl + s");
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// aucun effet
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// aucun effet
			}
			
		});
		
		
		// Importe listener
		this.importe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("import pressed");
				
			}
			
		});
		this.importe.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_I && e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK) {
					System.out.println("ctrl + s");
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// aucun effet
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// aucun effet
			}
			
		});
	}
	
	
	// Listeners pour les differents classes
	class ConsoleInputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Si la string n'est pas vide on fait le traitement
				String command = ci.getText();
				if (command.length() > 0) {
					cp.append(ci.getText()+"\n");
					ci.setText("");
					//On analyse avec le parsing
					//Puis on affichage le résultat
				} else {
					// On reinitialise le console input
					ci.setText("");
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
