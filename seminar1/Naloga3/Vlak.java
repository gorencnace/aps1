import java.util.Scanner;
class Vlak {	
    // loko = "lokomotiva" (head)
    protected VlakElement loko;
    protected VlakElement zadnji;

    Vlak() {
	makenull();
    }

    public void makenull() {
	loko = null;
	zadnji = null;
    }
    
    public void addLokomotiva(int t) {
	VlakElement lokomotiva = new VlakElement(t);
	loko = lokomotiva;
	zadnji = lokomotiva;
    }
    
    public void addVagon(int t, int stTov, Scanner sc) {
	Tovor tvr = new Tovor();
	int vsotaTeze = 0;
	for (int i = 0; i < stTov; i++) {
	    // prebere vhod
	    String beri = sc.nextLine();
	    String[] particije = beri.split(" ");
	    
	    String element = particije[0];
	    int teza = Integer.parseInt(particije[1]);
	    
	    vsotaTeze += teza;
	    tvr.addTovor(element, teza);
	}
	
	zadnji.next = new VlakElement(t, vsotaTeze, tvr, zadnji, null);
	zadnji = zadnji.next;
    }
    
    public void del(VlakElement tmp) {
	VlakElement prejsnji = tmp.prev;
	VlakElement naslednji = tmp.next;
	if (naslednji != null) {
	    prejsnji.next = naslednji;
	    naslednji.prev = prejsnji;
	}
	else {
	    prejsnji.next = null;
	    zadnji = prejsnji;
	}
    }
    
    // rekurzivno odstrani lihe vagone
    public void delOdd() {
	delOdd(loko);
    }
    
    public void delOdd(VlakElement tmp) {
	
	if (tmp == loko) {
	    if (tmp.next != null && tmp.next.next != null) {
		tmp = tmp.next.next;
		del(tmp);
		delOdd(tmp);
	    }
	}
	else if (tmp.next != null && tmp.next.next != null) {
	    tmp = tmp.next.next;
	    del(tmp);
	    delOdd(tmp);
	}
    }
    // odstrani_het n
    public void delHet(int n) {
	for (VlakElement tmp = loko.next; tmp != null; tmp = tmp.next) {
	    if (n <= tmp.tovor.length())
		del(tmp);
	}
    }
    // odstrani_zas p
    public void delZas(int p) {
	for (VlakElement tmp = loko.next; tmp != null; tmp = tmp.next) {
	    int procent = (tmp.tezaTovor * 100)/tmp.teza;
	    if (p <= procent)
		del(tmp);
	}
    }
    
    public void obrat() {
	if (loko != null && loko.next != null) {
	    loko.next.prev = null;
	    loko.next = zadnji;
	    zadnji.next = zadnji.prev;
	    zadnji.prev = loko;
	    
	    VlakElement tmp = zadnji;
	    while (zadnji.next != null) {
		zadnji = zadnji.next;
		zadnji.next = zadnji.prev;
		zadnji.prev = tmp;
		tmp = tmp.next;
	    }
	}
    }
    
    public int length() {
	VlakElement tmp = loko.next;
	int i = 0;
	while (tmp != null) {
	    i++;
	    tmp = tmp.next;
	}
	return i;
    }
    
    // bubble sort
    public void sort() {
	//int n = length();
	boolean swapped = false;
	while (swapped != true) {
	    swapped = true;
	    for (VlakElement tmp = loko.next; tmp.next != null; tmp = tmp.next) {
		if (tmp.tezaTovor > tmp.next.tezaTovor) {
		    swap(tmp, tmp.next);
		    swapped = false;
		    tmp = tmp.prev;
		}
	    }
	}
	VlakElement tmp = loko;
	while (tmp.next != null)
	    tmp = tmp.next;
	zadnji = tmp;
    }
    
    public void swap(VlakElement prvi, VlakElement drugi) {
	
	VlakElement predhodnik = prvi.prev;
	if (drugi.next != null) {
	    VlakElement naslednjik = drugi.next;
	    prvi.next = naslednjik;
	    naslednjik.prev = prvi;
	}
	else
	    prvi.next = null;
	
	drugi.prev = predhodnik;
	predhodnik.next = drugi;
	
	prvi.prev = drugi;
	drugi.next = prvi;
	
    }
    
    public int max(int a, int b) {
	if (a>b)
	    return a;
	return b;
    }
    
    public void premakni(String tip, int a, int b) {
	int c = max(a, b);
	// preveri, ce sta obe indeksni stevilki veljavni
	if (c <= length()) {
	    VlakElement tmpA = loko.next;
	    // gre do vagona z indeksom a
	    for (int i = 0; i < a; i++)
		tmpA = tmpA.next;
	    TovorElement premaknjen = tmpA.tovor.retndel(tip);
	    
	    if (premaknjen != null) {
		tmpA.tezaTovor -= premaknjen.teza;
		VlakElement tmpB = loko.next;
		for (int i = 0; i < b; i++)
		    tmpB = tmpB.next;
		tmpB.tezaTovor += premaknjen.teza;
		tmpB.tovor.addPremik(premaknjen);
	    }
	    
	}
    }
    
    public void write() {
	for(VlakElement tmp = loko; tmp != null; tmp = tmp.next) {
	    System.out.print(tmp.tezaTovor + "/" + tmp.teza + " ");
	    if (tmp.tovor != null)
		tmp.tovor.write();
	    System.out.println();
	}
	System.out.println();
    }
    
    // sesteje tezo vlaka na vagon lokomotiva
    public void tezaVlaka() {
	int teza = 0;
	for(VlakElement tmp = loko.next; tmp != null; tmp = tmp.next)
	    teza += tmp.tezaTovor;
	loko.tezaTovor = teza;
    }
    
    public void izhod() {
	tezaVlaka();
	System.out.println(loko.teza + " " + length());
	for (VlakElement tmp = loko.next; tmp != null; tmp = tmp.next) {
	    System.out.println(tmp.teza + " " + tmp.tovor.length());
	    tmp.tovor.izhod();
	}
	if (jeIzpraven())
	    System.out.println("DA");
	else
	    System.out.println("NE");
    }
    
    public boolean jeIzpraven() {
	for (VlakElement tmp = loko.next; tmp != null; tmp = tmp.next) {
	 if (tmp.tezaTovor > tmp.teza)
	     return false;
	}
	return loko.teza >= loko.tezaTovor;
    }
}
