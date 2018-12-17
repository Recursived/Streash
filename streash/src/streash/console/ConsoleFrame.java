package streash.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.Command;
import streash.variables.command.CommandFactory;

public class ConsoleFrame extends JFrame {  
	/* 
	 * Classe repr√©sentant la console streash
	 * Compos√© d'un input field
	 * et d'un display field 
	 */
	
	// Composant graphique
	private ConsoleInput ci;
	private ConsolePanel cp;
	private JScrollPane scrollpane;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem save;
	private JMenuItem importe;
	
	// Composants fonctionnels
	private static int compteur_temp = 0;
	private final Map<String, Variable> vars = new HashMap<String, Variable>();
	private Stack<Variable> stack = new Stack<Variable>();
	private final Vector<String> mru = new Vector<String>(5); // cache de commande
	private int cacheIndex = 0;
	
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
	
	/*
	 * 
	 * Partie concernant les listeners et la logique de la console
	 * 
	 */
	public void initListeners() {
		this.ci.addKeyListener(new ConsoleInputListener());
		
		/*
		 * Save listener
		 */
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
		
		
		/*
		 * Import listeners
		 */
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
	
	// Main listener
	class ConsoleInputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Si la string n'est pas vide on fait le traitement
				String line = ci.getText();
				this.putIntoCache(line);
				if (line.length() > 0) {
					// Analayse de la chaine et traitement
					if (this.isAffectation(line)) {
						this.process(line, 0);
					} else if (this.isTempAffectation(line)) {
						this.process(line, 1);
					} else {
						cp.append(">> Syntax error : incorrect affectation (my_var = ... or  1 2 <add>)\n");
						ci.setText("");
					}
				} else {
					// On reinitialise le console input
					cp.append(">> You should type something before sending\n");
					ci.setText("");
				}
				
			//*********** Gestion du cache des commandes **********//
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (!mru.isEmpty()) {
					if (cacheIndex == mru.size() - 1) {
						ci.setText(mru.get(cacheIndex));
					} else {
						cacheIndex++;
						ci.setText(mru.get(cacheIndex));
					}
				}
				
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (!mru.isEmpty()) {
					if (cacheIndex == 0) {
						ci.setText(mru.get(cacheIndex));
					} else {
						cacheIndex--;
						ci.setText(mru.get(cacheIndex));
					}
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
		
		// Helper function
		private boolean isNumerical(String expr) {
			return expr.matches("(-)?[0-9]*(/(-)?[0-9]*)?");
		}
		
		private boolean isAlpha(String expr) {
			return expr.matches("[a-zA-Z]{1}[a-zA-Z0-9_]*");
		}
		
		private boolean isAffectation(String expr) {
			return expr.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]* = .*");
		}
		
		private boolean isTempAffectation(String expr) {
			return expr.matches("^(-)?[0-9]*(/(-)?[0-9]*)? .*") || expr.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]*.*");
		}
		
		private boolean isCommand(String expr) {
			return expr.matches("<[a-z][a-z]*>");
			
		}
		
		private void putIntoCache(String command) {
			if (mru.size() == 5) {
				mru.set(0, command);
			} else {
				mru.add(command);
			}
		}
		
		// main function 
		private void process(String line, int type) {
			try {
				stack.clear();
				if (type == 0) {
					String[] token = line.split(" ");
					String var = token[0];
					// On commence aprËs le token egal
					for (int i = 2; i < token.length; i++) {
						// si cest une ref ‡ une autre var
						if (this.isAlpha(token[i])) {
							Variable a = vars.get(token[i]);
							stack.push(a);
						// si cest un nombre
						}  else if (this.isNumerical(token[i])) {
							Variable n = Rational.parseRational(token[i]);
							stack.push(n);
						// si c'est une commande
						} else if (this.isCommand(token[i])) {
							Command c  = CommandFactory.createCommand(token[i]);
							// mauvaise ecriture de commande
							if (c == null) {
								cp.append("Unknown command : "+ token[i] +"\n");
							}
							for (int j = 0; j < c.nbArgs(); j++) {
								c.takeArg(stack.pop());
								
							}
							c.displayCommandArgs();
							stack.push(c.process());
						} else {
							cp.append(">> Syntax error : wrong token '"+token[i]+"'\n");
							ci.setText("");
						}
					}
					Variable to_insert = stack.pop();
					if (to_insert != null) {
						cp.append(var + " : " +to_insert.getConsoleString()+"\n");
						ci.setText("");
						vars.put(var, to_insert);
					}
				} else if ( type == 1) {
					String[] token = line.split(" ");
					for (int i = 0; i < token.length; i++) {
						System.out.println(token[i]);
						// si cest une ref ‡ une autre var
						if (this.isAlpha(token[i])) {
							Variable a = vars.get(token[i]);
							stack.push(a);
						// si cest un nombre
						}  else if (this.isNumerical(token[i])) {
							Variable n = Rational.parseRational(token[i]);
							stack.push(n);
						// si c'est une commande
						} else if (this.isCommand(token[i])) {
							Command c  = CommandFactory.createCommand(token[i]);
							// mauvaise ecriture de commande
							if (c == null) {
								cp.append("Unknown command : "+ token[i] +"\n");
							}
							for (int j = 0; j < c.nbArgs(); j++) {
								c.takeArg(stack.pop());
							}
							stack.push(c.process());
							c.displayCommandArgs();
						} else {
							cp.append(">> Syntax error : wrong token '"+token[i]+"'\n");
							ci.setText("");
						}
					}
					Variable to_insert = stack.pop();
					if (to_insert != null) {
						cp.append("temp" + compteur_temp + " : " + to_insert.getConsoleString()+"\n");
						ci.setText("");
						vars.put("temp" + compteur_temp, to_insert);
						compteur_temp++;
					}
				}
			} catch (RuntimeException e) {
				if (e instanceof NumberFormatException) {
					cp.append(">> Could not parse the string correctly, be careful with whitespaces "
							+ "\n The format should be : "
							+ "1 _ . _ <command> _ . _ .)"
							+ "\n Zero whitespace at the begining and one after each elem");
				} else if (e instanceof EmptyStackException) {
					cp.append(">> Not enough args for this command");
				} else {
					cp.append(" >> " + e.getMessage() + "\n");
				}
				ci.setText("");
				// debug
				System.out.println(e);
			}
		}
		
	}
	
	
}
