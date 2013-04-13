package serials.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

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

public class EditServlet extends HttpServlet {
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
			throws IOException, ServletException {
		Form form = new Form();
		String answer = checkAction(req);
		int type = Integer.parseInt(req.getParameter("type"));
		if (CONSTANTS.NEW_ENTRY == answer) {
			if (CONSTANTS.SERIAL == type) {
				Serial serial = new Serial();
				serial.setSerialTitle("Введите название");
				form.setAction(CONSTANTS.NEW_ENTRY);
				form.setRequestType(CONSTANTS.SERIAL);
				form.setTitle("Новый сериал");
				form.setSerial(serial);
			}
			if (CONSTANTS.SEASON == type) {
				int id = Integer.parseInt(req.getParameter("id"));
				try {
					Serial serial = SerialManagement.getInstance().getSerialById(id);
					Season season = new Season();
					season.setSeasonTitle("Введите название");
					season.setSerialId(serial.getSerialId());
					form.setAction(CONSTANTS.NEW_ENTRY);
					form.setRequestType(CONSTANTS.SEASON);
					form.setTitle("Новый сезон в сериале " + serial.getSerialTitle());
					form.setSerial(serial);
					form.setSeason(season);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
			if (CONSTANTS.SERIES == type) {
				int id = Integer.parseInt(req.getParameter("id"));
				try {
					Season season = SerialManagement.getInstance().getSeasonById(id);
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					Series series = new Series();
					series.setSeriesTitle("Новая серия");
					series.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
					form.setAction(CONSTANTS.NEW_ENTRY);
					form.setRequestType(CONSTANTS.SERIES);
					form.setTitle("Новая серия в сезоне " + season.getSeasonTitle() + " сериала " + serial.getSerialTitle());
					form.setSerial(serial);
					form.setSeason(season);
					form.setSeries(series);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
		}
		if (CONSTANTS.EDIT == answer) {
			int id = Integer.parseInt(req.getParameter("id"));
			if (CONSTANTS.SERIAL == type) {
				try {
					Serial serial = SerialManagement.getInstance().getSerialById(id);
					form.setAction(CONSTANTS.EDIT);
					form.setRequestType(CONSTANTS.SERIAL);
					form.setTitle("Редактирование сериала " + serial.getSerialTitle());
					form.setSerial(serial);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
			if (CONSTANTS.SEASON == type) {
				try {
					Season season = SerialManagement.getInstance().getSeasonById(id);
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					form.setAction(CONSTANTS.EDIT);
					form.setRequestType(CONSTANTS.SEASON);
					form.setTitle("Редактирование сезона " + season.getSeasonTitle() + " сериала " + serial.getSerialTitle());
					form.setSeason(season);
					form.setSerial(serial);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
			if (CONSTANTS.SERIES == type) {
				try {
					Series series = SerialManagement.getInstance().getSeriesById(id);
					Season season = SerialManagement.getInstance().getSeasonById(series.getSeasonId());
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					form.setAction(CONSTANTS.EDIT);
					form.setRequestType(CONSTANTS.SERIES);
					form.setTitle("Редактирование серии из сезона " + series.getSeriesTitle() + " сериала " + season.getSeasonTitle() + " ������� " + serial.getSerialTitle());
					form.setSeries(series);
					form.setSeason(season);
					form.setSerial(serial);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
		}
		if (CONSTANTS.DELETE == answer) {
			int id = Integer.parseInt(req.getParameter("id"));
			if (CONSTANTS.SERIAL == type) {
				try {
					Serial serial = SerialManagement.getInstance().getSerialById(id);
					form.setAction(CONSTANTS.DELETE);
					form.setRequestType(CONSTANTS.SERIAL);
					form.setTitle("Удаление сериала " + serial.getSerialTitle());
					form.setSerial(serial);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
			if (CONSTANTS.SEASON == type) {
				try {
					Season season = SerialManagement.getInstance().getSeasonById(id);
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					form.setAction(CONSTANTS.DELETE);
					form.setRequestType(CONSTANTS.SEASON);
					form.setTitle("Удаление сезона " + season.getSeasonTitle() + " из сериала " + serial.getSerialTitle());
					form.setSeason(season);
					form.setSerial(serial);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
			if (CONSTANTS.SERIES == type) {
				try {
					Series series = SerialManagement.getInstance().getSeriesById(id);
					Season season = SerialManagement.getInstance().getSeasonById(series.getSeasonId());
					Serial serial = SerialManagement.getInstance().getSerialById(season.getSerialId());
					form.setAction(CONSTANTS.DELETE);
					form.setRequestType(CONSTANTS.SERIES);
					form.setTitle("Удаление серии " + series.getSeriesTitle() + " сезона " + season.getSeasonTitle() + " из сериала " + serial.getSerialTitle());
					form.setSeries(series);
					form.setSeason(season);
					form.setSerial(serial);
				} catch (SQLException sql_e) {
					throw new IOException(sql_e.getMessage());
				}
			}
		}
		req.setAttribute("form", form);
		req.setAttribute("constants", new CONSTANTS());
		getServletContext().getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
		return;
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
		return null;
	}
}
