import java.util.Date;

public class Main {
    public static void main (String[] args) {
        ClaimViewText claimViewText = new ClaimViewText();
        ClaimController controller = new ClaimController(new Claim(), new ClaimViewText());
        claimViewText.displayMainMenu(controller);
    }
}
