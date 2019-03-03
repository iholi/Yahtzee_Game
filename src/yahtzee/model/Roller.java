package yahtzee.model;

import java.util.Vector;

public class Roller
{
    private Vector<Die> myDice = new Vector<Die>(5);
    private int myNumberOfDice;
    private int[] myDiceValue = {-1,-1,-1,-1,-1};

    public Roller(int numDice)
    {
    	int i = 0;
    	while (i<numDice)
    	{
    		Die newDie = new Die();
    		myDice.add(newDie);
    		i++;
    	}
    	
    	myNumberOfDice = numDice;
    }
 

    public Vector<Die> getDice()
    {
        return myDice;
    }

    public int[] getDiceValues()
    {
    	int oneVal;
    	for (int i=0;i<myDice.size();i++)
    	{
     		oneVal = (myDice.get(i).getFaceValue());
    		myDiceValue[i]=oneVal;
    	}
        return myDiceValue;
    }
    
    public int[] getDiceValueToggled()
    {
    	int oneVal;
    	for (int i=0;i<myDice.size();i++)
    	{
    		if (myDice.get(i).getIsChosen()==true)
    		{
    			oneVal = myDice.get(i).getFaceValue();
    			myDiceValue[i]= oneVal;
    		}
    	}
    	return myDiceValue;
    }

    /*
     * roll all the dice
     */
    public void roll()
    {
    	for (int i=0;i<myDice.size();i++)
    	{
    		myDice.get(i).roll();
    	}
    	
    }

    public void rollSome(boolean[] toRoll)
    {
    	for (int i=0;i<toRoll.length;i++)
    	{
    		if (toRoll[i]==true)
    		{
    			myDice.get(i).toggleIsChosen();
    			myDice.get(i).roll();
    			
    		}
    	}
    	
    }

    public int getNumberOfDice()
    {
        return myNumberOfDice;
    }

    public String toString()
    {
        return null;
    
	}
}