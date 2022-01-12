class Citizen {
    private String n,i,vacc_given="not yet",status;
    private int nod=0,next_dose=0,a;
    Citizen(String name,int age,String id,String s){
        n=name;a=age;i=id;status=s;
    }
    String getn(){
        return this.n;
    }
    String geti(){
        return this.i;
    }
    String getvacc_given(){
        return this.vacc_given;
    }
    String setvacc_given(String v){
        this.vacc_given=v;
        return this.vacc_given;
    }
    String getstatus(){
        return this.status;
    }
    String setstatus(String name){
        this.status=name;
        return status;
    }
    int getnod(){
        return this.nod;
    }
    int setnod(){
        this.nod++;
        return this.nod;
    }
    int getnext_dose(){
        return this.next_dose;
    }
    int setnext_dose(int num){
        this.next_dose=num;
        return this.next_dose;
    }
    int geta(){
        return this.a;
    }
}
