class Slot {
    private int d,q,no_of_doses,g;
    private String v;
    Slot(int day,int quantity,String vaccine,int nd,int gap){
        d=day;q=quantity;v=vaccine;no_of_doses=nd;g=gap;
    }
    int getd(){
        return this.d;
    }
    int getq(){
        return this.q;
    }
    int setq(){
        this.q--;
        return this.q;
    }
    int getno_of_doses(){
        return this.no_of_doses;
    }
    int getg(){
        return this.g;
    }
    String getv(){
        return this.v;
    }
}
