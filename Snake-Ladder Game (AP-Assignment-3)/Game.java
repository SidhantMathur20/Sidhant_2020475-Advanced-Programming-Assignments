import java.util.*;

public class Game {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Dice dice=new Dice(2);

        System.out.print("Enter the player name and hit enter: ");
        String name=sc.nextLine();

        Player player=new Player(name);
        System.out.println("The game setup is ready");
        while (true) {
            Boolean bool=false;
            System.out.println("\nHit enter to roll the dice");
            System.out.print("---------------------------");
            String stopper1=sc.nextLine();
            
            if (player.get_position()==-1) {
                while (player.get_position()==-1) {
                    
                    int rol=dice.roll();
                    System.out.printf("Dice gave - %d\n",rol);
                    if (rol==1) {
                        player.set_position(player.get_position()+rol);
                        bool=true;
                        break;
                    }
                    System.out.println("Game cannor start untill you get 1");
                    System.out.println("\nHit enter to roll the dice");
                    System.out.print("---------------------------");
                    String stopper2=sc.nextLine();
                }
            }

            if (bool==false) {
                int rol=dice.roll();
                System.out.printf("Dice gave - %d\n",rol);
                player.set_position(player.get_position()+rol);
            }

            
            if (player.get_position()==0 || player.get_position()==1 || player.get_position()==3 || player.get_position()==4 || player.get_position()==6 || player.get_position()==7 || player.get_position()==9 || player.get_position()==10 || player.get_position()==12) {
                System.out.printf("Player position Floor - %d\n",player.get_position());
                empty_floor empty=new empty_floor();
                System.out.printf("%s has reached an %s\n",player.get_name(),empty.type);
                empty.action(player);
            }
            else if (player.get_position()==2) {
                elevator elv=new elevator();
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),elv.type);
                // System.out.printf("Total points: %d\n",player.get_points());
                elv.action(player);
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==5) {
                normal_snake snake=new normal_snake();
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),snake.type);
                // System.out.printf("Total points: %d\n",player.get_points());
                snake.action(player);
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==8) {
                ladder lad=new ladder();
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),lad.type);
                // System.out.printf("Total points: %d\n",player.get_points());
                lad.action(player);
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==11) {
                king_cobra cobra=new king_cobra();
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),cobra.type);
                // System.out.printf("Total points: %d\n",player.get_points());
                cobra.action(player);
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==13) {
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
                System.out.printf("Total points: %d\n",player.get_points());
                System.out.println("Game Over");
                System.out.printf("%s accumulated %d points\n",player.get_name(),player.get_points());
                break;
            }
            else {
                System.out.println("Cannot move");
                empty_floor empty=new empty_floor();
                empty.action(player);
                player.set_position(12);
            }
            System.out.printf("Total points: %d\n",player.get_points());
        }
    }
}

