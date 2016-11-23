package polsl.project.server.domain;

public class Message
{
	private final String message;

	public Message(final String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	//FIXME equals, hashCode, toString missing
}

