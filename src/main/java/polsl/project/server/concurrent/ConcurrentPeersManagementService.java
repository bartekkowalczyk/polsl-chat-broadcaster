package polsl.project.server.concurrent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import polsl.project.server.connection.RuntimeSocketException;
import polsl.project.server.domain.Message;

public class ConcurrentPeersManagementService implements PeersManagementService, BroadcastService
{
	private final ConcurrentHashMap<Socket, Thread> socketToThread = new ConcurrentHashMap<>();

	//PeersManagementService

	@Override
	public void sendToAllActivePeers(final Message message)
	{
		//fixme, change to guard this with {@class Lock}
		synchronized (this)
		{
			socketToThread.keySet()//
					.forEach(socket -> {
						try
						{
							PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

							out.println(message.getMessage());

							System.out.println("[DEBUG] sent message to " + socket.getInetAddress().getHostAddress()  //
									+ ":" + socket.getPort());
						}
						catch (IOException e)
						{
							throw new RuntimeSocketException();
						}
					});
		}
	}

	//BroadcastService

	@Override
	public void addPeer(final Socket socket, final Thread thread)
	{
		socketToThread.put(socket, thread);
	}

	@Override
	public void removePeer(final Socket socket)
	{
		socketToThread.remove(socket);
	}
}
