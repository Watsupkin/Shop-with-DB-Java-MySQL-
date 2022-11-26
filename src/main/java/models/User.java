package models;

public class User {
	private int userId;
	private int role;
	private String login;
	private String pass;
	private String fio;
	private String phone;
	
	public User(int userId, int role, String login, String pass, String fio, String phone) {
		super();
		this.userId = userId;
		this.role = role;
		this.login = login;
		this.pass = pass;
		this.fio = fio;
		this.phone = phone;
	}
	public int getUserId() {
		return userId;
	}
	public int getRole() {
		return role;
	}
	public String getLogin() {
		return login;
	}
	public String getPass() {
		return pass;
	}
	public String getFio() {
		return fio;
	}
	public String getPhone() {
		return phone;
	}
}
