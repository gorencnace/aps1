class VlakElement {
    int teza;
    int tezaTovor;
    VlakElement prev;
    VlakElement next;
    Tovor tovor;

    VlakElement(int t) {
	teza = t;
	next = null;
	prev = null;
    }

    VlakElement(int t, int vT, Tovor tvr, VlakElement prv, VlakElement nxt) {
	teza = t;
	tovor = tvr;
	next = nxt;
	prev = prv;
	tezaTovor = vT;
    }
	
}
