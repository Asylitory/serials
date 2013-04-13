package serials.web.forms;

import java.util.Collection;
import java.util.List;

import serials.logic.Season;
import serials.logic.Serial;
import serials.logic.Series;

public class Form {
	private String title;
	private int requestType;
	private Serial serial;
	private Season season;
	private Series series;
	private String action;

	private List<Serial> serialList;
	private List<Season> seasonList;
	private Collection<Series> seriesList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public Serial getSerial() {
		return serial;
	}
	public void setSerial(Serial serial) {
		this.serial = serial;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public Series getSeries() {
		return series;
	}
	public void setSeries(Series series) {
		this.series = series;
	}
	public List<Serial> getSerialList() {
		return serialList;
	}
	public void setSerialList(List<Serial> serialList) {
		this.serialList = serialList;
	}
	public List<Season> getSeasonList() {
		return seasonList;
	}
	public void setSeasonList(List<Season> seasonList) {
		this.seasonList = seasonList;
	}
	public Collection<Series> getSeriesList() {
		return seriesList;
	}
	public void setSeriesList(Collection<Series> seriesList) {
		this.seriesList = seriesList;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
