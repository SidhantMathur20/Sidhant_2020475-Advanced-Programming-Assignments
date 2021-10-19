import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BackPack {
    public static void View_Lecture_material(ArrayList<Lecture_slide> lectures,ArrayList<Lecture_video> videos) {
        for (int i=0;i<lectures.size();i++) {
            lectures.get(i).print_data();
        }

        for (int i=0;i<videos.size();i++) {
            videos.get(i).print_data();
        }
    }

    public static void View_Assessments(ArrayList<Assessment> assessments) {
        for (int i=0;i<assessments.size();i++) {
            if (assessments.get(i).getClass().getSimpleName()=="Assignment") {
                System.out.printf("ID: %d Assignment: %s Max Marks: %d\n",i,assessments.get(i).get_problem(),assessments.get(i).get_marks());
            }
            else if (assessments.get(i).getClass().getSimpleName()=="Quiz") {
                System.out.printf("ID: %d Question: %s \n",i,assessments.get(i).get_problem());
            }
        }
        System.out.println();        
    }

    public static void Add_comment(String topic_of_comment,ArrayList<Comment> comments,Date date,Instructor current_instructor) {
        Comment comment=new Comment(topic_of_comment, date, current_instructor);

        comments.add(comment);
    }

    public static void Add_comment(String topic_of_comment,ArrayList<Comment> comments,Date date,Student current_student) {
        Comment comment=new Comment(topic_of_comment, date, current_student);

        comments.add(comment);
    }

    public static void View_comment(ArrayList<Comment> comments) {
        for (int i=0;i<comments.size();i++) {
            if (comments.get(i).get_instructor()==null) {
                // System.out.printf("%s - %s\n",comments.get(i).get_topic(),comments.get(i).get_student().get_ID());
                comments.get(i).get_student().View_comment(comments.get(i));
            }
            else if (comments.get(i).get_student()==null) {
                // System.out.printf("%s - %s\n",comments.get(i).get_topic(),comments.get(i).get_instructor().get_ID());
                comments.get(i).get_instructor().View_comment(comments.get(i));
            }
            // System.out.println(comments.get(i).get_date());
            System.out.println();
        }
    }

    // public static void View_comment(ArrayList<Comment> comments,Student current_student) {
    //     for (int i=0;i<comments.size();i++) {
    //         System.out.printf("%s - %s\n",comments.get(i).get_topic(),comments.get(i).get_student().get_ID());
    //         System.out.println(comments.get(i).get_date());
    //         System.out.println();
    //     }
    // }

    public static void main(String[] args) {

        ArrayList<Lecture_slide> lectures=new ArrayList<>();
        ArrayList<Lecture_video> videos=new ArrayList<>();

        ArrayList<Comment> comments=new ArrayList<>();

        ArrayList<Assessment> assessments=new ArrayList<>();

        

        Scanner sc=new Scanner(System.in);

        Instructor I0=new Instructor("I0");
        Instructor I1=new Instructor("I1");

        ArrayList<Instructor> instructors=new ArrayList<>();
        
        instructors.add(I0);
        instructors.add(I1);

        Student S0=new Student("S0");
        Student S1=new Student("S1");
        Student S2=new Student("S2");

        ArrayList<Student> students=new ArrayList<>();
        
        students.add(S0);
        students.add(S1);
        students.add(S2);

        
        while (true) {

            System.out.printf("Welcome to BackPack\n1. Enter as Instructor\n2. Enter as Student\n3. Exit\n");
            
            int index=Integer.parseInt(sc.nextLine());
            if (index==1) { // Instructor
                System.out.printf("Instructors:\n");
                
                for (int i=0;i<instructors.size();i++) {
                    System.out.printf("%d - %s\n",i,instructors.get(i).get_ID());
                }
                
                System.out.printf("Choose id:");
                Instructor current_instructor=instructors.get(Integer.parseInt(sc.nextLine()));

                while (true) {
                    System.out.printf("Welcome %s\n{INSTRUCTOR MENU}\n1. Add class material\n2. Add assessments\n3. View lectures materials\n4. View assessments\n5. Grade assessments\n6. Close assessment\n7. View comments\n8. Add comments\n9. Logout\n",current_instructor.get_ID());

                    int ID=Integer.parseInt(sc.nextLine());

                    if (ID==1) {
                        System.out.printf("1. Add Lecture Slide\n2. Add Lecture Video\n");
                        int i=Integer.parseInt(sc.nextLine());

                        if (i==1) {
                            
                            System.out.print("Enter topic of slides: ");
                            String topic=sc.nextLine();
                            
                            System.out.print("Enter number of slides: ");
                            int num=Integer.parseInt(sc.nextLine());
                            Date date=java.util.Calendar.getInstance().getTime();
                            Lecture_slide lec=new Lecture_slide(topic,num,current_instructor,date);

                            System.out.println("Enter content of slides: ");
                            for (int j=1;j<=num;j++) {
                                System.out.printf("Content of slide %d: ",j);
                                String con=sc.nextLine();
                                lec.set_content(con);
                            }

                            lectures.add(lec);

                        }
                        else if (i==2) {
                            System.out.print("Enter topic of video: ");
                            String video=sc.nextLine();

                            System.out.print("Enter filename of video: ");
                            String filename=sc.nextLine();

                            if (filename.length()>3 && !filename.substring(filename.length()-4, filename.length()).equals(".mp4")) {
                                System.out.println("Wrong Format");
                                continue;
                            }
                            Date date=java.util.Calendar.getInstance().getTime();
                            Lecture_video vid=new Lecture_video(video,filename,date,current_instructor);

                            videos.add(vid);

                        }
                    }
                    else if (ID==2) {
                        System.out.printf("1. Add Assignment\n2. Add Quiz\n");
                        int i=Integer.parseInt(sc.nextLine());
                        if (i==1) {
                            System.out.print("Enter problem statement: ");
                            String problem=sc.nextLine();
                            
                            System.out.print("Enter marks: ");
                            int marks=Integer.parseInt(sc.nextLine());
                            
                            Assignment Ass=new Assignment(problem, marks, current_instructor);

                            // assignments.add(Ass);

                            assessments.add(Ass);

                            S0.set_assessment(Ass);
                            S1.set_assessment(Ass);
                            S2.set_assessment(Ass);
                        }
                        else if (i==2) {
                            System.out.print("Enter quiz question: ");
                            String problem=sc.nextLine();
                            
                            // System.out.print("Enter marks: ");
                            // int marks=Integer.parseInt(sc.nextLine());
                            int marks=1;

                            Quiz quiz=new Quiz(problem, marks, current_instructor);

                            // quizzes.add(quiz);

                            assessments.add(quiz);

                            S0.set_assessment(quiz);
                            S1.set_assessment(quiz);
                            S2.set_assessment(quiz);
                        }
                    }

                    else if (ID==3) {
                        View_Lecture_material(lectures, videos);
                    }

                    else if (ID==4) {
                        View_Assessments(assessments);
                    }

                    else if (ID==5) {
                        View_Assessments(assessments);

                        System.out.print("Enter ID of the assessments to view submissions: ");
                        int id1=Integer.parseInt(sc.nextLine());
                        System.out.println("Choose ID from these ungraded submissions");
                        if (assessments.get(id1).getClass().getSimpleName().equals("Assignment")) {
                            if (assessments.get(id1).get_submitted_Assignments().size()!=0) {
                                for (int i=0;i<assessments.get(id1).get_submitted_Assignments().size();i++) {
                                    System.out.printf("%d. %s\n",i,assessments.get(id1).get_submitted_Assignments().get(i).get_student().get_ID());
                                }
                                int id2=Integer.parseInt(sc.nextLine());
                                System.out.printf("Submission: %s\n",assessments.get(id1).get_submitted_Assignments().get(id2).get_filename());
                                System.out.println("---------------------------------------------");

                                System.out.printf("Max Marks: %d\n",assessments.get(id1).get_submitted_Assignments().get(id2).get_marks());
                                System.out.print("Marks scored: ");
                                int marks=Integer.parseInt(sc.nextLine());
                                assessments.get(id1).get_submitted_Assignments().get(id2).set_scored_marks(marks);
                                assessments.get(id1).get_submitted_Assignments().get(id2).set_checkedby_instructor(current_instructor);
                                assessments.get(id1).get_submitted_Assignments().get(id2).get_student().set_graded_assessment(assessments.get(id1).get_submitted_Assignments().get(id2));
                                assessments.get(id1).get_submitted_Assignments().get(id2).get_student().get_ungraded_assessments().remove(assessments.get(id1).get_submitted_Assignments().get(id2));
                            }
                            else {
                                System.out.println("No Submissions");
                            }
                        }
                        else {
                            if (assessments.get(id1).get_submitted_quizzess().size()!=0) {
                                for (int i=0;i<assessments.get(id1).get_submitted_quizzess().size();i++) {
                                    System.out.printf("%d. %s\n",i,assessments.get(id1).get_submitted_quizzess().get(i).get_student().get_ID());
                                }
                                int id2=Integer.parseInt(sc.nextLine());
                                System.out.printf("Submission: %s\n",assessments.get(id1).get_submitted_quizzess().get(id2).get_answer());
                                System.out.println("---------------------------------------------");

                                System.out.printf("Max Marks: %d\n",assessments.get(id1).get_submitted_quizzess().get(id2).get_marks());
                                System.out.print("Marks scored: ");
                                int marks=Integer.parseInt(sc.nextLine());
                                assessments.get(id1).get_submitted_quizzess().get(id2).set_scored_marks(marks);
                                assessments.get(id1).get_submitted_quizzess().get(id2).set_checkedby_instructor(current_instructor);
                                assessments.get(id1).get_submitted_quizzess().get(id2).get_student().set_graded_assessment(assessments.get(id1).get_submitted_quizzess().get(id2));
                                assessments.get(id1).get_submitted_quizzess().get(id2).get_student().get_ungraded_assessments().remove(assessments.get(id1).get_submitted_quizzess().get(id2));
                            }
                            else {
                                System.out.println("No Submissions");
                            }
                        }


                    }

                    else if (ID==6) {
                        System.out.println("List of Open Assignments:");
                        View_Assessments(assessments);
                        System.out.print("Enter ID of assignment to close: ");
                        int id=Integer.parseInt(sc.nextLine());

                        Assessment assessment=assessments.get(id);

                        assessments.remove(assessment);
                        S0.get_assessments().remove(assessment);
                        S1.get_assessments().remove(assessment);
                        S2.get_assessments().remove(assessment);
                    }

                    else if (ID==7) {
                        View_comment(comments);
                    }
                    
                    else if (ID==8) {
                        System.out.print("Enter comment: ");
                        String topic_of_comment=sc.nextLine();

                        Date date=java.util.Calendar.getInstance().getTime();
                        Add_comment(topic_of_comment,comments,date,current_instructor);
                    }

                    else if (ID==9) {
                        break;
                    }
                    
                }
            }
            else if (index==2) {
                System.out.printf("Students:\n");
                

                for (int i=0;i<students.size();i++) {
                    System.out.printf("%d - %s\n",i,students.get(i).get_ID());
                }

                System.out.printf("Choose id:");
                Student current_student=students.get(Integer.parseInt(sc.nextLine()));

                while (true) {
                    System.out.printf("Welcome %s\n1. View lectures materials\n2. View assessments\n3. Submit assessments\n4. View grades\n5. View comments\n6. Add comments\n7. Logout\n",current_student.get_ID());
                        
                    int ID=Integer.parseInt(sc.nextLine());

                    if (ID==1) { 
                        View_Lecture_material(lectures,videos);
                    }

                    else if (ID==2) {
                        View_Assessments(assessments);
                    }

                    else if (ID==3) {
                        System.out.println("Pending Assessments");
                        if (current_student.get_assessments().size()==0) {
                            System.out.println("No pending assessments");
                            continue;
                        }
                        for (int i=0;i<current_student.get_assessments().size();i++) {
                            
                            if (current_student.get_assessments().get(i).getClass().getSimpleName()=="Assignment") {
                                System.out.printf("ID: %d Assignment: %s Max Marks: %d\n",i,current_student.get_assessments().get(i).get_problem(),current_student.get_assessments().get(i).get_marks());
                            }
                            else if (current_student.get_assessments().get(i).getClass().getSimpleName()=="Quiz") {
                                System.out.printf("ID: %d Question: %s \n",i,current_student.get_assessments().get(i).get_problem());
                            }
                        }

                        System.out.print("Enter ID of assessment: ");
                        int id=Integer.parseInt(sc.nextLine());

                        // System.out.println(current_student.get_assessments());
                        // System.out.println(current_student.get_assessments().getClass().getSimpleName());
                        if (current_student.get_assessments().get(id).getClass().getSimpleName()=="Assignment") {
                            System.out.print("Enter filename of assignment: ");
                            String filename=sc.nextLine();
                            if (filename.length()>3 && !filename.substring(filename.length()-4,filename.length()).equals(".zip")) {
                                System.out.println("Wrong Format");
                                continue;
                            }

                            Assignment assignment=new Assignment(current_student.get_assessments().get(id).get_problem(), current_student.get_assessments().get(id).get_marks(), current_student);
                            current_student.get_assessments().get(id).set_submitted_Assignments(assignment, filename);
                            current_student.set_ungraded_assessment(assignment);
                            current_student.get_assessments().remove(id);
                            
                        }
                        else if (current_student.get_assessments().get(id).getClass().getSimpleName()=="Quiz") {
                            System.out.print(current_student.get_assessments().get(id).get_problem()+" ");
                            String answer=sc.nextLine();

                            Quiz quiz=new Quiz(current_student.get_assessments().get(id).get_problem(), current_student.get_assessments().get(id).get_marks(), current_student);
                            current_student.get_assessments().get(id).set_submitted_quizzes(quiz, answer);
                            current_student.set_ungraded_assessment(quiz);
                            current_student.get_assessments().remove(id);
                        
                        }
                    }

                    else if (ID==4) {
                        if (current_student.get_graded_assessments().size()!=0) {
                            System.out.println("Graded submissions");
                            for (int i=0;i<current_student.get_graded_assessments().size();i++) {
                                if (current_student.get_graded_assessments().get(i).getClass().getSimpleName()=="Assignment") {
                                    System.out.printf("Submission: %s\nMarks scored %d\nGraded by %s\n",current_student.get_graded_assessments().get(i).get_filename(),current_student.get_graded_assessments().get(i).get_scored_marks(),current_student.get_graded_assessments().get(i).get_checkedby_instructor().get_ID());
                                }
                                else {
                                    System.out.printf("Submission: %s\nMarks scored %d\nGraded by %s\n",current_student.get_graded_assessments().get(i).get_answer(),current_student.get_graded_assessments().get(i).get_scored_marks(),current_student.get_graded_assessments().get(i).get_checkedby_instructor().get_ID());
                                }
                            }
                            System.out.println("----------------------------\n");
                        }

                        if (current_student.get_ungraded_assessments().size()!=0) {
                            System.out.println("UnGraded submissions");
                            for (int i=0;i<current_student.get_ungraded_assessments().size();i++) {
                                if (current_student.get_ungraded_assessments().get(i).getClass().getSimpleName()=="Assignment") {
                                    System.out.printf("Submission: %s\n",current_student.get_ungraded_assessments().get(i).get_filename());
                                }
                                else {
                                    System.out.printf("Submission: %s\n",current_student.get_ungraded_assessments().get(i).get_answer());
                                }
                            }
                            System.out.println();
                        }
                    }

                    else if (ID==5) {
                        View_comment(comments);
                    }

                    else if (ID==6) {
                        System.out.print("Enter comment: ");
                        String topic_of_comment=sc.nextLine();

                        Date date=java.util.Calendar.getInstance().getTime();
                        Add_comment(topic_of_comment,comments,date,current_student);
                    }
                    else if (ID==7) {
                        break;
                    }
                } 
            }

            else if (index==3) {
                break;
            }
            else {}
        }
    }
}

