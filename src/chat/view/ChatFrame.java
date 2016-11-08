package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatController;
import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	private ChatController baseController;
	private ChatFrame baseFrame;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		
		setupFrame();
		
	}
	
	private void setupFrame()
	{
		this.setTitle("Chatbot");
		this.setSize(new Dimension(500, 500));
		this.setVisible(true);
	}

}
