package com.dukitan.vpu.excecao;

public class LoginInformationException extends Exception
{
	private static final long serialVersionUID = -7142671449619793088L;
	
	protected static String titulo = "[LoginInformationException]:  ";
	
	public LoginInformationException() 
	{
		super();
	}

	public LoginInformationException(String message)
	{
		super(titulo+message);
	}

	public LoginInformationException(String message, Throwable cause)
	{
		super(titulo+message, cause);
	}

	public LoginInformationException(Throwable cause)
	{
		super(cause);
	}

}
