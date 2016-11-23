package polsl.projeect.testclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * In case of no data send by server, run this and check if anything is going by wire.
 */
public class SampleBytesBasedReader
{

	public static void main(String... args) throws IOException
	{
		Socket echoSocket = new Socket("localhost", 9898);


		InputStreamReader in = new InputStreamReader(echoSocket.getInputStream());

		while (true)
		{
			final int read = in.read();
			System.out.println(read);
		}
	}
}
