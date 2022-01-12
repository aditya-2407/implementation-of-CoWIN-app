class Vaccine {
    
    private String v_n;
    private int n_d;
    private int g;
    
    Vaccine(String vacc_name, int no_doses, int gap){
        v_n = vacc_name;
        n_d = no_doses;
        g = gap;
    }
    String  getv_n(){
        return this.v_n;
    }
    int getn_d(){
        return this.n_d;
    }
    int getg(){
        return this.g;
    }

}

