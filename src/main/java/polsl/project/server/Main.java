package polsl.project.server;

import polsl.project.server.connection.ServerBootstrap;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		final ServerBootstrap serverBootstrap = new ServerBootstrap();

		//start listening - this method doesnt't block.
		serverBootstrap.startListening(9898);
	}
}
