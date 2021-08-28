/*
 * /=======================================================\
 * | Java Proxy Bridge                                     |
 * | Copyright (c) P7COMunications LLC 2021 - PANCHO7532   |
 * |=======================================================/
 * |-> Purpose: Main Class
 * ---------------------------------------------------------
 */
package net.p7com.proxybridge;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClass {
	private static String destinationHost = "127.0.0.1";
	private static int destinationPort = 8080, mainPort = 8888, bufsize = 8192;
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int c = 0; c < args.length; c++) {
			switch(args[c]) {
			case "-dhost":
				destinationHost = args[c+1];
				break;
			case "-dport":
				destinationPort = Integer.parseInt(args[c+1]);
				break;
			case "-mport":
				mainPort = Integer.parseInt(args[c+1]);
				break;
			case "-bufsize":
				bufsize = Integer.parseInt(args[c+1]);
				break;
			}
		}
		try {
			ServerSocket localServer = new ServerSocket(mainPort);
			System.out.println("[INFO] Server started on port " + mainPort);
			System.out.println("[INFO] Redirecting requests to " + destinationHost + " at port " + destinationPort);
			while(true) {
				Socket client = localServer.accept();
				System.out.println("[INFO] Connection received from " + client.getInetAddress() + ":" + client.getPort());
				new Thread(new ThreadBuilder(client, destinationHost, destinationPort, bufsize)).start();
			}
		} catch(Exception e) {
			System.out.println("[ERROR] An error occured while creating the server.");
			System.exit(1);
		}
	}
}