interface For_view_comment {
    public void View_comment(Comment comment);
}

interface Printer {
    public void print_data();
}

class Class_Material {
    private String topic;
    private Instructor instructor;
    private Student student;
    private Date date_of_upload;

    
    Class_Material(String topic,Instructor instructor,Date date) {
        this.topic=topic;
        this.instructor=instructor;
        this.date_of_upload=date;
    }
    Class_Material(String topic,Student student,Date date) {
        this.topic=topic;
        this.student=student;
        this.date_of_upload=date;
    }

    public void set_topic(String topic) {
        this.topic=topic;
    }
    public String get_topic() {
        return this.topic;
    }

    public void set_date(Date date) {
        this.date_of_upload=date;
    }
    public Date get_date() {
        return this.date_of_upload;
    }

    public void set_instructor(Instructor instructor) {
        this.instructor=instructor;
    }
    public Instructor get_instructor() {
        return this.instructor;
    }

    public void set_student(Student student) {
        this.student=student;
    }
    public Student get_student() {
        return this.student;
    }
    
}

class Lecture_slide extends Class_Material implements Printer{
    private int number_of_slides;
    private ArrayList<String> Content=new ArrayList<>();

    Lecture_slide(String topic,int number_of_slides,Instructor instructor,Date date) {
        super(topic,instructor,date);
        this.number_of_slides=number_of_slides;
    }

