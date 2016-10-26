package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatbotController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	
	public ChatbotController()
	{
		stupidBot = new Chatbot("stupid robot");
		display = new ChatViewer();
	}
	
	public void start()
	{
		String response = display.collectResponse("What do you want to talk about????");
		
		while(stupidBot.lengthChecker(response))
		{
			display.displayMessage(useChatbotCheckers(response));
			display.collectResponse("Oh you want to talk bout " + response +"? Tell me more...");
			
		}
	}
	
	private String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about " + input;
		
		if (stupidBot.memeChecker(input))
		{
			checkedInput += "\nYou like memes!\n ";
		}
		if (stupidBot.contentChecker(input))
		{
			checkedInput += "\nYou know my secret topic!\n";
		}
		
		return checkedInput;
	}
}
