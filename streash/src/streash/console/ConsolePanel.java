package streash.console;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

public class ConsolePanel extends JTextArea{

	public ConsolePanel() {
		super();
	}

	public ConsolePanel(Document arg0, String arg1, int arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ConsolePanel(Document arg0) {
		super(arg0);
	}

	public ConsolePanel(int arg0, int arg1) {
		super(arg0, arg1);
	}

	public ConsolePanel(String arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
	}

	public ConsolePanel(String arg0) {
		super(arg0);
	}



}
