package org.client;
import org.shared.RoomManager;
import java.rmi.Naming;

public class HotelClient {
    static void printCommands(){
        System.out.println("usage HotelClient [options]\n " +
                "--------------------Note--------------------\n"+
                "<server address> is written like address:port e.g localhost:1099\n"+
                "<room type> in ranges 0 to 4\n"+
                "<guest name> names separated by '_' e.g Elon_Musk\n"+
                "--------------------------------------------\n"+
                "Below is a list of options and what they do.\n"+
                "list <server address> => list the available number of rooms in each price range.\n"+
                "book <server address> <room type> <guest name> => books a room of the specified type (if available), and registers the name of the guest.\n"+
                "guests <server address> => list the names of all the registered guests.\n"+
                "revenue <server address> => prints the revenue breakdown based on the booked rooms and their types.\n");
    }

    public static void main(String[] args){
        if (args.length<2){
            printCommands();
            return;
        }

        String[] validCommands = {"list", "guests", "revenue", "book"};
        String command = null;
        for (String comm:validCommands){
            if (comm.equals(args[0])){
                command = args[0];
                break;
            }
        }
        if (command==null){
            System.out.println("Invalid Command supplied\n");
            printCommands();
            return;
        }
        try {
            RoomManager room = (RoomManager) Naming.lookup("rmi://"+args[1]+"/hotelService");
            if (command.equals("book")){
                System.out.println(room.book(args[2], args[3]));
                return;
            }
            System.out.println(room.getClass().getMethod(command).invoke(room));
        }
        catch (Exception e){
            System.out.println("Received exception: "+e);
        }
    }
}
