
public class QueueElement {
	
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
