import java.util.Date;

public class InsuranceCard {
    // Attributes
    private String cardHolder;
    private String policyOwner;
    private Date expirationDate;

    // Default Constructor
    public InsuranceCard() {};

    // Constructors
    public InsuranceCard(String cardHolder, String policyOwner, Date expirationDate) {
        this.cardHolder = cardHolder;
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

    protected String getPolicyOwner() {
        return policyOwner;
    }

    protected void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    protected Date getExpirationDate() {
        return expirationDate;
    }

    protected void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
