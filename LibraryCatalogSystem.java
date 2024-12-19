import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + year + ")";
    }
}

class LibraryCatalog {
    private ArrayList<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author, int year) {
        Book book = new Book(title, author, year);
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void searchBooks(String query) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }
        if (results.isEmpty()) {
            System.out.println("\nNo books found matching your query.");
        } else {
            System.out.println("\nSearch results:");
            for (Book book : results) {
                System.out.println("- " + book);
            }
        }
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books in the catalog.");
        } else {
            System.out.println("\nBooks in the catalog:");
            for (Book book : books) {
                System.out.println("- " + book);
            }
        }
    }
}

public class LibraryCatalogSystem {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Catalog System");
            System.out.println("1. Add Book");
            System.out.println("2. Search Books");
            System.out.println("3. List All Books");
            System.out.println("4. Exit");

            System.out.print("Choose an option (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the publication year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    catalog.addBook(title, author, year);
                    break;
                case "2":
                    System.out.print("Enter a title or author to search: ");
                    String query = scanner.nextLine();
                    catalog.searchBooks(query);
                    break;
                case "3":
                    catalog.listBooks();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
