import java.util.*;

abstract public class Floors {
    private int gain_or_lose;
    private String type;
    Floors(int gain_or_lose, String type) {
        this.gain_or_lose=gain_or_lose;
        this.type=type;
    }

    public void set_gain_or_lose(int gain_or_lose) {
        this.gain_or_lose=gain_or_lose;
    }
    public int get_gain_or_lose() {
        return this.gain_or_lose;
    }

    public String get_type() {
        return this.type;
    }
}

class empty_floor extends Floors {
    
    empty_floor() {
        super(1, "Empty_floor");
        
    }
    public void action(Player player) {
        player.set_points(player.get_points()+this.get_gain_or_lose());
    }


}
class normal_snake extends Floors {
    
    normal_snake() {
        super(-2, "Snake_floor");
        
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(1);
        player.set_points(player.get_points()+1);
    }

}
class king_cobra extends Floors {
    
    king_cobra() {
        super(-4, "King cobra_floor");
        
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(3);
        player.set_points(player.get_points()+1);
    }

    
}
class ladder extends Floors {
    
    ladder() {
        super(2, "Ladder_floor");
        
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(12);
        player.set_points(player.get_points()+1);
    }

 
}
class elevator extends Floors {
    
    elevator() {
        super(4, "Elevator_floor");
    
    }
    public void action(Player player) {
        // System.out.printf("Player position Floor - %d\n",player.get_position());
        player.set_points(player.get_points()+this.get_gain_or_lose());

        System.out.printf("Total points: %d\n",player.get_points());

        player.set_position(10);
        player.set_points(player.get_points()+1);
    }


}

