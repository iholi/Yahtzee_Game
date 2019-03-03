package yahtzee.model;

import java.util.Vector;

public class ComputerPlayer extends Player {
	private int roll = 1;
	private String compName;
	private int compNumberOfWins;
	private ScoreCard compScoreCard;

	private Roller compRoller;

	private int[] compDiceValue; // store dice values
	private int[] compDiceClone;


	private ComputerPlayer compPlayer;
	private Category compCategory;
	private CategoryType compCatType;

	private static boolean reRoll;
	private Vector<Category> compCatUpper;
	private Vector<Category> compCatLower;
	private int compUpperScore;
	private int compLowerScore;
	private int compTotalScore = compUpperScore + compLowerScore;

	private int compScore;
	private int index;
	private boolean upCat; //indicates if the cate is upper or not
	
	private int[] diceValues;
	private int maxValue;
	int zero = 0;

	/*
	 * constructor
	 */
	public ComputerPlayer() {
		// compPlayer = (ComputerPlayer) new Player("Computer");
		compScoreCard = new ScoreCard();
		this.setScoreCard(compScoreCard);
		compRoller = new Roller(5); // Dice are initiated in the Roller class
		compCategory = new Category();
		reRoll = true;
		compCatUpper = new Vector<Category>();
		compCatLower = new Vector<Category>();
		compUpperScore = 0;
		compLowerScore = 0;
		compScore = 0;
		roll=0;
		this.initiateVector();
	}

	/*
	 * initiate vector of category type
	 */
	public void initiateVector() {

		compCatUpper.add(new Category(CategoryType.ONES));
		compCatUpper.add(new Category(CategoryType.TWOS));
		compCatUpper.add(new Category(CategoryType.THREES));
		compCatUpper.add(new Category(CategoryType.FOURS));
		compCatUpper.add(new Category(CategoryType.FIVES));
		compCatUpper.add(new Category(CategoryType.SIXES));

		compCatLower.add(new Category(CategoryType.THREE_OF_KIND)); // index 0
		compCatLower.add(new Category(CategoryType.FOUR_OF_KIND)); // index 1
		compCatLower.add(new Category(CategoryType.FULL_HOUSE));// 2
		compCatLower.add(new Category(CategoryType.SMALL_STRAIGHT));// 3
		compCatLower.add(new Category(CategoryType.LARGE_STRAIGHT));// 4
		compCatLower.add(new Category(CategoryType.YAHTZEE));
		compCatLower.add(new Category(CategoryType.CHANCE));// 6
	}

	public void takeTurn(int[] values) {
		this.checkLowerSection();
		
		
	}

	/*
	 * the computer will roll all its dice then get the dice value then
	 * determine if it can qualify for any categories in the lower section (not
	 * CHANCE, since we're saving CHANCE for the later) if it does, then it'll
	 * take it and stop rolling if it doesn't, then it'll re-roll
	 */

	public void computerRoll() {
		compRoller.roll();
		compDiceValue = compRoller.getDiceValues();
		compDiceClone = compDiceValue;
		roll++;
	}



	/*
	 * compile all the Lower Section check
	 */
	public void checkLowerSection() {
		// compKinds=compCategory.checkLowerSection(compDiceValue);

		// else register the score if the category is unfilled
		compCategory.checkLowerSection(compDiceValue);
		if (compCategory.isYahtzee) {
			this.checkYahtzee(compDiceValue);
		}

		else if (compCategory.isFullHouse) {
			this.checkFullHouse(compDiceValue);
		}

		else if (compCategory.isLargeStraight) {
			this.checkLargeStraight(compDiceValue);
		}

		else if (compCategory.isFourOfKind) {
			this.checkFourOfKinds(compDiceValue);
		}

		else if (compCategory.isThreeOfKind) {
			this.checkThreeOfKinds(compDiceValue);
		}

		else if (compCategory.isSmallStraight) {
			this.checkSmallStraight(compDiceValue);
		} else {
			this.checkUpperSections();
		}
	}

	/*
	 * check Yahtzee if it's not Yahtzee, then it'll fill in Four of Kind, Three
	 * of Kind
	 */
	public void checkYahtzee(int[] nums) {
		if (compCategory.isYahtzee == true) {

			// if the score card is not filled yet, then fill it
			if (compCatLower.get(5).getIsFilled() == false){
					//compScoreCard.fillCategory(CategoryType.YAHTZEE, nums) == false) {
				this.compScoreCard.fillCategory(CategoryType.YAHTZEE, nums);
				compCatType = CategoryType.YAHTZEE;
				compCategory = compCatLower.get(5);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				upCat = true;
				index = 5;
			} else {
				this.checkFourOfKinds(compDiceValue);
			}

		}
	}

