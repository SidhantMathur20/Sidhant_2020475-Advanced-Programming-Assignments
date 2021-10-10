import java.util.*;

public class Covin {
    
    public static void main(String[] args) {
        int H_id_generator=100000;
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

            // System.out.println(Added_vaccines);
            // System.out.println(Reg_citizens);
            // System.out.println(Reg_hospitals);
            // System.out.println(Bookedslots);

            System.out.printf("\n-------------------------------\n");
            System.out.println("{Menu Options}");

            Scanner sc=new Scanner(System.in);
            int code=sc.nextInt();
        
            if (code==1) {
                System.out.print("Vaccine Name: ");
                String x=sc.next();

                // Error Manager
                boolean fat=false;
                for (int i=0;i<Added_vaccines.size();i++) {
                    if (Added_vaccines.get(i).name().equals(x)) { // For vaccines with same name.
                        System.out.println("Vaccine name Already exist");
                        fat=true;
                        break;
                    }
                }
                if (fat==true) {
                    continue;
                }
                // Error Manager

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

                // Error Manager
                boolean fat=false;
                for (int i=0;i<Reg_hospitals.size();i++) {
                    if (Reg_hospitals.get(i).name().equals(x) && Reg_hospitals.get(i).pincode()==y) { // Incase Hospital is already registered.
                        System.out.println("Hospital Already exist");
                        fat=true;
                        break;
                    }
                }
                if (fat==true) {
                    continue;
                } 

                if (y<100000 || y>999999) { // Basic Syntex.
                    System.out.println("Wrong Pincode");
                    continue;
                }
                // Error Manager

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
                String z=sc.next();
                
                // Error Manager 
                if (z.length()!=12) { // Basic length of the ID.
                    System.out.println("Wrong Input");
                    continue;
                }

                boolean fat=false;
                for (int i=0;i<Reg_citizens.size();i++) {
                    if (Reg_citizens.get(i).id().equals(z)) { // Incase someone Enter the ID that already exist.
                        System.out.println("Citizen Already exist");
                        fat=true;
                        break;
                    }
                }
                if (fat==true) {
                    continue;
                }
                // Error Manager

                System.out.printf("Citizen Name: %s, Age: %d, Unique ID: %s\n\n",x,y,z);
                if (y>=18) {
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

                // Error Manager
                boolean bil=false;
                for (int i=0;i<Reg_hospitals.size();i++) {
                    if (Reg_hospitals.get(i).id()==id) { // Incase user enter the Hospital ID that do not Exist.
                        bil=true;
                        thehos=Reg_hospitals.get(i);
                    }
                }

                if (id<100000 || id>999999 || bil==false) {
                    System.out.println("Wrong Input");
                    continue;
                    
                }
                // Error Manager

                
                
                System.out.print("Enter number of Slots to be added: ");
                int t=sc.nextInt();

                

                while (t-->0) {
                    System.out.print("Enter Day Number: ");
                    int x=sc.nextInt();


                    System.out.print("Enter Quantity: ");
                    int y=sc.nextInt();
                    
                    // Error Manager
                    if (y<1) { // Quantity should be positive.
                        System.out.println("Wrong Input");
                        continue;
                    }
                    // Error Manager

                    System.out.println("Select Vaccine");
                    int i;
                    for (i=0;i<Added_vaccines.size();i++) {
                        System.out.printf("%d. %s\n",i,Added_vaccines.get(i).name());
                    }

                    int z=sc.nextInt();

                    if (z<0 || z>=i) {
                        System.out.println("Wrong Input");
                        continue;
                    }

                    // Error Manager
                    boolean fat=false;
                    for (int m=0;m<Bookedslots.size();m++) {
                        if (Bookedslots.get(m).hospital_id()==id && Bookedslots.get(m).day()==x && Bookedslots.get(m).vaccine_name().equals(Added_vaccines.get(z).name())) {
                            System.out.println("Slot Already exist"); // Incase the Slot already exist.
                            fat=true;
                            break;
                        }
                    }
                    if (fat==true) {
                        continue;
                    }
                    // Error Manager

                    Bookedslots.add(new bookslot(Added_vaccines.get(z).name(),thehos.id(),x,y,thehos.pincode(),thehos.name()));
                    System.out.printf("Slot added by Hospital %d for Day: %d, Available Quantity: %d of Vaccine %s\n",thehos.id(),x,y,Added_vaccines.get(z).name());
                    
                }

            }
            else if (code==5) {
                System.out.print("Enter the patient Unique ID: ");
                String patient_id=sc.next();

                citizen p=null;

                // Error Manager
                boolean fat=false;
                for (int i=0;i<Reg_citizens.size();i++) {
                    if (Reg_citizens.get(i).id().equals(patient_id)) {
                        fat=true;
                        p=Reg_citizens.get(i);
                    }
                }
                if (fat==false) {
                    System.out.println("Citizen not registered");
                    continue;
                    
                }
                // Error Manager

                System.out.printf("1. Search by area\n2. Search by Vaccine\n3. Exit\nEnter option: ");
                int x=sc.nextInt();

                // Error Manager
                if (x<1 || x>3) {
                    System.out.println("Wrong Input");
                    continue;
                }
                // Error Manager

                if (x==1) {
                    System.out.print("Enter PinCode: ");
                    int y=sc.nextInt();

                    // Error Manager
                    if (y<100000 || y>999999) {
                        System.out.println("Wrong Length of the Pincode"); // 
                        continue;
                    }

                    ArrayList<Integer> helper=new ArrayList<>(); // Use incase Same hospital print again and again.
                    for (int i=0;i<Bookedslots.size();i++) {
                        if (Bookedslots.get(i).picode()==y && !helper.contains(Bookedslots.get(i).hospital_id())) {
                            helper.add(Bookedslots.get(i).hospital_id());
                            System.out.printf("%d %s\n",Bookedslots.get(i).hospital_id(),Bookedslots.get(i).hospital_name());
                        }
                    }
                    

                    System.out.print("Enter hospital id: ");
                    int z=sc.nextInt();

                    if (!helper.contains(z) && (z<100000 || z>999999)) { // Incase Hospital ID do not exist or syntex is Incorrect.
                        System.out.println("Wrong Hopital ID");
                        continue;
                    }


                    boolean b=false;
                    ArrayList<Integer> helper2=new ArrayList<>();
                    ArrayList<bookslot> tempx=new ArrayList<>();
                    for (int i=0;i<Bookedslots.size();i++) {
                        if (Bookedslots.get(i).hospital_id()==z && p.last_date_of_vaccination()<Bookedslots.get(i).day()) {
                            Bookedslots.get(i).temp_update(i);
                            tempx.add(Bookedslots.get(i));
                            helper2.add(i);
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

                        if (!helper2.contains(o)) {
                            System.out.println("Wrong Input");
                            continue;
                        }
                        boolean help=false;
                        for (int i=0;i<tempx.size();i++) {
                            if (tempx.get(i).temp()==o) {
                                if (tempx.get(i).quantity()>0 && (p.vaccinated_by()==null || tempx.get(i).vaccine_name().equals(p.vaccinated_by()))) {
                                    System.out.printf("%s vaccinated with %s\n",p.name(),tempx.get(i).vaccine_name());
                                    p.vaccinated_by(tempx.get(i).vaccine_name());
                                    p.last_date_of_vaccination_update(tempx.get(i).day());
                                    p.add(); // To Increment the variable given_number_of_doses.
                                    tempx.get(i).sub(); // To Decrement the variable quantity.
                                }
                                else {
                                    System.out.println("Either Hospital is out of Stock \nor You have earlier taken a different Vaccine");
                                    help=true;
                                    break;
                                }
                            }
                            
                            
                        }
                        if (help==true) {
                            continue;
                        }
                    }

                }
                else if (x==2) {
                    System.out.print("Enter Vaccine name: ");
                    String y=sc.next();

                    // Error Manager
                    boolean extra_help=false;

                    ArrayList<Integer> helper=new ArrayList<>();
                    for (int i=0;i<Bookedslots.size();i++) {
                        
                        if (Bookedslots.get(i).vaccine_name().equals(y)&& !helper.contains(Bookedslots.get(i).hospital_id())) {
                            System.out.printf("%d %s\n",Bookedslots.get(i).hospital_id(),Bookedslots.get(i).hospital_name());
                            helper.add(i);
                            extra_help=true;
                        }

                    }
                    if (extra_help==false) {
                        System.out.println("The Vaccine is not Registered or you have enter the wrong name or No slot is created yet");
                        continue;
                    }
                    // Error Manager

                    System.out.print("Enter hospital id: ");
                    int z=sc.nextInt();

                    // Error Manager
                    boolean bil=false;
                    for (int i=0;i<Reg_hospitals.size();i++) {
                        if (Reg_hospitals.get(i).id()==z) {
                            bil=true;
                        }
                    }

                    if (z<100000 || z>999999 || bil==false) {
                        System.out.println("Wrong Hopital ID");
                        continue;
                        
                    }
                    // Error Manager

                    boolean b=false;
                    ArrayList<Integer> helper2=new ArrayList<>();
                    ArrayList<bookslot> tempx=new ArrayList<>();
                    for (int i=0;i<Bookedslots.size();i++) {
                        if (Bookedslots.get(i).hospital_id()==z && Bookedslots.get(i).vaccine_name().equals(y) && p.last_date_of_vaccination()<Bookedslots.get(i).day()) {
                            Bookedslots.get(i).temp_update(i);
                            tempx.add(Bookedslots.get(i));
                            helper2.add(i);
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

                        if (!helper2.contains(o)) {
                            System.out.println("Wrong Input");
                            continue;
                        }

                        boolean help=false;
                        for (int i=0;i<tempx.size();i++) {
                            if (tempx.get(i).temp()==o) {
                                if (tempx.get(i).quantity()>0 && (p.vaccinated_by()==null || tempx.get(i).vaccine_name().equals(p.vaccinated_by()))) {
                                    System.out.printf("%s vaccinated with %s\n",p.name(),tempx.get(i).vaccine_name());
                                    p.vaccinated_by(tempx.get(i).vaccine_name());
                                    p.last_date_of_vaccination_update(tempx.get(i).day());
                                    p.add(); // To Increment the variable given_number_of_doses.
                                    tempx.get(i).sub(); // To Decrement the variable quantity.
                                }
                                else {
                                    System.out.println("Either Hospital is out of Stock \nor You have earlier taken a different Vaccine");
                                    help=true;
                                    break;
                                }
                            }
                            
                        }
                        if (help==true) {
                            continue;
                        }
                        
                    }
                    
                }
                else if (x==3) {
                    // continue;
                }
            }
            else if (code==6) {
                System.out.print("Enter Hospital Id: ");
                int x=sc.nextInt();

                boolean fat=false;
                for (int i=0;i<Reg_hospitals.size();i++) {
                    if (Reg_hospitals.get(i).id()==x) {
                        fat=true;
                        break;
                    }
                }
                if (fat==false) {
                    System.out.println("Hospital not Registered exist");
                    continue;
                }

                if (x<100000 || x>999999) {
                    System.out.println("Wrong Pincode");
                    continue;
                }

                for (int i=0;i<Bookedslots.size();i++) {
                    if (Bookedslots.get(i).hospital_id()==x) {
                        System.out.printf("Day: %d Vaccine: %s Available Qty: %d\n",Bookedslots.get(i).day(),Bookedslots.get(i).vaccine_name(),Bookedslots.get(i).quantity());
                    }
                }
            }
            else if (code==7) {
                System.out.print("Enter Patient ID: ");
                String id=sc.next();
                // Error Manager 
                if (id.length()!=12) { // Basic length of the ID.
                    System.out.println("Wrong Input");
                    continue;
                }
                int b=0;
                citizen c=null;
                int gap=0;
                for (int i=0;i<Reg_citizens.size();i++) {
                    if (Reg_citizens.get(i).id().equals(id)) {
                        int req_num_of_doses=0;
                        
                        c=Reg_citizens.get(i);
                        if (Reg_citizens.get(i).given_number_of_doses()==0) {
                            System.out.println("Citizen Registered");
                            b=1;
                        }
                        else {
                            for (int j=0;j<Added_vaccines.size();j++) {
                                if (Added_vaccines.get(j).name()==Reg_citizens.get(i).vaccinated_by()) {
                                    gap=Added_vaccines.get(j).gap();
                                    req_num_of_doses=Added_vaccines.get(j).doses();
                                }
                            }
                            
                            if (Reg_citizens.get(i).given_number_of_doses()>=req_num_of_doses) {
                                System.out.println("FULLY VACCINATED");
                                b=2;
                                break;
                            }
                            else {
                                System.out.println("PARTIALLY VACCINATED");
                                b=3;
                                break;
                            }
                        }
                        
                    }
                    
                    
                }
                if (b==0) {
                    System.out.println("Citizen Not-Registered");
                }
                else if (b==2 || b==3) {
                    System.out.printf("Vaccine Given: %s\n", c.vaccinated_by());
                    System.out.printf("Number of Doses Given: %d\n", c.given_number_of_doses());
                    if (b==3) {
                        System.out.printf("Next Dose due date: %d\n", c.last_date_of_vaccination()+gap);
                    }
                }
                
            }
            else if (code==8) {
                System.out.println("{End of Test Case}");
                break;
            }
            else {
                System.out.println("Wrong Code Entered");
            }  
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
    private String id;
    private int given_number_of_doses;
    private int last_date_of_vaccination;
    private String vaccinated_by;

    citizen(String name,int age,String id) {
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
    public String id() {
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