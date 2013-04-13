package serials.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serials.logic.Season;
import serials.logic.Serial;
import serials.logic.SerialManagement;
import serials.logic.Series;
import serials.web.forms.CONSTANTS;
import serials.web.forms.Form;

public class MainServlet extends HttpServlet {
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

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException  {
		Form form = new Form();
		try {
			form.setSerialList(SerialManagement.getInstance().getAllSerials());
		} catch (SQLException sql_e) {
			throw new IOException(sql_e.getMessage());
		}
		form.setTitle("Сериалы!");
		
		int answer = checkAction(req);
		
		if (CONSTANTS.SERIAL == answer) {
			int serialId = Integer.parseInt(req.getParameter("serialId"));
			try {
				Serial serial = SerialManagement.getInstance().getSerialById(serialId);
				if (null != serial) {
					form.setSerial(serial);
					form.setSeasonList(SerialManagement.getInstance().getSeasonsFromSerial(serial));
					form.setSeriesList(SerialManagement.getInstance().getSeriesFromSerial(serial));
					form.setTitle(serial.getSerialTitle());
					form.setRequestType(CONSTANTS.SERIAL);
				}
			} catch (SQLException sql_e) {
				throw new IOException(sql_e.getMessage());
			}
		}
		
		if (CONSTANTS.SEASON == answer) {
			int seasonId = Integer.parseInt(req.getParameter("seasonId"));
			try {
				Season season = SerialManagement.getInstance().getSeasonById(seasonId);
				if (null != season) {
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					form.setSerial(serial);
					form.setSeason(season);
					form.setSeriesList(SerialManagement.getInstance().getSeriesFromSeason(season));
					form.setTitle(serial.getSerialTitle() + ": " + season.getSeasonTitle());
					form.setRequestType(CONSTANTS.SEASON);
				}
			} catch (SQLException sql_e) {
				throw new IOException(sql_e.getMessage());
			}
		}
		if (CONSTANTS.SERIES == answer) {
			int seriesId = Integer.parseInt(req.getParameter("seriesId"));
			try {
				Series series = SerialManagement.getInstance().getSeriesById(seriesId);
				if (null != series) {
					Season season = SerialManagement.getInstance().getSeasonById(series.getSeasonId());
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					form.setSerial(serial);
					form.setSeason(season);
					form.setSeries(series);
					form.setTitle(serial.getSerialTitle() + ": " + season.getSeasonTitle() + " - " + series.getSeriesTitle());
					form.setRequestType(CONSTANTS.SERIES);
				}
			} catch (SQLException sql_e) {
				throw new IOException(sql_e.getMessage());
			}
		}
		
		req.setAttribute("form", form);
		req.setAttribute("constants", new CONSTANTS());
		getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
		return;
	}
	
	private int checkAction(HttpServletRequest req) {
		if (req.getParameter("serialId") != null) {
			return CONSTANTS.SERIAL;
		}
		if (req.getParameter("seasonId") != null) {
			return CONSTANTS.SEASON;
		}
		if (req.getParameter("seriesId") != null) {
			return CONSTANTS.SERIES;
		}
		return 0;
	}
}
