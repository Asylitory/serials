package serials.logic;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestSeries {
	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] {
				{"abc", 10, 10, 2010},
				{"aaa", 10, 11, 2010}
		});
	}
	
	Series thatSeries;
	Series thisSeries;
	
	public TestSeries(String title, int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		
		thatSeries = new Series();
		thatSeries.setSeriesTitle(title);
		thatSeries.setDate(calendar.getTime());
		
		calendar.set(2010, 10, 10);
		
		thisSeries = new Series();
		thisSeries.setSeriesTitle("aaa");
		thisSeries.setDate(calendar.getTime());
		}

	@Test
	public void TestCompareTo_ValueMore() {
		assertTrue(thatSeries.compareTo(thisSeries) > 0);
	}
	
	@Test
	public void TestCompareTo_ValueLess() {
		assertTrue(thisSeries.compareTo(thatSeries) < 0);
	}
	
	@Test
	public void TestCompareTo_ValueEqual() {
		assertTrue(thatSeries.compareTo(thatSeries) == 0);
	}
}
