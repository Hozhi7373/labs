package PO61.Matyakubov.wdad;

import PO61.Matyakubov.wdad.data.managers.JDBCDataManager;

import java.rmi.RemoteException;

public class Application {
    public static void main(String[] args) throws RemoteException {
        JDBCDataManager jdbcDataManager = new JDBCDataManager();
        jdbcDataManager.getReaders().forEach(System.out::println);
//        jdbcDataManager.negligentReaders().forEach(System.out::println);
    }
}
