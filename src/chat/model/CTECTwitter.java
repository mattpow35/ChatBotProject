package chat.model;

import chat.controller.ChatController;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.Status;
import java.util.List;
import java.util.Scanner;
import twitter4j.Query;
import twitter4j.QueryResult;

import java.text.DecimalFormat;
import java.util.ArrayList;

import twitter4j.GeoLocation;
import twitter4j.Paging;

public class CTECTwitter 
{
	private ChatController baseController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private List<String> tweetedHashtags;
	private GeoLocation brightonHigh;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.tweetedHashtags = new ArrayList<String>();
		this.chatbotTwitter = TwitterFactory.getSingleton();
		this.brightonHigh = new GeoLocation(40.612348, -111.826218);
		
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
		removeMentions();
		
		
		
		results += "There are " + tweetedWords.size() + " words in the tweets from " + user;
		results += calculatePopularWord();
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
	

	private String calculatePopularWord()
	{
		String information = "";
		String mostPopular = "";
		int popularIndex = 0;
		int popularCount = 0;
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			int currentPopularity = 0;
			for(int searched = 1; searched < tweetedWords.size(); searched++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)))
				{
					currentPopularity++;
					
				}
			}
			if (currentPopularity > popularCount)
			{
				popularIndex = index;
				popularCount = currentPopularity;
				mostPopular = tweetedWords.get(index);
			}
			
			
			
			
			
		}
		
		
		information = " The most popular word is: " + mostPopular + ", and it occurred " + popularCount +  " times out of " + tweetedWords.size() + ", AKA "
				+ (DecimalFormat.getPercentInstance().format(((double) popularCount)/tweetedWords.size()));
		
		return information;
	}
	/*
	 * state affection- this changes word list.
	 */
	private void removeMentions()
	{
		for (int index = 0; index < tweetedWords.size(); index ++)
		{
			if(tweetedWords.get(index).substring(0, 1).equals("@"))
			{
				tweetedWords.remove(index);
				index --;
			}
		}
		
	}
	
	private void keepOnlyHashtags()
	{
		for (int index = 0; index < tweetedWords.size(); index ++)
		{
			if(tweetedWords.get(index).substring(0, 1).equals("#"))
			{
				tweetedHashtags.add(tweetedWords.get(index));
			}
		}
	}
	
	public String getMostCommonHashtagAtBrighton(String date)
	{
		String results = "";
		collectTweetsFromBrighton(date);
		turnStatusesToWords();
		
		removeAllBoringWords();
		removeEmptyText();
		removeMentions();
		keepOnlyHashtags();
		
		results += "The most poplular hashtag since " + date;
		results += calculatePopularHashtag();
		return results;
	}
	
	private void collectTweetsFromBrighton(String date)
	{
		searchedTweets.clear();
		tweetedHashtags.clear();
		tweetedWords.clear();
		
		Query query = new Query();
		query.since(date);
		query.setGeoCode(brightonHigh, 5, Query.MILES);
		query.count(100);
		long lastId = Long.MAX_VALUE;
		
		while(searchedTweets.size() < 1000)
		{
			if(1000 - searchedTweets.size() > 100)
			{
				query.setCount(100);
			}
			else
			{
				query.setCount(1000 - searchedTweets.size());
			}
	
			try
			{
				QueryResult result = chatbotTwitter.search(query);
				searchedTweets.addAll(result.getTweets());
				for(Status currentTweet : searchedTweets)
				{
					if(currentTweet.getId() < lastId)
					{
						lastId = currentTweet.getId();
					}
				}
			
			}
			catch(TwitterException searchTweetError)
			{
				baseController.handleErrors(searchTweetError);
				
			
			}
			query.setMaxId(lastId);
			
		}
		
	}
	
	private String calculatePopularHashtag()
	{
		String information = "";
		String mostPopular = "";
		int popularIndex = 0;
		int popularCount = 0;
		
		for(int index = 0; index < tweetedHashtags.size(); index++)
		{
			int currentPopularity = 0;
			for(int searched = 1; searched < tweetedHashtags.size(); searched++)
			{
				if(tweetedHashtags.get(index).equalsIgnoreCase(tweetedHashtags.get(searched)))
				{
					currentPopularity++;
					
				}
			}
			if (currentPopularity > popularCount)
			{
				popularIndex = index;
				popularCount = currentPopularity;
				mostPopular = tweetedHashtags.get(index);
			}
			
			
			
			
			
		}
		
		
		information = " within 5 miles of Brighton is: " + mostPopular + ", and it occurred " + popularCount +  " times out of " + tweetedHashtags.size() + ", AKA "
				+ (DecimalFormat.getPercentInstance().format(((double) popularCount)/tweetedHashtags.size()));
		
		return information;
	}
}
