import java.util.Scanner;
import java.io.*;

public class Skladisce
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int stTrakov = sc.nextInt();
		// dt == "dolzina trakov"
		int dt = sc.nextInt();
		
		// kreiramo skladisce, vsak trak bo bil svoj "sklad" (LIFO sistem)
		Object[] skladisce = new Object[stTrakov];
		
		for (int i = 0; i < stTrakov; i++)
		{
			Trak t = new Trak();
			for (int j = 0; j < dt; j++)
				t.addNulls();
			skladisce[i] = t;
		}
		
		// ln = "lokacija 0"
		Queue ln = new Queue();
		
		String imena = sc.nextLine();
		
		for (String brezv: imena.split(","))
			ln.addQueueEl(brezv);
		
		ln.write();
	}
}
