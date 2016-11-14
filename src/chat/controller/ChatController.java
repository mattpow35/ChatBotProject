package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	
	public ChatController()
	{
		stupidBot = new Chatbot("stupid robot");
		display = new ChatViewer();
		
	}
	
	public void start()
	{
		String response = display.collectResponse("What do you want to talk about????");
		
		while(stupidBot.lengthChecker(response))
		{
			display.collectResponse("Oh you want to talk bout " + response +"? Tell me more...");
			display.displayMessage(useChatbotCheckers(response));
			
		}
	}
	
	private String useChatbotCheckers(String input)
	{
		String checkedInput = "";
		
		if (stupidBot.memeChecker(input))
		{
			checkedInput += "\nYou like dank memes!\n ";
		}
		if (stupidBot.contentChecker(input))
		{
			checkedInput += "\nYou know my secret topic!\n";
		}
		if (checkedInput.length() == 0)
		{
			checkedInput = "I have no idea what you mean about " + input;
		}
		if (stupidBot.politicalTopicChecker(input))
		{
			checkedInput += "\nYou want to talk about politics.\n";
		}
		
		if (stupidBot.keyboardMashChecker(input))
		{
			checkedInput += "\nYou just mashed random things on the keyboard";
		}
		if (stupidBot.twitterChecker(input))
		{
			checkedInput +="\nYou want to talk about twitter\n";
		}
		if (stupidBot.inputHTMLChecker(input))
		{
			checkedInput += "\nYou want to talk about HTML\n";
		}
		if (stupidBot.quitChecker(input))
		{
			checkedInput += "\nYou want to quit the chatbot :(\n";
		}
		
		return checkedInput;
	}
}
