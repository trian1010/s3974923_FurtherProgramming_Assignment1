import java.util.*;

public class ClaimController {
    // Attributes
    HashMap<String, Claim> claimList;
    Claim claim;
    ClaimView view;

    // Constructor
    public ClaimController(Claim claim, ClaimView view) {
        this.claim = claim;
        this.view = view;
        this.claimList = new HashMap<>();
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
        } else {
            System.out.println("Claim with the ID " + id + " not found.");
        }
    }

    // Method update
    public void update() {
        Scanner scanner = DataInput.getDataInput().getScanner();
        HashMap<String, String> data = view.displayUpdateClaimForm();
        String id = data.get(ClaimView.CLAIM_ID);

        if(claimList.containsKey(id)) {
            Claim claim = claimList.get(id);
            String insuredPerson = data.get(ClaimView.INSURED_PERSON);
            String cardNumber = data.get(ClaimView.CARD_NUMBER);
            Date examDate = new Date(Long.parseLong(data.get("EXAM_DATE"))); // Convert String to Date
            ArrayList<String> documentList = new ArrayList<>(Arrays.asList(data.get(String.valueOf(ClaimView.DOCUMENT_LIST)).split(","))); // Convert String to ArrayList
            double claimAmount = Double.parseDouble(data.get(String.valueOf(ClaimView.CLAIM_AMOUNT))); // Convert String to double
            String status = data.get(ClaimView.STATUS);
            ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(data.get(ClaimView.RECEIVER_BANKING_INFO_BANK), data.get(ClaimView.RECEIVER_BANKING_INFO_NAME), data.get(ClaimView.RECEIVER_BANKING_INFO_NUMBER));

            // Update specific attributes (ClaimID and ClaimDate will not be update since ClaimDate is the date that the Claim is created and ClaimID must remain the same)
            claim.setInsuredPerson(insuredPerson);
            claim.setCardNumber(cardNumber);
            claim.setExamDate(new Date());
            claim.setDocumentList(documentList);
            claim.setClaimAmount(claimAmount);
            claim.setStatus(status);
            claim.setReceiverBankingInfo(receiverBankingInfo);

            // Update claim in the claimList
            claimList.put(id, claim);

            view.display(claim);
        }
    }
}
