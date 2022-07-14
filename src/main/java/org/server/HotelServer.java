package org.server;
import java.rmi.Naming;

public class HotelServer { // server class
    public HotelServer(String address){ //constructor method
        try {
            RoomManagerImp room = new RoomManagerImp(); //instantiating RoomManager Class

            // binding the object (room) to url => rmi://address/hotelService where address is the argument passed
            Naming.rebind("rmi://"+address+"/hotelService", room);
            System.out.println("HotelServer started successfully!");
        }
        catch (Exception e){ // catch any exception and print it at the server side
            System.out.println("Trouble: _" + e);
        }
    }
    public static void main(String[] args){
        // check if no command line argument was passed and ask user to pass the address of the server
        if (args.length==0){
            System.out.println("please attach the right address when using this server e.g java HotelServer localhost:1099");
            return;
        }
        new HotelServer(args[0]); // passing the address to server constructor determining its address and port
    }
}
