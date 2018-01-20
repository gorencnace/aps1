
class Vlak {	
    
    protected VlakElement first;
    protected VlakElement last;

    Vlak() {
	makenull();
    }

    public void makenull() {
	first = null;
	last = null;
    }
    
    // najprej kreiramo najveyje mozno stevilo vagonov, potem dodajamo tovor    
    public void addVag(int t) {
	VlakElement vag = new VlakElement(t);
	vag.tovor = new LinkedList();
	if (first == null) {
	    first = vag;
	    last = vag;
	}
	else if (first.next == null){
	    vag.next = first;
	    first = vag;
	    last.prev = vag;
	}
	else {
	    vag.next = first;
	    first = vag;
	    first.next.prev = first;
	}
    }
    
    public void addPredemt(Predmet a) {
	VlakElement tmp = first;
	
	while (tmp != null) {
	    if (tmp.teza >= a.getTeza()) {
		if (!tmp.tovor.empty()) {
		    if (a.naziv.equals(tmp.tovor.first.next.element.getNaziv())) {
			tmp.tovor.addLast(a);
			tmp.teza -= a.getTeza();
			break;
		    }
		}
		else if (tmp.tovor.empty()) {
		    tmp.tovor.addLast(a);
		    tmp.teza -= a.getTeza();
		    break;
		}
	    }
	    tmp = tmp.next;
	}
    }
    
    
    public void addPredemtC(Predmet a) {
	VlakElement tmp = first;
	
	while (tmp != null) {
	    if (tmp.teza >= a.getTeza()) {
			    
		tmp.tovor.addLast(a);
		tmp.teza -= a.getTeza();
		break;
		
	    }
	    tmp = tmp.next;
	}
    }
    
    // bubble sort na podlagi teze vagona
    public void sort() {
	boolean swapped = true;
	while (swapped) {
	    swapped = false;
	    for (VlakElement tmp = first.next; tmp != null; tmp = tmp.next) {
		if (tmp.prev.teza > tmp.teza) {
		    swap(tmp.prev, tmp);
		    swapped = true;
		}
	    }
	}
	VlakElement tmp = first;
	while (tmp.prev != null)
	    tmp = tmp.prev;
	first = tmp;
	while (tmp.next != null)
	    tmp = tmp.next;
	last = tmp;
    }
    
    public void swap(VlakElement prvi, VlakElement drugi) {
	int tP = prvi.teza;
	int tD = drugi.teza;
	LinkedList lP = prvi.tovor;
	LinkedList lD = drugi.tovor;
	
	prvi.teza = tD;
	drugi.teza = tP;
	
	prvi.tovor = lD;
	drugi.tovor = lP;
	
    }
    
    public void secondSort(int n) {
	for (VlakElement tmp = last; tmp != null && tmp.teza >= n/2; tmp = tmp.prev) {
	    LinkedList l = tmp.tovor;
	    int t = n - tmp.teza;
	    for (VlakElement dod = first; dod != tmp; dod = dod.next) {
		if (dod.teza >= t) {
		    dod.tovor.connect(l);
		    tmp.tovor = new LinkedList();
		    tmp.teza = n;
		    dod.teza -= t;
		    break;
		}
	    }
	    
	}
    }
   
    public void delNulls() {
	VlakElement tmp = last;
            while (tmp.prev != null && tmp.tovor.empty())
            {
                tmp = tmp.prev;
                last = tmp;
                tmp.next = null;
            }
    }
    
    public int cost() {
	int c = 0;
	for (VlakElement tmp = first; tmp != null && !tmp.tovor.empty(); tmp = tmp.next) {
	    c += 2;
	    c = c + tmp.tovor.cost();
	}
	
	return c;
    }
}


class VlakElement {
    int teza;
    VlakElement prev;
    VlakElement next;
    LinkedList tovor;

    VlakElement(int t) {
	teza = t;
	next = null;
	prev = null;
    }

}
