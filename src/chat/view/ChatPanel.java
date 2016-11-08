package chat.view;

import javax.swing.SpringLayout;
import chat.controller.ChatController;
import javax.swing.JPanel;

public class ChatPanel extends JPanel
{
	private ChatController basePanel;
	private SpringLayout baseLayout;

	public ChatPanel(ChatController basePanel)
	{
		super();
		this.basePanel = basePanel;
		
		baseLayout = new SpringLayout();
		SetupPanel();
		SetupListeners();
	}
	
	private void SetupPanel()
	{
		
		
	}
	
	private void SetupListeners()
	{
		
	}
}
