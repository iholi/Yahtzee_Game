package yahtzee.testing;

import org.junit.Before;

import yahtzee.model.CategoryType;

public class TestCategoryChance extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {5, 1, 3, 3, 3};
		int [] bad = {-1, 4, 4, 4, 4};
		myType = CategoryType.CHANCE;
		myDescription = "Chance";
		myGoodValuesSum = 15;
		myGoodValues = good;
		myZeroValues = bad;
	}
}
