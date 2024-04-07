import java.util.Date;

public class InsuranceCard {
    // Attributes
    private String cardHolder;
    private String cardNumber;
    private String policyOwner;
    private String expirationDate;

    // Default Constructor
    public InsuranceCard() {};

    // Constructors
    public InsuranceCard(String cardHolder, String cardNumber, String policyOwner, String expirationDate) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    protected String getCardHolder() {
        return cardHolder;
    }

    protected void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    protected String getCardNumber() {
        return cardNumber;
    }

    protected void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    protected String getPolicyOwner() {
        return policyOwner;
    }

    protected void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    protected String getExpirationDate() {
        return expirationDate;
    }

    protected void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
