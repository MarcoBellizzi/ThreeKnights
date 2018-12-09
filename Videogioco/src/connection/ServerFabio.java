package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerFabio {
	
private static ServerSocket serverSocket ;

public static LinkedList<Socket> clientList = new LinkedList<Socket>();
public static LinkedList<PrintWriter> writerList = new LinkedList<PrintWriter>();

    public static void main(String[] args) {
    	try {
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void start() throws IOException {
        serverSocket = new ServerSocket(8187);
        while(true) {
            Socket client = serverSocket.accept();
            clientList.add(client);
            writerList.add(new PrintWriter(client.getOutputStream()));
            Thread thread = new ClientHandler(client,clientList,writerList);
            thread.start();
        }
    }

}
