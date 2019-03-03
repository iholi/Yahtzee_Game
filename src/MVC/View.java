package MVC;
import MVC.ButtonListener;

import MVC.Controller;
import yahtzee.model.ComputerPlayer;
import yahtzee.model.YahtzeeEngine;
import java.awt.*;
import java.lang.reflect.Method;
import javax.swing.*;

public class View extends JFrame{
	
	//Properties
	private YahtzeeEngine myYahtzeeEngine;
	private ComputerPlayer myComputer1;
	
	JFrame frame = new JFrame();
	JPanel panel = (JPanel) frame.getContentPane();
	private boolean dices1=false, dices2=false,dices3=false, dices4=false, dices5=false,dices6=false;
	private boolean[] selectingDie = {dices1,dices2,dices3,dices4,dices5,dices6}; 
	private JLabel my3KindP, my4KindP, myFullHouseP, 
	mySmallSP, myLargeSP , myYahtzeeP, myChanceP, myTotalLowerP, myGrandTotalP , myOnesC, myTwosC, myThreesC, myFoursC, myFivesC , 
	mySixesC, myTotalScoreC, myBonusScoreC, my3KindC, my4KindC, myFullHouseC, mySmallSC, myLargeSC , myYahtzeeC, myChanceC, myTotalLowerC, myGrandTotalC;
	private JLabel myOnesP, myTwosP, myThreesP, myFoursP, myFivesP , mySixesP, myTotalScoreP, myBonusScoreP;
	private JLabel[] PlayerUpperCategories = {myOnesP, myTwosP, myThreesP, myFoursP ,myFivesP , mySixesP, myTotalScoreP, myBonusScoreP};
	private JLabel[] ComputerUpperCategories = {myOnesC, myTwosC, myThreesC, myFoursC, myFivesC , mySixesC, myTotalScoreC, myBonusScoreC};
	private JLabel[] PlayerLowerCategories= {my3KindP, my4KindP,myFullHouseP, mySmallSP, myLargeSP, myYahtzeeP, myChanceP, myTotalLowerP, myGrandTotalP};
	private JLabel[] ComputerLowerCategories = {my3KindC, my4KindC, myFullHouseC,mySmallSC, myLargeSC, myYahtzeeC, myChanceC, myTotalLowerC, myGrandTotalC};
	private Controller myController;
	private String myPlayerName1;
	private JLabel myCup, myLogo, myLabelBackground, myPlayerName, myComputer, myPlayer,  myComputerName;
	private ImageIcon background, logo, cup;
	private JButton die1, die2, die3, die4, die5, roll , rollSelected , startGame, ones, twos, threes, fours, 
	fives, sixes, totalUpper, totalBonus, threeKinds, fourKinds, smallS, largeS, fullHouse, yahtzee, chance, totalLower, grandTotal;
	private JButton[] dices = { die1, die2, die3, die4, die5};
	private String[] images = {"one.png","two.png","three.png","four.png","five.png","six.png"};
	private String[] images1 = {"die1.png","die2.png","die3.png","die4.png","die5.png","die6.png"};
	private JButton[] upperCategories = { ones, twos, threes, fours, fives, sixes, totalUpper, totalBonus};
	private JButton[] lowerCategories = {threeKinds, fourKinds, fullHouse,smallS, largeS, yahtzee, chance ,
			totalUpper, totalLower, grandTotal};
	private ButtonListener myRollerListener , my1DieListener , my2DieListener, my3DieListener ,my4DieListener, my5DieListener, 
	myRollSomeListener, myOnesListener, myTwosListener, myThreesListener ,myFoursListener, myFivesListener, mySixesListener,
	myThreesKindListener, myFoursKindListener,myFullHouseListener, mySmallSListener, myLargeSListener, myYahtzeeListener, myChanceListener, 
	myStartListener;
	private Font myTextFont;
	
	private JLabel comment1, comment2, comment3, comment4, comment5, comment6, comment7, comment8, comment9, comment10, comment11, 
	comment12, comment13, comment14, comment15, comment16, comment17, comment18, comment19, comment20, comment21, comment22, comment23,
	comment24, comment25, comment26, comment27, comment28, comment29, comment30, comment31, comment32, comment33, comment34, comment35;
	int turns=0;
	
