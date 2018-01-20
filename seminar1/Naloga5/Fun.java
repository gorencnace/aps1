class Fun {
    String[] ukazi;
    
    public void setUkazi(String[] ukazi) {
	this.ukazi = ukazi;
    }

    public String[] getUkazi() {
	return ukazi;
    }
    
    public void write() {
	for (int i = 0; i < ukazi.length; i++)
	    System.out.println(ukazi[i]);
    }
}
