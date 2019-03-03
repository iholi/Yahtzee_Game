package yahtzee.model;

import java.util.Vector;

public class ScoreCard
{
    public static final int NUMBER_BOTTOM_CATEGORIES = 7;
    public static final int NUMBER_TOP_CATEGORIES = 6;
    public static final int NUMBER_CATEGORIES_TOTAL = 13;
    public static final int UPPER_BONUS = 63;

    private Vector<Category> myUpperCategories = new Vector<Category>();
    private Vector<Category> myLowerCategories = new Vector<Category>();

    private int zero=0;
    private int myUpperScore;
    private int myUpperTotal;
    private int myLowerTotal;
    private int myGrandTotal;
    private int filled;
    Category categoriess, categoriess1;

    private int myNumberCategoriesFilled;
    
    public boolean fillingUpper(int category, int[] nums)
    {
    	if(myUpperCategories.elementAt(category).checkFilledCategories(nums) == true)
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    }
    
    public boolean fillingLower( int category, int[] nums)
    {
    	if(myLowerCategories.elementAt(category).checkFilledCategories(nums) == true)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    public void categories() 
    {
    	myUpperCategories.add(new Category(CategoryType.ONES));
    	myUpperCategories.add(new Category(CategoryType.TWOS));
    	myUpperCategories.add(new Category(CategoryType.THREES));
    	myUpperCategories.add(new Category(CategoryType.FOURS));
    	myUpperCategories.add(new Category(CategoryType.FIVES));
    	myUpperCategories.add(new Category(CategoryType.SIXES));
    	
    	myLowerCategories.add(new Category(CategoryType.THREE_OF_KIND));
    	myLowerCategories.add(new Category(CategoryType.FOUR_OF_KIND));
    	myLowerCategories.add(new Category(CategoryType.FULL_HOUSE));
    	myLowerCategories.add(new Category(CategoryType.SMALL_STRAIGHT));
    	myLowerCategories.add(new Category(CategoryType.LARGE_STRAIGHT));
    	myLowerCategories.add(new Category(CategoryType.YAHTZEE));
    	myLowerCategories.add(new Category(CategoryType.CHANCE));
    }
    
    public void setCategories1()
    {
    	myUpperCategories.set(0,new Category(CategoryType.ONES));
    	myUpperCategories.set(1,new Category(CategoryType.TWOS));
    	myUpperCategories.set(2,new Category(CategoryType.THREES));
    	myUpperCategories.set(3,new Category(CategoryType.FOURS));
    	myUpperCategories.set(4,new Category(CategoryType.FIVES));
    	myUpperCategories.set(5,new Category(CategoryType.SIXES));
    	
    	myLowerCategories.set(0,new Category(CategoryType.THREE_OF_KIND));
    	myLowerCategories.set(1,new Category(CategoryType.FOUR_OF_KIND));
    	myLowerCategories.set(2,new Category(CategoryType.FULL_HOUSE));
    	myLowerCategories.set(3,new Category(CategoryType.SMALL_STRAIGHT));
    	myLowerCategories.set(4,new Category(CategoryType.LARGE_STRAIGHT));
    	myLowerCategories.set(5,new Category(CategoryType.YAHTZEE));
    	myLowerCategories.set(6,new Category(CategoryType.CHANCE));
    }
    
    /**
     * Constructor of the class.
     */
    public ScoreCard()
    {   this.categories();
    	myUpperScore = zero;
    	myUpperTotal = zero;
    	myLowerTotal = zero;
    	myGrandTotal = zero;
    	myNumberCategoriesFilled = zero;
    }
    
    /**
     * This method reset the scoreCard values to 0.
     */
    public void resetScoreCard()
    {
    	this.setCategories1();
    	myUpperScore = zero;
    	myUpperTotal = zero;
    	myLowerTotal = zero;
    	myGrandTotal = zero;
    	myNumberCategoriesFilled = zero;
    }
    
    /**
     * This method checks if the LowerCategories and UpperCategories are being filled or not. 
     * @param type CategoryType.type
     * @param values are the array of dice value from Roller class
     * @return true if it can fill in, false if it can't filled in/ false if not filled
     */
    public boolean fillCategory(CategoryType type, int[] values)
    {
    	for(int i = 0; i < myLowerCategories.size(); i++)
    	{
    		categoriess = myLowerCategories.elementAt(i);
    		if(categoriess.getType() == type)
    		{
    			return categoriess.fillCategoryValue(values);

    		}
    	}
    	
    	for(int i = 0; i < myUpperCategories.size(); i++)
    	{
    		categoriess = myUpperCategories.elementAt(i);
    		if(categoriess.getType() == type)
    		{
    			return categoriess.fillCategoryValue(values);
    			
    		}	
    	}	
    	return false;
    }

    /**
     * @param type
     * @return
     */
    public Category getCategory(CategoryType type)
    {
    	categoriess1 = new Category(type);
    	
    	for(int i = 0; i < myLowerCategories.size(); i++)
    	{
    		categoriess1 = myLowerCategories.elementAt(i);
    		if(categoriess1.getType() == type)
    		{
    		return categoriess1;
    		}
    	}
    	
    	for(int i = 0; i < myUpperCategories.size(); i++)
    	{
    		categoriess1 = myUpperCategories.elementAt(i);
    		if(categoriess1.getType() == type)
    		{
    		return categoriess1;
    		}
    	}
       return categoriess1;
    }

    public int getNumberCategoriesFilled()
    {
    	myNumberCategoriesFilled = zero;
    	
    	for( int i = 0; i < myUpperCategories.size(); i++)
    	{
    		categoriess = myUpperCategories.elementAt(i);
    		if(categoriess.getIsFilled() == false)
    		{
    			myNumberCategoriesFilled = myNumberCategoriesFilled;
    		}
    		else if(categoriess.getIsFilled() == true)
    		{
    			myNumberCategoriesFilled = myNumberCategoriesFilled+1;
    		}
    	}
    	
    	for (int i = 0; i < myLowerCategories.size(); i++)
    	{
    		categoriess = myLowerCategories.elementAt(i);
    		if(categoriess.getIsFilled() == false)
    		{
    			myNumberCategoriesFilled = myNumberCategoriesFilled;
    		}
    		else if(categoriess.getIsFilled() == true)
    		{
    			myNumberCategoriesFilled = myNumberCategoriesFilled + 1;
    		}
    	}
    	
        return myNumberCategoriesFilled;
    }

    /**
     * This method calculate all the upperCategories values. 
     * @return myUpperScore
     */
    public int calculateUpperScore()
    {
    	myUpperScore = zero;
    	for(int i = 0; i < myUpperCategories.size(); i++)
    	{
    		myUpperScore += myUpperCategories.get(i).getValue();
    		
    	}
        return myUpperScore;
    }

    /**
     * This method calculate the upperTotal, the upperTotal  should include upperScore and
     * the bonus if the proper number of points have been accumulated.
     * @return myUperTotal
     */
    public int calculateUpperTotal()
    {
    	myUpperTotal = zero;
    	
    	if(this.getUpperScore() < UPPER_BONUS || this.getUpperScore() != UPPER_BONUS)
    	{
    		myUpperTotal = myUpperScore;
    	}
    	if(this.getUpperScore() == UPPER_BONUS||this.getUpperScore() > UPPER_BONUS)
    	{
    		myUpperTotal = myUpperScore + 35;
    	}
        return myUpperTotal;
    }

    /**
     * This method calculate all the lowerCategories values. 
     * @return myLowerTotal
     */
    public int calculateLowerTotal()
    {
    	myLowerTotal = zero;
    	for(int i = 0; i < myLowerCategories.size(); i++)
    	{
    		myLowerTotal += myLowerCategories.elementAt(i).getValue();
    	}
        return myLowerTotal;
    }

    /**
     * This method calculate the GrandTotal by adding the LowerTotal and UpperTotal.
     * @return myGrandTotal
     */
    public int calculateGrandTotal()
    {
    	myGrandTotal = getUpperTotal() + getLowerTotal();
        return myGrandTotal;
    }

    /**
     * This method get the UpperScore
     * @return myUpperScore
     */
    public int getUpperScore()
    {
    	this.calculateUpperScore();
        return myUpperScore;
    }

    /**
     * This method get the UpperTotal
     * @return myUpperTotal
     */
    public int getUpperTotal()
    {
    	this.calculateUpperTotal();
        return myUpperTotal;
    }

    /**
     * This method get the lowerTotal
     * @return mylowerTotal
     */
    public int getLowerTotal()
    {
    	this.calculateLowerTotal();
        return myLowerTotal;
    }

    /**
     * This method get the GrandTotal
     * @return myGrandTotal
     */
    public int getGrandTotal()
    {
    	this.calculateGrandTotal();
        return myGrandTotal;
    }

    public String toString()
    {
    	return null;
    }

    /**
     * Clone myNumberCategoriesFilled, myUpperScore, myUpperTotal,myLowerTotal, myGrandTotal, 
     * myUpperCategories, and myLowerCategoried.
     */
	public ScoreCard clone()
    {
    	ScoreCard copy = new ScoreCard();
    	copy.myNumberCategoriesFilled = myNumberCategoriesFilled;
    	copy.myUpperScore = myUpperScore;
    	copy.myUpperTotal = myUpperTotal;
    	copy.myLowerTotal = myLowerTotal;
    	copy.myGrandTotal = myGrandTotal;    
    	copy.myUpperCategories = (Vector<Category>) myUpperCategories.clone();
    	copy.myLowerCategories = (Vector<Category>) myLowerCategories.clone();
    	return copy;
    }
}