    public void set_number_of_slides(int slides) {
        this.number_of_slides=slides;
    }

    public int get_number_of_slides() {
        return this.number_of_slides;
    }

    public void set_content(String content) {
        this.Content.add(content);
    }

    public ArrayList<String> get_content() {
        return this.Content;
    }

    public void print_data() {
        
        System.out.printf("Title: %s\n",this.get_topic());

        ArrayList<String> content=this.get_content();
        for (int j=0;j<content.size();j++) {
            System.out.printf("Slide %d: %s\n",j,content.get(j));
        }

        System.out.printf("Number of Slides: %d\n",this.number_of_slides);
        System.out.println("Date of upload: "+this.get_date());
        System.out.printf("Uploaded by: %s\n\n",this.get_instructor().get_ID());
        
    }
}

class Lecture_video extends Class_Material implements Printer{
    private String filename;

    Lecture_video(String topic,String filename,Date date,Instructor instructor) {
        super(topic,instructor,date);
        this.filename=filename;
    }

    public void set_filename(String filename) {
        this.filename=filename;
    }
    public String get_filename() {
        return this.filename;
    }

    public void print_data() {
        
        System.out.printf("Title of video: %s\nVideo file: %s\n",this.get_topic(),this.get_filename());

        System.out.println("Date of upload: "+this.get_date());
        System.out.printf("Uploaded by: %s\n\n",this.get_instructor().get_ID());
        
    }
  
}

