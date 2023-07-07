package tech.kbtg.BackendExam.exception;

public class NotFoundResourceException extends RuntimeException
{
	public NotFoundResourceException()
	{
	}

	public NotFoundResourceException( String message )
	{
		super( message );
	}

	public NotFoundResourceException( String message , Throwable cause )
	{
		super( message , cause );
	}

	public NotFoundResourceException( Throwable cause )
	{
		super( cause );
	}
}
