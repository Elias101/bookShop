package nodapo;

import nodapo.Book.genreOptions;

public class Shop {
	private String name;
	private Book[] books;
	private int sales;
	private String[] validIsbn = { "978-3608963762", "978-3442267747", "978-758245159", "978-3841335180",
			"978-3442267819" };

	// Constructor
	public Shop(String name, Book[] books, int sales) {
		this.name = name;
		this.books = books;
		this.sales = sales;
	}

	// Getters///////////////////
	public String getName() {
		return this.name;
	}

	public Book[] getBooks() {
		return this.books;
	}

	public int getSales() {
		return this.sales;
	}

	public String[] getValidIsbn() {
		return this.validIsbn;
	}
	//////////////////////////////////////////////////////////////////////

	// method to add a book to the shop's archive
	public void addBook(Book book) {
		boolean valid = false;
		Book[] tmpBooks = new Book[this.books.length + 1];
		for (int i = 0; i < this.books.length; i++) {
			tmpBooks[i] = this.books[i];
		}
		for (String s : this.validIsbn) {
			if (book.getIsbn() == s) {
				valid = true;
				break;
			}
		}
		if (valid == true) {
			tmpBooks[tmpBooks.length - 1] = new Book(book.getTitle(), book.getGenre(), book.getPrice(), book.getPages(),
					book.getIsbn());
			this.books = tmpBooks;
			System.out.println(tmpBooks[tmpBooks.length - 1].getTitle() + " was added to stock!");
		} else {
			System.out.println("Added book's ISBN is invalid! adding prohibited.");
		}
	}

	////////////////////////////////////////////////////////////////////////
	// method to remove a book from the shop's archive by providing the ISBN
	public void removeBook(String isbn) {
		int i = 0;
		boolean found = false;
		for (Book b : this.books) {
			if (b != null) {
				if (b.getIsbn() == isbn) {
					found = true;
					this.books[i] = this.books[this.books.length - 1];
					this.books[this.books.length - 1] = null;
					System.out.println("A book was removed from archive!\n" + b.getIsbn());
					Book[] tmpbooks = new Book[this.books.length - 1];
					for (int j = 0; j < i; j++) {
						tmpbooks[j] = this.books[j];
						this.books = tmpbooks;
					}
				}
			}
			i++;
		}
		if (!found) {
			System.out.println("No Such Book in Archive!");
		}
	}

	////////////////////////////////////////////////////////////////////
	// method to execute a sale
	public void sale(Customer customer, Book book) {
		boolean available = false;
		for (int i = 0; i < this.books.length; i++) {
			if (this.books[i].getIsbn() == book.getIsbn() && this.books[i] != null) {
				available = true;
				break;
			}
		}
		if (available) {
			if (book.getPrice() < customer.getMoney()) {
				customer.pay(book);
				this.sales += book.getPrice();
				this.removeBook(book.getIsbn());
				if (this.books[this.books.length - 1] == null) {
					Book[] tmpBooks = new Book[this.books.length - 1];
					for (int j = 0; j < this.books.length - 1; j++) {
						tmpBooks[j] = this.books[j];
					}
					this.books = tmpBooks;
				}
				System.out.println(book.getTitle() + " was sold to: " + customer.getName());
			} else {
				System.out.println("Customer's money is not enough to make transaction!");
			}
		} else {
			System.out.println("Book requested is currently unavailable!");
		}
	}

	/////////////////////////////////////////////////////////////////////
	// method to filter books in a shop by genre
	public Book[] filterByGenre(genreOptions genre) {
		int i = 0;
		Book[] filteredBooks = new Book[0];
		for (Book b : this.books) {
			if (b.getGenre() == genre) {
				Book tmpBooks[] = new Book[filteredBooks.length + 1];
				for (int j = 0; j < filteredBooks.length; j++) {
					tmpBooks[j] = filteredBooks[j];
				}
				filteredBooks = tmpBooks;
				if (b != null) {
					filteredBooks[i] = b;
					i++;
				}
			}

		}
		System.out.println("Filtered by Genre:\n");
		for (Book b : filteredBooks) {
			if (b != null) {
				b.printBook();
			} else {
				System.out.println("No Results Found!");
			}
		}
		return filteredBooks;
	}

	///////////////////////////////////////////////////////////////////////
	// method to return results without duplicates
	public Book[] removeDuplicates() {
		Book[] noDuplicates = new Book[1];
		Book[] books = this.books;
		Book[] tmpBooks = new Book[1];
		boolean found = false;

		for (Book b : books) {
			if (noDuplicates[0] == null) {
				noDuplicates[0] = b;
				continue;
			}
			for (int i = 0; i < noDuplicates.length; i++) {
				if (b != null && noDuplicates[i].getTitle() == b.getTitle() && noDuplicates[i].getIsbn() != b.getIsbn())
					found = true;
				if (found)
					break;
			}
			if (!found) {
				tmpBooks = new Book[noDuplicates.length + 1];
				for (int i = 0; i < noDuplicates.length; i++) {
					tmpBooks[i] = noDuplicates[i];
				}
				tmpBooks[tmpBooks.length - 1] = b;
				noDuplicates = tmpBooks;
			}
		}
		for (Book b : noDuplicates) {
			b.printBook();
		}
		return noDuplicates;
	}

	////////////////////////////////////////////////////////////////////////
	// method to compare archives of two shops and combining them in pairs
	public Book[] compare(Shop shopToCompare) {
		Book[] comparison = new Book[0];
		Book[] tmpBooks = new Book[0];
		for (Book b : shopToCompare.getBooks()) {
			for (int i = 0; i < this.books.length; i++) {
				if (b != null && b.getTitle() == this.books[i].getTitle()) {
					if (comparison.length == 0) {
						comparison = new Book[2];
						comparison[0] = b;
						comparison[1] = this.books[i];
						continue;
					}
					tmpBooks = new Book[comparison.length + 2];
					for (int j = 0; j < comparison.length; j++) {
						tmpBooks[j] = comparison[j];
					}
					tmpBooks[tmpBooks.length - 2] = b;
					tmpBooks[tmpBooks.length - 1] = this.books[i];
					comparison = tmpBooks;
				}
			}
		}
		return comparison;
	}

	//////////////////////////////////////////////////////////////////////
	// method to print all books currently available in a shop
	public void printShopBooks() {
		System.out.println("books of the " + this.getName() + " shop:");
		for (Book b : this.books) {
			if (b != null) {
				b.printBook();
			} else if (this.books.length == 0) {
				System.out.println("Shop currently has no books");
			}
		}
	}
}
