
import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);
        Random random = new Random();
        int yourhighestrecord=0;
        char keepplaying;
        do{
            int money = 10000;
            int yourrecord = 0;
            while (money > 0) {
                System.out.printf("Your current money = %d\n", money);
                System.out.print("How much dollar you bet: ");
                System.out.println();
                int bet = get.nextInt();
    
                if (bet > money) {
                    System.out.println("You cannot bet more than your current money. Please enter a valid bet.");
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
                    System.out.println("Do you want to draw another card? (YES= y - NO= n)");
                    char kartcek = get.next().charAt(0);
    
                    if (kartcek == 'y') {
                        kart = random.nextInt(15) + 1;
                        total += kart;
                    } else {
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
                                System.out.println();
                                System.out.println("You win!");
                                totalwinner = 1;
                            }
                        }
                        break;
                    }
                }
    
                if (total > 21) {
                    System.out.printf("Your card total: %d. You went over 21, you lose.\n", total);
                } else if (rakip > 21) {
                    System.out.printf("Dealer's card total: %d. Dealer went over 21, you win.\n", rakip);
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
        get.close();
    }
}

