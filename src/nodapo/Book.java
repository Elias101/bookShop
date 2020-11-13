package nodapo;

public class Book {
	private String title;
	private int price;
	private int numOfPages;
	private String isbn;

	//defining the Genre options given in the task description
	public enum genreOptions {
		adventure, biography, comic, fantasy
	};

	private genreOptions genre;

	// Constructor
	public Book(String title, genreOptions genre, int price, int numOfPages, String isbn) {
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.numOfPages = numOfPages;
		this.isbn = isbn;
	}

	// Getters
	public String getTitle() {
		return this.title;
	}

	public genreOptions getGenre() {
		return this.genre;
	}

	public int getPrice() {
		return this.price;
	}

	public int getPages() {
		return this.numOfPages;
	}

	public String getIsbn() {
		return this.isbn;
	}

	////////////////////////////////////////////////////
	// method to print a book entity
	public void printBook() {
		System.out.println("title: " + this.getTitle() + " --genre: " + this.getGenre().toString() + " --price: "
				+ this.getPrice() + " --ISBN: " + this.getIsbn());
	}
}
