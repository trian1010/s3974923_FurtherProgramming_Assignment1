/**
 * @author <Nguyen Tran Tri An - s3974923>
 */

import java.util.HashMap;
import java.util.Scanner;


public class InsuranceCardViewText extends InsuranceCardView {
    // Display essential information
    @Override
    public void display(InsuranceCard ic) {
        System.out.println("Card Holder: " + ic.getCardHolder());
        System.out.println("Card Number: " + ic.getCardNumber());
        System.out.println("Policy Owner: " + ic.getPolicyOwner());
        System.out.println("Expiration Date: " + ic.getExpirationDate());
        System.out.println("==============================================================================================");
        System.out.println();
    }

    // Display a form for admin to create a new Insurance Card
    @Override
    public HashMap<String, String> displayNewInsuranceCardForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("New Insurance Card Form");
        System.out.println();
        System.out.println("Enter card holder: ");
        data.put(CARD_HOLDER, scanner.nextLine());
        // Prompts admin to input card number with restriction
        System.out.println("Enter card number (10 numbers): ");
        String CardNumber;
        do {
            CardNumber = scanner.nextLine();
            if (!CardNumber.matches("\\d{10}")) {
                System.out.println("Invalid card number. Must be in the format 10 numbers");
            }
        } while (!CardNumber.matches("\\d{10}"));
        data.put(CARD_NUMBER, CardNumber);
        System.out.println("Enter policy owner: ");
        data.put(POLICY_OWNER, scanner.nextLine());
        // Prompts admin to enter expiration date with restriction
        System.out.println("Enter expiration date (DD/MM/YYYY): ");
        String ExpirationDate;
        do {
            ExpirationDate = scanner.nextLine();
            if (!ExpirationDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println("Invalid expiration date. Must be in the format DD/MM/YYYY.");
            }
        } while (!ExpirationDate.matches("\\d{2}/\\d{2}/\\d{4}"));
        data.put(EXPIRATION_DATE, ExpirationDate);
        return data;
    }

    // Display a form for admin to delete an Insurance Card
    @Override
    public HashMap<String, String> displayDeleteInsuranceCardForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the insurance card's card holder name that you want to delete: ");
        data.put(CARD_HOLDER, scanner.nextLine());
        return data;
    }

    // Display a form for admin to get one Insurance Card
    @Override
    public HashMap<String, String> displayGetOneInsuranceCardForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the insurance card's card holder name that you want to check: ");
        data.put(CARD_HOLDER, scanner.nextLine());
        return data;
    }

    // Display a form for admin to update an Insurance Card
    @Override
    public HashMap<String, String> displayUpdateInsuranceCardForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();

        System.out.println("Enter the insurance card holder's name that you want to update: ");
        String cardHolder = scanner.nextLine();
        data.put(CARD_HOLDER, cardHolder);

        System.out.println("What do you want to update?");
        System.out.println("1. Card Number");
        System.out.println("2. Policy Owner");
        System.out.println("3. Expiration Date");
        System.out.println("Enter the number corresponding to the attribute you want to update: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                // Prompts admin to update card number with restriction
                System.out.println("Enter updated card number (10 numbers): ");
                String cardNumber;
                do {
                    cardNumber = scanner.nextLine();
                    if (!cardNumber.matches("\\d{10}")) {
                        System.out.println("Invalid card number. Must be in the format 10 numbers");
                    }
                } while (!cardNumber.matches("\\d{10}"));
                data.put(CARD_NUMBER, cardNumber);
                break;
            case 2:
                System.out.println("Enter updated policy owner: ");
                data.put(POLICY_OWNER, scanner.nextLine());
                break;
            case 3:
                // Prompts admin to enter expiration date with restriction
                System.out.println("Enter updated expiration date (DD/MM/YYYY): ");
                String expirationDate;
                do {
                    expirationDate = scanner.nextLine();
                    if (!expirationDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        System.out.println("Invalid expiration date. Must be in the format DD/MM/YYYY.");
                    }
                } while (!expirationDate.matches("\\d{2}/\\d{2}/\\d{4}"));
                data.put(EXPIRATION_DATE, expirationDate);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        return data;
    }
}
