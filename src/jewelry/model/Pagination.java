package jewelry.model;

public class Pagination {
	private int page = 1;
	private int perPage = 12;

	public Pagination() {
		// TODO Auto-generated constructor stub
	}

	public Pagination(int page, int perPage) {
		super();
		this.page = page;
		this.perPage = perPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

}
