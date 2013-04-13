package serials.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serials.logic.Season;
import serials.logic.Serial;
import serials.logic.SerialManagement;
import serials.logic.Series;

import serials.web.forms.CONSTANTS;

public class CompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		String answer = checkAction(req);
		int type = Integer.parseInt(req.getParameter("type"));
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
			if(CONSTANTS.NEW_ENTRY == answer) {
				if (CONSTANTS.SERIAL == type) {
					Serial serial = new Serial();
					serial.setSerialTitle(req.getParameter("serialTitle"));
					SerialManagement.getInstance().insertSerial(serial);
					resp.sendRedirect("main");
					return;
				}
				if (CONSTANTS.SEASON == type) {
					Season season = new Season();
					season.setSeasonTitle(req.getParameter("seasonTitle"));
					season.setSerialId(Integer.parseInt(req.getParameter("serialId")));
					SerialManagement.getInstance().insertSeason(season);
					resp.sendRedirect("main?serialId=" + season.getSerialId());
					return;
				}
				if (CONSTANTS.SERIES == type) {
					Series series = new Series();
					series.setSeriesTitle(req.getParameter("seriesTitle"));
					series.setDate(sdf.parse(req.getParameter("date").trim()));
					series.setSeasonId(Integer.parseInt(req.getParameter("seasonId")));
					SerialManagement.getInstance().insertSeries(series);
					resp.sendRedirect("main?seasonId=" + series.getSeasonId());
					return;
				}
			}
			if (CONSTANTS.EDIT == answer) {
				if (CONSTANTS.SERIAL == type) {
					Serial serial = new Serial();
					serial.setSerialTitle(req.getParameter("serialTitle"));
					serial.setSerialId(id);
					SerialManagement.getInstance().updateSerial(serial);
					resp.sendRedirect("main?serialId=" + id);
					return;
				}
				if (CONSTANTS.SEASON == type) {
					Season season = new Season();
					season.setSeasonTitle(req.getParameter("seasonTitle"));
					season.setSerialId(Integer.parseInt(req.getParameter("serialId")));
					season.setSeasonId(id);
					SerialManagement.getInstance().updateSeason(season);
					resp.sendRedirect("main?seasonId=" + id);
					return;
				}
				if (CONSTANTS.SERIES == type) {
					Series series = new Series();
					series.setSeriesTitle(req.getParameter("seriesTitle"));
					series.setDate(sdf.parse(req.getParameter("date").trim()));
					series.setSeasonId(Integer.parseInt(req.getParameter("seasonId")));
					series.setSeriesId(id);
					SerialManagement.getInstance().updateSeries(series);
					resp.sendRedirect("main?seriesId=" + id);
					return;
				}
			}
			if (CONSTANTS.DELETE == answer) {
				if (CONSTANTS.SERIAL == type) {
					Serial serial = SerialManagement.getInstance().getSerialById(id);
					SerialManagement.getInstance().deleteSerial(serial);
					resp.sendRedirect("main");
					return;
				}
				if (CONSTANTS.SEASON == type) {
					Season season = SerialManagement.getInstance().getSeasonById(id);
					SerialManagement.getInstance().deleteSeason(season);
					resp.sendRedirect("main?serialId=" + season.getSerialId());
					return;
				}
				if (CONSTANTS.SERIES == type) {
					Series series = new Series();
					series.setSeriesId(id);
					SerialManagement.getInstance().deleteSeries(series);
					resp.sendRedirect("main?seasonId=" + series.getSeasonId());
					return;
				}
			}
			if (CONSTANTS.CANCEL == answer) {
				switch (type) {
				case CONSTANTS.SERIAL:
					resp.sendRedirect("main?serialId=" + id);
					break;
				case CONSTANTS.SEASON:
					resp.sendRedirect("main?seasonId=" + id);
					break;
				case CONSTANTS.SERIES:
					resp.sendRedirect("main?seriesId=" + id);
					break;
				}
				return;
			}
			
			if (CONSTANTS.NEW_CANCEL == answer) {
				switch (type) {
				case CONSTANTS.SERIAL:
					resp.sendRedirect("main");
					break;
				case CONSTANTS.SEASON:
					resp.sendRedirect("main?serialId=" + Integer.parseInt(req.getParameter("serialId")));
					break;
				case CONSTANTS.SERIES:
					resp.sendRedirect("main?seasonId=" + Integer.parseInt(req.getParameter("seasonId")));
					break;
				}
				return;
			}
		} catch (SQLException sql_e) {
			throw new IOException(sql_e.getMessage());
		} catch (ParseException p_e) {
			throw new IOException(p_e.getMessage());
		}
	}

	private String checkAction(HttpServletRequest req) {
		if (req.getParameter(CONSTANTS.NEW_ENTRY) != null) {
			return CONSTANTS.NEW_ENTRY;
		}
		if (req.getParameter(CONSTANTS.EDIT) != null) {
			return CONSTANTS.EDIT;
		}
		if (req.getParameter(CONSTANTS.DELETE) != null) {
			return CONSTANTS.DELETE;
		}
		if (req.getParameter(CONSTANTS.CANCEL) != null) {
			return CONSTANTS.CANCEL;
		}
		if (req.getParameter(CONSTANTS.NEW_CANCEL) != null) {
			return CONSTANTS.NEW_CANCEL;
		}
		return null;
	}
}
