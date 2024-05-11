import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    public static void main(String[] args) {
        long seed=System.currentTimeMillis();
        Scanner get = new Scanner(System.in);
        Random random = new Random(seed);
        int yourhighestrecord=0;
        char keepplaying;
        do{
            int money = 10000;
            int yourrecord = 0;
            while (money > 0) {
                int joker=random.nextInt(2);
                System.out.println("You have "+joker+" times joker");
                int bet = 0;
                System.out.printf("Your current money = %d\n", money);
                System.out.print("enter a command: (SPECIFIC = s (BY HAND) / ALL IN => a / Half => h / %25 => q / %75 => b ) ");
                char command = get.next().charAt(0);
                if (command == 'a') {
                    bet = money;
                } else if (command == 'h') {
                    bet = money / 2;
                } else if (command == 'q') {
                    bet = money / 4;
                } else if (command == 'b') {
                    bet = (money * 3) / 4;
                } else if (command == 's') {
                    System.out.println();
                    System.out.print("How much dollar you bet: ");
                    try {
                        bet = get.nextInt();
                        if (bet > money) {
                            System.out.println("You cannot bet more than your current money. Please enter a valid bet.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid bet. Please enter a valid command.");
                        continue;
                    }
                    System.out.println();
                } else {
                    System.out.println("Invalid command. Please enter a valid command.");
                    continue;
                }

    
                int total = 0;
                int rakip = 0;
                int kart = random.nextInt(15) + 1;
                int rakipkart = random.nextInt(15) + 1;
                total += kart;
                rakip += rakipkart;
                int totalwinner = 0;
    
                while (total < 21 && rakip < 21) {
                    System.out.println("Your card total: " + total);
                    System.out.println("Dealer's card total: " + rakip);
    
                    if (total == 21) {
                        System.out.println("BLACKJACK!");
                        break;
                    }
                    
                    System.out.println();
                    System.out.println("Choose your command : (Double => d / Hit => h / Stand => s /Joker => j ) :");
                    System.out.println();
                    char kartcek = get.next().charAt(0);
    
                    if (kartcek == 'h') {
                        kart = random.nextInt(15) + 1;
                        total += kart;
                    } else if(kartcek == 's') {
                        while (rakip < 17) {
                            rakipkart = random.nextInt(15) + 1;
                            rakip += rakipkart;
                            System.out.println("Dealer drew a card!!");
                            System.out.println("Dealer's card total: " + rakip);
                        }
    
                        if (rakip >= 17) {
                            if (rakip > total && rakip <= 21) {
                                System.out.println("Dealer wins!");
                            } else if (rakip == total) {
                                System.out.println("It's a draw!");
                                money += bet;
                            } else {
                                System.out.println("You win!");
                                totalwinner = 1;
                            }
                        }
                        break;
                    } else if (kartcek == 'd') {
                            if (money >= bet * 2) {
                                bet *= 2; 
                                kart = random.nextInt(15) + 1; 
                                total += kart; 
                                rakipkart = random.nextInt(15) + 1; 
                                rakip += rakipkart; 
                                if ((total <= 21) && (total > rakip || rakip > 21)) { 
                                    totalwinner=1; 
                                    System.out.println("You doubled. You won!"); 
                                } else if (total == rakip) { 
                                    money += bet/2;
                                    totalwinner=0;
                                    System.out.println("Draw!");
                                    
                                } else { 
                                    totalwinner=0; 
                                    System.out.println("You lost!!");
                                    
                                }
                            } else {
                                System.out.println("You don't have enough money for double!"); 
                                continue; 
                            }
                            break;
                    }else if(kartcek=='j'){
                        if(joker>0){
                            kart=random.nextInt(15)+1;
                            System.out.println("Next card is "+kart);
                            System.out.println("Do you want to hit ? (Hit => h / Stand =>s ) :");
                            char hit=get.next().charAt(0);
                            if(hit=='h'){
                                total+=kart;
                            }else if(hit == 's'){
                                rakip+=kart;
                                while (rakip < 17) {
                                    rakipkart = random.nextInt(15) + 1;
                                    rakip += rakipkart;
                                    System.out.println("Dealer drew a card!!");
                                    System.out.println("Dealer's card total: " + rakip);
                                }
            
                                if (rakip >= 17) {
                                    if (rakip > total && rakip <= 21) {
                                        System.out.println("Dealer wins!");
                                    } else if (rakip == total) {
                                        System.out.println("It's a draw!");
                                        money += bet;
                                    } else {
                                        System.out.println("You win!");
                                        totalwinner = 1;
                                    }
                                }
                                break;
                            }
                        }else{
                            System.out.println("You don't have joker !!");
                            continue;
                        }
                    }
                }
    
                if (total > 21) {
                    System.out.println();
                    System.out.printf("Your card total: %d. You went over 21, you lose.\n", total);
                } else if (rakip > 21) {
                    System.out.println();
                    System.out.printf("Dealer's card total: %d. Dealer went over 21, you win.\n", rakip);
                    System.out.println();
                    totalwinner = 1;
                } else if (total == 21) {
                    System.out.println("BLACKJACK! You win.");
                    totalwinner = 1;
                }
    
                if (totalwinner == 1) {
                    money += bet;
                    yourrecord += bet;
                } else {
                    money -= bet;
                }
    
                if (money <= 0) {
                    System.out.println("You've run out of money. Game over!");
                    break;
                }
            }
            if(yourhighestrecord<yourrecord){
                yourhighestrecord=yourrecord;
            }
            System.out.println();
            System.out.println();
            System.out.printf("Your final record is: %d\n", yourrecord);
            System.out.printf("Your highest record is: %d\n", yourhighestrecord);
            System.out.println();
            System.out.println();
            System.out.println("Do you want to keep playing ? (YES => y and No => n )");
            keepplaying=get.next().charAt(0);
        }while(keepplaying == 'y');
        
    }
}



