package com.dukitan.vpu.excecao;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1368045182949701686L;
	
	protected static String titulo = "[LoginException]:  ";
	
	public LoginException() 
	{
		super();
	}

	public LoginException(String message)
	{
		super(titulo+message);
	}

	public LoginException(String message, Throwable cause)
	{
		super(titulo+message, cause);
	}

	public LoginException(Throwable cause)
	{
		super(cause);
	}

}
