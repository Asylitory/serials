package serials.web.forms;

public class CONSTANTS {
	public static final int SERIAL = 1;
	public static final int SEASON = 2;
	public static final int SERIES = 3;
	
	public static final String NEW_ENTRY = "NEW_ENTRY";
	public static final String EDIT = "EDIT";
	public static final String DELETE = "DELETE";
	public static final String NEW_OK = "NEW_OK";
	public static final String NEW_CANCEL = "NEW_CANCEL";
	public static final String EDIT_OK = "EDIT_OK";
	public static final String DELETE_OK = "DELETE_OK";
	public static final String CANCEL = "CANCEL";
	
	private int serial = SERIAL;
	private int season = SEASON;
	private int series = SERIES;
	
	private String new_entry = NEW_ENTRY;
	private String edit = EDIT;
	private String delete = DELETE;
	private String new_ok = NEW_OK;
	private String new_cancel = NEW_CANCEL;
	private String edit_ok = EDIT_OK;
	private String delete_ok = DELETE_OK;
	private String cancel = CANCEL;
	
	public int getSerial() {
		return serial;
	}
	public int getSeason() {
		return season;
	}
	public int getSeries() {
		return series;
	}
	public String getNew_entry() {
		return new_entry;
	}
	public String getEdit() {
		return edit;
	}
	public String getDelete() {
		return delete;
	}
	public String getNew_ok() {
		return new_ok;
	}
	public String getNew_cancel() {
		return new_cancel;
	}
	public String getEdit_ok() {
		return edit_ok;
	}
	public String getDelete_ok() {
		return delete_ok;
	}
	public String getCancel() {
		return cancel;
	}
}
