package yahtzee.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import yahtzee.model.Category;
import yahtzee.model.CategoryType;
import yahtzee.model.ComputerPlayer;
import yahtzee.model.Roller;
import yahtzee.model.ScoreCard;

/**
 * test the computer player to make sure all the methods are running
 * @author Tram Nguyen
 */
public class TestComputerPlayer {

	@Before
	public void initialize()
	{
		int [] good = {3, 2, 3, 2, 3};
		int [] bad = {3, 2, 3, 2, 4};
		
	}
	
	/**
	 * Test check lower Section
	 */
	@Test
	public void testCheckLowerSection()
	{
		int [] good = {3, 2, 3, 2, 3};
		int[] good2 = {2, 2, 2, 1, 1};
		String compType;
		
        ComputerPlayer compPlayer = new ComputerPlayer();
        compPlayer.setDice(good);
        compPlayer.checkLowerSection();
        
        compType = compPlayer.getCompType().getName();
        assertEquals(compType, CategoryType.FULL_HOUSE.getName());
        int compScore = compPlayer.getCompScore();
        assertEquals(compPlayer.getReRoll(), false);
        
        compPlayer.setDice(good2);
        compPlayer.checkLowerSection();
        int lower = compPlayer.getScoreCard().calculateLowerTotal();
        compType = compPlayer.getCompType().getName();
        assertEquals(compType, CategoryType.THREE_OF_KIND.getName());
        compScore = compPlayer.getCompScore();
	}
	/**
	 * Test if upper sections
	 */
	@Test
	public void testCheckUpperSection()
	{

		int [] good = {3, 1, 3, 2, 3};
		int[] good2 = {2, 1, 3, 1, 1};
		String compType;
		String compType1;
		int score;
		
        ComputerPlayer compPlayer = new ComputerPlayer();
        compPlayer.setDice(good);
        compPlayer.checkLowerSection();
        
        compType = compPlayer.getCompType().getName();
        assertEquals(compType, CategoryType.THREE_OF_KIND.getName());
        assertEquals(compPlayer.getReRoll(), false);
        
        compPlayer.setDice(good2);
        compPlayer.checkLowerSection();
        compType1 = compPlayer.getCompType().getName();
//        score = compPlayer.getScoreCard().getGrandTotal();
        
        assertEquals(compType1, CategoryType.ONES.getName());
       
        
	}
	/**
	 * Test check Three of Kinds
	 */
	@Test
	 public void testCheckThreeOfKind()
    {
		int [] good = {3, 2, 3, 2, 3};
		String compType;
		
        ComputerPlayer compPlayer = new ComputerPlayer();
        compPlayer.computerRoll();
        int diceNumber = compPlayer.getDice();
        int[] diceValues = compPlayer.getDiceValues();
        
        compPlayer.checkThreeOfKinds(good);
        
        compType = compPlayer.getCompType().getName();
        assertEquals(compType, CategoryType.THREE_OF_KIND.getName());
        assertEquals(compPlayer.getReRoll(), false);
     
        
    }
	
	/**
	 * test dice arrays
	 */ 
	@Test
		public void testCheckDiceArrays()
	    {
	        ComputerPlayer compPlayer = new ComputerPlayer();
	        compPlayer.computerRoll();
	        int diceNumber = compPlayer.getDice();
	        assertEquals(diceNumber, 5);
	    }
}
