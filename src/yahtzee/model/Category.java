package yahtzee.model;

import java.util.Arrays;

public class Category {
	public static final int NO_VALUE = 0;

	private CategoryType myType;
	private String myName;
	private int myValue;
	private boolean myIsFilled;

	public static boolean isThreeOfKind;
	public static boolean isFourOfKind;
	public static boolean isFullHouse;
	public static boolean isSmallStraight;
	public static boolean isLargeStraight;
	public static boolean isYahtzee;
	
	private int[] myKinds = { 0, 0 };

	public Category(CategoryType type) {
		myType = type;
		myName = type.getName();
		myValue = NO_VALUE;
		myIsFilled = false;
	}

	public boolean checkFilledCategories (int[]nums)
	{
		this.checkLowerSection(nums);
		if(myName==CategoryType.ONES.getName())
		{
			if(this.getIsFilled() == false)
			{
				for (int i=0; i< nums.length; i++)
				{
					if(nums[i]==1)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
		return false;
		}
		else if (myName == CategoryType.TWOS.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(nums[i] ==2)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.THREES.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(nums[i] ==3)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.FOURS.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(nums[i] ==4)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.FIVES.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(nums[i] ==5)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.SIXES.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(nums[i] ==6)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.THREE_OF_KIND.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(isThreeOfKind==true)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.FOUR_OF_KIND.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(isFourOfKind==true)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.FULL_HOUSE.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(isFullHouse==true)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.SMALL_STRAIGHT.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(isSmallStraight==true)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.LARGE_STRAIGHT.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(isLargeStraight==true)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.YAHTZEE.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				for(int i=0; i<nums.length; i++)
				{
					if(isYahtzee==true)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
			return false;
		}
		else if (myName == CategoryType.CHANCE.getName())
		{
			if (this.getIsFilled() ==false)
			{ 
				return true;
			}
			else
			{
				return false;
			}
		}
		return myIsFilled;
	}

	public Category()
	{
		
	}

	public boolean fillCategoryValue(int[] nums) {

		if (myIsFilled == true)
		{
			return false;
		}
		if (myIsFilled == false) 
		{

			// ONES
			if (myName == CategoryType.ONES.getName()) {
				this.fillUpperCategories(nums,1);

			}
			// TWOS
			if (myName == CategoryType.TWOS.getName()) {
				this.fillUpperCategories(nums,2);

			}
			// THREES
			if (myName == CategoryType.THREES.getName()) {
				this.fillUpperCategories(nums, 3);

			}
			// FOURS
			if (myName == CategoryType.FOURS.getName()) {
				this.fillUpperCategories(nums, 4);
				

			}
			// FIVES
			if (myName == CategoryType.FIVES.getName()) {
				this.fillUpperCategories(nums, 5);

			}

			// SIX
			if (myName == CategoryType.SIXES.getName()) {
				this.fillUpperCategories(nums, 6);

			}

			// check if it can be allowed first in a separate method, keep track
			// of the max count
			// THREE_OF_KIND: has to have 3 of a kind

			if (myName == CategoryType.THREE_OF_KIND.getName()) {
				this.fillThreeOfKind(nums);
			}

			/*
			 * Four of kind
			 */
			if (myName == CategoryType.FOUR_OF_KIND.getName()) {
				this.fillFourOfKind(nums);
			}

			/*
			 * FULL HOUSE
			 */
			if (myName == CategoryType.FULL_HOUSE.getName()) {
				this.fillFullHouse(nums);
			}

			// Small Straight
			if (myName == CategoryType.SMALL_STRAIGHT.getName()) {
				this.fillSmallStraight(nums);
			}

			// Large Straight
			if (myName == CategoryType.LARGE_STRAIGHT.getName()) {
				this.fillLargeStraight(nums);
			}

			// Yahtzee
			if (myName == CategoryType.YAHTZEE.getName()) {
				this.fillYahtzee(nums);
			}

			// CHANCE
			if (myName == CategoryType.CHANCE.getName()) {
				this.fillChance(nums);

			}

		}

//		else {
//			myIsFilled = false;
//		}
		return myIsFilled;
		
	}

	/*
	 * fill in Category One, Two, Three, Four, Five, Sixes
	 * @para: int[]nums array of dices' values
	 * int cat: Category type
	 */
	public void fillUpperCategories(int[]nums, int cat)
	{
		if (myIsFilled==false)
		{
			for (int i = 0; i < 5; i++) {
				if (nums[i] == cat) {
					myValue = myValue + nums[i];
				}
				/*
				 * set myValue = 0 if out of range
				 */
				if (nums[i] < 1 || nums[i] > 6) {
					myValue = 0;
					break;
				}
			} 
			myIsFilled = true;
		}
	}
	
	/*
	 * fill Lower categories
	 */
	public void fillThreeOfKind(int[]nums)
	{
		if (myIsFilled == false)
		{
			this.checkLowerSection(nums);
			
			if (isThreeOfKind == true) {
				for (int i = 0; i < 5; i++) {
					myValue = myValue + nums[i];
	
					/*
					 * set myValue = 0 if out of range
					 */
					if (nums[i] < 1 || nums[i] > 6) {
						myValue = 0;
						break;
					}
				}
				myIsFilled = true;
			} else {
				myValue = 0;
			}
		}
	}
	
	public void fillFourOfKind(int[]nums)
	{
		if (myIsFilled==false)
		{
			this.checkLowerSection(nums);
			if (isFourOfKind == true) {
				for (int i = 0; i < 5; i++) {
					myValue = myValue + nums[i];
	
					/*
					 * set myValue = 0 if out of range
					 */
					if (nums[i] < 1 || nums[i] > 6) {
						myValue = 0;
						break;
					}
				}
				myIsFilled = true;
			} else {
				myValue = 0;
			}
		}
	}
	
	public void fillFullHouse(int[]nums)
	{
		if (myIsFilled == false)
		{

			this.checkLowerSection(nums);
			if (isFullHouse == true) 
			{
				myValue = 25;
				
			}
			else {
				myValue = 0;
			}
			this.checkValues(nums);
			if (myValue !=0)
			{
				myIsFilled=true;
			}
		}
	}
	
	public void fillSmallStraight(int[]nums)
	{
		if (myIsFilled == false)
		{
			this.checkLowerSection(nums);
			if (isSmallStraight == true)
			{
				myValue = 30;
				
			}
			else {
				myValue = 0;
			}
			this.checkValues(nums);
			
		}
	}
	
	public void fillLargeStraight(int[]nums)
	{
		if (myIsFilled==false)
		{
			this.checkLowerSection(nums);
			if (isLargeStraight == true) 
			{
				myValue = 40;
				
			
			} else 
			{
				myValue = 0;
			}
			this.checkValues(nums);
		}
	}
	
	public void fillYahtzee(int[]nums)
	{
		if(myIsFilled==false)
		{
			this.checkLowerSection(nums);
			if (isYahtzee == true) 
			{
				myValue = 50;
			}
				
			else {
				myValue = 0;
			}
			this.checkValues(nums);
		}
	}
	
	/*
	 * check value of out range
	 */
	public void checkValues(int[]nums)
	{
		for (int i = 0; i < 5; i++) {
			/*
			 * set myValue = 0 if out of range
			 */
			if (nums[i] < 1 || nums[i] > 6) {
				myValue = 0;
				break;
			}
		}
		if (myValue != 0)
		{
			myIsFilled=true;
		}
	}
	
	public void fillChance(int[]nums)
	{
		if (myIsFilled==false)
		{
			for (int i = 0; i < 5; i++) {
				myValue = myValue + nums[i];
				/*
				 * set myValue = 0 if out of range
				 */
				if (nums[i] < 1 || nums[i] > 6) {
					myValue = 0;
					break;
				}
			}
			if (myValue!=0)
			{
				myIsFilled=true;
			}
		}
	}
	
	/*
	 * helper class to test if the array passes the category
	 */
	public int[] checkLowerSection(int[] dices) {
		myKinds[0]=0;
		myKinds[1]=0;
		isThreeOfKind = false;
		isFourOfKind = false;
		isYahtzee = false;
		isFullHouse = false;
		isSmallStraight = false;
		isLargeStraight = false;

		/*
		 * create an array of 2 elements 1 to keep track of similar dices 2 to
		 * keep track if the number is incremented
		 */
		
		int maxConsecutive = 0;
		int maxIncrement = 0;

		Arrays.sort(dices);
		if (dices[0]==dices[1] && dices[2]==dices[3] && dices[3]==dices[4])
		{
			isFullHouse = true;
		}
		else if (dices[0]==dices[1] && dices[1]==dices[2] && dices[3]==dices[4])
		{
			isFullHouse = true;
		}
		else{
			isFullHouse = false;
		}
		
		for (int i = 0; i < 5; i++) {
			if (i == 0 || dices[i] == dices[i - 1]) {
				maxConsecutive++;
				if (maxConsecutive > myKinds[0]) {
					myKinds[0] = maxConsecutive;
				}
			} else if (dices[i] != dices[i - 1]) {
				
				maxConsecutive = 1;
			}

			if (i == 0 || dices[i] == dices[i - 1] + 1) {
				maxIncrement++;
				if (maxIncrement > myKinds[1]) {
					myKinds[1] = maxIncrement;
				}

			} else if (dices[i] != dices[i - 1]) {
				maxIncrement = 1;
			}
		}
		
		
		/*
		 * classify
		 */
		if (myKinds[0] > 4) {
			isThreeOfKind = true;
			isFourOfKind = true;
			isYahtzee = true;
			isFullHouse = true;
		} else if (myKinds[0] > 3) {
			isThreeOfKind = true;
			isFourOfKind = true;
		} else if (myKinds[0] == 3) {
			
			isThreeOfKind = true;
		}

		if (myKinds[1] == 5) {
			isLargeStraight = true;
			isSmallStraight = true;

		} else if (myKinds[1] == 4) {
			isSmallStraight = true;
		}
		
		return myKinds;
	}

	public boolean getIsFilled() {
		return myIsFilled;
	}

	public int getValue() {

		return myValue;
	}

	public String getName() {
		return myName;
	}

	public CategoryType getType() {
		return myType;
	}

	public String toString() {
		return myName;
	}

	public Object clone() {
		Category copy = new Category(myType);
		copy.myName = myName;
		copy.myIsFilled = myIsFilled;
		copy.myValue = myValue;

		return copy;
	}
}