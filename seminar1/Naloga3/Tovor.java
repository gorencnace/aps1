class Tovor {
    protected TovorElement first;
    protected TovorElement last;

    Tovor() {
	makenull();
    }

    public void makenull() {
	first = new TovorElement(null, 0, null);
	last = null;
    }
    
    // doda na konec seznama
    public void addTovor(String tvr, int t) {
	TovorElement nov = new TovorElement(tvr, t);
	
	if (last == null) { 
	    last = nov;
	    first.next = nov;
	}
	else {
	    last.next = nov;
	    last = last.next;
	}
	
    }
    
    // return and delete
    public TovorElement retndel(String tvr) {
	TovorElement tmp = first.next;
	TovorElement prev_el = first;

	while (tmp != null && !tmp.tovor.equals(tvr)) {
	    prev_el = tmp;
	    tmp = tmp.next;
	}

	if (tmp != null) {
	    prev_el.next = tmp.next;
	    return tmp;
	}
	return null;
    }

    public int length() {
	return length(first.next);
    }

    public int length(TovorElement el) {
	if (el == null)
	    return 0;
	else
	    return length(el.next) + 1;
    }

    public int sum() {
	return sum(first.next);
    }

    public int sum(TovorElement el) {
	if (el == null)
	    return 0;
	else
	    return sum(el.next) + el.teza;
    }

    //premik tovora iz evega vagona na drugega, odstrani duplikate
    public void addPremik(TovorElement el) {
	TovorElement tmp = first.next;
	while (tmp != null) {
	    if (tmp.tovor.equals(el.tovor)) {
		tmp.teza += el.teza;
		el = null;
		break;
	    }
	    tmp = tmp.next;
	}
	
	if (el != null) {
	    last.next = el;
	    el.next = null;
	}
    }
    
    public void write() {
	for (TovorElement tmp = first.next; tmp != null; tmp = tmp.next) {
	    System.out.print("-> " + tmp.tovor + ": " + tmp.teza + " ");
	}
    }

    public void izhod() {
	for (TovorElement tmp = first.next; tmp != null; tmp = tmp.next) {
	    System.out.println(tmp.tovor + " " + tmp.teza);
	}
    }
}
