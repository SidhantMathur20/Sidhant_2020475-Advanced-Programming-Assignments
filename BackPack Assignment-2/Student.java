import java.util.ArrayList;

class Student implements For_view_comment{
    private String ID;
    private ArrayList<Assessment> pending_submissions=new ArrayList<>();
    private ArrayList<Assessment> ungraded=new ArrayList<>();
    private ArrayList<Assessment> graded=new ArrayList<>();
    
    Student(String id) {
        this.ID=id;
    }

    public String get_ID() {
        return ID;
    }

    public void set_assessment(Assignment assignment) {
        this.pending_submissions.add(assignment);
    }

    public void set_assessment(Quiz quiz) {
        this.pending_submissions.add(quiz);
    }

    public ArrayList<Assessment> get_assessments() {
        return this.pending_submissions;
    }

    public void set_graded_assessment(Assignment assignment) {
        this.graded.add(assignment);
    }

    public void set_graded_assessment(Quiz quiz) {
        this.graded.add(quiz);
    }

    public ArrayList<Assessment> get_graded_assessments() {
        return this.graded;
    }

    public void set_ungraded_assessment(Assignment assignment) {
        this.ungraded.add(assignment);
    }

    public void set_ungraded_assessment(Quiz quiz) {
        this.ungraded.add(quiz);
    }

    public ArrayList<Assessment> get_ungraded_assessments() {
        return this.ungraded;
    }

    public void View_comment(Comment comment) {
        
        System.out.printf("%s - %s\n",comment.get_topic(),comment.get_student().get_ID());
        System.out.println(comment.get_date());
        System.out.println();
        
    }
}