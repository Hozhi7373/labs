package PO61.Matyakubov.wdad.learn.rmi.server;

import PO61.Matyakubov.wdad.utils.PreferencesManagerConstants;
import PO61.Matyakubov.wdad.data.managers.PreferencesManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server {

    private static final String BIND_NAME = "DataManager";
    private static int port;
    private static String address;
    private static ShutdownHook shutdownHook;
    private static PreferencesManager pm;

    public static void main(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        shutdownHook = new ShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        pm = PreferencesManager.getInstance();
        port = Integer.parseInt(pm.getProperty(PreferencesManagerConstants.registryport));
        address = pm.getProperty(PreferencesManagerConstants.registryaddress);
        System.setProperty("java.security.policy", pm.getProperty(PreferencesManagerConstants.policypath));
        System.setProperty("java.rmi.server.useCodebaseOnly", pm.getProperty(PreferencesManagerConstants.usecodebaseonly));
        System.setProperty("java.rmi.server.hostname", address);
        System.setSecurityManager(new SecurityManager());
        try {
            System.out.print("Starting registry...");
            final Registry registry;
            if (pm.getProperty(PreferencesManagerConstants.createregistry).equals("yes")) {
                registry = LocateRegistry.createRegistry(port);
                System.out.println(" OK");
            }
            else {
                registry = LocateRegistry.getRegistry(port);
                System.out.println(" OK");
            }

            final DataManagerImpl service = new DataManagerImpl();
            UnicastRemoteObject.exportObject(service, 0);

            System.out.print("Binding service...");
            registry.bind(BIND_NAME, service);
            System.out.println(" OK");

            pm.addBindedObject(BIND_NAME,"DataManager");

            System.out.println("Server working...");

        } catch (Exception e) {
            System.out.println(" fail");
            e.printStackTrace();
            System.exit(0);
        }
        while (true) {
            switch (scanner.nextLine()){
                case "quit": System.exit(0);
            }
        }
    }

    static class ShutdownHook extends Thread {
        public void run() {
            pm.removeBindedObject(BIND_NAME);
        }
    }

}
