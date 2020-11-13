package main;

import nodapo.Book;
import nodapo.Book.genreOptions;
import nodapo.Customer;
import nodapo.Shop;

/*
 * 
 * 
 * 
 * 
 * 
 *         /////       This Main Class was done for debugging & demo purposes		/////
 * 
 * 
 * 
 * 
 */

public class Main {

	public static void main(String[] args) {
		Book book1 = new Book("The Lord of the Rings", genreOptions.fantasy, 50, 1800, "978-3608963762");
		Book book2 = new Book("The Great Gatsby", genreOptions.adventure, 35, 400, "978-3442267747");
		Book book3 = new Book("The Diary of a Nobody", genreOptions.comic, 12, 165, "978-3841335180");
		Book book4 = new Book("The Lord of the Rings", genreOptions.fantasy, 50, 1800, "978-3442267819");
		Book book5 = new Book("Long Walk to Freedom", genreOptions.biography, 15, 250, "978-758245159");
		Book book6 = new Book("Around the World in Eighty Days", genreOptions.adventure, 13, 150, "978-3680437760");
		Book book7 = new Book("Treasure Island", genreOptions.adventure, 18, 350, "978-3680437760");
		Shop shop1 = new Shop("Hamlet's", new Book[] { book1, book2, book4 }, 0);
		Shop shop2 = new Shop("Smith's", new Book[] { book3, book5 }, 0);

		Customer customer1 = new Customer("John Doe", 60, new Book[] { book6, book4 });

		shop1.printShopBooks();
		shop2.printShopBooks();

		System.out.println("----------------------------------");
		shop1.compare(shop2);
		System.out.println("----------------------------------");

		System.out.println("----------------------------------");
		shop1.filterByGenre(genreOptions.adventure);
		System.out.println("----------------------------------");

		System.out.println("Duplicates----------------------------------");
		shop1.removeDuplicates();
		System.out.println("----------------------------------");

		shop1.printShopBooks();

		System.out.println("----------------------------------");
		shop1.addBook(book7);
		System.out.println("----------------------------------");

		shop1.printShopBooks();
		shop2.printShopBooks();

		System.out.println("----------------------------------");
		shop1.removeBook("978-3442267747");
		System.out.println("----------------------------------");

		shop1.printShopBooks();
		shop2.printShopBooks();

		System.out.println("----------------------------------");
		shop1.addBook(book2);
		System.out.println("----------------------------------");

		shop1.printShopBooks();
		shop2.printShopBooks();

		System.out.println("----------------------------------");
		shop1.sale(customer1, book1);
		System.out.println("Customer money left: " + customer1.getMoney() + " ---Shop Sales:" + shop1.getSales());
		shop1.sale(customer1, book2);
		System.out.println("----------------------------------");

		shop1.printShopBooks();
	}
}
