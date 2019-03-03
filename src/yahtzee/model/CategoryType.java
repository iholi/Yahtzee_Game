package yahtzee.model;

public enum CategoryType
{
    ONES("Ones"),
    TWOS("Twos"),
    THREES("Threes"),
    FOURS("Fours"),
    FIVES("Fives"),
    SIXES("Sixes"),
    THREE_OF_KIND("Three of a kind"),
    FOUR_OF_KIND("Four of a kind"),
    FULL_HOUSE("Full House"),
    SMALL_STRAIGHT("Small Straight"),
    LARGE_STRAIGHT("Large Straight"),
    YAHTZEE("Yahtzee"),
    CHANCE("Chance");
	
	private String myCategory;

    CategoryType(String name)
    {
    	myCategory=name;
    }

    public String getName()
    {
        return myCategory;
    }
}
