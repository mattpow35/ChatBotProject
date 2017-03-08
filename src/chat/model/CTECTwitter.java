package chat.model;

import chat.controller.ChatController;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.Status;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import twitter4j.Paging;

public class CTECTwitter 
{
	private ChatController baseController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.chatbotTwitter = TwitterFactory.getSingleton();
		
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			chatbotTwitter.updateStatus(textToTweet + "#JavaChatbot @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}
	
	private String [] createIgnoredWordArray()
	{
		String [] boringWords;
		
		int wordCount = 0;
		Scanner wordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		
		while(wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		
		boringWords = new String [wordCount];
		wordScanner.close();
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		for(int index = 0; index < boringWords.length; index ++)
		{
			boringWords[index] = wordScanner.nextLine();
		}
		
		wordScanner.close();
		return boringWords;
	}
	
	private void collectTweets(String username)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		
		while(page <= 10)
		{
			statusPage.setPage(page);
			try
			{
				searchedTweets.addAll(chatbotTwitter.getUserTimeline(username, statusPage));
				
				page ++;
			}
			catch(TwitterException searchTweetError)
			{
				baseController.handleErrors(searchTweetError);
				
				page = 11;
			}
			
		}
	}
	
	public String getMostCommonWord(String user)
	{
		String results = "";
		
		collectTweets(user);
		turnStatusesToWords();
		
		removeAllBoringWords();
		removeEmptyText();
		
		
		results += "There are " + tweetedWords.size() + " words in the tweets from " + user;
		return results;
	}
	
	private void removeEmptyText()
	{
		for (int index = 0; index < tweetedWords.size(); index ++)
		{
			if (tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index --;
			}
		}
	}
	
	private void removeAllBoringWords()
	{
		String [] boringWords = createIgnoredWordArray();
		for (int index = 0; index < tweetedWords.size(); index ++ )
		{
			for (int boringIndex = 0; boringIndex < boringWords.length; boringIndex ++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText();
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(tweetWords[index]);
			}
		}
	}

}