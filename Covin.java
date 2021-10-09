import java.util.*;

public class Covin {
    
    public static void main(String[] args) {
        int H_id_generator=0;
        ArrayList<vaccine> Added_vaccines=new ArrayList<>();
        ArrayList<hospital> Reg_hospitals=new ArrayList<>();
        ArrayList<citizen> Reg_citizens=new ArrayList<>();
        ArrayList<bookslot> Bookedslots=new ArrayList<>();
        System.out.println("-------------------------------");
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination");
        System.out.println("6. List all slots for a Hospital");
        System.out.println("7. Chack Vaccination Status");
        System.out.println("8. Exit");
        System.out.println("-------------------------------");

        while (true) {

            Scanner sc=new Scanner(System.in);
            int code=sc.nextInt();
        
            if (code==1) {
                System.out.print("Vaccine Name: ");
                String x=sc.next();

                System.out.print("Number of Doses: ");
                int y=sc.nextInt();

                int z=0;
                if (y==1) {}
                else {
                    System.out.print("Gap between Doses: ");
                    z=sc.nextInt();
                }
                System.out.printf("Vaccine Name: %s, Number of Doses: %d, Gap Between Doses: %d\n\n",x,y,z);
                vaccine v=new vaccine(x,y,z);
                Added_vaccines.add(v);
                
            }
            else if (code==2) {
                System.out.print("Hospital Name: ");
                String x=sc.next();

                System.out.print("PinCode: ");
                int y=sc.nextInt();

                System.out.printf("Allocated Hospital ID is %d\n",H_id_generator);
                int z=H_id_generator;

                System.out.printf("Hospital Name: %s, PinCode: %d, Unique ID: %d\n\n",x,y,z);

                hospital h=new hospital(x,y,z);
                Reg_hospitals.add(h);
                H_id_generator++;

            }
            else if (code==3) {
                System.out.print("Citizen Name: ");
                String x=sc.next();

                System.out.print("Age: ");
                int y=sc.nextInt();

                System.out.print("Unique ID: ");
                int z=sc.nextInt();

                System.out.printf("Citizen Name: %s, Age: %d, Unique ID: %d\n\n",x,y,z);
                if (y>18) {
                    citizen c=new citizen(x,y,z);
                    Reg_citizens.add(c);
                }
                else {
                    System.out.println("Only above 18 are allowed");
                }
            }
            else if (code==4) {
                System.out.print("Enter Hospital ID: ");
                int id=sc.nextInt();
                hospital thehos=null;
                for (int hos=0;hos<Reg_hospitals.size();hos++) {
                    if (Reg_hospitals.get(hos).id()==id) {
                        thehos=Reg_hospitals.get(hos);
                        break;
                    }
                }
                System.out.print("Enter number of Slots to be added: ");
                int t=sc.nextInt();
                while (t-->0) {
                    System.out.print("Enter Day Number: ");
                    int x=sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int y=sc.nextInt();
                    System.out.println("Select Vaccine");
                    int i;
                    for (i=0;i<Added_vaccines.size();i++) {
                        System.out.printf("%d. %s\n",i,Added_vaccines.get(i).name());
                    }

                    int z=sc.nextInt();

                    Bookedslots.add(new bookslot(Added_vaccines.get(z).name(),thehos.id(),x,y,thehos.pincode(),thehos.name()));
                    System.out.printf("Slot added by Hospital %d for Day: %d, Available Quantity: %d of Vaccine %s\n",thehos.id(),x,y,Added_vaccines.get(z).name());
                    // System.out.println(Bookedslots);
                }

            }
            else if (code==5) {
                System.out.print("Enter the patient Unique ID: ");
                int patient_id=sc.nextInt();
                citizen p=null;
                for (int i=0;i<Reg_citizens.size();i++) {
                    if (Reg_citizens.get(i).id()==patient_id) {
                        p=Reg_citizens.get(i);
                    }
                }
                System.out.printf("1. Search by area\n2. Search by Vaccine\n3. Exit\nEnter option: ");
                int x=sc.nextInt();
                if (x==1) {
                    System.out.print("Enter PinCode: ");
                    int y=sc.nextInt();
                    ArrayList<Integer> helper=new ArrayList<>();
                    for (int i=0;i<Bookedslots.size();i++) {
                        if (Bookedslots.get(i).picode()==y && !helper.contains(Bookedslots.get(i).hospital_id())) {
                            helper.add(Bookedslots.get(i).hospital_id());
                            System.out.printf("%d %s\n",Bookedslots.get(i).hospital_id(),Bookedslots.get(i).hospital_name());
                        }
                    }
                    System.out.print("Enter hospital id: ");
                    int z=sc.nextInt();

                    ArrayList<bookslot> tempx=new ArrayList<>();
                    for (int i=0;i<Bookedslots.size();i++) {
                        if (Bookedslots.get(i).hospital_id()==z) {
                            Bookedslots.get(i).temp_update(i);
                            tempx.add(Bookedslots.get(i));
                            System.out.printf("%d -> Day: %d Vaccine: %s Available Qty: %d\n",i,Bookedslots.get(i).day(),Bookedslots.get(i).vaccine_name(),Bookedslots.get(i).quantity());
                        }
                    }
                    System.out.print("Choose Slot: ");
                    int o=sc.nextInt();

                    for (int i=0;i<tempx.size();i++) {
                        if (tempx.get(i).temp()==o) {
                           
                            System.out.printf("%s vaccinated with %s\n",p.name(),tempx.get(i).vaccine_name());
                            p.vaccinated_by(tempx.get(i).vaccine_name());
                            p.last_date_of_vaccination_update(tempx.get(i).day());
                            p.add();;
                            tempx.get(i).sub();
                        }
                    }

                    
                    

                }
                else if (x==2) {
                    System.out.print("Enter Vaccine name: ");
                    String y=sc.next();

                    for (int i=0;i<Bookedslots.size();i++) {
                        // System.out.print(Bookedslots.get(i).vaccine_name+" ");
                        if (Bookedslots.get(i).vaccine_name().equals(y)) {
                            System.out.printf("%d %s\n",Bookedslots.get(i).hospital_id(),Bookedslots.get(i).hospital_name());
                        }

                    }
                    System.out.print("Enter hospital id: ");
                    int z=sc.nextInt();

                    boolean b=false;
                    ArrayList<bookslot> tempx=new ArrayList<>();
                    for (int i=0;i<Bookedslots.size();i++) {
                        // System.out.print(Bookedslots.get(i).hospital_id+" "+Bookedslots.get(i).vaccine_name);
                        if (Bookedslots.get(i).hospital_id()==z && Bookedslots.get(i).vaccine_name().equals(y) && p.last_date_of_vaccination()<=Bookedslots.get(i).day()) {
                            Bookedslots.get(i).temp_update(i);
                            tempx.add(Bookedslots.get(i));
                            System.out.printf("%d -> Day: %d Vaccine: %s Available Qty: %d\n",i,Bookedslots.get(i).day(),Bookedslots.get(i).vaccine_name(),Bookedslots.get(i).quantity());
                            b=true;
                        }
                    }
                    if (b==false) {
                        System.out.println("No slot available");    
                    }
                    else {
                        System.out.print("Choose Slot: ");
                        int o=sc.nextInt();

                        for (int i=0;i<tempx.size();i++) {
                            if (tempx.get(i).temp()==o) {
                                
                                System.out.printf("%s vaccinated with %s\n",p.name(),tempx.get(i).vaccine_name());
                                p.vaccinated_by(tempx.get(i).vaccine_name());
                                p.add();
                                tempx.get(i).sub();;
                            }
                        }

                        
                    }
                    
                }
                else if (x==3) {
                    continue;
                }
            }
            else if (code==6) {
                System.out.print("Enter Hospital Id: ");
                int x=sc.nextInt();

                for (int i=0;i<Bookedslots.size();i++) {
                    if (Bookedslots.get(i).hospital_id()==x) {
                        System.out.printf("Day: %d Vaccine: %s Available Qty: %d\n",Bookedslots.get(i).day(),Bookedslots.get(i).vaccine_name(),Bookedslots.get(i).quantity());
                    }
                }
            }
            else if (code==7) {
                System.out.print("Enter Patient ID: ");
                int id=sc.nextInt();
                int b=2;
                citizen c=null;
                int gap=0;
                for (int i=0;i<Reg_citizens.size();i++) {
                    if (Reg_citizens.get(i).id()==id) {
                        int req_num_of_doses=0;
                        c=Reg_citizens.get(i);
                        if (Reg_citizens.get(i).given_number_of_doses()==0) {
                            System.out.println("Citizen Registered");
                        }
                        else {
                            for (int j=0;j<Added_vaccines.size();j++) {
                                if (Added_vaccines.get(j).name()==Reg_citizens.get(i).vaccinated_by()) {
                                    gap=Added_vaccines.get(j).gap();
                                    req_num_of_doses=Added_vaccines.get(i).doses();
                                }
                            }
                            
                            if (Reg_citizens.get(i).given_number_of_doses()>=req_num_of_doses) {
                                System.out.println("FULLY VACCINATED");
                                b=1;
                                break;
                            }
                            else {
                                System.out.println("PARTIALLY VACCINATED");
                                break;
                            }
                        }
                        
                    }
                    else {
                        System.out.println("Citizen Not-Registered");
                    }
                    
                }
                System.out.printf("Vaccine Given: %s\n", c.vaccinated_by());
                System.out.printf("Number of Doses Given: %d\n", c.given_number_of_doses());
                if (b==2) {
                    System.out.printf("Next Dose due date: %d\n", c.last_date_of_vaccination()+gap);
                }
            }
            else if (code==8) {
                break;
            }
            else {
                System.out.println("Wrong Code Entered");
            }
            System.out.printf("\n-------------------------------\n");
            System.out.println("{Menu Options}");
        }
    }  
}
class vaccine {
    private String name;
    private int doses;
    private int gap;
    vaccine(String name,int doses,int gap) {
        this.name=name;
        this.doses=doses;
        this.gap=gap;
    }

