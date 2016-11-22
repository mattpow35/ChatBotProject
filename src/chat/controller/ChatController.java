package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;

public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer display;
	private ChatFrame baseFrame;
	
	public ChatController()
	{
		stupidBot = new Chatbot("stupid robot");
		display = new ChatViewer();
		baseFrame = new ChatFrame(this);
		
	}
	
	public void start()
	{
		

	}
	
	public String useChatbotCheckers(String input)
	{
		String checkedInput = "";
		if(!stupidBot.quitChecker(input))
		{
		
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
				checkedInput += "\nYou just mashed random things on the keyboard\n";
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
			
			int canBeRandom= (int) (Math.random() *2);
			if(canBeRandom % 2 == 0)
			{
				checkedInput += randomTopicGenerator();
			}
		
		}
		else
		{
			display.displayMessage("Thanks for chatting, seeya soon!");
			System.exit(0);
		}
		
		return checkedInput;
	}
	
	private String randomTopicGenerator()
	{
		String randomTopic = "";
		int random = (int) (Math.random() * 7);
		
		switch(random)
		{
		case 0:
			randomTopic = "Did you hear about the daft punk beastie boys mix?";
			break;
		case 1:
			randomTopic = "can you bring me the sriracha";
			break;
		case 2:
			randomTopic = "Writing code is lots of fun";
			break;
		case 3:
			randomTopic = "Did you hear about those new dank memes?";
			break;
		case 4:
			randomTopic = "Politics is so crazy right now!";
			break;
		case 5:
			randomTopic = "I really love to play lacrosse";
			break;
		case 6:
			randomTopic = "Do you have a twitter?";
			break;
		default:
			randomTopic = "This can't be happening!!";
			break;
		}
		
		return randomTopic;
	}
	
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
	
	public Chatbot getChatbot()
	{
		return stupidBot;
	}
	
	
}

