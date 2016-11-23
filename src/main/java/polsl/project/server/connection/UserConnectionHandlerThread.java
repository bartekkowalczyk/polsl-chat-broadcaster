package polsl.project.server.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import polsl.project.server.concurrent.BroadcastService;
import polsl.project.server.domain.Message;

public class UserConnectionHandlerThread extends Thread
{
	private final Socket socket;
	private final BroadcastService broadcastService;

	public UserConnectionHandlerThread(final Socket socket, final BroadcastService broadcastService)
	{
		this.socket = socket;
		this.broadcastService = broadcastService;
	}

	@Override
	public void run()
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println("[DEBUG] InputStreamReader initialized");

			while (true)
			{
				final String line = in.readLine();

				if (line != null)
				{
					System.out.println("[DEBUG] socket read : " + line);
					broadcastService.sendToAllActivePeers(new Message(line)); //send to all
				}
			}
		}
		catch (IOException e)
		{
			throw new RuntimeSocketException();
		}
	}
}
