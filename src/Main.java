import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        System.out.println("-------------------------------------------------");
        System.out.println("*************************************************");
        System.out.println("*             Welcome to HANGMAN                *");
        System.out.println("*************************************************");
        System.out.println("-------------------------------------------------");
        System.out.println("\nEnter your name : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        player.setName(name);
        Word test = new Word(player);
        test.displayMenu();

    }
}
