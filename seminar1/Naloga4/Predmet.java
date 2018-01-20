class Predmet {
    String naziv;
    int teza;
    
    Predmet() {
	makenull();
    }
    
    public void makenull() {
	naziv = null;
	teza = 0;
    }
    
    Predmet(String tvr, int t) {
	naziv = tvr;
	teza = t;
    }

    public String getNaziv() {
	return naziv;
    }

    public int getTeza() {
	return teza;
    }

    public void setNaziv(String a) {
	this.naziv = a;
    }

    public void setTeza(int teza) {
	this.teza = teza;
    }
    
    //kopiranje
    public Predmet(Predmet t) {
	this.naziv = t.naziv;
	this.teza = t.teza;
    }
}
