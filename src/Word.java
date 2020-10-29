import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Word {
    private ArrayList<WordBank> arrayList = new ArrayList<>();
    private int categories;
    private Game game;
    private Player player;

    public Word(Player playerData) {
        this.arrayList = generateList();
        this.game = new Game();
        this.player = playerData;
    }

    private ArrayList<WordBank> generateList() {

        this.arrayList.add(new Sports());
        this.arrayList.add(new Fruits());
        this.arrayList.add(new Animals());
        return this.arrayList;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease make a selection : ");
        System.out.println("1. Start the Game.");
        System.out.println("2. View the rules of the game.");
        System.out.println("0. Exit the Game.\n");

        try {
            int menuSelection = scanner.nextInt();
            menuSelection(menuSelection);
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid option to for the game.");
            displayMenu();
        }
    }

    private int displayCategories() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type of Categories to play the Game.");
        System.out.println("1. Sports");
        System.out.println("2. Fruits");
        System.out.println("3. Animals");
        int categories = scanner.nextInt();
        if (categories != 1 && categories != 2 && categories != 3) {
            System.out.println("Select category in which you're interested to Play.");
            displayMenu();
        } else {
            return categories;
        }
        return categories;
    }

    private void menuSelection(int menuSelection) {
        Scanner scanner = new Scanner(System.in);
        if (menuSelection == 1) {
            this.categories = displayCategories();
            getCategoryDetail();

        } else if (menuSelection == 2) {

            String userChoice = null;
            System.out.println("                  RULES OF THE GAME                               ");
            System.out.println("1. You can select a Category in which you are interested to play.");
            System.out.println("2. Start guessing letters that you think might be in the word.");
            System.out.println("3. You'll get 10 chances to to win each level.");
            System.out.println("4. Whenever you guess a letter that is in the secret word, the letter will appear in to " +
                    "the blank where it occurs.");
            System.out.println("5. If you guessing a letter that is not in secret word, your playing chances will decrease and new part " +
                    "of Hangman figure will be added.");

            System.out.println(" \n Want to play the Game? Select an option.");


            System.out.println(" Go To Main Menu" + "        Exit        ");
            System.out.println("       Y        " + "          N         ");


            userChoice = scanner.nextLine();
            if (userChoice.equalsIgnoreCase("Y")) {
                displayMenu();
            } else {
                System.out.println("Thank you....");
            }
        } else if (menuSelection == 0) {
            System.out.println("Thank you....");
        } else {
            System.out.println("Please enter valid option to start the game.");
            displayMenu();
        }

    }

    public void getCategoryDetail() {
        String[] categoryList;
        String selectedWord = "";
        String wordToDashes;
        if (categories == 1) {
            categoryList = ((Sports) this.arrayList.get(0)).wordbank();
            // System.out.println("size sport" + categoryList.length);
            selectedWord = getRandomWord(categoryList);
            wordToDashes = generateWordToDashes(selectedWord);
            generateWordArray(wordToDashes, selectedWord);
        }
        if (categories == 2) {

            categoryList = ((Fruits) this.arrayList.get(1)).wordbank();
            // System.out.println("size fruit" + categoryList.length);
            selectedWord = getRandomWord(categoryList);
            wordToDashes = generateWordToDashes(selectedWord);
            generateWordArray(wordToDashes, selectedWord);

        }
        if (categories == 3) {
            categoryList = ((Animals) this.arrayList.get(2)).wordbank();
            //System.out.println("size ANIMAL" + categoryList.length);
            selectedWord = getRandomWord(categoryList);
            wordToDashes = generateWordToDashes(selectedWord);
            generateWordArray(wordToDashes, selectedWord);
        }
    }

    private String generateWordToDashes(String selectedWord) {
        String wordToDashes = "";
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == ' ') {
                System.out.println(i);
                wordToDashes += " ";

            } else {
                wordToDashes += "-";
            }
        }
        return wordToDashes;
    }

    private String getRandomWord(String[] arraylist) {
        int selectedWordPos = new Random().nextInt(arraylist.length);
        String selectedWord = arraylist[selectedWordPos];
        return selectedWord;
    }

    private void generateWordArray(String wordToDashes, String selectedWord) {
        char[] tempGuess;
        if (game.getLevel() < 6) {
            System.out.println("Question " + game.getLevel() + " : " + " You guessed a letter! " + wordToDashes + "\n");
            tempGuess = wordToDashes.toCharArray();
            System.out.println("Alphabet Bank : ");
            char[] alphabetBank = generateBank();
            for (int i = 0; i < alphabetBank.length; i++) {
                System.out.print(" " + alphabetBank[i]);
            }
            userInput(selectedWord, tempGuess, alphabetBank);
        }
    }

    private char[] generateBank() {
        char alphabetBank[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return alphabetBank;
    }

    private void userInput(String selectedWord, char[] tempGuess, char[] alphabetBank) {
        int count = 0;
        char[] incorrectAlphaList = new char[0];
        char[] correctAlphabetList = new char[0];
        char dash = '-';
        String incorrectAlphabetstr = "";
        String correctAlphabetstr = "";
        boolean isLetterFound = false;
        boolean isCorrectLetter = false;
        int inCorrectLetterCount = 0;
        int st = 0;


        if (game.getLevel() < 6) {
            System.out.println("\nWelcome to Level " + game.getLevel());
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("\nEnter your guess:");
                String guessLetter = scanner.next();
                dash = guessLetter.toUpperCase().charAt(0);


                if (incorrectAlphaList != null && incorrectAlphaList.length > 0 || correctAlphabetList.length > 0 && correctAlphabetList != null) {
                    if (incorrectAlphabetstr.contains(guessLetter.toUpperCase()) || correctAlphabetstr.contains(guessLetter.toUpperCase())) {
                        count++;
                        System.out.println(guessLetter.toUpperCase() + " " + "has already been choosen. ");
                    } else {
                        isCorrectLetter = checkCorrectLetter(selectedWord, dash, tempGuess, st);
                        if (isCorrectLetter) {
                            correctAlphabetstr += guessLetter.toUpperCase().charAt(0);
                            correctAlphabetList = correctAlphabetstr.toCharArray();
                        } else {
                            incorrectAlphabetstr += guessLetter.toUpperCase().charAt(0);
                            incorrectAlphaList = incorrectAlphabetstr.toCharArray();
                            inCorrectLetterCount++;
                            drawHangman(inCorrectLetterCount);
                        }
                        count++;
                        st = 0;
                        String tempWord = String.valueOf(tempGuess);
                        if (tempWord.equalsIgnoreCase(selectedWord)) {
                            isLetterFound = true;
                            System.out.println("\nYou finish level " + game.getLevel());
                            game.setLevel(game.getLevel() + 1);
                            game.setCurrentQuestion("Question " + game.getLevel());
                            System.out.println("Awww The Word Was ! " + tempWord);
                        } else {
                            System.out.println("The Word is " + tempWord);
                            displayAlphabetBank(alphabetBank, guessLetter, incorrectAlphaList, correctAlphabetList);
                        }
                    }
                } else {
                    isCorrectLetter = checkCorrectLetter(selectedWord, dash, tempGuess, st);
                    if (isCorrectLetter) {
                        correctAlphabetstr += guessLetter.toUpperCase().charAt(0);
                        correctAlphabetList = correctAlphabetstr.toCharArray();
                    } else {
                        incorrectAlphabetstr += guessLetter.toUpperCase().charAt(0);
                        incorrectAlphaList = incorrectAlphabetstr.toCharArray();
                        inCorrectLetterCount++;
                        drawHangman(inCorrectLetterCount);
                    }
                    count++;
                    st = 0;
                    String tempWord = String.valueOf(tempGuess);
                    if (tempWord.equalsIgnoreCase(selectedWord)) {
                        isLetterFound = true;
                        System.out.println("\nYou finish level " + game.getLevel());
                        game.setLevel(game.getLevel() + 1);
                        game.setCurrentQuestion("Question " + game.getLevel());
                        System.out.println("Awww The Word Was ! " + tempWord);
                    } else {
                        System.out.println("The Word is " + tempWord);
                        displayAlphabetBank(alphabetBank, guessLetter, incorrectAlphaList, correctAlphabetList);
                    }
                }
            } while (count != 10 && !isLetterFound);

            if (!isLetterFound) {
                System.out.println("\nYou couldn't finish the level. please try again...Never give up!!!");
                displayMenu();
            } else {
                if (game.getLevel() == 6) {
                    System.out.println("You WIN! Well Done! " + player.getName());
                } else
                    displayMenu();
            }
        }

    }

    public static boolean checkCorrectLetter(String selectedWord, char dash, char[] tempGuess, int st) {

        boolean isCorrectLetter = false;
        for (int i = st; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == dash) {
                isCorrectLetter = true;
                tempGuess[i] = dash;
                st = i + 1;
            }
        }
        return isCorrectLetter;
    }

    public static void drawHangman(int count) {

        if (count == 1) {
            System.out.println("Wrong guess, try again");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("==========");
            System.out.println();
        }
        if (count == 2) {
            System.out.println("Wrong guess, try again");
            System.out.println("    +");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("==========");
            System.out.println();
        }

        if (count == 3) {
            System.out.println("Wrong guess, try again");
            System.out.println("+------+");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("===============");
            System.out.println();
        }

        if (count == 4) {
            System.out.println("Wrong guess, try again");
            System.out.println("+------+");
            System.out.println("|      |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("===============");
            System.out.println();
        }
        if (count == 5) {
            System.out.println("Wrong guess, try again");
            System.out.println("+-----+");
            System.out.println("|     |");
            System.out.println("0     |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("===============");
            System.out.println();
        }
        if (count == 6) {
            System.out.println("Wrong guess, try again");
            System.out.println("+---+");
            System.out.println("|   |");
            System.out.println("0   |");
            System.out.println("|   |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("===============");
            System.out.println();
        }
        if (count == 7) {
            System.out.println("Wrong guess, try again");
            System.out.println(" +-----+");
            System.out.println(" |     |");
            System.out.println(" 0     |");
            System.out.println("/|     |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("===============");
            System.out.println();

        }
        if (count == 8) {
            System.out.println("Wrong guess, try again");
            System.out.println(" +----+");
            System.out.println(" |     |");
            System.out.println(" 0     |");
            System.out.println("/|\\   |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("===============");
            System.out.println();
        }
        if (count == 9) {
            System.out.println("Wrong guess, try again");
            System.out.println(" +----+");
            System.out.println(" |     |");
            System.out.println(" 0     |");
            System.out.println("/|\\   |");
            System.out.println("/      |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("===============");
            System.out.println();
        }

        if (count == 10) {
            System.out.println("Wrong guess, try again");
            System.out.println(" +----+");
            System.out.println(" |     |");
            System.out.println(" 0     |");
            System.out.println("/|\\   |");
            System.out.println("/ \\   |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("===============");
            System.out.println();
        }

    }

    public static void displayAlphabetBank(char[] alphabetBank, String selectedLetter,
                                           char[] incorrectAlphabet, char[] correctAlphabet) {
        int length;
        for (int a = 0; a < alphabetBank.length; a++) {

            if (alphabetBank[a] == selectedLetter.toUpperCase().charAt(0)) {
                // System.out.println("pos " + a);
                for (int j = a; j < alphabetBank.length; j++) {
                    if (j < 25)
                        alphabetBank[j] = alphabetBank[j + 1];
                }
            }
        }
        System.out.print("Alphabet Bank : ");
        int a = incorrectAlphabet.length + correctAlphabet.length;
        length = alphabetBank.length - a;
        //System.out.println("length" + length);
        for (int i = 0; i < length; i++) {
            System.out.print(" " + alphabetBank[i]);
        }

        if (incorrectAlphabet.length > 0) {
            System.out.print("\nInCorrect Letters :  ");
            for (int i = 0; i < incorrectAlphabet.length; i++) {
                System.out.print(" " + incorrectAlphabet[i]);
            }
        }

        if (correctAlphabet.length != 0 && correctAlphabet.length > 0) {
            System.out.print("\nCorrect Letters : ");
            for (int i = 0; i < correctAlphabet.length; i++) {
                System.out.print(" " + correctAlphabet[i]);
            }
        }
    }


}
