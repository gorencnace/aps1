import java.util.Scanner;

public class Naloga1
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int stTrakov = sc.nextInt();
        // dt == "dolzina trakov"
        int dt = sc.nextInt();
        
        sc.nextLine();
        
        // kreiram vozicek
        String vozicek = "";
        
        // robot je na zacetki na lokaciji 0
        int robot = 0;
        
        // kreiramo skladisce, vsak trak bo bil svoj "sklad" (LIFO sistem)
        Trak[] skladisce = new Trak[stTrakov];
        
        for (int i = 0; i < stTrakov; i++)
        {
            skladisce[i] = new Trak();
            for (int j = 0; j < dt; j++)
            {
                skladisce[i].addNulls();
            }
        }
        
        
        // ln = "lokacija 0"
        Queue ln = new Queue();
        
        String imena = sc.nextLine();
        
        for (String brezv: imena.split(","))
            ln.addQueueEl(brezv);
        
        String ukaz = sc.nextLine();
        
        while (ukaz.length() > 0)
        {
            
            
            switch(ukaz)
            {
                case "NALOZI":
                    if (vozicek.isEmpty())
                        {
                        if (robot == 0)
						{
							if (!ln.isQueueEmpty())
								vozicek = (String) ln.retanddelQEl();
                        }
						else
                        {
                            if (!skladisce[robot-1].empty())
                            {
                                vozicek = (String) skladisce[robot-1].del();
                            }
                        }
                    }
                    break;
                case "ODLOZI":
                    if (robot > 0)
                    {
                        if (skladisce[robot-1].empty())
                        {
                            if (!vozicek.isEmpty())
                            {
                                Object tmp = vozicek;
                                skladisce[robot-1].add(tmp);
                                vozicek = "";
                            }
                        }
                    }
                    break;
                case "GOR":
                    if (robot > 0)
                    {
                        skladisce[robot-1].up();
                    }
                    break;
                case "DOL":
                    if (robot > 0)
                    {
                        skladisce[robot-1].down();
                    }
                    break;
                default:
                    Queue premik = new Queue();
                    for (String pre: ukaz.split(" "))
                        premik.addQueueEl(pre);
                    
                    premik.delQueueEl();
                    // metodo najdu na netu
                    robot = Integer.parseInt((String) premik.returnQueueEl());
            }
            
            ukaz = sc.nextLine();
        }
        
        printSkladisce(skladisce, stTrakov);
    }
    
    public static void printSkladisce(Trak[] s, int st)
    {
        for (int i = 0; i < st; i++)
        {
            int index = i+1;
            System.out.print(index + ":");
            s[i].write();
        }
    }
}


class Trak
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
            return prvi.element == null;
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
	
        public void delEndNulls()
        {
            TrakElement tmp = zadnji;
            while (tmp.prev != null && tmp.element == null)
            {
                tmp = tmp.prev;
                zadnji = tmp;
                tmp.next = null;
            }
        }
        
	public void write()
	{
            delEndNulls();
            
		TrakElement tmp = prvi;
		
		while (tmp != null)
		{
                    if (tmp.next != null)
                    {
                        if (tmp.element != null)
                            System.out.print(tmp.element + ",");
                        else
                            System.out.print(",");
                    }
                    else
                        if (tmp.element != null)
                            System.out.print(tmp.element);
		
                    tmp = tmp.next;
		}
		
		System.out.println();
	}
}


class TrakElement
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
        public Object retanddelQEl()
        {
            Object obj = returnQueueEl();
            delQueueEl();
            return obj;
        }
		
	public boolean isQueueEmpty()
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