    public String name() {
        return this.name;
    }
    public int doses() {
        return this.doses;
    }
    public int gap() {
        return this.gap;
    }
}

class hospital {
    private String name;
    private int pincode;
    private int id;
    hospital(String name,int pincode,int id) {
        this.name=name;
        this.pincode=pincode;
        this.id=id;
    }
    public String name() {
        return this.name;
    }
    public int pincode() {
        return this.pincode;
    }
    public int id() {
        return this.id;
    }
}

class citizen {
    private String name;
    private int age;
    private int id;
    private int given_number_of_doses;
    private int last_date_of_vaccination;
    private String vaccinated_by;

    citizen(String name,int age,int id) {
        this.name=name;
        this.age=age;
        this.id=id;
    }
    public void last_date_of_vaccination_update(int k) {
        this.last_date_of_vaccination=k;
    }
    public void vaccinated_by(String s) {
        this.vaccinated_by=s;
    }
    public void add() {
        this.given_number_of_doses++;
    }
    public String name() {
        return this.name;
    }
    public int age() {
        return this.age;
    }
    public int id() {
        return this.id;
    }
    public int given_number_of_doses() {
        return this.given_number_of_doses;
    }
    public int last_date_of_vaccination() {
        return this.last_date_of_vaccination;
    }
    public String vaccinated_by() {
        return this.vaccinated_by;
    }

}

class bookslot {
    private String vaccine_name;
    private String hospital_name;
    private int picode;
    private int hospital_id;
    private int day;
    private int quantity;
    private int temp;
    bookslot(String name,int id,int day,int quan,int pin,String hos_name) {
        this.vaccine_name=name;
        this.hospital_id=id;
        this.day=day;
        this.quantity=quan;
        this.picode=pin;
        this.hospital_name=hos_name;
    }
    public void temp_update(int k) {
        this.temp=k;
    }
    public void sub() {
        this.quantity--;
    }
    public String vaccine_name() {
        return this.vaccine_name;
    }
    public int hospital_id() {
        return this.hospital_id;
    }
    public int picode() {
        return this.picode;
    }
    public int day() {
        return this.day;
    }
    public int quantity() {
        return this.quantity;
    }
    public int temp() {
        return this.temp;
    }
    public String hospital_name() {
        return this.hospital_name;
    }
}