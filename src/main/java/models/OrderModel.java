package models;

import java.util.Date;

public class OrderModel {
	private int orderId;
	private Date dateOfOrder;
	private int userId;
	private int sumOfOrder;
	private String status;
	
	public OrderModel(int orderId, Date dateOfOrder, int userId, int sumOfOrder, String status) {
		super();
		this.orderId = orderId;
		this.dateOfOrder = dateOfOrder;
		this.userId = userId;
		this.sumOfOrder = sumOfOrder;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public int getUserId() {
		return userId;
	}

	public int getSumOfOrder() {
		return sumOfOrder;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "OrderModel [orderId=" + orderId + ", dateOfOrder=" + dateOfOrder + ", userId=" + userId
				+ ", sumOfOrder=" + sumOfOrder + ", status=" + status + "]";
	}
	
}
