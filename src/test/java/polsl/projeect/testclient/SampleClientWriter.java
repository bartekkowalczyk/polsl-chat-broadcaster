package polsl.projeect.testclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SampleClientWriter
{
	public static void main(String... args) throws IOException
	{
		Socket echoSocket = new Socket("localhost", 9898);


		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		//thanks to that we can read and write simultaneously
		new Thread(() -> {
			try
			{
				while (true) {
					System.out.println("[received]: " + in.readLine());
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}).start();

		String userInput;
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
		}

	}
}
