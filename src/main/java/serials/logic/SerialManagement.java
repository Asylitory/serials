package serials.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SerialManagement {
	private static SerialManagement instance;
	private static Connection conn;
	private static DataSource dataSource;
	
	private SerialManagement() {}
	
	public static synchronized SerialManagement getInstance() {
		if (instance == null) {
			try {
				instance = new SerialManagement();
				Context ctx = new InitialContext();
				dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/SerialsDS");
				conn = dataSource.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public List<Serial> getAllSerials() throws SQLException {
		List<Serial> serials = new ArrayList<Serial>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT serial_id, serial_title FROM serials " +
					"ORDER BY serial_title");
			
			while (rs.next()) {
				Serial sr = new Serial();
				
				sr.setSerialId(rs.getInt(1));
				sr.setSerialTitle(rs.getString(2));
				
				serials.add(sr);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return serials;
	}
	
	public Serial getSerialById(int serialId) throws SQLException
	{
		Serial serial = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
					"SELECT serial_id, serial_title " + 
					"FROM serials " + 
					"WHERE serial_id=?");
			stmt.setInt(1, serialId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				serial = new Serial();
				serial.setSerialId(rs.getInt(1));
				serial.setSerialTitle(rs.getString(2));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return serial;
	}
	
	public void insertSerial(Serial serial) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO serials " + 
					"(serial_title) " + 
					"VALUES (?)");
			stmt.setString(1, serial.getSerialTitle());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void updateSerial(Serial serial) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
		stmt = conn.prepareStatement(
				"UPDATE serials SET " + 
				"serial_title=? " + 
				"WHERE serial_id=?");
		stmt.setString(1, serial.getSerialTitle());
		stmt.setInt(2, serial.getSerialId());
		
		stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void deleteSerial(Serial serial) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"DELETE FROM series " +
					"WHERE season_id IN " +
						"(SELECT season_id FROM seasons " +
						"WHERE serial_id=?)");
			stmt.setInt(1, serial.getSerialId());
			
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(
					"DELETE FROM seasons " +
					"WHERE serial_id=?");
			stmt.setInt(1, serial.getSerialId());
			
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(
					"DELETE FROM serials " +
					"WHERE serial_id=?");
			stmt.setInt(1, serial.getSerialId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public List<Season> getAllSeasons() throws SQLException {
		List<Season> seasons = new ArrayList<Season>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT season_id, serial_id, season_title FROM seasons");
			
			while (rs.next()) {
				Season sn = new Season();
				sn.setSeasonId(rs.getInt(1));
				sn.setSerialId(rs.getInt(2));
				sn.setSeasonTitle(rs.getString(3));
				
				seasons.add(sn);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return seasons;
	}
	
	public List<Season> getSeasonsFromSerial(Serial serial) throws SQLException {
		List<Season> seasons = new ArrayList<Season>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
					"SELECT season_id, serial_id, season_title " +
					"FROM seasons " +
					"WHERE serial_id=?");
			stmt.setInt(1, serial.getSerialId());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Season sn = new Season();
				
				sn.setSeasonId(rs.getInt(1));
				sn.setSerialId(rs.getInt(2));
				sn.setSeasonTitle(rs.getString(3));
				
				seasons.add(sn);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return seasons;
	}
	
	public Season getSeasonById(int seasonId) throws SQLException {
		Season season = new Season();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
					"SELECT season_id, serial_id, season_title "+
					"FROM seasons " + 
					"WHERE season_id=?");
			stmt.setInt(1, seasonId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				season.setSeasonId(rs.getInt(1));
				season.setSerialId(rs.getInt(2));
				season.setSeasonTitle(rs.getString(3));
			} else {
				rs.close();
				stmt.close();
				
				return null;
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return season;
	}
	
	public void insertSeason(Season season) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO seasons " +
				"(serial_id, season_title) " +
				"VALUES (?, ?)");
		stmt.setInt(1, season.getSerialId());
		stmt.setString(2, season.getSeasonTitle());
		
		stmt.execute();
	}
	
	public void updateSeason(Season season) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"UPDATE seasons SET " +
					"season_title=? " + 
					"WHERE season_id=?");
			stmt.setString(1, season.getSeasonTitle());
			stmt.setInt(2, season.getSerialId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void deleteSeason(Season season) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"DELETE FROM series " +
					"WHERE season_id=?");
			stmt.setInt(1, season.getSeasonId());
		
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(
					"DELETE FROM seasons " +
					"WHERE season_id=?");
			stmt.setInt(1, season.getSeasonId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Collection<Series> getAllSeries() throws SQLException {
		Collection<Series> series = new ArrayList<Series>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT series_id, season_id, date, series_title FROM series");
			
			while(rs.next()) {
				Series sr = new Series();
				
				sr.setSeriesId(rs.getInt(1));
				sr.setSeasonId(rs.getInt(2));
				sr.setDate(rs.getDate(3));
				sr.setSeriesTitle(rs.getString(4));
				
				series.add(sr);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return series;
	}
	
	public Collection<Series> getSeriesFromSerial(Serial serial) throws SQLException {
		Collection<Series> series = new ArrayList<Series>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
					"SELECT series_id, season_id, date, series_title " + 
					"FROM series " + 
					"WHERE season_id IN " + 
					"(SELECT season_id FROM seasons " +
					"WHERE serial_id=?) " +
					"ORDER BY date");
			stmt.setInt(1, serial.getSerialId());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Series sr = new Series();
				
				sr.setSeriesId(rs.getInt(1));
				sr.setSeasonId(rs.getInt(2));
				sr.setDate(rs.getDate(3));
				sr.setSeriesTitle(rs.getString(4));
				
				series.add(sr);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return series;
	}
	
	public Collection<Series> getSeriesFromSeason(Season season) throws SQLException {
		Collection<Series> series = new ArrayList<Series>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(
					"SELECT series_id, season_id, date, series_title " + 
					"FROM series " +
					"WHERE season_id=? " +
					"ORDER BY date");
			stmt.setInt(1, season.getSeasonId());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Series sr = new Series();
				
				sr.setSeriesId(rs.getInt(1));
				sr.setSeasonId(rs.getInt(2));
				sr.setDate(rs.getDate(3));
				sr.setSeriesTitle(rs.getString(4));
				
				series.add(sr);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return series;
	}
	
	public Series getSeriesById(int seriesId) throws SQLException {
		Series series = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {		
			stmt = conn.prepareStatement(
					"SELECT series_id, season_id, date, series_title " + 
					"FROM series " + 
					"WHERE series_id=?");
			stmt.setInt(1, seriesId);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				series = new Series();
				series.setSeriesId(rs.getInt(1));
				series.setSeasonId(rs.getInt(2));
				series.setDate(rs.getDate(3));
				series.setSeriesTitle(rs.getString(4));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return series;
	}
	
	public void insertSeries(Series series) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO series " + 
					"(season_id, date, series_title) " + 
					"VALUES (?, ?, ?)");
			stmt.setInt(1, series.getSeasonId());
			stmt.setDate(2, new Date(series.getDate().getTime()));
			stmt.setString(3, series.getSeriesTitle());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void updateSeries(Series series) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"UPDATE series SET " + 
					"season_id=?, date=?, series_title=?" + 
					"WHERE series_id=?");
			
			stmt.setInt(1, series.getSeasonId());
			stmt.setDate(2, new Date(series.getDate().getTime()));
			stmt.setString(3, series.getSeriesTitle());
			stmt.setInt(4, series.getSeriesId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void deleteSeries(Series series) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"DELETE FROM series " + 
					"WHERE series_id=?");
			stmt.setInt(1, series.getSeriesId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
}
