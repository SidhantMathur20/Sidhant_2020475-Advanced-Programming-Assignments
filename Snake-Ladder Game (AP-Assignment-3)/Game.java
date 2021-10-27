import java.util.*;

public class Game {

    public static int random() {
        Random random=new Random();
        return random.nextInt(13);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Dice dice=new Dice(2);

        System.out.print("Enter the player name and hit enter: ");
        String name=sc.nextLine();

        Player player=new Player(name);


        empty_floor empty=new empty_floor();

        ladder lad=new ladder();
        elevator elv=new elevator();

        normal_snake snake=new normal_snake();
        king_cobra cobra=new king_cobra();
        

        System.out.println("The game setup is ready");

        
        int Star_position=random();
        System.out.printf("\nBonus Star is at Floor no. %d\n\nStar will give you '2' extra Points\nYou will acquire '1' Star each time you reach it's Floor, but it's change position everytime you acquire it,\nGood Luck Playing :)\n",Star_position);


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
                    System.out.println("Game cannot start until you get 1");
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

            if (player.get_position()==Star_position) {
                System.out.printf("%s has acquired a Star\n",player.get_name());
                player.set_star();
                Star_position=random();
                System.out.printf("Now Bonus Star is at Floor no. %d\n\n",Star_position);
            }
            
            if (player.get_position()==0 || player.get_position()==1 || player.get_position()==3 || player.get_position()==4 || player.get_position()==6 || player.get_position()==7 || player.get_position()==9 || player.get_position()==10 || player.get_position()==12) {
                player.set_current_floor(empty);

                System.out.printf("Player position Floor - %d\n",player.get_position());
                
                System.out.printf("%s has reached an %s\n",player.get_name(),empty.get_type());
                empty.action(player);
            }
            else if (player.get_position()==2) {
                player.set_current_floor(elv);

                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),elv.get_type());
                
                elv.action(player);

                if (player.get_position()==Star_position) {
                    System.out.printf("\n%s has acquired a Star\n",player.get_name());
                    player.set_star();
                    Star_position=random();
                    System.out.printf("Now Bonus Star is at Floor no. %d\n\n",Star_position);
                }

                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==5) {
                player.set_current_floor(snake);
                
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),snake.get_type());
                
                snake.action(player);

                if (player.get_position()==Star_position) {
                    System.out.printf("\n%s has acquired a Star\n",player.get_name());
                    player.set_star();
                    Star_position=random();
                    System.out.printf("Now Bonus Star is at Floor no. %d\n\n",Star_position);
                }

                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==8) {
                player.set_current_floor(lad);
                
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),lad.get_type());
                
                lad.action(player);

                if (player.get_position()==Star_position) {
                    System.out.printf("\n%s has acquired a Star\n",player.get_name());
                    player.set_star();
                    Star_position=random();
                    System.out.printf("Now Bonus Star is at Floor no. %d\n\n",Star_position);
                }

                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==11) {
                player.set_current_floor(cobra);

                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),cobra.get_type());
                
                cobra.action(player);

                if (player.get_position()==Star_position) {
                    System.out.printf("%s has acquired a Star\n",player.get_name());
                    player.set_star();
                    Star_position=random();
                    System.out.printf("Now Bonus Star is at Floor no. %d\n\n",Star_position);
                }

                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
            }
            else if (player.get_position()==13) {
                System.out.printf("Player position Floor - %d\n",player.get_position());
                System.out.printf("%s has reached an %s\n",player.get_name(),"Empty_floor");
                System.out.printf("Total points: %d\n",player.get_points());
                System.out.println("Game Over");
                System.out.println("---------------------------");
                System.out.printf("%s accumulated %d points\n",player.get_name(),player.get_points());
                System.out.println("---------------------------\n");

                System.out.printf("%s accumulated %d points after including the Stars\n\n",player.get_name(),player.get_points()+player.get_star()*2);
                break;
            }
            else {
                System.out.println("Cannot move");
                // empty.action(player);
                player.set_position(12);
            }
            System.out.printf("Total points: %d\n",player.get_points());

            System.out.println("---------------------------");
            System.out.printf("Bonus Star is at Floor no. %d\n",Star_position);
            System.out.println("---------------------------\n");

            System.out.println("---------NEXT TURN---------");
        }
    }

    public static void print_before() {}

    public static void print_after() {}

}

