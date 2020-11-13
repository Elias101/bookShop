package nodapo;

public class Customer {
	private String name;
	private int money;
	private Book[] ownedBooks;

	///////////////////////////////////////////////////////////
	// Constructor
	public Customer(String name, int money, Book[] ownedBooks) {
		this.name = name;
		this.money = money;
		this.ownedBooks = ownedBooks;
	}

	////////////////////////////////////////////////////////////
	// getters
	public String getName() {
		return this.name;
	}

	public int getMoney() {
		return this.money;
	}

	public Book[] getOwnedBooks() {
		return this.ownedBooks;
	}

	//////////////////////////////////////////////////////////////////////////
	// method to make payment from a customer's money while adding the bough book
	public void pay(Book book) {
		this.money -= book.getPrice();
		this.ownedBooks = new Book[this.ownedBooks.length + 1];
		this.ownedBooks[this.ownedBooks.length - 1] = book;
	}
}
