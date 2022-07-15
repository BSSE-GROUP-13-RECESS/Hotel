package org.server;
import org.shared.RoomManager; //import RoomManager interface
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RoomManagerImp extends UnicastRemoteObject implements RoomManager {
    String[] roomTypes = {"single", "double", "twin", "triple", "quad"};
    int[] roomTotals = {10,20,5,3,2}; // number of rooms per type
    int[] roomPrices = {55000,75000,80000,150000,230000}; // prices of rooms per type
    int[] takenRooms = {0,0,0,0,0}; // number of rooms booked per type
    String[][] guestList = new String[5][]; // list of guests per type
    RoomManagerImp() throws RemoteException {
        super();
        // initialising guestList array with arrays to hold guests per type
        for (int i=0; i<5; i++) {
            guestList[i] = new String[roomTotals[i]];
        }
    }

    public String list() throws RemoteException { //returns list of available rooms per room type
        StringBuilder str = new StringBuilder();
        for (int i=0; i<5; i++) {
            str.append(roomTotals[i] - takenRooms[i]).append(" rooms of type ").append(i).append(" are available for ").append(roomPrices[i]).append(" UGX per night\n");
        }
        return str.toString();
    }

    public String book(int type, String name) throws RemoteException{ //books a room if available and returns notification to client
        if (type < 0 || type > 4)
            return "Room type does not exist";
        String str = "";
        if (takenRooms[type]<roomTotals[type]){
            guestList[type][takenRooms[type]++] = name;
            str+=("You have booked a "+roomTypes[type]+" room for "+name+" room at "+roomPrices[type]+" UGX for one night.\n");
        }
        else {
            str+=("Rooms of type "+type+" have all been occupied. Please choose a different type!\n");
        }
        return str;
    }
    
    public String guests() throws RemoteException{ //returns list of all guests per room type
        StringBuilder str = new StringBuilder();
        str.append("Order => Name, RoomType, RoomCategory\n");
        int sum = 0;
        for (int i=0; i<5; i++){
            sum+=takenRooms[i];
            for (int j=0; j<takenRooms[i]; j++) {
                str.append(guestList[i][j]).append(", ").append(i).append(", ").append(roomTypes[i]).append(" room\n");
            }
        }
        str.append("Total number of guests: ").append(sum).append("\n");
        return str.toString();
    }

    public String revenue() throws RemoteException{ //returns revenue breakdown based on the booked rooms and their types.
        StringBuilder str = new StringBuilder("Order => RoomType, RoomCategory, RoomsBooked, Price, Amount\n");
        for (int i=0; i<5; i++){
            str.append(i).append(", ").append(roomTypes[i]).append(" rooms, ").append(takenRooms[i]).append(", ").append(roomPrices[i]).append(", ").append(roomPrices[i] * takenRooms[i]).append(" UGX\n");
        }
        return str.toString();
    }
}
