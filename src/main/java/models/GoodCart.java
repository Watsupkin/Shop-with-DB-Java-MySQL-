package models;

public class GoodCart {
	private int good_id;
	private int count;
	private int user_id;
	private int price;
	private String title;
	private int sum;
	public GoodCart(int good_id, int count, int user_id, int price, String title, int sum) {
		this.good_id = good_id;
		this.count = count;
		this.user_id = user_id;
		this.price = price;
		this.title = title;
		this.sum = sum;
	}
	public int getGood_id() {
		return good_id;
	}
	public int getCount() {
		return count;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getPrice() {
		return price;
	}
	public String getTitle() {
		return title;
	}
	public int getSum() {
		return sum;
	}
	
	
}
