import java.util.Scanner;
public class Naloga3 {

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	// teza lokomotive (nosilnost)
	int tL = sc.nextInt();
	// st vagonov
	int stVag = sc.nextInt();
	// kreiramo vlak
	Vlak vlak = new Vlak();
	// kreiramo prvi vagon = lokomotivo
	vlak.addLokomotiva(tL);
	
	// dodajamo vagone
	for (int i = 0; i < stVag; i++) {
	    // teza vagona
	    int tV = sc.nextInt();
	    // st tovorov
	    int stTov = sc.nextInt();
	    //preberemo lajno do konca
	    sc.nextLine();
	    vlak.addVagon(tV, stTov, sc);
	}
	
	String ukaz = sc.nextLine();
	
	String[] particije = new String[4];
	particije = ukaz.split(" ");
	
	
	while (ukaz.length() > 0) {
	    
	    switch(particije[0])
            {
                case "ODSTRANI_LIHE":
                    vlak.delOdd();
                    break;
                case "ODSTRANI_HET":
                    int n = Integer.parseInt(particije[1]);
		    vlak.delHet(n);
                    break;
                case "ODSTRANI_ZAS":
                    int p = Integer.parseInt(particije[1]);
		    vlak.delZas(p);
                    break;
                case "OBRNI":
                    vlak.obrat();
                    break;
		case "UREDI":
		    vlak.sort();
		    break;
		case "PREMAKNI":
		    String tip = particije[1];
		    int prvi = Integer.parseInt(particije[2]);
		    int drugi = Integer.parseInt(particije[3]);
		    vlak.premakni(tip, prvi, drugi);
		    break;
			
            }
	    
	    ukaz = sc.nextLine();
            particije = ukaz.split(" ");
	}
	
	vlak.izhod();
    }
}
