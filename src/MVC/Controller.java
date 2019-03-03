package MVC;

import javax.swing.JOptionPane;
import yahtzee.model.CategoryType;
import yahtzee.model.ComputerPlayer;
import yahtzee.model.YahtzeeEngine;
import MVC.View;

public class Controller {

	private View myView;
	private YahtzeeEngine myYahtzeeEngine;
	private ComputerPlayer myComputer;
	private int myUpperTotalPlayer,myBonusTotalPlayer,myLowerTotalPlayer,myGrandTotalPlayer,myUpperTotalComputer,
	myBonusTotalComputer, myLowerTotalComputer,myGrandTotalComputer;
	
	public Controller()
	{
		myYahtzeeEngine = new YahtzeeEngine(0);
		myView = new View(this);
		myComputer = new ComputerPlayer();
	}
	/*
	 * triggered when "Start Game" button is pushed
	 */
	public void startGame()
	{
		myYahtzeeEngine.startGame();
		this.UpdateTotalScore();
	}
	
	/*
	 * triggered when "Roll" button is pushed
	 */
	public void roll()
	{
		myView.startGame();
		myView.imgDice();
		myView.dices();
		myView.comments();
		if( myYahtzeeEngine.getNumberRollsUsed()!= 3 || myYahtzeeEngine.getNumberRollsUsed()< 3)
		{
			myYahtzeeEngine.getRoller().roll();
			myYahtzeeEngine.incrementRollsUsed();
			int[] values = myYahtzeeEngine.getRoller().getDiceValues();
			myView.setDice(values);
			myView.setDefaultSelected();
			
			for (int i = 0 ; i < 5; i ++)
			{
				if (myYahtzeeEngine.getPlayerUp().getScoreCard().fillingUpper(i, values) == true)
				{
					myView.setCategoriesBackground(i);
				}
				else
				{
					myView.setUpDefaultBackground(i);
				}
			}
			
			for ( int i = 0; i < 6; i ++)
			{
				if (myYahtzeeEngine.getPlayerUp().getScoreCard().fillingLower(i, values) == true)
				{
					myView.setCategoriesBackground1(i);
				}
				else
				{
					
					myView.setLowDefaultBackground(i);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You Have Used All Your Rolls");
		}
		
	}
	/*
	 * triggered when "Roll Selected" button is pushed
	 */
	public void rollSelected()
	{
		myView.dices();
		if( myYahtzeeEngine.getNumberRollsUsed() < 3 )
		{
			boolean[] toRoll = myView.getSelectedDice();
			myYahtzeeEngine.getRoller().rollSome(toRoll);
			myYahtzeeEngine.incrementRollsUsed();
			int[] values = myYahtzeeEngine.getRoller().getDiceValues();
			myView.setDice(values);
			myView.setDefaultSelected();
			
			for (int i = 0 ; i < 5 ; i ++)
			{
				if (myYahtzeeEngine.getPlayerUp().getScoreCard().fillingUpper(i, values) == true)
				{
					myView.setCategoriesBackground(i);
				}
				else
				{
					myView.setUpDefaultBackground(i);
				}
			}
			
			for ( int i = 0; i < 6; i ++)
			{
				if (myYahtzeeEngine.getPlayerUp().getScoreCard().fillingLower(i, values) == true)
				{
					myView.setCategoriesBackground1(i);
				}
				else
				{
					
					myView.setLowDefaultBackground(i);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You Have Used All Your Rolls");
		}		
	}
	
	/**
	 * goes to the next turn and lets the computer player take its turn 
	 */
	public void turn()
	{
		myView.dices();
		myView.comment2();
		myView.turnsAndWinner();
		myComputer.computerRoll();
		int[] values = myComputer.getDiceValues();
		myComputer.takeTurn(values);
		myView.setDice(values);
		int value = myComputer.getCompScore(); //comp score in that round
		int index = myComputer.getIndexCat();
		
//		myYahtzeeEngine.resetNumberRollsUsed();
		
		for (int i = 0 ; i < 5 ; i ++)
		{
			if (myComputer.getScoreCard().fillingUpper(i, values) == true)
			{
				myView.setCategoriesBackground(i);
			}
			else
			{
				myView.setUpDefaultBackground(i);
			}
		}
		
		for ( int i = 0; i < 6; i ++)
		{
			if (myComputer.getScoreCard().fillingLower(i, values) == true)
			{
				myView.setCategoriesBackground1(i);
			}
			else
			{
				
				myView.setLowDefaultBackground(i);
			}
		}
		
		if( myComputer.getUpCat() == true)
		{
			myView.setComputerUpperC(index, value);
		}
		else
		{
			myView.setComputerLowerC(index, value);
		}
		
		myYahtzeeEngine.resetNumberRollsUsed();
		myYahtzeeEngine.getRoller().roll();
		this.UpdateTotalScore();
		myView.turnsAndWinner();
	}
	
	public void UpdateTotalScore()
	{
		myUpperTotalPlayer = myYahtzeeEngine.getPlayerUp().getScoreCard().calculateUpperScore();
		myBonusTotalPlayer = myYahtzeeEngine.getPlayerUp().getScoreCard().calculateUpperTotal();
		myLowerTotalPlayer = myYahtzeeEngine.getPlayerUp().getScoreCard().calculateLowerTotal();
		myGrandTotalPlayer = myYahtzeeEngine.getPlayerUp().getScoreCard().calculateGrandTotal();
		myUpperTotalComputer = myComputer.getCompUpperScore();
		myBonusTotalComputer = myComputer.getScoreCard().calculateUpperTotal();
		myLowerTotalComputer = myComputer.getCompLowerScore();
		myGrandTotalComputer = myComputer.getCompTotalScore();
		
		if( myUpperTotalPlayer ==63 || myUpperTotalPlayer>63)
		{
			myView.setUpperScoresPlayer(7, 35);
		}
		else if (myUpperTotalPlayer< 63 || myUpperTotalPlayer !=63)
		{
			myView.setUpperScoresPlayer(7, 0);
		}
		if( myUpperTotalComputer ==63 || myUpperTotalComputer>63)
		{
			myView.setUpperScoresComputer(7, 35);
		}
		else if (myUpperTotalComputer <63 || myUpperTotalComputer!= 63)
		{
			myView.setUpperScoresComputer(7, 0);
		}
		myView.setUpperScoresPlayer(6, myUpperTotalPlayer);
		myView.setScoreLowPlayer(7, myLowerTotalPlayer);
		myView.setUpperScoresPlayer(7, myBonusTotalPlayer);
		myView.setScoreLowPlayer(8, myGrandTotalPlayer);
		
		myView.setUpperScoresComputer(6, myUpperTotalComputer);
		myView.setScoreLowComputer(7, myLowerTotalComputer);
		myView.setUpperScoresComputer(7, myBonusTotalComputer);
		myView.setScoreLowComputer(8, myGrandTotalComputer);
	}
	
/*
 * When dice are selected to reroll
 */
	public void oneDieSelected()
	{
		myView.setSelected1(0 , myYahtzeeEngine.getRoller().getDiceValues()[0]);
	}
	
	public void twoDieSelected()
	{
		myView.setSelected2(1, myYahtzeeEngine.getRoller().getDiceValues()[1]);
	}
	
	public void threeDieSelected()
	{
		myView.setSelected3(2, myYahtzeeEngine.getRoller().getDiceValues()[2]);
	}
	
	public void fourDieSelected()
	{
		myView.setSelected4(3, myYahtzeeEngine.getRoller().getDiceValues()[3]);
	}
	
	public void fiveDieSelected()
	{
		myView.setSelected5(4, myYahtzeeEngine.getRoller().getDiceValues()[4]);
	}
	
	/*
	 * triggered when player chooses their categories
	 * and let the computer play
	 */
	public void fillOnes()
	{	myView.one();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.ONES, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerUpperC(0, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.ONES).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);
	}
	
	public void fillTwos()
	{	myView.two();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.TWOS, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerUpperC(1, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.TWOS).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillThrees()
	{	myView.three();
		myYahtzeeEngine.
		getPlayerUp().getScoreCard().fillCategory(CategoryType.THREES, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerUpperC(2, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.THREES).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillFours()
	{	myView.four();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.FOURS, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerUpperC(3, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.FOURS).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillFives()
	{	myView.five();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.FIVES, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerUpperC(4, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.FIVES).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillSixes()
	{	myView.six();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.SIXES, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerUpperC(5, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.SIXES).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillThreeOfKind()
	{
		myView.threeKind();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.THREE_OF_KIND, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(0, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.THREE_OF_KIND).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillFourOfKind()
	{	myView.fourKind();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.FOUR_OF_KIND, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(1, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.FOUR_OF_KIND).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	public void fillFullHouse()
	{
		myView.fullHouse();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.FULL_HOUSE, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(2, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.FULL_HOUSE).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillSmallStraight()
	{
		myView.small();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.SMALL_STRAIGHT, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(3, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.SMALL_STRAIGHT).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);

	}
	
	public void fillLargeStraight()
	{
		myView.large();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.LARGE_STRAIGHT, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(4,  myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.LARGE_STRAIGHT).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillYahtzee()
	{
		myView.yahtzee();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.YAHTZEE, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(5, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.YAHTZEE).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);


	}
	
	public void fillChance()
	{
		myView.chance();
		myYahtzeeEngine.getPlayerUp().getScoreCard().fillCategory(CategoryType.CHANCE, myYahtzeeEngine.getRoller().getDiceValues());
		myView.setPlayerLowerC(6, myYahtzeeEngine.getPlayerUp().getScoreCard().getCategory(CategoryType.CHANCE).getValue());
		this.UpdateTotalScore();
		this.turn();

		myView.setUpDefaultBackground(0);
		myView.setUpDefaultBackground(1);
		myView.setUpDefaultBackground(2);
		myView.setUpDefaultBackground(3);
		myView.setUpDefaultBackground(4);
		myView.setUpDefaultBackground(5);
		myView.setLowDefaultBackground(0);
		myView.setLowDefaultBackground(1);
		myView.setLowDefaultBackground(2);
		myView.setLowDefaultBackground(3);
		myView.setLowDefaultBackground(4);
		myView.setLowDefaultBackground(5);
		myView.setLowDefaultBackground(6);

	}

	public int getNumberOfRolls()
	{
		return myYahtzeeEngine.getNumberRollsUsed();
	}
}
