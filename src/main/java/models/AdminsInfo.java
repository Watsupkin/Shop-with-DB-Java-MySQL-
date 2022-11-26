package models;

import java.util.Date;

public class AdminsInfo {
	private int userId;
	private int orderId;
	private String fio;
	private String phone;
	private Date dateOfOrder;
	private int sumOfOrder;
	private String status;
	public AdminsInfo(int userId, int orderId, String fio, String phone, Date dateOfOrder, int sumOfOrder,
			String status) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.fio = fio;
		this.phone = phone;
		this.dateOfOrder = dateOfOrder;
		this.sumOfOrder = sumOfOrder;
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public String getFio() {
		return fio;
	}
	public String getPhone() {
		return phone;
	}
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public int getSumOfOrder() {
		return sumOfOrder;
	}
	public String getStatus() {
		return status;
	}
	
}
