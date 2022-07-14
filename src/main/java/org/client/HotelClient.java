package org.client;
import org.shared.RoomManager; //import shared interface
import java.rmi.Naming;

public class HotelClient { //Client Class
    static void printUsage(){ // prints usage of the client program
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
        if (args.length<2){ //check if number of arguments supplied < 2 and if true print usage
            printUsage();
            return;
        }

        String[] validCommands = {"list", "guests", "revenue", "book"}; //list of valid commands
        String command = null;
        for (String comm:validCommands){ //loop to check if command at argument index 0 is in the valid commands else it is null
            if (comm.equals(args[0])){
                command = args[0];
                break;
            }
        }
        if (command==null){ //if command is null print usage
            System.out.println("Invalid Command supplied\n");
            printUsage();
            return;
        }
        try {
            //fetching object from server and casting it to RoomManager, argument at index 1 is used as server address
            RoomManager room = (RoomManager) Naming.lookup("rmi://"+args[1]+"/hotelService");
            if (command.equals("book")){ // if command is book, call room.book and print its output
                System.out.println(room.book(Integer.parseInt(args[2]), args[3]));
                return;
            }
            // call method by its name, using the command string and print its output (works for methods list, revenue and guests which don't take parameters)
            System.out.println(room.getClass().getMethod(command).invoke(room));
        }
        catch (Exception e){ //catch any exception and print it
            System.out.println("Received exception: "+e);
        }
    }
}
