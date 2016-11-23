package polsl.project.server.concurrent;

import polsl.project.server.domain.Message;

public interface BroadcastService
{
	void sendToAllActivePeers(final Message message);
}
