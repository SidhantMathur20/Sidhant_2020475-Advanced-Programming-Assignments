import java.util.*;

public class Player {
    private String name;
    private int position;
    private int points;
    private Floors current_floor;
    private int star_counter=0;

    Player(String name) {
        this.name=name;
        this.position=-1;
    }

    public String get_name() {
        return this.name;
    }

    public void set_position(int position) {
        this.position=position;
    }
    public int get_position() {
        return this.position;
    }

    public void set_points(int points) {
        this.points=points;
    }
    public int get_points() {
        return this.points;
    }

    public void set_current_floor(Floors current_floor) {
        this.current_floor=current_floor;
    }
    public Floors get_current_floor() {
        return this.current_floor;
    }

    public void set_star() {
        this.star_counter++;
    }

    public int get_star() {
        return this.star_counter;
    }

}
