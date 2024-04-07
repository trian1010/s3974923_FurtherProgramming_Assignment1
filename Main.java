/**
 * @author <Nguyen Tran Tri An - s3974923>
 */

public class Main {
    public static void main (String[] args) {
        // Create instances of ClaimViewText and ClaimViewController
        ClaimViewText claimViewText = new ClaimViewText();
        ClaimController controller = new ClaimController(new Claim(), new ClaimViewText());

        // Create instances of InsuranceCardViewText and InsuranceCardController
        InsuranceCardViewText insuranceCardViewText = new InsuranceCardViewText();
        InsuranceCardController icController = new InsuranceCardController(new InsuranceCard(), new InsuranceCardViewText());

        // Navigate to the Main Menu
        claimViewText.displayMainMenu(controller, icController);
    }
}
