package chat.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import chat.controller.ChatbotController;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatPanel
{
	private ChatbotController basePanel;
	private JButton chatButton;
	private SpringLayout baseLayout;

	public ChatPanel(ChatbotController basePanel)
	{
		super();
		
		baseLayout = new SpringLayout();
		setupPanel();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		
	}
}