class Comment extends Class_Material{
    Comment(String topic,Date date,Instructor instructor) {
        super(topic,instructor,date);
    }

    Comment(String topic,Date date,Student student) {
        super(topic,student,date);
    }
}

class Assessment {
    private String problem;
    private int marks;
    private int marks_scored;
    private Instructor instructor;
    private Instructor Checkedby;
    private Student student;
    private String filename;
    private String answer;
    private ArrayList<Assignment> submitted_assignments=new ArrayList<>();
    private ArrayList<Quiz> submitted_quizzes=new ArrayList<>();

    Assessment(String problem,int marks,Instructor instructor) {
        this.problem=problem;
        this.marks=marks;
        this.instructor=instructor;
    }
    Assessment(String problem,int marks,Student student) {
        this.problem=problem;
        this.marks=marks;
        this.student=student;
    }

    public void set_problem(String problem) {
        this.problem=problem;
    }
    public String get_problem() {
        return this.problem;
    }

    public void set_marks(int marks) {
        this.marks=marks;
    }
    public int get_marks() {
        return this.marks;
    }

    public void set_scored_marks(int marks) {
        this.marks_scored=marks;
    }
    public int get_scored_marks() {
        return this.marks_scored;
    }

    public void set_instructor(Instructor instructor) {
        this.instructor=instructor;
    }
    public Instructor get_instructor() {
        return this.instructor;
    }

