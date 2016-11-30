package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel
{ 
	/**
	 * data members that are needed in order to build the chatpanel.
	 * The chatpanel will use the controller, springlayout, a JTextField,
	 * a JButton, and a JLabel.
	 */
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JLabel chatLabel;
/**
 * the controller for chatpanel. It initializes all of the data members and starts all of the helper methods. (setup methods)
 * @param baseController
 */
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatButton = new JButton("Chat with the bot");
		chatLabel = new JLabel("Welcome to Chatbot");
		
		
		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * Sets up the display on the GUI so that it is neither editable or enabled.
	 * It also wraps the text so it goes on to the next line and fits inside the display. 
	 * 
	 */
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setLineWrap(true);
	}
	
	/**
	 * Sets up the panel, defines the type of layout, changes background color, 
	 * adds a button text field display and label.
	 * sets the visibility to true so that we can see and use the panel.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.BLUE);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatDisplay);
		this.add(chatLabel);
		this.setVisible(true);
		
	}
	
	/**
	 * All of the unwanted auto generated code that came from the design window.
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 187, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 63, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -12, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 45, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 63, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 135, SpringLayout.WEST, this);
		
	}
	
	/**
	 * sets up all of the "listeners" aka buttons. 
	 * gives the button a function and makes it so something happens when it is clicked.
	 */
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String input = chatField.getText();
				String chatbotResponse = baseController.useChatbotCheckers(input);
				
				chatDisplay.setText("You said: " + input +"\n"+ "Chatbot says " + chatbotResponse);
				chatField.setText("");
			}
		});
	}
}
