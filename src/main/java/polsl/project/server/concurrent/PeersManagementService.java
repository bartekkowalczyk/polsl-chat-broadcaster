package polsl.project.server.concurrent;

import java.net.Socket;

public interface PeersManagementService
{
	void addPeer(final Socket socket, Thread thread);

	void removePeer(final Socket socket);
}
