import views.*;

public class Main {
    private static final LoginView login = new LoginView();
    private static final OnBoardView onBoard = new OnBoardView();

    public static void main(String[] args) {
        onBoard.displayOnBoard();
        login.showLoginScreen();
    }
}