    public void set_checkedby_instructor(Instructor instructor) {
        this.Checkedby=instructor;
    }
    public Instructor get_checkedby_instructor() {
        return this.Checkedby;
    }

    public void set_student(Student student) {
        this.student=student;
    }
    public Student get_student() {
        return this.student;
    }

    public void set_filename(String filename) {
        this.filename=filename;
    }
    public String get_filename() {
        return this.filename;
    }

    public void set_answer(String answer) {
        this.answer=answer;
    }
    public String get_answer() {
        return this.answer;
    }

    public void set_submitted_Assignments(Assignment assignment,String filename) {
        
        assignment.set_filename(filename);
        this.submitted_assignments.add(assignment);
    }
    public ArrayList<Assignment> get_submitted_Assignments() {
        return this.submitted_assignments;
    }

    public void set_submitted_quizzes(Quiz quiz,String answer) {
        
        quiz.set_answer(answer);
        this.submitted_quizzes.add(quiz);
    }
    public ArrayList<Quiz> get_submitted_quizzess() {
        return this.submitted_quizzes;
    }
}

class Assignment extends Assessment{
    private String filename;
    private ArrayList<Assignment> submitted_assignments=new ArrayList<>();

    Assignment(String problem,int marks,Instructor instructor) {
        super(problem,marks,instructor);
    }
    Assignment(String problem,int marks,Student student) {
        super(problem,marks,student);
    }

    @Override
    public void set_filename(String filename) {
        this.filename=filename;
    }
    @Override
    public String get_filename() {
        return this.filename;
    }
    
    public void set_submitted_Assignments(Assignment assignment,String filename) {
        
        assignment.set_filename(filename);
        this.submitted_assignments.add(assignment);
    }
    public ArrayList<Assignment> get_submitted_Assignments() {
        return this.submitted_assignments;
    }

}

class Quiz extends Assessment{
    private String answer;
    private ArrayList<Quiz> submitted_quizzes=new ArrayList<>();

    Quiz(String problem,int marks,Instructor instructor) {
        super(problem,marks,instructor);
    }
    Quiz(String problem,int marks,Student student) {
        super(problem,marks,student);
    }

    @Override
    public void set_answer(String answer) {
        this.answer=answer;
    }
    @Override
    public String get_answer() {
        return this.answer;
    }

    public void set_submitted_quizzes(Quiz quiz,String answer) {
        
        quiz.set_answer(answer);
        this.submitted_quizzes.add(quiz);
    }
    public ArrayList<Quiz> get_submitted_quizzess() {
        return this.submitted_quizzes;
    }
}


