package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatController;
import java.awt.Dimension;
/*
 * Author: Matt Powley
 * GUI frame class, builds the frame that the panel will
 * be placed in.
 */
public class ChatFrame extends JFrame
{
	private ChatController baseController;
	private ChatPanel appPanel;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		this.appPanel = new ChatPanel(baseController);
		
		setupFrame();
		
	}
	/*
	 * sets up the frame making it visible and sets dimensions.
	 */
	private void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setTitle("Chatbot");
		this.setSize(new Dimension(500, 500));
		this.setVisible(true);
	}

}
