package org.server;
import java.rmi.Naming;

public class HotelServer {
    public HotelServer(String address){
        try {
            RoomManagerImp room = new RoomManagerImp();
            Naming.rebind("rmi://"+address+"/hotelService", room);
            System.out.println("HotelServer started successfully!");
        }
        catch (Exception e){
            System.out.println("Trouble: _" + e);
        }
    }
    public static void main(String[] args){
        if (args.length==0){
            System.out.println("please attach the right address when using this server e.g java HotelServer localhost:1099");
            return;
        }
        new HotelServer(args[0]);
    }
}
