import java.util.Date;

public class Main {
    public static void main (String[] args) {
        // Create sample data for Insurance Card
        InsuranceCard insuranceCard1 = new InsuranceCard("Jace", "RMIT University", new Date());

        // Create sample data for PolicyHolder
        PolicyHolder policyHolder1 = new PolicyHolder("c9999999", "Jace", insuranceCard1);

        // Create sample data for Dependant
        Dependent dependent1 = new Dependent("c6666666", "Han", insuranceCard1, policyHolder1);


        ClaimViewText claimViewText = new ClaimViewText();
        ClaimController controller = new ClaimController(new Claim(), new ClaimViewText());
        claimViewText.displayMainMenu(controller);
    }
}
