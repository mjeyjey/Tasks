class Book {
    // private int booknum;
    private String title;
    private String author;
    private int yearPublish;
    private double price;
    // public Book(int booknum, String title, String author, int yearPublish, double price) {
    public Book(String title, String author, int yearPublish, double price) {
        // this.booknum = booknum;
        this.title = title;
        this.author = author;
        this.yearPublish = yearPublish;
        this.price = price;
    }
    
    // public void toString() {
        // System.out.println("\n\nBook "+ booknum + ":"+
        //                    "\nTitle: "+ title +
        //                    "\nAuthor: "+ author +
        //                    "\nYear: "+ yearPublish +
        //                    "\nPrice: $"+ price);
    @Override
    public String toString(){
        // return  "\n\nBook "+ booknum + ":"+
           return   "Title: "+ title +
                    "\nAuthor: "+ author +
                    "\nYear: "+ yearPublish +
                    "\nPrice: $"+ price;
    }
}

public class Task11 {
    public static void main(String []args) {
        Book[] book = new Book[] {
        //     new Book (1,"Java Programming", "John Smith", 2021, 39.99),
        //     new Book (2,"Python Basics", "Jane Doe", 2020, 29.99),
        //     new Book (3,"C++ Essentials", "Michael Johnson", 2019, 49.99),
        
            new Book ("Java Programming", "John Smith", 2021, 39.99),
            new Book ("Python Basics", "Jane Doe", 2020, 29.99),
            new Book ("C++ Essentials", "Michael Johnson", 2019, 49.99),
        };
        //   for (Book b : book) {
        //     // b.toString();
        //     System.out.println(b); 
        // }
        for (int b = 0; b < book.length; ++b  ) {
            System.out.println("\nBook " + (b + 1) + ":");
            System.out.println(book[b]);
        }
    }
}