package jewelry.bean;


import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class stats {
	private int type ;
	private int year ;
	private int month;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}
