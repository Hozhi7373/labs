package PO61.Matyakubov.wdad.learn.xml;

import PO61.Matyakubov.wdad.data.model.Author;
import PO61.Matyakubov.wdad.data.model.Book;
import PO61.Matyakubov.wdad.data.model.Genre;
import PO61.Matyakubov.wdad.data.model.*;

import java.rmi.RemoteException;

public class TestXmlTask {
    public static void main(String[] args) throws RemoteException {
        XmlTask task = new XmlTask();
        Book book = new Book();
        book.setAuthor(new Author("wwwe","weee"));
        book.setGenre(Genre.EPOPEE);
        book.setName("ttt");
        book.setPrintYear(1999);
        book.setTakeDate("05.01.2018");
//        task.addBook(XmlTask.getReaders().get(1),book);
//        task.removeBook(XmlTask.getReaders().get(1),book);
        task.negligentReaders().forEach(System.out::println);
    }
}
