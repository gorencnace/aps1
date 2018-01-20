class StackElement
{
	String element;
	StackElement next;

	StackElement()
	{
		element = null;
		next = null;
	}
}

class Stack
{	
	private StackElement top;
	
	public Stack()
	{
		makenull();
	}
	
	public void makenull()
	{
		this.top = null;
	}
	
	public boolean empty()
	{
		return (this.top == null);
	}
	
	public String top()
	{
		if (!empty())
		    return this.top.element;
		return null;
	}
	
	public void push(String obj)
	{
		// Funkcija vstavi nov element na vrh sklada (oznacuje ga kazalec top)
		StackElement tmp = new StackElement();
		tmp.element = obj;
		tmp.next = this.top;
		this.top = tmp;
	}
	
	public void pop()
	{
		if (!empty())
		    this.top = this.top.next;
	}
	
	// rekurzija za kopiranje sklada
	public Stack clone(Stack c) {
	    StackElement el = top;
	    return clone(el, c);
	}
	
	public Stack clone(StackElement el, Stack c) {
	    if (el == null)
		return c;
	        
	    c.clone(el.next, c);
	    c.push(el.element);
	    return c;
	}
}
