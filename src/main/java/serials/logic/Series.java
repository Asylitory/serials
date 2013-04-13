package serials.logic;

import java.text.DateFormat;
import java.util.Date;

public class Series implements Comparable<Series> {
	private int seriesId;
	private int seasonId;
	private Date date;
	private String seriesTitle;
	
	public int getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSeriesTitle() {
		return seriesTitle;
	}
	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}
	@Override
	public String toString() {
		return seriesTitle + "; Дата выхода: " +
				DateFormat.getDateInstance(DateFormat.SHORT).format(date);
	}

	public int compareTo(Series series) {
		int compareRezult = this.date.compareTo(series.date);
		if (compareRezult != 0)
		{
			return compareRezult;
		}
		else
		{
			return this.seriesTitle.compareTo(series.seriesTitle);
		}
	}	
}
