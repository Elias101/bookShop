package UnitTest;

import static org.junit.Assert.*;
import org.junit.Test;

import nodapo.Book;
import nodapo.Shop;
import nodapo.Book.genreOptions;
import nodapo.Customer;

public class MainTest {
	// preparation of testing environment
	Shop shop = new Shop("Hamlet's", new Book[] {}, 0);
	Shop shopToCompare = new Shop("Smith's", new Book[] {}, 0);
	Book book1 = new Book("The Lord of the Rings", genreOptions.fantasy, 50, 1800, "978-3608963762");
	Book book2 = new Book("The Great Gatsby", genreOptions.adventure, 35, 400, "978-3442267747");
	Book book3 = new Book("Around the World in Eighty Days", genreOptions.adventure, 13, 150, "978-3680437760");
	Book book4 = new Book("The Lord of the Rings", genreOptions.fantasy, 50, 1800, "978-3442267819");
	Book book5 = new Book("Long Walk to Freedom", genreOptions.biography, 15, 250, "978-758245159");
	Customer customer = new Customer("Ricky Tick", 60, new Book[] {});

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Set of required tests, each test method name (label) is indicitive to what the method is testing
	@Test
	public void addBookTest() {
		shop.addBook(book1);
		assertEquals(book1.getIsbn(), shop.getBooks()[shop.getBooks().length - 1].getIsbn());
	}

	@Test
	public void removeBookTest() {
		shop.addBook(book1);
		shop.removeBook(book1.getIsbn());
		assertArrayEquals(shop.getBooks(), new Book[] { null });
	}

	@Test
	public void saleTest() {
		shop.addBook(book1);
		shop.sale(customer, book1);
		assertArrayEquals(shop.getBooks(), new Book[] {});
		assertArrayEquals(customer.getOwnedBooks(), new Book[] { book1 });
		assertEquals(customer.getMoney(), 60 - book1.getPrice());
		assertEquals(shop.getSales(), book1.getPrice());
	}

	@Test
	public void filterByGenre() {
		shop.addBook(book1);
		shop.addBook(book2);
		shop.addBook(book3);
		Book[] filteredBooks = shop.filterByGenre(genreOptions.adventure);
		String[] resultBooksIsbn = { book2.getIsbn(), book3.getIsbn() };
		for (int i = 0; i < filteredBooks.length; i++) {
			assertEquals(filteredBooks[i].getIsbn(), resultBooksIsbn[i]);
		}
	}

	@Test
	public void removeDuplicates() {
		shop.addBook(book1);
		shop.addBook(book2);
		shop.addBook(book3);
		shop.addBook(book4);
		Book[] noDuplicates = shop.removeDuplicates();
		String[] resultBooksIsbn = { book1.getIsbn(), book2.getIsbn(), book3.getIsbn() };
		for (int i = 0; i < noDuplicates.length; i++) {
			assertEquals(noDuplicates[i].getIsbn(), resultBooksIsbn[i]);
		}
	}

	@Test
	public void compareStores() {
		shop.addBook(book1);
		shop.addBook(book2);
		shop.addBook(book3);
		shopToCompare.addBook(book4);
		shopToCompare.addBook(book5);
		Book[] comparison = shop.compare(shopToCompare);
		for (int i = 0; i < comparison.length; i += 2) {
			assertEquals(comparison[i].getTitle(), comparison[i + 1].getTitle());
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////
}
