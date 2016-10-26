package chat.model;

import java.util.ArrayList;

public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicsList;
	private String userName;
	private String content;

	/**
	 * * Creates an instance of the Chatbot with the supplied username. * @param
	 * userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		memesList = new ArrayList<String>();
		buildMemesList();
		politicsList = new ArrayList<String>();
		buildPoliticalTopicsList();
		this.userName = userName;
		this.content = "lacrosse";
	}

	private void buildMemesList()
	{
		memesList.add("doge");
		memesList.add("cute animals");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
		memesList.add("willy wonka");
		memesList.add("harambe");
		memesList.add("john cena");
		memesList.add("ken bone");
		memesList.add("arthur fist");
		memesList.add("kermit");
		memesList.add("shrek");
		memesList.add("the office");
		memesList.add("bad luck brian");
		memesList.add("baby fist pump");
		memesList.add("sports");
		memesList.add("gavin");
		memesList.add("steve harvey");
		memesList.add("chuck norris");
		memesList.add("trump");
		
	}	
	

	private void buildPoliticalTopicsList()
	{
		politicsList.add("Democrat");
		politicsList.add("Republican");
		politicsList.add("conservative");
		politicsList.add("11/8/16");
		politicsList.add("Clinton");
		politicsList.add("Trump");
		politicsList.add("Kaine");
		politicsList.add("Pence");
		politicsList.add("Stein");
		politicsList.add("Johnson");
		politicsList.add("election");
		politicsList.add("president");
		politicsList.add("debate");
		politicsList.add("vote");
		politicsList.add("liberal");
		politicsList.add("politics");
		politicsList.add("white house");
		politicsList.add("vice president");
		politicsList.add("campaign");
		politicsList.add("Hillary");
		politicsList.add("11/8/2016");
	}

	/**
	 * * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. 
	 * * @param currentInput 
	 * * @return
	 * A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		
		if (currentInput != null && !currentInput.equals(""))
		{
			hasLength = true;
		}
		
		return  hasLength;
	}

	/**
	 * * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * 
	 * @param currentInput
	 * The supplied String to be checked. 
	 * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;
		if (currentInput.contains(content))
		{
			hasContent = true;
		}
		
		return hasContent;
	}

	/**
	 * * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean isPolitical = false;
		
		for(String checkPolitical : politicsList)
		{
			if(currentInput.equals(checkPolitical))
			{
				isPolitical = true;	
			}
			
		}
		
		return isPolitical;
	}

	/**
	 * * Checks to see that the supplied String value is in the current
	 * memesList variable.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean isMeme = false;
		
		for (String checkMeme : memesList)
		{
			if(currentInput.equalsIgnoreCase(checkMeme))
			{
				isMeme = true;
			}
		}
		
		return isMeme;
	}

	/**
	 * * Returns the username of this Chatbot instance. * @return The username
	 * of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * * Returns the content area for this Chatbot instance. * @return The
	 * content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * * Getter method for the memesList object. * @return The reference to the
	 * meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * * Getter method for the politicalTopicList object. * @return The
	 * reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicsList;
	}

	/**
	 * * Updates the content area for this Chatbot instance. * @param content
	 * The updated value for the content area.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public boolean quitChecker(String currentInput)
	{
		boolean checkQuit = false;
		
		if (currentInput.equals("exit"))
		{
			checkQuit = false;
		}
		if (currentInput.equals("quit"))
		{
			checkQuit = true;
		}
		
		return checkQuit;
	}

}
