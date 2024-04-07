

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileWritertxt {
    Claim claim;
    InsuranceCard insuranceCard;

    // Method write all created Claim to file text
    public void writeClaimToFile(Claim claim) {
        String filePath = "Claim.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            // Write container information to the file
            writer.write("Claim ID: " + claim.getId());
            writer.newLine();
            writer.write("Claim Date: " + formatDate(claim.getClaimDate()));
            writer.newLine();
            writer.write("Insured Person: " + claim.getInsuredPerson());
            writer.newLine();
            writer.write("Card Number: " + claim.getCardNumber());
            writer.newLine();
            writer.write("Exam Date: " + formatDate(claim.getExamDate()));
            writer.newLine();
            writer.write("Documents: " + String.join(", ", claim.getDocumentList()));
            writer.newLine();
            writer.write("Claim Amount: " + claim.getClaimAmount());
            writer.newLine();
            writer.write("Status: " + claim.getStatus());
            writer.newLine();
            writer.write("ReceiverBankingInfo: " + claim.getReceiverBankingInfo());
            writer.newLine();
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method rewrite all updated or removed Claim to the file
    public void rewriteClaimToFile(HashMap<String, Claim> claimList, String filePath) {
        // Clear the file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Write the claims to the file
        for (Claim claim : claimList.values()) {
            writeClaimToFile(claim);
        }
    }

    // Method write all created Insurance Card to file text
    public void writeInsuranceCardToFile(InsuranceCard insuranceCard) {
        String filePath = "InsuranceCard.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            // Write container information to the file
            writer.write("Card Holder: " + insuranceCard.getCardHolder());
            writer.newLine();
            writer.write("Card Number: " + insuranceCard.getCardNumber());
            writer.newLine();
            writer.write("Policy Owner: " + insuranceCard.getPolicyOwner());
            writer.newLine();
            writer.write("Expiration Date: " + insuranceCard.getExpirationDate());
            writer.newLine();
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method rewrite all updated or removed Insurance Card to the file
    public void rewriteInsuranceCardToFile(HashMap<String, InsuranceCard> insuranceCardList, String filePath) {
        // Clear the file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Write the claims to the file
        for (InsuranceCard insuranceCard : insuranceCardList.values()) {
            writeInsuranceCardToFile(insuranceCard);
        }
    }

    // Format Date so that the Date write to file is the same with the Date displayed in the System
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        return dateFormat.format(date);
    }
}
