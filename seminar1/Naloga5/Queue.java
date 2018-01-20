class QueueElement {
	
	Object element;
	QueueElement next;
	
	QueueElement(Object obj)
	{
		element = obj;
		next = null;
	}
	
	QueueElement(Object obj, QueueElement nxt)
	{
		element = obj;
		next = nxt;
	}
}

class Queue {
	
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
	
	
	public void addQueueEl(Object obj)
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
	
	public void delQueueEl()
	{
		if (first != null)
			first = first.next;
	}
	
        public Object returnQueueEl()
        {
            return first.element;
        }
        
        // return and delete
        public Object retnDelQEl()
        {
            Object obj = returnQueueEl();
            delQueueEl();
            return obj;
        }
        
        public boolean empty()
	{
            return first == null;
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
