/**
 * 
 */
package lottery;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import dsa.ArraySet;
import dsa.SetADT;

/**
 * A GUI for handling input/output of a simple lottery application.
 * 
 * @author Sylvia Wong
 * @version 10/10/2020
 */
public class GUILottery{
	
	// The range of acceptable lottery numbers.
	private static final int SMALLEST = 1;
	
	private Lottery lotto;
	
	private JFrame lotteryFrame;
	private JTextField[] textFields;
	private JTextArea announceWinnerTextArea;
	private JButton resetButton;
	private JButton buyTicketButton;
	private JButton runLotteryButton;
	private JButton checkWinButton;
	private JButton exitButton;
	// A dialog window for selling lottery tickets
	private JDialog dialogue;
	
	/**
	 * Constructor
	 */
	public GUILottery(Lottery lotto) {
		
		this.lotto = lotto;
		
		// create the main GUI window
		makeFrame();
	}
	

	/*
	 * Create a window to model the running of a lottery session.
	 * This window is the main GUI component for this application.
	 */
	private void makeFrame(){
		lotteryFrame = new JFrame("Lottery");
		lotteryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = lotteryFrame.getContentPane();		
		// set layout
		contentPane.setLayout(new BorderLayout());
		// create a label
		JLabel label = new JLabel("Current Winning Numbers:");
		label.setForeground(Color.GRAY);
		contentPane.add(label, BorderLayout.NORTH);
		// create an area for announcing winner(s)
		announceWinnerTextArea = new JTextArea(10,10);
		// green text
		announceWinnerTextArea.setForeground(new Color(112,192,117));
		announceWinnerTextArea.setBackground(new Color(255,255,255));
		//textArea.setSize(150,100);
		announceWinnerTextArea.setEditable(false);
		announceWinnerTextArea.setWrapStyleWord(true);
		announceWinnerTextArea.setLineWrap(true);
		announceWinnerTextArea.setBorder(new EtchedBorder());
		// create a scrollable pane
		JScrollPane scrollable = new JScrollPane(announceWinnerTextArea);
		contentPane.add(scrollable, BorderLayout.CENTER);
		// create a panel for placing buttons and text fields
		JPanel tfPanel = new JPanel();		
		// create a button for drawing the winning numbers
		runLotteryButton = new JButton("Draw Numbers");
		// nice gray-ish blue background
		runLotteryButton.setBackground(new Color(116,151,200));
		runLotteryButton.setForeground(Color.WHITE);
		runLotteryButton.setSize(30,20);
		runLotteryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Disable the buy ticket button so that no-one
				 * can buy any more ticket now.
				 */ 
				buyTicketButton.setEnabled(false);
				// Remove the ticket booth dialogue window if it exists.
				if(dialogue != null)
					dialogue.dispose();				
				/* Disable the run lottery button so that the current
				 * winning numbers can't be accidentally overridden.
				 */ 
				runLotteryButton.setEnabled(false);
				// Enable the check win button.
				checkWinButton.setEnabled(true);					
				int[] results = lotto.drawNumbers();
				for (int i = 0; i < textFields.length; i++)
					textFields[i].setText(""+results[i]);
				announceWinnerTextArea.setText(lotto.winningNumbers());
			}
		});		
		// Add the button to the panel where the text fields are kept.
		tfPanel.add(runLotteryButton);				
		textFields = new JTextField[lotto.maxPick()];
		// create text fields for inputting lottery numbers
		for (int i = 0; i < lotto.maxPick(); i++)
		{
			textFields[i] = new JTextField(2);
			// Disable the text field so that no-one can enter anything.
			textFields[i].setEditable(false);
			// green text
			textFields[i].setForeground(new Color(112,192,117));
			// black background
			textFields[i].setBackground(new Color(255,255,255));
			textFields[i].setBorder(new EtchedBorder());
			tfPanel.add(textFields[i]);			
		}
		// Create a button for checking if any one has won the lottery.
		checkWinButton = new JButton("Who has won?");
		// Disable the check win button.
		checkWinButton.setEnabled(false);		
		// nice gray-ish blue background
		checkWinButton.setBackground(new Color(116,151,200));
		checkWinButton.setForeground(Color.WHITE);
		checkWinButton.setSize(30,20);
		checkWinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Display a message on the text area 
				 * to announce who has won.
				 */
				 announceWinnerTextArea.setText(lotto.hasWon()+'\n'+ lotto.allTickets());
				//announceWinnerTextArea.setText(lotto.hasWon());
			}
		});		
		// Add the button to the panel where the text fields are kept.
		tfPanel.add(checkWinButton);			
		contentPane.add(tfPanel, BorderLayout.NORTH);		
		/* Create a button for resetting the lottery. 
		 * This needs to be everytime when we open a new lottery session.
		 */ 
		resetButton = new JButton("New Lottery Session");
		// nice gray-ish blue background
		resetButton.setBackground(new Color(116,151,200));
		resetButton.setForeground(Color.WHITE);
		resetButton.setSize(30,20);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reset the lottery member fields.
				lotto.reset();
				// Reset the winning number text fields 
				for (int i = 0; i < textFields.length; i++)
					textFields[i].setText("");	
				// Reset the winner announcement text field
				announceWinnerTextArea.setText("");
				// Disable the check win button.
				checkWinButton.setEnabled(false);
				// Enable the buy ticket button again.
				buyTicketButton.setEnabled(true);
				// Enable the run lottery button again.
				runLotteryButton.setEnabled(true);	
				// Remove the ticket booth dialogue window if it exists.
				if(dialogue != null)
					dialogue.dispose();
			}
		});
		// Create a button for starting up a ticket booth.
		buyTicketButton = new JButton("Buy a Lottery Ticket");
		// nice gray-ish blue background
		buyTicketButton.setBackground(new Color(116,151,200));
		buyTicketButton.setForeground(Color.WHITE);
		buyTicketButton.setSize(30,20);
		buyTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a ticket booth for buying lottery tickets
				makeDialog(lotteryFrame);
			}
		});		
		// create an exit button
		exitButton = new JButton("Exit");
		// nice gray-ish blue background
		exitButton.setBackground(new Color(116,151,200));
		exitButton.setForeground(Color.WHITE);
		exitButton.setSize(30,20);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// create a panel for placing the button
		JPanel panel = new JPanel();
		new BoxLayout(panel, BoxLayout.X_AXIS); 
		panel.add(resetButton);
		panel.add(buyTicketButton);
		panel.add(exitButton);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		lotteryFrame.pack();
		lotteryFrame.setVisible(true);
		
	}
	
	/*
	 * Create a dialogue window to model the purchasing of a lottery ticket.
	 */
	private void makeDialog(JFrame owner){
		// The basic components in the dialogue window.
		final JTextField[] textFields;
		final JButton buyButton;
		final JButton luckyDipButton;
		
		dialogue = new JDialog(owner, "Lottery Ticket Booth");
		dialogue.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Container contentPane = dialogue.getContentPane();		
		// set layout
		contentPane.setLayout(new BorderLayout());
		// create a label
		JLabel label = new JLabel(
				String.format("Choose Unique Numbers from 1-%d please... ", lotto.largest())
				);
		label.setForeground(Color.GRAY);
		contentPane.add(label, BorderLayout.NORTH);
		JPanel tfPanel = new JPanel();
		new BoxLayout(tfPanel, BoxLayout.X_AXIS);
		textFields = new JTextField[lotto.maxPick()];
		// create text fields for inputting lottery numbers
		for (int i = 0; i < lotto.maxPick(); i++)
		{
			textFields[i] = new JTextField(2);
			// green text
			textFields[i].setForeground(new Color(112,192,117));
			// black background
			textFields[i].setBackground(new Color(255,255,255));			
			textFields[i].setEditable(true);
			textFields[i].setBorder(new EtchedBorder());
			tfPanel.add(textFields[i]);			
		}
		contentPane.add(tfPanel, BorderLayout.CENTER);
		// Create a panel for keeping buttons.
		JPanel bPanel = new JPanel();
		new BoxLayout(bPanel, BoxLayout.X_AXIS);
		// create a button
		buyButton = new JButton("Buy Ticket");
		// nice gray-ish blue background
		buyButton.setBackground(new Color(116,151,200));
		buyButton.setForeground(Color.WHITE);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// a program flag to see if there exists any error in the inputs
				boolean error = false;
				/* Get all entered numbers */
				SetADT<Integer> purchasingNumbers = new ArraySet<Integer>(textFields.length);
				// add all entered unique numbers into the set
				for (int i = 0; i < textFields.length; i++)
				{
					// get the number from a text field and add it to the set
					try {
						int choice = Integer.parseInt(textFields[i].getText());
						// Is the chosen number within the acceptable range?
						if (choice >= SMALLEST && choice <= lotto.largest())
						{
							purchasingNumbers.add(choice);
						}
						else
						{
							textFields[i].setText("");
							error = true;
						}
					}
					catch (NumberFormatException enf) {
						textFields[i].setText("");
						error = true;
					}
				}
				/* If sufficient numbers are entered, 
				 * 		the purchase can take place; 
				 * otherwise
				 * 		show an error message.
				 */
				if (purchasingNumbers.size() == lotto.maxPick())
				{
					int ticketID = lotto.sellTicket(purchasingNumbers);
					showMessage("Ticket number: " + ticketID);
					for (int i = 0; i < textFields.length; i++)
						textFields[i].setText("");
				}
				else
					error = true;
				
				// Any error(s) occurred in the inputs?
				if (error)
					showMessage("Please enter " + lotto.maxPick() + " unique numbers between " + SMALLEST + "-" + lotto.largest() + " or simply buy a Lucky Dip ticket.");
			}
		});
		bPanel.add(buyButton);		
		// create a button for Lucky Dip
		luckyDipButton = new JButton("Buy Lucky Dip");
		// nice gray-ish blue background
		luckyDipButton.setBackground(new Color(116,151,200));
		luckyDipButton.setForeground(Color.WHITE);
		luckyDipButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				/* No need to get any number from user. 
				 * All numbers should be randomly generated.
				 */
				for (int i = 0; i < textFields.length; i++)
				{
					// set the content of each text field to be "" 
					textFields[i].setText("");
				}
				// Sell a Lucky Dip ticket.
				int ticketID = lotto.sellTicket();
				showMessage("Ticket number: " + ticketID);
			}
		});
		bPanel.add(luckyDipButton);
		contentPane.add(bPanel, BorderLayout.SOUTH);
		
		dialogue.pack();
		dialogue.setVisible(true);
	}	
	
	/*
	 * Display a dialogue window with a specified message 
	 */
	private void showMessage(String message){
		JOptionPane.showMessageDialog(lotteryFrame, message);
	}
	
}
