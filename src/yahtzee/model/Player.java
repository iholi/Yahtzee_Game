package yahtzee.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player
{
    public static String DEFAULT_NAME = "Player";
    
    private String myName;
    private int myNumberOfWins;
    private ScoreCard myScoreCard;
    int zero=0;
    
    public Player()
    {	
    	ScoreCard card= new ScoreCard();
    	this.setScoreCard(card);
    }

    public Player(String name)
    {
    	myName=name;
    	ScoreCard card= new ScoreCard();
    	this.setScoreCard(card);
    }

    /**
     * This method check if the name is valid. The names should only contain alphanumeric characters.
     * @param name
     * @return
     */
    public static boolean validateName(String name)
    {
    	// "[^\w]" belong to the character classes and they are used to define the content of the pattern. The pattern for "[^\w]" is a non-word character.
    	//http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/ 
    	
    	 String expression = "[^\\w]";					
         Pattern pattern = Pattern.compile(expression);
         Matcher matcher = pattern.matcher(name);
         
         if(matcher.find())
         {
        	 return false;
         }
         else
         {
              return true;
         }
    }

    /**
     * This method increment the number of wins.
     */
    public void incrementWins()
    {
    	myNumberOfWins++;
    }
    
    /**
     * This method reset the number of wins to 0.
     */
    public void resetWins()
    {
    	myNumberOfWins=zero;
    }

    public void setName(String name)
    {
    	myName=name;
    }

    /**
     * @return myName
     */
    public String getName()
    {
        return myName;
    }

    /**
     * @return myNumberOfWins
     */
    public int getNumberOfWins()
    {
        return myNumberOfWins;
    }

    public void setScoreCard(ScoreCard card)
    {
    	myScoreCard=card;
    }

    /**
     * @return myScoreCard
     */
    public ScoreCard getScoreCard()
    {
		return myScoreCard;
    }

    public String toString()
    {
        return myName;
    }

    /**
     * Clone player name and number of wins.
     */
    public Object clone()
    {
    	Player copy = new Player();
    	copy.myName = myName;
    	copy.myNumberOfWins = myNumberOfWins;
    	return copy;
    }
}