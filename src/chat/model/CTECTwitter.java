package chat.model;

import chat.controller.ChatController;


public class CTECTwitter 
{
	private ChatController baseController;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
			
		}
	}

}
