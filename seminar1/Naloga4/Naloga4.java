import java.util.*;
public class Naloga4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	
	int stT = sc.nextInt();
	int maxT = sc.nextInt();
	
	Predmet[] tabela = new Predmet[stT];
	
	sc.nextLine();
	
	for (int i = 0; i < stT; i++) {
	    String niz = sc.nextLine();
	    
	    String[] particije = new String[2];
	    particije = niz.split(" ");
	    
	    tabela[i] = new Predmet();
	    
	    tabela[i].setNaziv(particije[0]);
	    tabela[i].setTeza(Integer.parseInt(particije[1]));
	}
	
	sort(tabela);
	
	// kreira vlak
	Vlak vlak = new Vlak();
	
	for (int i = 0; i < stT; i++) {
	    vlak.addVag(maxT);
	}
	
	for (int i = 0; i < stT; i++) {
	    vlak.addPredemt(tabela[i]);
	}
	
	// zbriše prazne vagone
	vlak.delNulls();
	
	vlak.sort();
	
	vlak.secondSort(maxT);
	
	vlak.delNulls();
	
	int c1 = vlak.cost();
	
	secondSort(tabela);
	
	// kreira vlak
	Vlak vlak2 = new Vlak();
	
	for (int i = 0; i < stT; i++) {
	    vlak2.addVag(maxT);
	}
	
	for (int i = 0; i < stT; i++) {
	    vlak2.addPredemtC(tabela[i]);
	}
	
	// zbriše prazne vagone
	vlak2.delNulls();
	
	int c2 = vlak2.cost();
	
	
	System.out.println(min(c1, c2));
    }
    
    public static int min(int a, int b) {
	if (a > b)
	    return b;
	return a;
    }
    
    public static void secondSort(Predmet[] t) {
	int n = t.length;
	boolean swapped = true;
	while (swapped) {
	    swapped = false;
	    for (int i = 1; i < n; i++) {
		int a = t[i-1].getTeza();
		int b = t[i].getTeza();
		if (a < b) {
		    swap(t, i);
		    swapped = true;
		}
	    }
	}
    }
    
    public static void sort(Predmet[] t) {
	int n = t.length;
	boolean swapped = true;
	while (swapped) {
	    swapped = false;
	    for (int i = 1; i < n; i++) {
		String aS = t[i-1].getNaziv();
		String bS = t[i].getNaziv();
		if (aS.compareTo(bS) > 0) {
		    swap(t, i);
		    swapped = true;
		}
		else if (aS.compareTo(bS) == 0) {
		    int a = t[i-1].getTeza();
		    int b = t[i].getTeza();
		    if (a < b) {
			swap(t, i);
			swapped = true;
		    }
		}
	    }
	}
    }
    
    public static void swap(Predmet[] t, int i) {
	Predmet a = new Predmet(t[i-1]);
	Predmet b = new Predmet(t[i]);
	t[i-1] = new Predmet(b);
	t[i] = new Predmet(a);
    }
}
