import java.util.*;
class Hospital{

    private ArrayList<Vaccine> vacc_in_hos=new ArrayList<Vaccine>();
    private ArrayList<Slot> slot_obj=new ArrayList<Slot>();
    private String n;
    private int p;
    Hospital(String name,int pincode){
        n=name;
        p=pincode;
    }
    ArrayList<Vaccine> getvacc_in_hos(){
        return this.vacc_in_hos;
    }
    ArrayList<Slot> getSlot_obj(){
        return this.slot_obj;
    }
    String getn(){
        return this.n;
    }
    int getp(){
        return this.p;
    }
    void add_slot(ArrayList<Vaccine> vacc,String id){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of Slots to be added: ");
        int slots=sc.nextInt();
        for(int i=0;i<slots;i++){
            System.out.print("Enter Day Number: ");
            int day=sc.nextInt();
            System.out.print("Enter Quantity: ");
            int quan=sc.nextInt();
            System.out.println("Select Vaccine");
            for(int j=0;j<vacc.size();j++){
                System.out.println(j+". "+vacc.get(j).getv_n());
            }
            int vacc_num=sc.nextInt();
            vacc_in_hos.add(vacc.get(vacc_num));
            slot_obj.add(new Slot(day,quan,(vacc.get(vacc_num)).getv_n(),(vacc.get(vacc_num).getn_d()),(vacc.get(vacc_num).getg())));
            System.out.println("Slot added by Hospital "+id+" for Day: "+day+", Available Quantity: "+quan+" of Vaccine "+vacc.get(vacc_num).getv_n());
        }
    }

    void book_slot(String name,ArrayList<Citizen> cit_obj,String di,String vca){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<slot_obj.size();i++){
            if((slot_obj.get(i).getq()!=0 && slot_obj.get(i).getv().equals(vca))||(slot_obj.get(i).getq()!=0 && vca.equals("don't have")))
            System.out.println(i+"-> Day: "+(slot_obj.get(i)).getd()+" Available Qty:"+(slot_obj.get(i)).getq()+" Vaccine:"+(slot_obj.get(i)).getv());
            else if(slot_obj.get(i).getq()==0) {
                slot_obj.remove(i);

            }
        }
        System.out.print("Choose Slot: ");
        int sl=sc.nextInt();
        slot_obj.get(sl).setq();
        for(int k=0;k<cit_obj.size();k++){
            if(((cit_obj.get(k)).geti()).equals(di)){
                cit_obj.get(k).setnod();
                if(cit_obj.get(k).getnod()<slot_obj.get(sl).getno_of_doses()){
                    cit_obj.get(k).setstatus("PARTIALLY VACCINATED");
                }
                else if(cit_obj.get(k).getnod()==slot_obj.get(sl).getno_of_doses()){
                    cit_obj.get(k).setstatus("FULLY VACCINATED");
                }
                cit_obj.get(k).setvacc_given(slot_obj.get(sl).getv());
                if(!cit_obj.get(k).getstatus().equals("FULLY VACCINATED")){
                cit_obj.get(k).setnext_dose((slot_obj.get(sl)).getd()+(slot_obj.get(sl)).getg());}
                else{cit_obj.get(k).setnext_dose(0);}
            }
        }
        System.out.println(name+" vaccinated with "+slot_obj.get(sl).getv());
    }

    boolean check(String name,ArrayList<Citizen> cit_obj,String di,String vacc_name){
        int pos=-1;
        for(int i=0;i<cit_obj.size();i++){
            if((cit_obj.get(i).geti()).equals(di)){
                pos=i;
            }
        }
        if(pos==-1){
            System.out.println("Cannot find any citizen registered with this ID");
            return false;
        }
        if(cit_obj.get(pos).getvacc_given().equals("not yet")){
            return true;
        }
        if(vacc_name.equals("don't have")){
            for(int j=0;j<slot_obj.size();j++){
                if(slot_obj.get(j).getd()==cit_obj.get(pos).getnext_dose() && slot_obj.get(j).getv().equals(cit_obj.get(pos).getvacc_given())){
                    return true;
                }
            }System.out.println("No slots available");
            return false;
        }
        else{
            if(!vacc_name.equals(cit_obj.get(pos).getvacc_given())){
                System.out.println("Vaccine mixing is not allowed");
                return false;
            }
            else{
                for(int j=0;j<slot_obj.size();j++){
                    if(slot_obj.get(j).getd()==cit_obj.get(pos).getnext_dose() && slot_obj.get(j).getv().equals(cit_obj.get(pos).getvacc_given())){
                        return true;
                    }
                }
                System.out.println("No slots available");return false;
            }
        }
    }

    void printSlots(){
        for(int i=0;i<slot_obj.size();i++){
            System.out.println("Day: "+slot_obj.get(i).getd()+" Vaccine: "+slot_obj.get(i).getv()+" Available Qty: "+slot_obj.get(i).getq());
        }
    }

}
