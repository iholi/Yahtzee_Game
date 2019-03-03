package yahtzee.model;

import java.util.Random;

public class Die
{
    public static final int NO_VALUE = -1;

    private int myFaceValue;
    private Random myRandom;
    private boolean myIsRolled;
    private boolean getIsChosen;

    public Die()
    {
    	myFaceValue = NO_VALUE;
    	myIsRolled = false;
    	getIsChosen = false;
    	myRandom = new Random();
  
    }

    /*
     * return the value being rolled
     */
    public int roll()
    {
    	myIsRolled=true;	
    	int value = myRandom.nextInt(6);
    	myFaceValue = value +1;
        return myFaceValue;
    }

    public void toggleIsChosen()
    {
    	getIsChosen = true;
    }

    public boolean getIsRolled()
    {
    	
        return myIsRolled;
    }

    public int getFaceValue()
    {
        return myFaceValue;
    }

    public void setNotRolled()
    {
    	myIsRolled = false;
    }

    public String toString()
    {
        return "Die value" + myFaceValue;
    }

    /*
     * Create a new die and make it the same as the pre-existing die
     */
    public Object clone()
    {
    	Die copy = new Die();
    	copy.myFaceValue = myFaceValue;
    	copy.getIsChosen = getIsChosen;
    	copy.myIsRolled = myIsRolled;
    	
        return copy;
    }

	public boolean getIsChosen() {
		
		return getIsChosen;
	}
}