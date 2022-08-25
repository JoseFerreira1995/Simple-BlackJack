

public class Game {

    private static final int POINTS_TO_WIN = 21;

    private static boolean isGameOver = false;

    public Game() {
    }

    public static void main(String[] args) {
        Player player = new Player("Player");
        Player dealer = new Player("Dealer");
        playGame(player, dealer);
    }

    public static void printWinner(Player player) {
        System.out.println((" The winner of the game is " + player.getName()));
    }

    private static void playGame(Player player, Player dealer) {
        while(!isGameOver){
            if(!doTurn(player)){
                printWinner(dealer);
                return;
            }

            if(!doTurn(dealer)){
                printWinner(player);
                return;
            }
            Player winner = determineWinner(player, dealer);
            if(winner != null) {
                printWinner(winner);
                return;
            }

        }
    }

    private static Player determineWinner(Player player, Player dealer) {
        if(player.getPoints() == 21 && dealer.getPoints() == 21){
            return dealer;
        }
        if(player.getPoints() == 21 && dealer.getPoints() < 21){
            return player;
        }
        return null;
    }

    private static boolean doTurn(Player player) {
        int cardPoints = drawCards();
        player.increasePoints(cardPoints);
        System.out.println((player.getName() + " draw " + cardPoints+ " now has " + player.getPoints() + " points"));

        if(burst(player)){
            System.out.println((player.getName() + " burst " + cardPoints));
            isGameOver= true;
            return false;

        }
        return true;

    }


    private static int drawCards() {
        return (int) (Math.random() * (13 -1) + 1);
    }

    private static boolean burst(Player player){
       return player.getPoints() > POINTS_TO_WIN;
    }


}