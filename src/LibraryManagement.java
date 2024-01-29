import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagement {

    public static List<Book> bookList = new ArrayList<>();
    public static String filePath = "catalog.txt";

    public LibraryManagement() {}

    /**
     * add new book to the collection [bookList]
     */
    public void addNewBook() {
        List<Book> books = readBooksFromCatalog(filePath);
        if(books.isEmpty()) {
            System.out.println("No Book found in the catalog !");
        }
        else {
            for(Book book : books) {
                if(findBookById(book.getId()) == null) { //book not in the collection
                    bookList.add(book);
                    System.out.println("Book added to the collection.");
                    break;
                }
            }
        }
    }

    /**
     * delete book with the id from the collection
     */
    public void deleteBook(long id) {
        if(!bookList.isEmpty()){
            Book book = findBookById(id);
            if(book != null) {
                bookList.remove(book);
                System.out.println("Book deleted from collection.");
            }
            else {
                System.out.println("Book not found in the collection !");
            }
        }
        else {
            System.out.println("Collection is empty !");
        }
    }

    private Book findBookById(long id) {
        for (Book book : bookList) {
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    /**
     * print all books in the collection
     */
    public void printAllBooks() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    /**
     * read all books from catalog.txt file and add it to the collection : bookList
     */
    private List<Book> readBooksFromCatalog(String filePath) {
        try {
            InputStream inputStream = LibraryManagement.class.getResourceAsStream(filePath);

            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                List<Book> books = new ArrayList<>();

                while ((line = bufferedReader.readLine()) != null) {
                    // Split the line into fields using a comma as the separator
                    String[] fields = line.split(",");

                    // Assuming the format is: id, title, author
                    if (fields.length == 3) {
                        long id = Long.parseLong(fields[0]);
                        String title = fields[1];
                        String author = fields[2];
                        books.add(new Book(id, title, author));
                    } else {
                        System.err.println("Invalid line format: " + line);
                    }
                }
                // Close the BufferedReader
                bufferedReader.close();
                return books;
            } else {
                System.err.println("File not found: " + filePath);
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
