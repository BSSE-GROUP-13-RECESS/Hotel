package org.server;
import org.shared.RoomManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class RoomManagerImp extends UnicastRemoteObject implements RoomManager {
    String[] roomTypes = {"single", "double", "twin", "triple", "quad"};
    int[] roomTotals = {10,20,5,3,2};
    int[] roomPrices = {55000,75000,80000,150000,230000};
    int[] takenRooms = {0,0,0,0,0};
    String[][] guestList = new String[5][];
    RoomManagerImp() throws RemoteException {
        super();
        for (int i=0; i<5; i++){
            guestList[i] = new String[roomTotals[i]];
        }
    }

    @Override
    public String list() throws RemoteException {
        StringBuilder str = new StringBuilder();
        for (int i=0; i<5; i++){
            str.append(roomTotals[i] - takenRooms[i]).append(" rooms of type ").append(i).append(" are available for ").append(roomPrices[i]).append(" UGX per night\n");
        }
        return str.toString();
    }

    public String book(String type, String name) throws RemoteException{
        int typeInt = Integer.parseInt(type);
        String str = "";
        if (takenRooms[typeInt]<roomTotals[typeInt]){
            guestList[typeInt][takenRooms[typeInt]++] = name;
            str+=("You ("+name+") have booked a "+roomTypes[typeInt]+" room at "+roomPrices[typeInt]+" UGX for one night. Thanks\n");
        }else{
            str+=("Hello "+name+", rooms of type "+type+" have all been occupied. Please choose a different type!\n");
        }
        return str;
    }
    
    public String guests() throws RemoteException{
        StringBuilder str = new StringBuilder();
        str.append("Order => Name, RoomType, RoomCategory\n");
        int sum = 0;
        for (int i=0; i<5; i++){
            sum+=takenRooms[i];
            for (int j=0; j<takenRooms[i]; j++){
                str.append(guestList[i][j]).append(", ").append(i).append(", ").append(roomTypes[i]).append(" room\n");
            }
        }
        str.append("Total number of guests: ").append(sum).append("\n");
        return str.toString();
    }

    public String revenue() throws RemoteException{
        StringBuilder str = new StringBuilder("Order => RoomType, RoomCategory, RoomsBooked, Price, Amount\n");
        for (int i=0; i<5; i++){
            str.append(i).append(", ").append(roomTypes[i]).append(" rooms, ").append(takenRooms[i]).append(", ").append(roomPrices[i]).append(", ").append(roomPrices[i] * takenRooms[i]).append(" UGX\n");
        }
        return str.toString();
    }
}
