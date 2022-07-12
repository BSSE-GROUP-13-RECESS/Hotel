# HOTEL MANAGEMENT SYSTEM 
> Please note that: <br>
> 1099 is any available port and one can employ theirs e.g 12080 <br>
> localhost is any host and one can employ theirs e.g 10.15.69.75 <br>
> 
> Navigate to src/main/java and compile all files, open terminal and type; <br>
> $ javac org/shared/RoomManager.java <br>
> $ javac org/server/RoomManagerImp.java <br>
> $ javac org/server/HotelServer.java <br>
> $ javac org/client/HotelClient.java <br>
> $ rmiregistry 1099
> 
> Open another terminal in the same directory and type; <br>
> $ java org.server.HotelServer localhost:1099 <br>
> 
>
> Open another terminal in the same directory, type the below statement and read its output; <br>
> $ java org.client.HotelClient <br>