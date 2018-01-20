
class LinkedList 
{
	protected LinkedListElement first;
	protected LinkedListElement last;
	
	LinkedList()
	{
		makenull();
	}
	
	//Funkcija makenull inicializira seznam
	public void makenull()
	{
		//drzimo se implementacije iz ucbenika:
		//po dogovoru je na zacetku glava seznama (header)
		first = new LinkedListElement(null, null);
		last = null;
	}
	
	//Funkcija addLast doda nov element na konec seznama
	public void addLast(Predmet obj)
	{
		//najprej naredimo nov element
		LinkedListElement newEl = new LinkedListElement(obj, null);
		
		//ali je seznam prazen?
		// po dogovoru velja: ce je seznam prazen, potem kazalec "last" ne kaze nikamor
		if (last == null)
		{
			//ce seznam vsebuje samo en element, kazalca "first" in "last" kazeta na glavo seznama
			first.next = newEl;
			last = first;
		}
		else
		{
			last.next.next = newEl;
			last = last.next;
		}
	}
	
	// je seznam prazen
	public boolean empty() {
	    return last == null;
	}
	
	
	public void connect(LinkedList a) {
	    if (last != null) {
		last.next.next = a.first.next;
		last = a.last;
	    }
	    else {
		first = a.first;
		last = a.last;
	    }
	}
	
	public int cost() {
	    int c = 0;
	    for (LinkedListElement tmp = first.next; tmp != null && tmp.next != null; tmp = tmp.next) {
		if (!tmp.element.naziv.equals(tmp.next.element.naziv)) {
		    c++;
		}
	    }
	    return c;
	}
}

class LinkedListElement 
{
	Predmet element;
	LinkedListElement next;
	
	LinkedListElement(Predmet obj)
	{
		element = obj;
		next = null;
	}
	
	LinkedListElement(Predmet obj, LinkedListElement nxt)
	{
		element = obj;
		next = nxt;
	}
}