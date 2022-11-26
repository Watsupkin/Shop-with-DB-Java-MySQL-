package models;

public class Good {
	private int id;
	private String title;
	private int price;
	private String info;
	private String img;
	
	public Good(int id, String title, int price, String info, String img) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.info = info;
		this.img = img;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public String getInfo() {
		return info;
	}

	public String getImg() {
		return img;
	}
	
	@Override
	public String toString() {
		return title + " стоит " + price;
	}
	
}
