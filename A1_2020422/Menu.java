import java.util.*;

class Menu{

    Scanner sc=new Scanner(System.in);

    static String getID(int n){
        String new_n=Integer.toString(n);
        int size=new_n.length();
        switch(size){
            case 1: new_n="00000"+new_n;break;
            case 2: new_n="0000"+new_n;break;
            case 3: new_n="000"+new_n;break;
            case 4: new_n="00"+new_n;break;
            case 5: new_n="0"+new_n;break;
        }
        return new_n;
    }

    static int getIndex(String id){
        int flag=0;
        if(id.equals("000000")){
            return flag;
        }
        int ind=Integer.parseInt(id);
        return ind;
    }
    
    void print_menu(){

        ArrayList<Vaccine> vacc_obj=new ArrayList<Vaccine>();
        ArrayList<Hospital> hos_obj=new ArrayList<Hospital>();
        ArrayList<Citizen> cit_obj=new ArrayList<Citizen>();
        int hos_index=0;
        while(1>0){
            System.out.println("1. Add Vaccine \n2. Register Hospital \n3. Register Citizen \n4. Add Slot for Vaccination \n5. Book Slot for Vaccination \n6. List all slots for a hospital \n7. Check Vaccination Status \n8. Exit ");
            System.out.println("---------------------------------");
            int choice=sc.nextInt();
            if(choice==8){
                return;
            }
            if(choice==1){
                System.out.print("Vaccine Name: ");
                String vacc=sc.next();
                System.out.print("Number of Doses: ");
                int no_doses=sc.nextInt();
                int gap_doses;
                if(no_doses!=1){
                System.out.print("Gap between Doses: ");
                gap_doses=sc.nextInt();
                }
                else{
                    gap_doses=0;
                }
                vacc_obj.add(new Vaccine(vacc,no_doses,gap_doses));
                System.out.println("Vaccine Name: "+vacc+", Number of Doses: "+no_doses+", Gap Between Doses: "+gap_doses);
            }
            if(choice==2){
                System.out.print("Hospital Name: ");
                String hos=sc.next();
                System.out.print("PinCode: ");
                int pin=sc.nextInt();
                hos_obj.add(new Hospital(hos,pin));
                String iD=getID(hos_index);
                System.out.println("Hospital Name: "+hos+", PinCode: "+pin+", Unique ID: "+iD);
                hos_index++;
            }
            if(choice==3){
                System.out.print("Citizen Name: ");
                String name=sc.next();
                System.out.print("Age: ");
                int age=sc.nextInt();
                System.out.print("Unique ID: ");
                String unid=sc.next();
                System.out.println("Citizen Name: "+name+", Age: "+age+", Unique ID: "+unid);
                if(age<18){
                    System.out.println("Only above 18 are allowed");
                    System.out.println("---------------------------------");
                    continue;
                }
                else{
                    cit_obj.add(new Citizen(name,age,unid,"REGISTERED"));
                }
            }
            if(choice==4){
                System.out.print("Enter Hospital ID: ");
                String id=sc.next();
                int id_index=getIndex(id);
                (hos_obj.get(id_index)).add_slot(vacc_obj,id);
            }
            if(choice==5){
                System.out.print("Enter patient Unique ID: ");
                String id=sc.next();
                int flagger=0;
                String eman="";
                for(int z=0;z<cit_obj.size();z++){
                    if(cit_obj.get(z).geti().equals(id)){
                        flagger++;
                        eman=cit_obj.get(z).getn();
                    }
                }
                if(flagger==0){
                    System.out.println("No patient registered with this ID");
                    System.out.println("---------------------------------");
                    continue;
                }
                System.out.println("1. Search by area\n2. Search by Vaccine\n3. Exit");
                System.out.print("Enter option: ");
                int option=sc.nextInt();
                if(option==1){
                    System.out.print("Enter PinCode: ");
                    int pin=sc.nextInt();
                    int flag=0;
                    for(int i=0;i<hos_obj.size();i++){
                        if((hos_obj.get(i)).getp()==pin){
                            String j=getID(i);
                            System.out.println(j+" "+(hos_obj.get(i)).getn());
                            flag++;
                        }
                    }
                    if(flag==0){
                        System.out.println("No hospitals in this PinCode.");
                        System.out.println("---------------------------------");
                        continue;
                    }
                    else{
                        System.out.print("Enter hospital id: ");
                        String di=sc.next();
                        int di_index=getIndex(di);
                        boolean corr=(hos_obj.get(di_index)).check(eman,cit_obj,id,"don't have");
                        if(corr==true){
                        (hos_obj.get(di_index)).book_slot(eman,cit_obj,id,"don't have");}
                        else{
                            System.out.println("---------------------------------");
                            continue;
                        }
                    }
                }
                else if(option==2){
                    System.out.print("Enter Vaccine name: ");
                    String vaccine_name=sc.next();
                    int flaga=0;
                    for(int a=0;a<hos_obj.size();a++){
                        for(int b=0;b<hos_obj.get(a).getvacc_in_hos().size();b++){
                            if(hos_obj.get(a).getvacc_in_hos().get(b).getv_n().equals(vaccine_name)){
                                System.out.println(getID(a)+" "+hos_obj.get(a).getn());
                                flaga++;
                            }
                        }
                    }
                    if(flaga==0){
                        System.out.println("No registered hospitals currently have this vaccine");
                    }
                    else{
                        System.out.print("Enter hospital id: ");
                        String di=sc.next();
                        int di_index=getIndex(di);
                        boolean corr=(hos_obj.get(di_index)).check(eman,cit_obj,id,vaccine_name);
                        if(corr==true){
                        (hos_obj.get(di_index)).book_slot(eman,cit_obj,id,vaccine_name);}
                        else{
                            System.out.println("---------------------------------");
                            continue;
                        }
                    }

                }
                else if(option==3){
                    System.out.println("---------------------------------");
                    continue;
                }
                else{
                    System.out.println("Invalid choice");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            if(choice==6){
                System.out.print("Enter Hospital Id: ");
                String idee=sc.next();
                int idee_new=getIndex(idee);
                hos_obj.get(idee_new).printSlots();
            }
            if(choice==7){
                System.out.print("Enter Patient ID: ");
                String cit_id=sc.next();
                int flags=0;
                for(int i=0;i<cit_obj.size();i++){
                    if((cit_obj.get(i).geti()).equals(cit_id)){
                        flags++;
                        System.out.println(cit_obj.get(i).getstatus());
                        if(!cit_obj.get(i).getstatus().equals("REGISTERED")){
                        System.out.println("Vaccine Given: "+cit_obj.get(i).getvacc_given());
                        System.out.println("Number of Doses given: "+cit_obj.get(i).getnod());
                        if(cit_obj.get(i).getnext_dose()!=0){
                        System.out.println("Next dose due date: "+cit_obj.get(i).getnext_dose());}
                        }
                    }
                }
            }
            
            System.out.println("---------------------------------");
        }

    }
    public static void main(String args[]){
        
        System.out.println();
        System.out.println("CoWin Portal initialized....");
        System.out.println("---------------------------------");
        Menu obj=new Menu();
        obj.print_menu();
        System.out.println("-----------------------------------------------------------------------------");
    
    }
}
