package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;


public class ClientHandler extends Thread {
	private Socket client;
	private LinkedList<Socket> clients, clientsToRemove;
	private LinkedList<PrintWriter> writers, writersToRemove;

	public ClientHandler(Socket client, LinkedList<Socket> clients,
			LinkedList<PrintWriter> writers) {
		this.client = client;
		this.clients = clients;
		this.writers = writers;
		clientsToRemove = new LinkedList<Socket>();
		writersToRemove = new LinkedList<PrintWriter>();
	}

	@Override
	public void run() {
		super.run();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			String line = in.readLine();
			while (line != null) {
				line = in.readLine();
				synchronized (clients) {
					int i = 0;
					for (Socket actualClient : clients) {
						if (actualClient == null) {
							clientsToRemove.add(actualClient);
							synchronized (writers) {
								writersToRemove.add(writers.get(i));
							}
						} else {
							synchronized (writers) {
								if (!client.equals(actualClient)) {
									writers.get(i).println(line);
									writers.get(i).flush();
								}
							}
						}
						i++;
					}
					clients.removeAll(clientsToRemove);
					synchronized (writers) {
						writers.removeAll(writersToRemove);
					}
				}
			}
		} catch (IOException e) {
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
