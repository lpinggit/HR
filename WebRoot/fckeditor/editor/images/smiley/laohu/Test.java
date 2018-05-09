import java.io.*;

public class Test
{
	public static void main(String[] args)
	{
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream("a.txt");
			int i=1;
			while(i<45)
			{
				fos.write(("'face" + i + ".gif',").getBytes());
				i++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}	
		}
	}
}