import java.util.ArrayList;
import java.util.Date;

public class Claim {
    // Attributes
    private String id;
    private Date claimDate;
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private ArrayList<String> documentList;
    private double claimAmount;
    private String status;
    private ReceiverBankingInfo receiverBankingInfo;

    // Default Constructor
    public Claim() {}

    // Constructors

    public Claim(String id, Date claimDate, String insuredPerson, String cardNumber, Date examDate, ArrayList<String> documentList, double claimAmount, String status, ReceiverBankingInfo receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documentList = documentList;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }


    // Getters and Setters

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected Date getClaimDate() {
        return claimDate;
    }

    protected void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    protected String getInsuredPerson() {
        return insuredPerson;
    }

    protected void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    protected String getCardNumber() {
        return cardNumber;
    }

    protected void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    protected Date getExamDate() {
        return examDate;
    }

    protected void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    protected ArrayList<String> getDocumentList() {
        return documentList;
    }

    protected void setDocumentList(ArrayList<String> documentList) {
        this.documentList = documentList;
    }

    protected double getClaimAmount() {
        return claimAmount;
    }

    protected void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    protected String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    public ReceiverBankingInfo getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public void setReceiverBankingInfo(ReceiverBankingInfo receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }
}
