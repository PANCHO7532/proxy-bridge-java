/*
 * /=======================================================\
 * | Java Proxy Bridge                                     |
 * | Copyright (c) P7COMunications LLC 2021 - PANCHO7532   |
 * |=======================================================/
 * |-> Purpose: Pre-Staging of Sockets and build respective threads for buffers.
 * ---------------------------------------------------------
 */
package net.p7com.proxybridge;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import net.p7com.proxybridge.threads.TunnelThread;

public class ThreadBuilder implements Runnable {
	private Socket client, remote;
	private String destinationHost;
	private int destinationPort, bufSize;
	private InputStream remoteInbound, clientInbound;
	private OutputStream remoteOutbound, clientOutbound;
	public ThreadBuilder(Socket c, String dH, int dP, int bS) {
		client = c;
		destinationHost = dH;
		destinationPort = dP;
		bufSize = bS;
		try {
			remote = new Socket(destinationHost, destinationPort);
		} catch(Exception e) {
			System.out.println("[ERROR] Couldn't connect to " + destinationHost + ":" + destinationPort);
		}
	}
	public void run() {
		try {
			remoteInbound = remote.getInputStream();
			clientInbound = client.getInputStream();
			remoteOutbound = remote.getOutputStream();
			clientOutbound = client.getOutputStream();
			// Uncomment the following if you want to add a response text to be sent to the client.
			// clientOutbound.write(new String("HTTP/1.1 200 OK\r\n\r\n").getBytes());
			new Thread(new TunnelThread(remoteInbound, clientOutbound, bufSize)).start();
			new Thread(new TunnelThread(clientInbound, remoteOutbound, bufSize)).start();
		} catch (Exception e) {
			System.out.println("[ERROR] Couldn't get required streams.");
		}
		
	}
}
