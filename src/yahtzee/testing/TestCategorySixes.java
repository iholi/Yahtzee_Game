package yahtzee.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import yahtzee.model.Category;
import yahtzee.model.CategoryType;

public class TestCategorySixes extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {6, 5, 6, 4, 6};
		int [] bad = {2, 3, 2, 3, 1};
		myType = CategoryType.SIXES;
		myDescription = "Sixes";
		myGoodValuesSum = 18;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling fives category.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {6, 6, 1, 2, 4};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 12);
	}
	
}
