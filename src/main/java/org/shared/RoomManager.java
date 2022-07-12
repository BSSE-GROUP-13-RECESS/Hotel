package org.shared;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RoomManager extends Remote{
    String list () throws RemoteException;
    String book(String type, String name) throws RemoteException;

    String guests() throws RemoteException;

    String revenue() throws RemoteException;
}
