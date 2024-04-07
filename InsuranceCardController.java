import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

public class InsuranceCardController {
    // Attributes
    private HashMap<String, InsuranceCard> insuranceCardList;
    private String filePath = "InsuranceCard.txt";
    private InsuranceCard insuranceCard;
    private InsuranceCardView icView;

    // Constructor
    public InsuranceCardController(InsuranceCard insuranceCard, InsuranceCardView icView) {
        this.insuranceCard = insuranceCard;
        this.icView = icView;
        this.insuranceCardList = new HashMap<>();
        readFromFile();
    }

    // Method add
    public void add() {
        String answer = "Yes";
        Scanner scanner = DataInput.getDataInput().getScanner();
        while (answer.equalsIgnoreCase("Yes")) {
            HashMap<String, String> data = icView.displayNewInsuranceCardForm();
            String cardHolder = data.get(InsuranceCardView.CARD_HOLDER);
            String cardNumber = data.get(InsuranceCardView.CARD_NUMBER);
            String policyOwner = data.get(InsuranceCardView.POLICY_OWNER);
            String expirationDate = data.get(InsuranceCardView.EXPIRATION_DATE);

            insuranceCard = new InsuranceCard(cardHolder, cardNumber, policyOwner, expirationDate);

            insuranceCardList.put(cardHolder, insuranceCard);  // Add the Insurance Card to the Insurance Card list

            icView.display(insuranceCard);

            System.out.println("Continue? (Yes/No) ");
            answer = scanner.nextLine();

            // Write input to file
            FileWritertxt fileWriter = new FileWritertxt();
            fileWriter.writeInsuranceCardToFile(insuranceCard);
        }
    }

    // Method get all
    public void getAll() {
        if (insuranceCardList.isEmpty()) {
            System.out.println("No insurance card available.");
        } else {
            System.out.println("All insurance card: ");
            System.out.println();
            // Implement iterator to get all value in the Insurance Card list
            Iterator<InsuranceCard> iterator = insuranceCardList.values().iterator();
            while (iterator.hasNext()) {
                InsuranceCard insuranceCard = iterator.next();
                icView.display(insuranceCard);
            }
        }
    }

    // Method get one
    public void getOne() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = icView.displayGetOneInsuranceCardForm();
        String cardHolder = data.get(InsuranceCardView.CARD_HOLDER);

        if (insuranceCardList.containsKey(cardHolder)) {
            InsuranceCard insuranceCard = insuranceCardList.get(cardHolder);
            icView.display(insuranceCard);
        } else {
            System.out.println("Insurance Card with the Card Holder " + cardHolder + " not found.");
        }
    }

    // Method delete
    public void delete() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = icView.displayDeleteInsuranceCardForm();
        String cardHolder = data.get(InsuranceCardView.CARD_HOLDER);

        if(insuranceCardList.containsKey(cardHolder)) {
            InsuranceCard insuranceCard = insuranceCardList.get(cardHolder);
            System.out.println("Insurance Card with the Card Holder named " + cardHolder + " has been removed.");
            System.out.println();
            insuranceCardList.remove(cardHolder);

            // Rewrite input to file
            FileWritertxt fileWriter = new FileWritertxt();
            fileWriter.rewriteInsuranceCardToFile(insuranceCardList, filePath);
        } else {
            System.out.println("Insurance Card with the Card Holder named " + cardHolder + " not found.");
        }
    }

    // Method update
    public void update() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = icView.displayUpdateInsuranceCardForm();
        String cardHolder = data.get(InsuranceCardView.CARD_HOLDER);

        if (insuranceCardList.containsKey(cardHolder)) {
            // Update specific attributes (except card holder)
            InsuranceCard insuranceCard = insuranceCardList.get(cardHolder);

            String cardNumber = data.get(InsuranceCardView.CARD_NUMBER);
            if (cardNumber != null && !cardNumber.isEmpty()) {
                insuranceCard.setCardNumber(cardNumber);
            }

            String policyOwner = data.get(InsuranceCardView.POLICY_OWNER);
            if (policyOwner != null && !policyOwner.isEmpty()) {
                insuranceCard.setPolicyOwner(policyOwner);
            }

            String expirationDate = data.get(InsuranceCardView.EXPIRATION_DATE);
            if (expirationDate != null && !expirationDate.isEmpty()) {
                insuranceCard.setExpirationDate(expirationDate);
            }

            // Update insurance card in the insuranceCardList
            insuranceCardList.put(cardHolder, insuranceCard);

            icView.display(insuranceCard);

            // Rewrite input to file
            FileWritertxt fileWriter = new FileWritertxt();
            fileWriter.rewriteInsuranceCardToFile(insuranceCardList, filePath);
        } else {
            System.out.println("Insurance card for card holder " + cardHolder + " does not exist. Cannot update.");
        }
    }

    // Method read and access data from the file to the system
    public void readFromFile() {
        String filePath = "InsuranceCard.txt";
        try {
            File file = new File(filePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Card Holder: ")) {
                            String cardHolder = line.substring("Card Holder: ".length());
                            String cardNumber = reader.readLine().substring("Card Number: ".length());
                            String policyOwner = reader.readLine().substring("Policy Owner: ".length());
                            String expirationDate = reader.readLine().substring("Expiration Date: ".length());

                            InsuranceCard insuranceCard = new InsuranceCard(cardHolder, cardNumber, policyOwner, expirationDate);
                            insuranceCardList.put(cardHolder, insuranceCard);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
