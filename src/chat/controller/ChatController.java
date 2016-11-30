package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;
/**
 * 
 * @author Matt Powley
 *Controller class for chatBot
 */
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
				checkedInput += "\nYou like dank memes! I love dank memes also!!\n ";
			}
			if (stupidBot.contentChecker(input))
			{
				checkedInput += "\nYou know my secret topic!\n";
			}
			if (input.equals(""))
			{
				checkedInput += "\n You did not type anything\n";
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
			randomTopic = "\nDid you hear about the daft punk beastie boys mix?\n";
			break;
		case 1:
			randomTopic = "\nDo you play lacrosse?\n";
			break;
		case 2:
			randomTopic = "\nWriting code is lots of fun\n";
			break;
		case 3:
			randomTopic = "\nDid you hear about those new dank memes? What is your favorite meme?\n";
			break;
		case 4:
			randomTopic = "\nPolitics is so crazy right now!\n";
			break;
		case 5:
			randomTopic = "\nI really love to play lacrosse\n";
			break;
		case 6:
			randomTopic = "\nDo you have a twitter? What is you username or favorite hashtag?\n";
			break;
		default:
			randomTopic = "\nThis can't be happening!!\n";
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

