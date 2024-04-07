import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClaimViewText extends ClaimView {
    // Form to display main menu
    @Override
    public void displayMainMenu(ClaimController controller) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("COSC2440 Further Programming");
        System.out.println("CONSOLE APP");
        System.out.println("Instructor: Mr. Minh Vu Thanh");
        System.out.println("s3974923");
        System.out.println("Nguyen Tran Tri An");
        System.out.println("====================================");
        System.out.println("CONSOLE APP MAIN MENU");
        System.out.println("Press a number to execute specific operation");
        System.out.println("'1' to add a Claim");
        System.out.println("'2' to update a Claim");
        System.out.println("'3' to delete a Claim");
        System.out.println("'4' to get one Claim");
        System.out.println("'5' to get all Claim");
        System.out.println("'6' to exit the system");
        int menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
                controller.add();
                displayMainMenu(controller);
                return;
            case 2:
                controller.update();
                displayMainMenu(controller);
            case 3:
                controller.delete();
                displayMainMenu(controller);
            case 4:
                controller.getOne();
                displayMainMenu(controller);
                return;
            case 5:
                controller.getAll();
                displayMainMenu(controller);
                return;
            case 6:
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option");
        }
    }

    // Form to display a Claim
    @Override
    public void display(Claim c) {
        System.out.println("Claim id: " + c.getId());
        System.out.println("Claim date: " + c.getClaimDate());
        System.out.println("Claim insured person: " + c.getInsuredPerson());
        System.out.println("Claim card number: " + c.getCardNumber());
        System.out.println("Claim exam date: " + c.getExamDate());
        System.out.println("Claim document list: " + c.getDocumentList());
        System.out.println("Claim amount: " + c.getClaimAmount());
        System.out.println("Claim status: " + c.getStatus());
        System.out.println("Claim receiver banking info: " + c.getReceiverBankingInfo());
        System.out.println("==============================================================================================");
        System.out.println();
    }

    // Display a form for admin to create a new Claim
    @Override
    public HashMap<String, String> displayNewClaimForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("New Claim Form");
        System.out.println();
        System.out.println("Enter ID for the new claim (10 numbers): ");
        // Restriction due to the requirement prompting admin to input Claim id with the correct format
        String id;
        do {
            id = scanner.nextLine();
            if (!id.matches("\\d{10}")) {
                System.out.println("Invalid Claim ID. Must be 10 numbers.");
            }
        } while (!id.matches("\\d{10}"));
        String ClaimID = "f-" + id;
        data.put(CLAIM_ID, ClaimID);
        data.put("CLAIM_DATE", String.valueOf(CLAIM_DATE.getTime()));
        System.out.println("Enter insured person: ");
        data.put(INSURED_PERSON, scanner.nextLine());
        // Restriction due to the requirement prompting admin to input Claim's insurance card number with the correct format
        System.out.println("Enter card number (10 numbers): ");
        String CardNumber;
        do {
            CardNumber = scanner.nextLine();
            if (!CardNumber.matches("\\d{10}")) {
                System.out.println("Invalid card number. Must be in the format 10 numbers");
            }
        } while (!CardNumber.matches("\\d{10}"));
        data.put(CARD_NUMBER, CardNumber);
        data.put("EXAM_DATE", String.valueOf(EXAM_DATE.getTime()));
        System.out.println("Input your documents; spacing with ',': ");
        ArrayList<String> documentList = new ArrayList<>();
        // Restriction due to the requirement prompting admin to input Documents with the correct format
        String[] documents = scanner.nextLine().split(",");
        for (String docs : documents) {
            String PDFName = ClaimID + "_" + docs.trim() + ".PDF";
            documentList.add(PDFName);
        }
        data.put(String.valueOf(DOCUMENT_LIST), String.join(",", documentList));
        System.out.println("Enter the claim amount: ");
        data.put(String.valueOf(CLAIM_AMOUNT), String.valueOf(scanner.nextDouble()));
        scanner.nextLine();
        System.out.println("Enter status (1 for New, 2 for Processing, 3 for Done): ");
        // Accepting user input for status using switch case
        String statusInput = scanner.nextLine();
        String status;
        switch (statusInput) {
            case "1":
                status = Status.New.name();
                break;
            case "2":
                status = Status.Processing.name();
                break;
            case "3":
                status = Status.Done.name();
                break;
            default:
                System.out.println("Invalid input. Setting status to New by default.");
                status = Status.New.name();
                break;
        }
        data.put(STATUS, status);

        System.out.println("Enter Receiver Banking Info");
        System.out.println("Enter the Bank: ");
        data.put(RECEIVER_BANKING_INFO_BANK, scanner.nextLine());
        System.out.println("Enter Your Name");
        data.put(RECEIVER_BANKING_INFO_NAME, scanner.nextLine());
        System.out.println("Enter bank number: ");
        data.put(RECEIVER_BANKING_INFO_NUMBER, scanner.nextLine());
        ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo();

        return data;
    }

    // Display a form for admin to get one Claim through its ID
    @Override
    public HashMap<String, String> displayGetOneClaimForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the ID of the Claim that you want to check: ");
        data.put(CLAIM_ID, scanner.nextLine());
        return data;
    }

    // Display a form for admin to delete a Claim through its ID
    @Override
    public HashMap<String, String> displayDeleteClaimForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the ID of the Claim that you want to delete: ");
        data.put(CLAIM_ID, scanner.nextLine());
        return data;
    }

    // Display a form for admin to update a Claim through its ID
    @Override
    public HashMap<String, String> displayUpdateClaimForm() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the ID of the Claim that you want to update: ");
        String ClaimID = scanner.nextLine();
        data.put(CLAIM_ID, ClaimID);

        System.out.println("What do you want to update?");
        System.out.println("1. Insured Person");
        System.out.println("2. Card Number");
        System.out.println("3. Document List");
        System.out.println("4. Claim Amount");
        System.out.println("5. Status");
        System.out.println("6. Receiver Banking Info");
        System.out.println("Enter the number corresponding to the attribute you want to update: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.println("Enter updated insured person: ");
                data.put(INSURED_PERSON, scanner.nextLine());
                break;
            case 2:
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
            case 3:
                System.out.println("Enter updated documents; spacing with ',': ");
                ArrayList<String> updatedDocumentList = new ArrayList<>();
                String[] updatedDocuments = scanner.nextLine().split(",");
                for (String doc : updatedDocuments) {
                    String trimmedDoc = doc.trim();
                    if (!trimmedDoc.isEmpty()) {
                        String PDFName = ClaimID + "_" + trimmedDoc + ".PDF";
                        updatedDocumentList.add(PDFName);
                    }
                }
                data.put(String.valueOf(DOCUMENT_LIST), String.join(",", updatedDocumentList));
                break;
            case 4:
                System.out.println("Enter updated claim amount: ");
                data.put(String.valueOf(CLAIM_AMOUNT), scanner.nextLine());
                break;
            case 5:
                System.out.println("Enter updated status (1 for New, 2 for Processing, 3 for Done): ");
                String statusInput = scanner.nextLine();
                String status;
                switch (statusInput) {
                    case "1":
                        status = Status.New.name();
                        break;
                    case "2":
                        status = Status.Processing.name();
                        break;
                    case "3":
                        status = Status.Done.name();
                        break;
                    default:
                        System.out.println("Invalid input. Setting status to New by default.");
                        status = Status.New.name();
                        break;
                }
                data.put(STATUS, status);
                break;
            case 6:
                System.out.println("Enter updated Receiver Banking Info");
                System.out.println("Enter the Bank: ");
                data.put(RECEIVER_BANKING_INFO_BANK, scanner.nextLine());
                System.out.println("Enter Your Name");
                data.put(RECEIVER_BANKING_INFO_NAME, scanner.nextLine());
                System.out.println("Enter bank number: ");
                data.put(RECEIVER_BANKING_INFO_NUMBER, scanner.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
        }
        return data;
    }
}
