package PO61.Matyakubov.wdad.learn.rmi.client;

import PO61.Matyakubov.wdad.learn.xml.managers.PreferencesManager;
import PO61.Matyakubov.wdad.learn.rmi.XmlDataManager;
import PO61.Matyakubov.wdad.utils.PreferencesManagerConstants;
import PO61.Matyakubov.wdad.data.model.*;
import PO61.Matyakubov.wdad.learn.rmi.*;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private static final String BIND_NAME = "XmlDataManager";
    private static int port;
    private static String address;


    public static void main(String... args) {
        PreferencesManager pm = PreferencesManager.getInstance();
        address = pm.getProperty(PreferencesManagerConstants.registryaddress);
        port = Integer.parseInt(pm.getProperty(PreferencesManagerConstants.registryport));
        System.setProperty("java.security.policy", pm.getProperty(PreferencesManagerConstants.policypath));
        System.setProperty("java.rmi.server.useCodebaseOnly", pm.getProperty(PreferencesManagerConstants.usecodebaseonly));
        System.setSecurityManager(new RMISecurityManager());
        try {
            System.out.print("Authorization...");
            Registry registry = LocateRegistry.getRegistry(address, port);
            XmlDataManager service = (XmlDataManager) registry.lookup(BIND_NAME);
            System.out.println(" OK");
            test(service);
        } catch (Exception e) {
            System.out.println(" fail");
            e.printStackTrace();
        }
    }

    private static void test(XmlDataManager xmlDataManager) throws RemoteException {
        xmlDataManager.getReaders().forEach(System.out::println);
    }

}
