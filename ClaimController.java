/**
 * @author <Nguyen Tran Tri An - s3974923>
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClaimController {
    // Attributes
    HashMap<String, Claim> claimList;
    private String filePath = "Claim.txt";
    Claim claim;
    ClaimView view;

    // Constructor
    public ClaimController(Claim claim, ClaimView view) {
        this.claim = claim;
        this.view = view;
        this.claimList = new HashMap<>();
        readFromFile();
    }

    // Method add
    public void add() {
        String answer = "Yes";
        Scanner scanner = DataInput.getDataInput().getScanner();
        while (answer.equalsIgnoreCase("Yes")) {
            HashMap<String, String> data = view.displayNewClaimForm();
            String id = data.get(ClaimView.CLAIM_ID);
            Date claimDate = new Date(Long.parseLong(data.get("CLAIM_DATE"))); // Convert String to Date
            String insuredPerson = data.get(ClaimView.INSURED_PERSON);
            String cardNumber = data.get(ClaimView.CARD_NUMBER);
            Date examDate = new Date(Long.parseLong(data.get("EXAM_DATE"))); // Convert String to Date
            ArrayList<String> documentList = new ArrayList<>(Arrays.asList(data.get(String.valueOf(ClaimView.DOCUMENT_LIST)).split(","))); // Convert String to ArrayList
            double claimAmount = Double.parseDouble(data.get(String.valueOf(ClaimView.CLAIM_AMOUNT))); // Convert String to double
            String status = data.get(ClaimView.STATUS);
            ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(data.get(ClaimView.RECEIVER_BANKING_INFO_BANK), data.get(ClaimView.RECEIVER_BANKING_INFO_NAME), data.get(ClaimView.RECEIVER_BANKING_INFO_NUMBER));


            claim = new Claim(id, claimDate, insuredPerson, cardNumber, examDate, documentList, claimAmount, status, receiverBankingInfo);

            claimList.put(id, claim); //Add the claim to the claimList

            view.display(claim);

            System.out.println("Continue? (Yes/No) ");
            answer = scanner.nextLine();

            // Write input to file
            FileWritertxt fileWriter = new FileWritertxt();
            fileWriter.writeClaimToFile(claim);
        }
    }

    // Method get all
    public void getAll() {
        if (claimList.isEmpty()) {
            System.out.println("No claims available.");
        } else {
            System.out.println("All Claims:");
            System.out.println();
            Iterator<Claim> iterator = claimList.values().iterator(); // Use iterator to get all Claim in the HashMap
            while (iterator.hasNext()) {
                Claim claim = iterator.next();
                view.display(claim);
            }
        }
    }

    // Method get one
    public void getOne() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = view.displayGetOneClaimForm();
        String id = data.get(ClaimView.CLAIM_ID);

        if (claimList.containsKey(id)) {
            Claim claim = claimList.get(id);
            view.display(claim);
        } else {
            System.out.println("Claim with the ID " + id + " not found.");
        }
    }

    // Method delete
    public void delete() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = view.displayDeleteClaimForm();
        String id = data.get(ClaimView.CLAIM_ID);

        if(claimList.containsKey(id)) {
            Claim claim = claimList.get(id);
            System.out.println("Claim with the ID " + id + " has been removed.");
            System.out.println();
            claimList.remove(id);

            // Rewrite input to file
            FileWritertxt fileWriter = new FileWritertxt();
            fileWriter.rewriteClaimToFile(claimList, filePath);
        } else {
            System.out.println("Claim with the ID " + id + " not found.");
        }
    }

    // Method update
    public void update() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = view.displayUpdateClaimForm();
        String id = data.get(ClaimView.CLAIM_ID);

        if (claimList.containsKey(id)) {
            // Update specific attributes (ClaimID and ClaimDate will not be update since ClaimDate is the date that the Claim is created and ClaimID must remain the same)
            Claim claim = claimList.get(id);

            String insuredPerson = data.get(ClaimView.INSURED_PERSON);
            if (insuredPerson != null && !insuredPerson.isEmpty()) {
                claim.setInsuredPerson(insuredPerson);
            }

            String cardNumber = data.get(ClaimView.CARD_NUMBER);
            if (cardNumber != null && !cardNumber.isEmpty()) {
                claim.setCardNumber(cardNumber);
            }

            // The system update examDate
            claim.setExamDate(new Date());

            String documents = data.get(String.valueOf(ClaimView.DOCUMENT_LIST));
            if (documents != null && !documents.isEmpty()) {
                ArrayList<String> documentList = new ArrayList<>(Arrays.asList(documents.split(",")));
                claim.setDocumentList(documentList);
            }

            String claimAmountStr = data.get(String.valueOf(ClaimView.CLAIM_AMOUNT));
            if (claimAmountStr != null && !claimAmountStr.isEmpty()) {
                double claimAmount = Double.parseDouble(claimAmountStr);
                claim.setClaimAmount(claimAmount);
            }

            String status = data.get(ClaimView.STATUS);
            if (status != null && !status.isEmpty()) {
                claim.setStatus(status);
            }

            String bank = data.get(ClaimView.RECEIVER_BANKING_INFO_BANK);
            String name = data.get(ClaimView.RECEIVER_BANKING_INFO_NAME);
            String number = data.get(ClaimView.RECEIVER_BANKING_INFO_NUMBER);
            if (bank != null && !bank.isEmpty() && name != null && !name.isEmpty() && number != null && !number.isEmpty()) {
                ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(bank, name, number);
                claim.setReceiverBankingInfo(receiverBankingInfo);
            }

            // Update claim in the claimList
            claimList.put(id, claim);

            view.display(claim);

            // Rewrite input to file
            FileWritertxt fileWriter = new FileWritertxt();
            fileWriter.rewriteClaimToFile(claimList, filePath);
        } else {
            System.out.println("Claim with ID " + id + " does not exist. Cannot update.");
        }
    }

    // Method read and retrieve data from the file text
    public void readFromFile() {
        String filePath = "Claim.txt";
        try {
            File file = new File(filePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Claim ID: ")) {
                            String id = line.substring("Claim ID: ".length());
                            Date claimDate = parseDateFromString(reader.readLine().substring("Claim Date: ".length()));
                            String insuredPerson = reader.readLine().substring("Insured Person: ".length());
                            String cardNumber = reader.readLine().substring("Card Number: ".length());
                            Date examDate = parseDateFromString(reader.readLine().substring("Exam Date: ".length()));
                            ArrayList<String> documentList = new ArrayList<>(Arrays.asList(reader.readLine().substring("Documents: ".length()).split(",")));
                            double claimAmount = Double.parseDouble(reader.readLine().substring("Claim Amount: ".length()));
                            String status = reader.readLine().substring("Status: ".length());
                            String[] bankingInfoParts = reader.readLine().substring("ReceiverBankingInfo: ".length()).split(",");
                            ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(bankingInfoParts[0].trim(), bankingInfoParts[1].trim(), bankingInfoParts[2].trim());

                            Claim claim = new Claim(id, claimDate, insuredPerson, cardNumber, examDate, documentList, claimAmount, status, receiverBankingInfo);
                            claimList.put(id, claim);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Format Date so that the Date write to file is the same with the Date displayed in the System
    private Date parseDateFromString(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
