import java.util.*;

abstract public class Floors {
    private int gain_or_lose;
    Floors(int gain_or_lose) {
        this.gain_or_lose=gain_or_lose;
    }

    public void set_gain_or_lose(int gain_or_lose) {
        this.gain_or_lose=gain_or_lose;
    }
    public int get_gain_or_lose() {
        return this.gain_or_lose;
    }
}

class empty_floor extends Floors {
    String type;
    empty_floor() {
        super(1);
        this.type="Empty_floor";
    }
    public void action(Player player) {
        player.set_points(player.get_points()+this.get_gain_or_lose());
    }

    public String get_type() {
        return this.type;
    }

}
class normal_snake extends Floors {
    String type;
    normal_snake() {
        super(-2);
        this.type="Snake_floor";
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(1);
        player.set_points(player.get_points()+1);
    }

    public String get_type() {
        return this.type;
    }
}
class king_cobra extends Floors {
    String type;
    king_cobra() {
        super(-4);
        this.type="King cobra_floor";
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(3);
        player.set_points(player.get_points()+1);
    }

    public String get_type() {
        return this.type;
    }
}
class ladder extends Floors {
    String type;
    ladder() {
        super(2);
        this.type="Ladder_floor";
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(12);
        player.set_points(player.get_points()+1);
    }

    public String get_type() {
        return this.type;
    }
}
class elevator extends Floors {
    String type;
    elevator() {
        super(4);
        this.type="Elevator_floor";
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(10);
        player.set_points(player.get_points()+1);
    }

    public String get_type() {
        return this.type;
    }
}

