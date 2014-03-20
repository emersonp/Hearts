public class Numeric
{
	public static boolean isNumeric( String s )
	{
		return ( isDouble(s) || isInteger(s) );
	}

	public static boolean isDouble( String s )
	{
		double n = 0;
		try
		{
			n = Double.valueOf( s );
		}

		catch ( NumberFormatException e )
		{
			return false;
		}

		return true;
	}

	public static boolean isInteger( String s )
	{
		int n = 0;
		try
		{
			n = Integer.valueOf( s );
		}

		catch ( NumberFormatException e )
		{
			return false;
		}

		return true;
	}

}