	// check Four of Kinds
	public void checkFourOfKinds(int[] nums) {
		if (compCategory.isFourOfKind || compCategory.isYahtzee) {
			if (compCatLower.get(1).getIsFilled() == false){
					//compScoreCard.fillCategory(CategoryType.FOUR_OF_KIND, nums) == true) {
				this.compScoreCard.fillCategory(CategoryType.FOUR_OF_KIND, nums);
				compCatType = CategoryType.FOUR_OF_KIND;
				compCategory = compCatLower.get(1);
				compCategory.fillFourOfKind(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				upCat = false;
				index = 1;
			} else {
				this.checkThreeOfKinds(compDiceValue);
			}
		}
	}

	// check Three of Kinds
	public void checkThreeOfKinds(int[] nums) {
		compCategory.checkLowerSection(nums);
		if (compCategory.isThreeOfKind || compCategory.isFourOfKind || compCategory.isYahtzee) {
			// if the score card is not filled yet, then fill it
			if (compCatLower.get(0).getIsFilled() == false){
					
				this.compScoreCard.fillCategory(CategoryType.THREE_OF_KIND, nums);
				compCatType = CategoryType.THREE_OF_KIND;
				compCategory = compCatLower.get(0);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				
				reRoll = false;
				index = 0;
				upCat = false;
			} else {
				this.checkUpperSections();
			}
		}
	}

	// check Full House, if it's filled, then go to Three of Kind
	public void checkFullHouse(int[] nums) {
		if (compCategory.isFullHouse) {
			if (compCatLower.get(2).getIsFilled() == false){
				this.compScoreCard.fillCategory(CategoryType.FULL_HOUSE, nums);
				compCatType = CategoryType.FULL_HOUSE;
				compCategory = compCatLower.get(2);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				index = 2 ;
				upCat = false;
			} else {
				this.checkThreeOfKinds(compDiceValue);
			}
		}
	}

	/*
	 * check Straights, check Large Straight, if large Straight is filled then
	 * move to small straght
	 */
	public void checkSmallStraight(int[] nums) {
		if (compCategory.isSmallStraight) {
			if (compCatLower.get(3).getIsFilled() == false) {
				this.compScoreCard.fillCategory(compCatLower.elementAt(3).getType(), nums);
				compCatType = CategoryType.SMALL_STRAIGHT;
				compCategory = compCatLower.get(3);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				upCat = false;
				index = 3;
			} else {
				// go to upper categorires
			}
		}
	}

	public void checkLargeStraight(int[] nums) {
		if (compCategory.isLargeStraight) {
			if (compCatLower.get(4).getIsFilled() == false) {
				this.compScoreCard.fillCategory(CategoryType.LARGE_STRAIGHT, nums);
				compCatType = CategoryType.LARGE_STRAIGHT;
				compCategory = compCatLower.get(4);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				upCat = false;
				index = 4;
			} else {
				this.checkSmallStraight(compDiceValue);
			}
		}
	}
	
	/*
	 * determine which category to fill in the Score card the score card class
	 * will determine if the category is filled or not Subsequently, if the
	 * category is filled, the computer will try the next best possible
	 * available category
	 */
	public CategoryType selectCategoryToFill(int[] values) {
		this.checkLowerSection();

		return compCatType;
	}

	/*
	 * check Upper Sections
	 */
	public void checkUpperSections() {

		diceValues = this.checkDiceValues(compDiceValue);
		maxValue = this.getMaxValue(diceValues);

		this.checkUpperCategories(maxValue, compDiceValue);
	}

	/*
	 * select which dices to re-roll
	 */

	public boolean[] reRollUpperCategories(int[] nums, int type) {
		boolean[] diceToRoll = { false, false, false, false, false };
		for (int i = 0; i < 5; i++) {
			if (nums[i] != type) {
				diceToRoll[i] = true;
			}
		}
		return diceToRoll;

	}

	public void rollAgain() {
		if (roll < 4) {
			/*
			 * need to update compDiceValue
			 */
			boolean[] reRoll = this.reRollUpperCategories(compDiceValue, maxValue);

			compRoller.rollSome(reRoll);

			compDiceClone = compRoller.getDiceValueToggled();
			for (int i = 0; i < 5; i++) {
				if (reRoll[i] == true) {
					compDiceValue[i] = compDiceClone[i];
				}
			}

			roll++;

		}
		if (roll > 3) {
			reRoll = false;
		}
	}

	/*
	 * check Upper categories to see if they are available to fill out if all
	 * upper categories are filled, then it'll fill the CHANCE categories type =
	 * number of largest repetitive nums = dice value
	 * 
	 */
	public void checkUpperCategories(int type, int[] nums) {
		if (roll < 4) {
			reRoll = true;
		}
		if (type > 0) {
			if (compCatUpper.get(type - 1).getIsFilled() == false) {
				this.compScoreCard.fillCategory(compCatUpper.elementAt(type-1).getType(), nums);
				compCatType = compCatUpper.get(type - 1).getType();
				compCategory = compCatUpper.get(type - 1);
				

				if (reRoll == true) {
					this.rollAgain();
					this.checkLowerSection();
				}
				else{
					compCategory.fillCategoryValue(nums);
					compScore = compScoreCard.getCategory(compCatType).getValue();
					compUpperScore = compScoreCard.calculateUpperTotal();
					compLowerScore = compScoreCard.calculateLowerTotal();
					compTotalScore = compScoreCard.calculateGrandTotal();
					index = type-1;
					upCat = true;
				}
				
			} else  {
				checkUpperCategories(type-1, nums);
			}

		}

		if (type == 0) {
			if (compCatLower.get(6).getIsFilled() == false){
					//compScoreCard.fillCategory(CategoryType.CHANCE, nums) == true) {
				this.compScoreCard.fillCategory(CategoryType.CHANCE, nums);
				compCatType = CategoryType.CHANCE;
				compCategory = compCatLower.get(6);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				index = 6;
				upCat = false;
			}
			

		}
//		else {
//			if (this.fillCategoryUpper(nums)==false)
//				{
//					this.fillCategoryLower(nums);
//				}
//		}
			
	}
	
	/*
	 * if the dice values can't qualify for any categories, 
	 * then the computer has to pick one category to make it =0
	 */
	public boolean fillCategoryUpper(int[] nums)
	{
		//Upper
		for (int i =0; i<5; i++)
		{
			if (compCatUpper.get(i).getIsFilled()==false)
			{
				this.compScoreCard.fillCategory(compCatUpper.get(i).getType(), nums);
				compCatType = compCatUpper.get(i).getType();
				compCategory = compCatUpper.get(i);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				index = i;
				upCat = true;
				return true;
				
				
			}
		}
		return false;
	}
		
	public boolean fillCategoryLower(int[]nums)
	{
		//Lower
		for (int i = 0; i<6;i++)
		{
			if (compCatLower.get(i).getIsFilled()==false)
			{
				this.compScoreCard.fillCategory(compCatLower.get(i).getType(), nums);
				compCatType = compCatLower.get(i).getType();
				compCategory = compCatLower.get(i);
				compCategory.fillCategoryValue(nums);
				compScore = compScoreCard.getCategory(compCatType).getValue();
				compUpperScore = compScoreCard.calculateUpperTotal();
				compLowerScore = compScoreCard.calculateLowerTotal();
				compTotalScore = compScoreCard.calculateGrandTotal();
				reRoll = false;
				index = i;
				upCat = false;
				return true;
				
			}
		}
		return false;
		
	}

	/*
	 * check Upper Categories, which ones the comp can get highest score
	 * therefore, the comp will figure out how many times one number repeats,
	 * then look for largest possible. If it's filled, then move on the next one
	 */
	public int checkIncrement(int[] nums, int value) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == value) {
				count++;
			}
		}
		return count;
	}

	/*
	 * a method that returns an array of 6 elements correspond to how many time
	 * that value appears in the array
	 */
	public int[] checkDiceValues(int[] nums) {
		int[] values = { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 6; i++) {
			values[i] = checkIncrement(compDiceValue, i + 1);
		}
		return values;
	}

	public int getMaxValue(int[] nums) {
		int max = -1;
		int maxValue = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= max) {
				max = nums[i];
				maxValue = i + 1;
			}
		}
		return maxValue;
	}

	public int getDice() {
		return compDiceValue.length;
	}

	public int[] getDiceValues() {
		return compDiceValue;
	}

	public CategoryType getCompType() {
		return compCatType;
	}

	public String toString() {
		return "Computer Player";
	}

	public void setDice(int[] nums) {
		compDiceValue = nums;
	}

	public int getCompUpperScore() {
		
		return compUpperScore;
	}

	public int getCompLowerScore() {
		
		return compLowerScore;
	}

	public int getCompTotalScore() {
		
		return compTotalScore;
	}

	public boolean getReRoll() {
		return reRoll;
	}

	public int getCompScore() {
		return compScore;
	}
	public boolean getUpCat()
	{
		return upCat;
	}
	public int getIndexCat()
	{
		return index;
	}
	
}