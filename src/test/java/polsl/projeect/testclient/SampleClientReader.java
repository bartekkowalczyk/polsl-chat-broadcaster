package polsl.projeect.testclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SampleClientReader
{
	public static void main(String... args) throws IOException
	{
		Socket echoSocket = new Socket("localhost", 9898);


		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

		while (true)
		{
			String line = in.readLine();
			if (line != null)
			{
				System.out.println("echo: " + line);
			}
		}
	}
}
