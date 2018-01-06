package at.spot.hybris.patch.exception;

public class PatchException extends Exception
{
	public PatchException(final String message)
	{
		super(message);
	}

	public PatchException(final String message, final Throwable rootCause)
	{
		super(message, rootCause);
	}
}
