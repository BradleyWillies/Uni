/**
 * 
 */
package phonebook;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A GUI for handling input/output 
 * 	for the maintenance of the records in a phone book.
 * 
 * @author Sylvia Wong
 * @version 13 Sep 2020
 */
public class GUI {
	
	/* !!!!
	 * Define the type of values that the field "phoneBook" is expected to hold. 
	 */
	private      phoneBook;
	
	private Font font;	// font used by all GUI components
	
	private JFrame frame;
	private JTextArea displayTextArea;
	private JButton addButton;
	private JButton lookupButton;
	private JButton exitButton;

	private JTextField nicknameTextField;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;
	
	/**
	 * Constructor
	 */
	public GUI(PhoneBook<String, Correspondence, String> phoneBook) {
		
		this.phoneBook = phoneBook;
		
		/* ++++
		 * Create a suitable font to be used for displaying UTF8 characters
		 */ 
		char[] forDisplay = {'ö', 'ŏ', '조'}; // Non-ASCII characters to be supported by this application. 
		String supportedFont = GUIUtil.getSuitableFontNames(forDisplay);  // Looks up a font that supports the specified characters		
		if (supportedFont != null) {
				font = new Font(supportedFont,Font.BOLD,20);
		}
		else {
			 /* Throw an exception when no suitable font can be used 
			  * to display the requested character (set).
			  */
			throw new UnsupportedCharsetException("Character set with the character ö, ŏ or 조.");
		}
		
		// create the main GUI window
		makeFrame();
	}


	/*
	 * Create a window to model the running of a lottery session.
	 * This window is the main GUI component for this application.
	 */
	private void makeFrame(){
		frame = new JFrame("Phone Book");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();		
		// set layout
		contentPane.setLayout(new BorderLayout());
		// create an area for displaying contents of this contact list
		displayTextArea = new JTextArea(10,10);
		// purple text
		displayTextArea.setForeground(new Color(150,0,171));
		displayTextArea.setBackground(new Color(255,255,255));
		displayTextArea.setEditable(false);
		displayTextArea.setWrapStyleWord(true);
		displayTextArea.setLineWrap(true);
		displayTextArea.setBorder(new EtchedBorder());
		// create a scrollable pane
		JScrollPane scrollable = new JScrollPane(displayTextArea);
		contentPane.add(scrollable, BorderLayout.CENTER);
		// create a panel for placing a text field and its label
		JPanel tfPanel1 = new JPanel();
		tfPanel1.setLayout(new BoxLayout(tfPanel1, BoxLayout.X_AXIS));
		// create a label
		JLabel nicknameLabel = new JLabel("Nickname:");
		nicknameLabel.setForeground(Color.GRAY);
		tfPanel1.add(nicknameLabel);
		// create a text field
		nicknameTextField = new JTextField(40);
		nicknameTextField.setEditable(true);
		tfPanel1.add(nicknameTextField);
		
		// create a panel for placing a text field and its label
		JPanel tfPanel2 = new JPanel();
		tfPanel2.setLayout(new BoxLayout(tfPanel2, BoxLayout.X_AXIS));
		// create a label
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setForeground(Color.GRAY);
		tfPanel2.add(nameLabel);
		// create a text field
		nameTextField = new JTextField(20);
		nameTextField.setEditable(true);
		tfPanel2.add(nameTextField);	

		// create a panel for placing a text field and its label
		JPanel tfPanel3 = new JPanel();
		tfPanel3.setLayout(new BoxLayout(tfPanel3, BoxLayout.X_AXIS));
		// create a label
		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setForeground(Color.GRAY);
		tfPanel3.add(phoneLabel);
		// create a text field
		phoneTextField = new JTextField(40);
		phoneTextField.setEditable(true);
		tfPanel3.add(phoneTextField);
		
		// create a panel for placing a text field and its label
		JPanel tfPanel4 = new JPanel();
		tfPanel4.setLayout(new BoxLayout(tfPanel4, BoxLayout.X_AXIS));
		// create a label
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setForeground(Color.GRAY);
		tfPanel4.add(addressLabel);
		// create a text field
		addressTextField = new JTextField(40);
		addressTextField.setEditable(true);
		tfPanel4.add(addressTextField);		
		
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new BoxLayout(tfPanel, BoxLayout.Y_AXIS));
		tfPanel.add(tfPanel1);
		tfPanel.add(tfPanel2);
		tfPanel.add(tfPanel3);
		tfPanel.add(tfPanel4);		
		contentPane.add(tfPanel, BorderLayout.NORTH);

		// create a button for adding new contact record
		addButton = createJButton("Add");
		/* ++++
		 * Use an anonymous inner class to implement the ActionListener
		 * 	for the add button.
		 */
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nickname = nicknameTextField.getText().trim();
				String name = nameTextField.getText().trim();
				String phone = phoneTextField.getText().trim();
				if(!nickname.equals("") && !name.equals("") && !phone.equals(""))
				{
					Correspondence record = phoneBook.lookupName(nickname+name);
					if(record == null) {
						// Create a new correspondence record and add it to the phone book.
						phoneBook.add(
								new Correspondence(nickname,
										name,
										phone,
										addressTextField.getText().trim())
						);
					}
					else {
						/* ++++
						 * Add the given phone to the correspondence record.
						 */
						record.addPhone(phone);
					}
					// Reset all text fields.
					nicknameTextField.setText("");
					nameTextField.setText("");
					phoneTextField.setText("");
					addressTextField.setText("");
					// Display all contact info.
					displayTextArea.setText(phoneBook.toString());						
				}
				else {
					displayTextArea.setText("Please enter contact details.\n");
				}
			}
		});		
		
		// create a button for look up a contact record
		lookupButton = createJButton("Search");
		/* ++++
		 * Use an anonymous inner class to implement the ActionListener
		 * 	for the lookup button.
		 */
		lookupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* ++++
				 * The event handler for the lookup button.
				 * Two kinds of lookup may be done here.
				 * If the user enters a nickname and a name, the search is by name.
				 * If the user enters a phone number only, the search is by phone number.
				 */
				String results = "Record not found!";
				String key = nicknameTextField.getText().trim() + nameTextField.getText().trim();
				String phone = phoneTextField.getText().trim();
				/* Look up the required record(s) */
				if(!key.equals("")) {
					Correspondence record = phoneBook.lookupName(key);
					if(record != null) {
						results = record.toString();
					}
				}
				else {
					Set<Correspondence> records = phoneBook.lookupPhone(phone);
					/* Prepare a string for displaying the results */
					if(records != null) {
						results = "";
						for(Correspondence c : records) {
							results += c.toString() + "\n";
						}
					}					
				}
				// Reset all text fields.
				nicknameTextField.setText("");
				nameTextField.setText("");
				phoneTextField.setText("");
				addressTextField.setText("");
				// Display all contact info.
				displayTextArea.setText(results);				
			}
		});			

		// create an exit button
		exitButton = createJButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// create a panel for placing the button
		JPanel buttonPanel = new JPanel();
		new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.add(addButton);		
		buttonPanel.add(lookupButton);	
		buttonPanel.add(exitButton);		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		/* ++++ 
		 * Customise the font used in each component with the JFrame. 
		 */
		GUIUtil.setFont4All(frame, font);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/*
	 * Create a standard JButton with a specified label.
	 * @param label
	 */
	private JButton createJButton(String label) {
		JButton jb = new JButton(label);
		// nice gray-ish blue background
		jb.setBackground(new Color(116,151,200));
		jb.setForeground(Color.WHITE);
		jb.setSize(30,20);
		return jb;
	}


}
