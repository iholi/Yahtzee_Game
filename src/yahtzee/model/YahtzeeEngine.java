package yahtzee.model;

import static org.junit.Assert.assertTrue;

import java.util.Vector;

public class YahtzeeEngine
{
    public static final int MAX_ROLLS = 3;

    private Vector<Player> myPlayers;
    private Player myPlayerUp;
    private Roller myRoller;
    private int myNumberRollsUsed;
    private int zero=0;
    private int myNumberPlayers;
    private int mySwitchPlayerUp;
    Player player;

    /**
     * The constructor of the class.
     * @param numPlayers
     */
    public YahtzeeEngine(int numPlayers)
    {
    	myPlayers=new Vector<Player>();
    	
    	if( numPlayers >1 || numPlayers<6) 
    	{
    		myNumberPlayers=numPlayers;
    	}
    	if (numPlayers >6 || numPlayers<1) 
    	{
    		myNumberPlayers=2;
    	}
    	
        for(int i=1; i<= myNumberPlayers; i++)
        {
            myPlayers.add(new Player("Player"));
        }
    }

    /**
     * This method start the game.
     */
    public void startGame()
    {       
    	player = this.getPlayers().get(zero);
    	this.selectPlayerUp(player);
    	myRoller = new Roller(5);
    }

    /**
     * 
     * @param player
     */
    public void selectPlayerUp(Player player)
    {
    	myPlayerUp=player;
    }

    public void switchPlayerUp()
    {
    	mySwitchPlayerUp= this.getPlayers().indexOf(this.getPlayerUp());
        if (myPlayerUp != this.getPlayers().get(mySwitchPlayerUp))
        {
      	  mySwitchPlayerUp--;
        }
        else 
        {
          mySwitchPlayerUp++;
        }
        if(mySwitchPlayerUp > this.getPlayers().size() ||mySwitchPlayerUp == this.getPlayers().size())
        {
          mySwitchPlayerUp = 0;
        }
        myPlayerUp = this.getPlayers().get(mySwitchPlayerUp);       
    }

    /**
     * This method increment the roll used if it is not greater than 3.
     */
    public boolean incrementRollsUsed()
    {	
    	if (myNumberRollsUsed==MAX_ROLLS || myNumberRollsUsed<MAX_ROLLS) 
    	{
        myNumberRollsUsed++;
    	return true;
    	}
    	else if (myNumberRollsUsed>MAX_ROLLS)
    	{
    		return false;
    	}
    	return false;
    }

    /**
     * This method reset to 0 the number of rolls used.
     */
    public void resetNumberRollsUsed()
    {
    	myNumberRollsUsed=zero;
    }

    /**
     * @return myRoller
     */
    public Roller getRoller()
    {
        return myRoller;
    }

    /**
     * @return myPlayerUp
     */
    public Player getPlayerUp()
    {
        return myPlayerUp;
    }

    /**
     * @return myPlayers
     */
    public Vector<Player> getPlayers()
    {
        return myPlayers;
    }

    /**
     * @return myNumberRollsUsed
     */
    public int getNumberRollsUsed()
    {
        return myNumberRollsUsed;
    }

    public String toString()
    {
        return null;
    }
}