	//Player name
	public void playerName() 
	{
		myPlayerName1 = JOptionPane.showInputDialog("Enter Player Name");
	}
	
	//Constructor
public View( Controller controller){
	
		myYahtzeeEngine = new YahtzeeEngine(0);
		myComputer1 = new ComputerPlayer();
		playerName();
		panel.setLayout(null);
		
		myController = controller;
		this.associateListeners();
		
		background = new ImageIcon("background.png");
		logo = new ImageIcon("logo3.png");
		cup = new ImageIcon("cup.png");
		
		roll = new JButton("Roll");
		rollSelected = new JButton("Roll Selected");
		startGame = new JButton("Start Game");
		myComputer = new JLabel ("computer");
		myLogo = new JLabel(logo);
		myCup = new JLabel(cup);
		myLabelBackground = new JLabel (background);
		myPlayerName = new JLabel(myPlayerName1);
		myPlayer = new JLabel(myPlayerName1);
		myComputerName = new JLabel("Computer");
		myTextFont = new Font("TimesRoman", Font.BOLD, 26);
		
		comment1 = new JLabel ("<html>Click the button 'Start Game' to start <br> the game and then 'Roll' to roll dices<html>");
		comment2 = new JLabel ("<html>Choose the dice you want to re-roll and <br> click 'Roll Selected' or choose a category to assign your dice values to.<html>");
		comment3 = new JLabel ("<html>I'm done rolling. Your turn. Click 'Roll' <br>to continue playing<html>");
		comment4 = new JLabel ("Choose a category to assign your dice to");
		comment5 = new JLabel ("That category has alredy been used. Please pick another.");
		comment6 = new JLabel ("I chose the ONES category");
		comment9 =new JLabel ("I chose the TWOS category");
		comment10= new JLabel ("I chose the THREES category");
		comment11 =new JLabel ("I chose the FOURS category");
		comment12 = new JLabel ("I chose the FIVES category");
		comment13 = new JLabel ("I chose the SIXES category");
		comment14= new JLabel ("I chose the 3 OF KIND category");
		comment15= new JLabel ("I chose the 4 OF KIND category");
		comment16= new JLabel (" chose the FULL HOUSE category");
		comment17 =new JLabel ("<html>I chose the SMALL STRAIGHT <br> category <html>");
		comment18 = new JLabel ("<html>I chose the Large STRAIGHT <br>category <html>");
		comment19 = new JLabel ("I chose the YAHTZEE category");
		comment20= new JLabel (" I chose the CHANCE category");
		
		//Create button for the dices
		dices[0] = new JButton(images[0]);
		dices[1] = new JButton(images[1]);
		dices[2] = new JButton(images[2]);
		dices[3] = new JButton(images[3]);
		dices[4] = new JButton(images[4]);
		
		//Upper categories button
		upperCategories[0] = new JButton("Ones");
		upperCategories[1] = new JButton("Twos");
		upperCategories[2] = new JButton("Threes");
		upperCategories[3] = new JButton("Fours");
		upperCategories[4] = new JButton("Fives");
		upperCategories[5] = new JButton("Sixes");

		//Lower categories button
		lowerCategories[0] = new JButton("3 Kind");
		lowerCategories[1] = new JButton("4 Kind");
		lowerCategories[2] = new JButton("FullHouse");
		lowerCategories[3] = new JButton("SmallS");
		lowerCategories[4] = new JButton("LargeS");
		lowerCategories[5] = new JButton("Yahtzee");
		lowerCategories[6] = new JButton("Chance");
			
		//Player score for the upper category
		PlayerUpperCategories[0] = new JLabel("-");
		PlayerUpperCategories[1] = new JLabel("-");
		PlayerUpperCategories[2] = new JLabel("-");
		PlayerUpperCategories[3] = new JLabel("-");
		PlayerUpperCategories[4] = new JLabel("-");
		PlayerUpperCategories[5] = new JLabel("-");
		PlayerUpperCategories[6] = new JLabel("-");
		PlayerUpperCategories[7] = new JLabel("-");
		
		//Player score for the lower category
		PlayerLowerCategories[0] = new JLabel("-");
		PlayerLowerCategories[1] = new JLabel("-");
		PlayerLowerCategories[2] = new JLabel("-");
		PlayerLowerCategories[3] = new JLabel("-");
		PlayerLowerCategories[4] = new JLabel("-");
		PlayerLowerCategories[5] = new JLabel("-");
		PlayerLowerCategories[6] = new JLabel("-");
		PlayerLowerCategories[7] = new JLabel("-");
		PlayerLowerCategories[8] = new JLabel("-");
		
		//Computer score for the upper category
		ComputerUpperCategories[0] = new JLabel("-");
		ComputerUpperCategories[1] = new JLabel("-");
		ComputerUpperCategories[2] = new JLabel("-");
		ComputerUpperCategories[3] = new JLabel("-");
		ComputerUpperCategories[4] = new JLabel("-");
		ComputerUpperCategories[5] = new JLabel("-");
		ComputerUpperCategories[6] = new JLabel("-");
		ComputerUpperCategories[7] = new JLabel("-");
		
		//Computer score for the lower category
		ComputerLowerCategories[0] = new JLabel("-");
		ComputerLowerCategories[1] = new JLabel("-");
		ComputerLowerCategories[2] = new JLabel("-");
		ComputerLowerCategories[3] = new JLabel("-");
		ComputerLowerCategories[4] = new JLabel("-");
		ComputerLowerCategories[5] = new JLabel("-");
		ComputerLowerCategories[6] = new JLabel("-");
		ComputerLowerCategories[7] = new JLabel("-");
		ComputerLowerCategories[8] = new JLabel("-");
		
		//addMouseListener for reflection
		roll.addMouseListener(myRollerListener);
		rollSelected.addMouseListener(myRollSomeListener);
		startGame.addMouseListener(myStartListener);
		
		dices[0].addMouseListener(my1DieListener);
		dices[1].addMouseListener(my2DieListener);
		dices[2].addMouseListener(my3DieListener);
		dices[3].addMouseListener(my4DieListener);
		dices[4].addMouseListener(my5DieListener);
		
		upperCategories[0].addMouseListener(myOnesListener);
		upperCategories[1].addMouseListener(myTwosListener);
		upperCategories[2].addMouseListener(myThreesListener);
		upperCategories[3].addMouseListener(myFoursListener);
		upperCategories[4].addMouseListener(myFivesListener);
		upperCategories[5].addMouseListener(mySixesListener);
		
		lowerCategories[0].addMouseListener(myThreesKindListener);
		lowerCategories[1].addMouseListener(myFoursKindListener);
		lowerCategories[2].addMouseListener(myFullHouseListener);
		lowerCategories[3].addMouseListener(mySmallSListener);
		lowerCategories[4].addMouseListener(myLargeSListener);
		lowerCategories[5].addMouseListener(myYahtzeeListener);
		lowerCategories[6].addMouseListener(myChanceListener);
		
		
		panel.add(PlayerUpperCategories[0]);
		panel.add(PlayerUpperCategories[1]);
		panel.add(PlayerUpperCategories[2]);
		panel.add(PlayerUpperCategories[3]);
		panel.add(PlayerUpperCategories[4]);
		panel.add(PlayerUpperCategories[5]);
		panel.add(PlayerUpperCategories[6]);
		panel.add(PlayerUpperCategories[7]);
		
		panel.add(PlayerLowerCategories[0]);
		panel.add(PlayerLowerCategories[1]);
		panel.add(PlayerLowerCategories[2]);
		panel.add(PlayerLowerCategories[3]);
		panel.add(PlayerLowerCategories[4]);
		panel.add(PlayerLowerCategories[5]);
		panel.add(PlayerLowerCategories[6]);
		panel.add(PlayerLowerCategories[7]);
		panel.add(PlayerLowerCategories[8]);
		
		panel.add(ComputerUpperCategories[0]);
		panel.add(ComputerUpperCategories[1]);
		panel.add(ComputerUpperCategories[2]);
		panel.add(ComputerUpperCategories[3]);
		panel.add(ComputerUpperCategories[4]);
		panel.add(ComputerUpperCategories[5]);
		panel.add(ComputerUpperCategories[6]);
		panel.add(ComputerUpperCategories[7]);
		
		panel.add(ComputerLowerCategories[0]);
		panel.add(ComputerLowerCategories[1]);
		panel.add(ComputerLowerCategories[2]);
		panel.add(ComputerLowerCategories[3]);
		panel.add(ComputerLowerCategories[4]);
		panel.add(ComputerLowerCategories[5]);
		panel.add(ComputerLowerCategories[6]);
		panel.add(ComputerLowerCategories[7]);
		panel.add(ComputerLowerCategories[8]);
		panel.add(dices[0]);
		panel.add(dices[1]);
		panel.add(dices[2]);
		panel.add(dices[3]);
		panel.add(dices[4]);
		
		panel.add(upperCategories[0]);
		panel.add(upperCategories[1]);
		panel.add(upperCategories[2]);
		panel.add(upperCategories[3]);
		panel.add(upperCategories[4]);
		panel.add(upperCategories[5]);
		
		panel.add(lowerCategories[0]);
		panel.add(lowerCategories[1]);
		panel.add(lowerCategories[2]);
		panel.add(lowerCategories[3]);
		panel.add(lowerCategories[4]);
		panel.add(lowerCategories[5]);
		panel.add(lowerCategories[6]);

		panel.add(comment1);
		panel.add(comment2);
		panel.add(comment3);
		panel.add(comment6);
		panel.add(comment9);
		panel.add(comment10);
		panel.add(comment11);
		panel.add(comment12);
		panel.add(comment13);
		panel.add(comment14);
		panel.add(comment15);
		panel.add(comment16);
		panel.add(comment17);
		panel.add(comment18);
		panel.add(comment19);
		panel.add(comment20);

		comment16.setBounds(590, 120, 220, 80);	
		panel.add(roll);
		panel.add(rollSelected);
		panel.add(startGame);
		panel.add(myPlayerName);
		panel.add(myPlayer);
		panel.add(myComputerName);
		panel.add(myComputer);
		panel.add(myCup);
		panel.add(myLogo);
		panel.add(myLabelBackground);
				
		//Set location
		comment1.setBounds(580, 230, 220, 80);
		comment2.setBounds(580, 230, 220, 80);
		comment3.setBounds(580, 230, 220, 80);
		roll.setBounds(540, 535, 120, 40);
		rollSelected.setBounds(640, 535, 120, 40);
		startGame.setBounds(440, 535, 120, 40);
		myPlayerName.setFont(myTextFont);
		myPlayerName.setBounds(350, 90, 100, 25);
		myPlayer.setBounds(120, 120, 70, 30);
		myComputerName.setFont(myTextFont);
		myComputerName.setBounds(790, 90, 130, 25);
		
		myCup.setBounds(520, 370, 150, 160);
		myLabelBackground.setBounds(0, 0, 950, 750);
		myLogo.setBounds(450, 5, 300, 80);
		
		upperCategories[0].setBounds(28, 150, 90, 30);
		upperCategories[1].setBounds(28, 180, 90, 30);
		upperCategories[2].setBounds(28, 210, 90, 30);
		upperCategories[3].setBounds(28, 240, 90, 30);
		upperCategories[4].setBounds(28, 270, 90, 30);
		upperCategories[5].setBounds(28, 300, 90, 30);
		myComputer.setBounds(200, 120, 70, 30);
		
		lowerCategories[0].setBounds(28, 420, 90, 30);
		lowerCategories[1].setBounds(28, 450, 90, 30);
		lowerCategories[2].setBounds(28, 480, 90, 30);
		lowerCategories[3].setBounds(28, 510, 90, 30);
		lowerCategories[4].setBounds(28, 540, 90, 30);
		lowerCategories[5].setBounds(28, 570, 90, 30);
		lowerCategories[6].setBounds(28, 600, 90, 30);
			
		PlayerUpperCategories[0].setBounds(128, 150, 75, 30);
		PlayerUpperCategories[1].setBounds(128, 180, 75, 30);
		PlayerUpperCategories[2].setBounds(128, 210, 75, 30);
		PlayerUpperCategories[3].setBounds(128, 240, 75, 30);
		PlayerUpperCategories[4].setBounds(128, 270, 75, 30);
		PlayerUpperCategories[5].setBounds(128, 300, 75, 30);
		PlayerUpperCategories[6].setBounds(128, 330, 75, 30);
		PlayerUpperCategories[7].setBounds(128, 360, 75, 30);
		
		PlayerLowerCategories[0].setBounds(128, 422, 75, 30);
		PlayerLowerCategories[1].setBounds(128, 452, 75, 30);
		PlayerLowerCategories[2].setBounds(128, 482, 75, 30);
		PlayerLowerCategories[3].setBounds(128, 512, 75, 30);
		PlayerLowerCategories[4].setBounds(128, 542, 75, 30);
		PlayerLowerCategories[5].setBounds(128, 572, 75, 30);
		PlayerLowerCategories[6].setBounds(128, 602, 75, 30);
		PlayerLowerCategories[7].setBounds(128, 632, 75, 30);
		PlayerLowerCategories[8].setBounds(128, 662, 75, 30);
			
		ComputerUpperCategories[0].setBounds(205, 150, 65, 30);
		ComputerUpperCategories[1].setBounds(205, 180, 65, 30);
		ComputerUpperCategories[2].setBounds(205, 210, 65, 30);
		ComputerUpperCategories[3].setBounds(205, 240, 65, 30);
		ComputerUpperCategories[4].setBounds(205, 270, 65, 30);
		ComputerUpperCategories[5].setBounds(205, 300, 65, 30);
		ComputerUpperCategories[6].setBounds(205, 330, 65, 30);
		ComputerUpperCategories[7].setBounds(205, 360, 65, 30);
		
		ComputerLowerCategories[0].setBounds(205, 422, 65, 30);
		ComputerLowerCategories[1].setBounds(205, 452, 65, 30);
		ComputerLowerCategories[2].setBounds(205, 482, 65, 30);
		ComputerLowerCategories[3].setBounds(205, 512, 65, 30);
		ComputerLowerCategories[4].setBounds(205, 542, 65, 30);
		ComputerLowerCategories[5].setBounds(205, 572, 65, 30);	
		ComputerLowerCategories[6].setBounds(205, 602, 65, 30);
		ComputerLowerCategories[7].setBounds(205, 632, 65, 30);
		ComputerLowerCategories[8].setBounds(205, 662, 65, 30);
		
		dices[0].setBounds(280, 410, 102, 100);
		dices[1].setBounds(400, 410, 102, 100);
		dices[2].setBounds(540, 410, 102, 100);
		dices[3].setBounds(680, 410, 102, 100);
		dices[4].setBounds(820, 410, 102, 100);	
		
		dices[0].setVisible(false);
		dices[1].setVisible(false);
		dices[2].setVisible(false);
		dices[3].setVisible(false);
		dices[4].setVisible(false);

		comment3.setVisible(false);
		comment2.setVisible(false);
		comment6.setVisible(false);
		comment9.setVisible(false);
		comment10.setVisible(false);
		comment11.setVisible(false);
		comment12.setVisible(false);
		comment13.setVisible(false);
		comment14.setVisible(false);
		comment15.setVisible(false);
		comment16.setVisible(false);
		comment17.setVisible(false);
		comment18.setVisible(false);
		comment19.setVisible(false);
		comment20.setVisible(false);

		frame.setSize(950, 750);
		frame.setVisible(true); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Reflection
	public void associateListeners()
	{
		String error;
		Class<? extends Controller> controllerClass;
		Method myRollerMethod, oneDieMethod, twoDieMethod, threeDieMethod, fourDieMethod, fiveDieMethod
				, rollSomeMethod, myOnesMethod, myTwosMethod, myThreesMethod, myFoursMethod, myFivesMethod, mySixesMethod
				, mySmallStraightMethod, myLargeStraightMethod, myFoursKindMethod, myThreesKindMethod, myFullHouseMethod, myYahtzeeMethod, 
				myChanceMethod , startGameMethod;
		
		Class<?>[] classArgs;
		Integer[] args;
		
		controllerClass = myController.getClass();
		
		error = null;
		myRollerMethod = null;
		oneDieMethod = null;
		twoDieMethod = null;
		threeDieMethod = null;
		fourDieMethod = null;
		fiveDieMethod = null;
		rollSomeMethod = null;
		myOnesMethod = null;
		myTwosMethod = null;
		myThreesMethod = null;
		myFoursMethod = null;
		myFivesMethod = null;
		mySixesMethod = null;
		myThreesKindMethod = null;
		myFoursKindMethod = null;
		myFullHouseMethod = null;
		mySmallStraightMethod = null;
		myLargeStraightMethod = null;
		myYahtzeeMethod = null;
		myChanceMethod = null;
		startGameMethod = null;		
		
		classArgs = new Class[1];
		args = new Integer[1];
		
		 try
	        {
	           classArgs[0] = Class.forName("java.lang.Integer");
	        }
	        catch(ClassNotFoundException exception)
	        {
	           error = exception.toString();
	           System.out.println(error);
	        }
			try 
			{
				myRollerMethod = controllerClass.getMethod("roll", (Class<?>[]) null);
				oneDieMethod = controllerClass.getMethod("oneDieSelected", (Class<?>[]) null);
				twoDieMethod = controllerClass.getMethod("twoDieSelected", (Class<?>[]) null);
				threeDieMethod = controllerClass.getMethod("threeDieSelected", (Class<?>[]) null);
				fourDieMethod = controllerClass.getMethod("fourDieSelected", (Class<?>[]) null);
				fiveDieMethod = controllerClass.getMethod("fiveDieSelected", (Class<?>[]) null);
				rollSomeMethod = controllerClass.getMethod("rollSelected", (Class<?>[]) null);
				myOnesMethod = controllerClass.getMethod("fillOnes", (Class<?>[])null);
				myTwosMethod = controllerClass.getMethod("fillTwos", (Class<?>[])null);
				myThreesMethod = controllerClass.getMethod("fillThrees", (Class<?>[])null);
				myFoursMethod = controllerClass.getMethod("fillFours", (Class<?>[])null);
				myFivesMethod = controllerClass.getMethod("fillFives", (Class<?>[])null);
				mySixesMethod = controllerClass.getMethod("fillSixes", (Class<?>[])null);
				myThreesKindMethod = controllerClass.getMethod("fillThreeOfKind", (Class<?>[])null);
				myFoursKindMethod = controllerClass.getMethod("fillFourOfKind", (Class<?>[])null);
				myFullHouseMethod = controllerClass.getMethod("fillFullHouse", (Class<?>[])null);
				mySmallStraightMethod = controllerClass.getMethod("fillSmallStraight", (Class<?>[])null);
				myLargeStraightMethod = controllerClass.getMethod("fillLargeStraight", (Class<?>[])null);
				myYahtzeeMethod = controllerClass.getMethod("fillYahtzee", (Class<?>[])null);
				myChanceMethod = controllerClass.getMethod("fillChance", (Class<?>[])null);
				startGameMethod = controllerClass.getMethod("startGame", (Class<?>[])null);
				} 
			  catch(NoSuchMethodException exception)
	        {
	           error = exception.toString();
	           System.out.println(error);
	        }
	        catch(SecurityException exception)
	        {
	           error = exception.toString();
	           System.out.println(error);
	        }
	        
			myRollerListener = new ButtonListener(myController, myRollerMethod, null);
			my1DieListener = new ButtonListener(myController, oneDieMethod, null);
			my2DieListener= new ButtonListener(myController, twoDieMethod, null);
			my3DieListener = new ButtonListener(myController, threeDieMethod, null);
			my4DieListener = new ButtonListener(myController, fourDieMethod, null);
			my5DieListener = new ButtonListener(myController, fiveDieMethod, null);
			myRollSomeListener = new ButtonListener(myController, rollSomeMethod, null);
			myOnesListener = new ButtonListener(myController, myOnesMethod, null);
			myTwosListener = new ButtonListener(myController, myTwosMethod, null);
			myThreesListener = new ButtonListener(myController, myThreesMethod, null);
			myFoursListener = new ButtonListener(myController, myFoursMethod, null);
			myFivesListener = new ButtonListener(myController, myFivesMethod, null);
			mySixesListener = new ButtonListener(myController, mySixesMethod, null);
			myThreesKindListener = new ButtonListener(myController, myThreesKindMethod, null);
			myFoursKindListener = new ButtonListener(myController, myFoursKindMethod, null);
			myFullHouseListener = new ButtonListener(myController, myFullHouseMethod, null);
			mySmallSListener = new ButtonListener(myController, mySmallStraightMethod, null);
			myLargeSListener = new ButtonListener(myController, myLargeStraightMethod, null);
			myYahtzeeListener = new ButtonListener(myController, myYahtzeeMethod, null);
			myChanceListener = new ButtonListener(myController, myChanceMethod, null);
			myStartListener = new ButtonListener(myController, startGameMethod, null);
	}
	
	public void startGame()
	{
		comment1.setVisible(false);
		comment2.setVisible(true);
		comment3.setVisible(false);
	}
	
	public void comment2()
	{
		comment2.setVisible(false);
		comment3.setVisible(true);
	}
	
	public void imgDice()
	{
		dices[0].setVisible(true);
		dices[1].setVisible(true);
		dices[2].setVisible(true);
		dices[3].setVisible(true);
		dices[4].setVisible(true);
		myCup.setVisible(false);
	}
	
	public void one() 
	{
		comment6.setVisible(true);
		comment2.setVisible(false);
		comment6.setBounds(535, 120, 220, 80);
	}
	public void two() 
	{
		comment9.setVisible(true);
		comment2.setVisible(false);
		comment9.setBounds(535, 120, 220, 80);
	}
	public void three() 
	{
		comment10.setVisible(true);
		comment2.setVisible(false);
		comment10.setBounds(535, 120, 220, 80);
	}
	public void four() 
	{
		comment11.setVisible(true);
		comment2.setVisible(false);
		comment11.setBounds(535, 120, 220, 80);
	}
	public void five() 
	{
		comment12.setVisible(true);
		comment2.setVisible(false);
		comment12.setBounds(535, 120, 220, 80);
	}
	public void six() 
	{
		comment13.setVisible(true);
		comment13.setBounds(535, 120, 220, 80);
	}	
	public void threeKind()
	{
		comment14.setVisible(true);
		comment2.setVisible(false);
		comment14.setBounds(535, 120, 220, 80);
	}
	public void fourKind() 
	{
		comment15.setVisible(true);
		comment2.setVisible(false);
		comment15.setBounds(535, 120, 220, 80);
	}
	public void fullHouse() 
	{
		comment16.setVisible(true);
		comment2.setVisible(false);
		comment16.setBounds(535, 120, 220, 80);
	}
	public void small() 
	{
		comment17.setVisible(true);
		comment2.setVisible(false);
		comment17.setBounds(535, 120, 220, 80);
	}	
	public void large() 
	{
		comment18.setVisible(true);
		comment2.setVisible(false);
		comment18.setBounds(535, 120, 220, 80);
	}
	public void yahtzee() 
	{
		comment19.setVisible(true);
		comment2.setVisible(false);
		comment19.setBounds(535, 120, 220, 80);
	}
	public void chance() 
	{
		comment20.setVisible(true);
		comment2.setVisible(false);
		comment20.setBounds(535, 120, 220, 80);
	}
	
	public void setPlayerUpperC( int category, int value)
	{
		PlayerUpperCategories[category].setText(Integer.toString(value));
	}
	
	public void setPlayerLowerC( int category, int value)
	{
		PlayerLowerCategories[category].setText(Integer.toString(value));
	}
	
	public void setComputerUpperC( int category , int value)
	{
		ComputerUpperCategories[category].setText(Integer.toString(value));
	}
	
	public void setComputerLowerC( int category, int value)
	{
		ComputerLowerCategories[category].setText(Integer.toString(value));
		
	}
	/*
	 * set the Dice initial images
	 */
	public void setDice(int[] values)
	{
			dices[0].setIcon(new ImageIcon(images[values[0] -1]));
			dices[1].setIcon(new ImageIcon(images[values[1] -1]));
			dices[2].setIcon(new ImageIcon(images[values[2] -1]));
			dices[3].setIcon(new ImageIcon(images[values[3] -1]));
			dices[4].setIcon(new ImageIcon(images[values[4] -1]));
	}
	/*
	 * set default for the background
	 */
	public void setCategoriesBackground( int categories)
	{
		upperCategories[categories].setEnabled(true);
	}
	
	public void setCategoriesBackground1( int categories)
	{
		lowerCategories[categories].setEnabled(true);
	}
	
	public void setUpDefaultBackground( int categories)
	{
		upperCategories[categories].setEnabled(false);
	}
	
	public void setLowDefaultBackground ( int categories )
	{		
		lowerCategories[categories].setEnabled(false);
	}
	
	public void setDefaultSelected()
	{
		for ( int i = 0 ; i < selectingDie.length; i ++)
		{
			selectingDie[i] = false;
		}
	}
	
	/*
	 * get selected dice
	 */
	public boolean[] getSelectedDice()
	{
		return selectingDie;
	}
	
	public void setSelected1(int die , int value)
	{
		if( selectingDie[0] == false)
		{
			selectingDie[0] = true;
			dices[0].setBounds(280, 610, 105, 100);
		}
		else
		{
			dices[0].setBounds(280, 410, 105, 100);
			selectingDie[0] = false;
		}
	}
	
	public void setSelected2(int die, int value)
	{
		if( selectingDie[1] == false)
		{
			selectingDie[1] = true;
			dices[1].setBounds(400, 610, 105, 100);
		}
		
		else
		{
			dices[1].setBounds(400, 410, 105, 100);
			selectingDie[1] = false;
		}
	}
	
	public void setSelected3(int die, int value)
	{
		if( selectingDie[2] == false)
		{
			selectingDie[2] = true;
			dices[2].setBounds(540, 610, 105, 100);
		}
		else
		{
			dices[2].setBounds(540, 410, 105, 100);
			selectingDie[2] = false;
		}
	}
	
	public void setSelected4(int die, int value)
	{
		if( selectingDie[3] == false)
		{
			selectingDie[3] = true;
			dices[3].setBounds(680, 610, 105, 100);
		}
		
		else
		{
			dices[3].setBounds(680, 410, 105, 100);
			selectingDie[3] = false;
		}
	}
	public void setSelected5(int die, int value)
	{
		if( selectingDie[4] == false)
		{
			selectingDie[4] = true;
			dices[4].setBounds(820, 610, 105, 100);	
		}
		
		else
		{
			dices[4].setBounds(820, 410, 105, 100);	
			selectingDie[4] = false;
		}
	}
	
	public JButton[] getCategoriesUpper()
	{
		return upperCategories;
	}
	
	public JButton[] getCategoriesLower()
	{
		return lowerCategories;
	}
	
	/*
	 * This is for Update Total Score for Player and Computer
	 */
	public void setUpperScoresPlayer( int indexUpperScoreP, int upperScoreValueP)
	{
		PlayerUpperCategories[indexUpperScoreP].setText(Integer.toString(upperScoreValueP));
	}
	
	public void setUpperScoresComputer( int indexUpperScoreC , int upperScoreValueC)
	{
		
		ComputerUpperCategories[indexUpperScoreC].setText(Integer.toString(upperScoreValueC));
	}
	
	public void setScoreLowPlayer( int indexLowScoreP , int lowScoreValueP)
	{
		PlayerLowerCategories[indexLowScoreP].setText(Integer.toString(lowScoreValueP));
	}
	
	public void setScoreLowComputer( int indexLowScoreC, int lowScoreValueC)
	{
		ComputerLowerCategories[indexLowScoreC].setText(Integer.toString(lowScoreValueC));
	}
	
	/*
	 * set up comments for instructions
	 */
	public void comments()
	{
		comment6.setVisible(false);
		comment9.setVisible(false);
		comment10.setVisible(false);
		comment11.setVisible(false);
		comment12.setVisible(false);
		comment13.setVisible(false);
		comment14.setVisible(false);
		comment15.setVisible(false);
		comment16.setVisible(false);
		comment17.setVisible(false);
		comment18.setVisible(false);
		comment19.setVisible(false);
		comment20.setVisible(false);
	}
	
	public void dices()
	{
		dices[0].setBounds(280, 410, 105, 100);
		dices[1].setBounds(400, 410, 105, 100);
		dices[2].setBounds(540, 410, 105, 100);
		dices[3].setBounds(680, 410, 105, 100);
		dices[4].setBounds(820, 410, 105, 100);	
	}

	/*
	 * count turns, so that we can end the game and choose the winner
	 */
	public void turnsAndWinner()
	{
		
		int player, computer;
		
		if (turns<=26)
		{
			turns ++;
		}
		else if (turns >26)
		{
			player= myYahtzeeEngine.getPlayerUp().getScoreCard().calculateGrandTotal();
			computer= myComputer1.getCompTotalScore();
			if( player > computer)
			{
				JOptionPane.showMessageDialog(null, "You are the Winner!:)");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You loss :( the computer is the Winner");
			}
			
		}
	}
	
}
