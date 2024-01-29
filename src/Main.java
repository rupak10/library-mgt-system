public class Main {

    public static void main(String[] args) {
        /**
         * Currently there are 3 books in the catalog [catalog.txt file]
         */

        LibraryManagement lms = new LibraryManagement();
        lms.addNewBook(); // add book to the collection
        lms.addNewBook(); // add book to the collection
        lms.printAllBooks(); // print all books in the collection
        lms.deleteBook(1); // delete book with id : 1 from the collection
        lms.printAllBooks(); // print all books in the collection after deleting the book
    }

}