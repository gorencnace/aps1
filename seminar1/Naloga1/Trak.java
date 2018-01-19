
public class Trak
{
	protected TrakElement prvi;
	protected TrakElement zadnji;
	
	Trak()
	{
		makenull();
	}
	
	//Funkcija makenull inicializira seznam
	public void makenull()
	{
		prvi = null;
		zadnji = null;
	}
	
	// funkcija za kreiranje samega traku
	public void addNulls()
	{
		// kreireamo nov nicelni element
		TrakElement nov = new TrakElement(null);
		// ce na traku ni se nobenega elementa dodamo "nov"
		if (prvi == null)
		{
			prvi = nov;
			zadnji = nov;
		}
		// ce je na traku zgolj en element
		else if (prvi.next == null)
		{
			nov.next = prvi;
			prvi = nov;
			zadnji.prev = nov;
		}
		// na traku so ze elementi, dodamo "nov" spredaj
		else
		{
			nov.next = prvi;
			prvi = nov;
			prvi.next.prev = prvi;
		}
	}
	
	// nalozi element na trak, samo doda element
	public void add(Object obj)
	{
		// na traku mora biti prostor
		if (prvi.element == null)
			prvi.element = obj;
	}
	
	public boolean empty()
	{
		if (prvi.element == null)
			return true;
		return false;
	}
	
	Object del()
	{
		Object obj = prvi.element;
		prvi.element = null;
		return obj;
	}
	
	public void up()
	{
		TrakElement tmp = new TrakElement(null, prvi, null);
		prvi.prev = tmp;
		prvi = prvi.prev;
		
		zadnji = zadnji.prev;
		zadnji.next = null;
	}
	
	public void down()
	{
		TrakElement tmp = new TrakElement(null, null, zadnji);
		zadnji.next = tmp;
		zadnji = zadnji.next;
		
		prvi = prvi.next;
		prvi.prev = null;
	}
	
		
	public void write()
	{
		TrakElement tmp = prvi;
		
		while (tmp != null)
		{
			System.out.print(tmp.element + ",");
			
			tmp = tmp.next;
		}
		
		System.out.println();
		//System.out.println("prvi: " + prvi.element);
		//System.out.println("zadnji: " + zadnji.element);
	}
}
