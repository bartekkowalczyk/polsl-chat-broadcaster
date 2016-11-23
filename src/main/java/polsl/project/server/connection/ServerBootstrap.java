package polsl.project.server.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import polsl.project.server.concurrent.ConcurrentPeersManagementService;
import polsl.project.server.exception.RuntimeServerException;

/**
 * Class responsible for bootstrapping
 */
public class ServerBootstrap
{
	private final ConcurrentPeersManagementService concurrentPeersManagementService = //
			new ConcurrentPeersManagementService();

	/**
	 * Start new thread that will listen for incoming connection - and will wrap each new connection with corresponding
	 * thread responsible for handling input/output data.
	 *
	 * @throws RuntimeServerException when unable to start server.
	 */
	public void startListening(final int port)
	{
		new Thread(() -> {
			final ServerSocket serverSocket;
			try
			{
				serverSocket = new ServerSocket(port);

				while (true)
				{
					System.out.println("Waiting for connection...");

					final Socket newConnection = serverSocket.accept();

					//write test message
					PrintWriter out = new PrintWriter(newConnection.getOutputStream(), true);

					out.println("Hello from broadcastng server {ip:" + serverSocket.getInetAddress() //
							.getHostAddress() + ":" + serverSocket.getLocalPort() + "}");


					processNewConnection(newConnection);
				}
			}
			catch (IOException e)
			{
				throw new RuntimeServerException(e);
			}
		}).start();
	}

	private void processNewConnection(final Socket newConnection)
	{
		final UserConnectionHandlerThread userConnectionHandlerThread = //
				new UserConnectionHandlerThread(newConnection, concurrentPeersManagementService);

		userConnectionHandlerThread.start();

		concurrentPeersManagementService.addPeer(newConnection, userConnectionHandlerThread);
	}
}
