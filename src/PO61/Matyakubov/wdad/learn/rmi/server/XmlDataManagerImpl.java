package PO61.Matyakubov.wdad.learn.rmi.server;

import PO61.Matyakubov.wdad.data.model.Book;
import PO61.Matyakubov.wdad.data.model.Reader;
import PO61.Matyakubov.wdad.learn.xml.XmlTask;
import PO61.Matyakubov.wdad.data.model.*;
import PO61.Matyakubov.wdad.learn.rmi.XmlDataManager;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class XmlDataManagerImpl implements XmlDataManager, Serializable {
    private XmlTask xmlTask;

    XmlDataManagerImpl() throws RemoteException {
        xmlTask = new XmlTask();
    }


    public List<Reader> getReaders() throws RemoteException {
        return xmlTask.getReaders();
    }

    @Override
    public List<Reader> negligentReaders() throws RemoteException {
        return xmlTask.negligentReaders();
    }

    @Override
    public void removeBook(Reader reader, Book book)throws RemoteException {
        xmlTask.removeBook(reader,book);
    }

    @Override
    public void addBook(Reader reader, Book book) throws RemoteException {
        xmlTask.addBook(reader,book);
    }

    @Override
    public HashMap<Book, Date> getDateReturn(Reader reader) throws RemoteException {
        return (HashMap<Book, Date>) reader.getReturnList();
    }
}
