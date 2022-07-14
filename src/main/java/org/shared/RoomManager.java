package org.shared; //shared package between client and server
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RoomManager extends Remote{
    String list () throws RemoteException; //returns list of available rooms
    String book(int type, String name) throws RemoteException; //books a room if available and returns notification to client
    String guests() throws RemoteException; //returns list of all guests
    String revenue() throws RemoteException; //returns revenue breakdown based on the booked rooms and their types.
}
