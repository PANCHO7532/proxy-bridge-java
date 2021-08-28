/*
 * /=======================================================\
 * | Java Proxy Bridge                                     |
 * | Copyright (c) P7COMunications LLC 2021 - PANCHO7532   |
 * |=======================================================/
 * |-> Purpose: Class with the sole task of transport bytes under a Threaded environment.
 * ---------------------------------------------------------
 */
package net.p7com.proxybridge.threads;
import java.io.InputStream;
import java.io.OutputStream;

public class TunnelThread implements Runnable {
	private int bufSize;
	private InputStream in;
	private OutputStream out;
	public TunnelThread(InputStream i, OutputStream o, int b) {
		in = i;
		out = o;
		bufSize = b;
	}
	public void run() {
		byte buffer[] = new byte[bufSize];
		try {
			while(true) {
				int bytesRead = in.read(buffer);
				if(bytesRead == -1) {
					System.out.println("[STREAM] Reached end of stream.");
					break;
				}
				out.write(buffer, 0, bytesRead);
			}
		} catch(Exception e) {
			System.out.println("[STREAM-ERR] Failed to read/write over socket");
		}
	}
}
