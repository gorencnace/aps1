import java.util.*;
public class Naloga5 {
    
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	int n = sc.nextInt();
	int m = sc.nextInt();
	
	int[][] tabela = new int[m][n];
	
	for (int j = n-1; j >= 0; j--) {
	    for (int i = 0 ; i < m; i++) {
		tabela[i][j] = sc.nextInt();
	    }
	}
	
	// naredi tabelo funkcij
	int stFunkcij = sc.nextInt();
	String[][] funkcije = new String[stFunkcij][];
	for (int i = 0; i < stFunkcij; i++) {
	    int stUkazov = sc.nextInt();
	    funkcije[i] = new String[stUkazov];
	    sc.nextLine();
	    
	    for (int j = 0; j < stUkazov; j++) {
		funkcije[i][j] = sc.nextLine();
	    }
	}
	
	// kreira robota
	int y = sc.nextInt();
	int x = sc.nextInt();
	int smer = sc.nextInt();
	Robot robo = new Robot(x, y, smer);
	
	// stevilo korakov izvajanja
	int koraki = sc.nextInt();
	
	Stack run = new Stack();
	addFtoS(funkcije, 0, run);
	
	// kreiramo vrsto za setjump
	Queue setJ = new Queue();
	
	
	while (koraki > 0 && !run.empty()) {
	    
	    String ukaz = run.top();
	    run.pop();
	    switch(ukaz) {
		case "FWD":
		    //kam se premakne
		    koraki -= 1;
		    int x0 = robo.getX();
		    int y0 = robo.getY();
		    switch (robo.getSmer()) {
			case 0:
			    if (tabela[x0][y0+1] == 0)
				robo.setY(y0+1);
			    else
				delF(run);
			    break;
			case 1:
			    if (tabela[x0+1][y0] == 0)
				robo.setX(x0+1);
			    else
				delF(run);
			    break;
			case 2:
			    if (tabela[x0][y0-1] == 0)
				robo.setY(y0-1);
			    else
				delF(run);
			    break;
			case 3:
			    if (tabela[x0-1][y0] == 0)
				robo.setX(x0-1);
			    else
				delF(run);
			    break;
		    }
		    break;
		case "RGT":
		    koraki -= 1;
		    int smerR = robo.getSmer();
		    robo.setSmer((smerR+1) % 4);
		    break;
		case "LFT":
		    koraki -= 1;
		    int smerL = robo.getSmer();
		    robo.setSmer((smerL+3) % 4);
		    break;
		case "SETJMP":
		    Stack clone = new Stack();
		    clone = run.clone(clone);
		    setJ.addQueueEl(clone);
		    break;
		case "JMP":
		    if (!setJ.empty()) {
			run = (Stack) setJ.retnDelQEl();
		    }
		    else {
			delF(run);
		    }
		    break;
		case "END":
		    break;
		default:
		    String[] particije = new String[2];
		    particije = ukaz.split(" ");
		    int funNo = Integer.parseInt(particije[1]);
		    
		    addFtoS(funkcije, funNo-1, run);
	    }
	}
	
	System.out.println(robo.getY() + " " + robo.getX() + " " + robo.getSmer());
	
    }
    
    public static void addFtoS(String[][] f, int i, Stack s) {
	s.push("END");
	for (int j = f[i].length - 1; j >= 0; j--) {
	    s.push(f[i][j]);
	}
    }
    
    public static void delF(Stack s) {
	while (!s.top().equals("END"))
	    s.pop();
	s.pop();
    }
}
