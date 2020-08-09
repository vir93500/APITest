package enums;

public enum LogType {
	Request("Request to execute"), Response("API execution response"), DB("Db result");

	private String log;

	private LogType(String log) {
		this.log = log;
	}

	public String getValue() {
		return this.log;
	}
}
