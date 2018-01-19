
public class Queue {
	
	protected QueueElement first;
	protected QueueElement last;
	
	Queue()
	{
		makenull();
	}
	
	//Funkcija makenull inicializira seznam
	public void makenull()
	{
		first = null;
		last = null;
	}
	
	
	void addQueueEl(Object obj)
	{
		
		if (last == null)
		{
			last = new QueueElement(obj);
			first = last;
		}
		
		else
		{
			last.next = new QueueElement(obj);
			
			last = last.next;
		}
	}
	
	void delQueueEl()
	{
		if (first != null)
			first = first.next;
	}
	
	public void write()
	{
		QueueElement tmp = first;
		
		while (tmp != null)
		{
			System.out.print(tmp.element + ",");
			
			tmp = tmp.next;
		}
		
		System.out.println();
	}
}
