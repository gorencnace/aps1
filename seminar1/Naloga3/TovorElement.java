class TovorElement {
	String tovor;
	int teza;
	TovorElement next;
	
	TovorElement(String tvr, int t) {
		tovor = tvr;
		teza = t;
	}
	
	TovorElement(String tvr, int t, TovorElement nxt) {
		tovor = tvr;
		teza = t;
		next = nxt;
	}
}
