package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatbotController;
import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	private ChatbotController baseFrame;
	
	public ChatFrame(ChatbotController baseFrame)
	{
		super();
		this.baseFrame = baseFrame;
		
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setTitle("Chatbot");
		this.setSize(new Dimension(500, 500));
		this.setVisible(true);
	}

}
