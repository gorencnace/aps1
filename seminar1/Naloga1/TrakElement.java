
public class TrakElement
{
	Object element;
	TrakElement next;
	TrakElement prev;
	
	TrakElement(Object obj)
	{
		element = obj;
		next = null;
		prev = null;
	}
	
	TrakElement(Object obj, TrakElement nxt, TrakElement prv)
	{
		element = obj;
		next = nxt;
		prev = prv;
	}
}
