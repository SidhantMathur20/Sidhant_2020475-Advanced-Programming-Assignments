import java.util.ArrayList;

class Instructor implements For_view_comment{
    private String ID;
    Instructor(String id) {
        this.ID=id;
    }

    public String get_ID() {
        return ID;
    }

    public void View_comment(Comment comment) {
        
        System.out.printf("%s - %s\n",comment.get_topic(),comment.get_instructor().get_ID());
        System.out.println(comment.get_date());
        System.out.println();
    }

}










