import java.util.HashMap;

public abstract class Customer {
    // Attributes
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;
    private HashMap<String, Claim> claimList;

    // Default Constructor
    public Customer() {};

    // Constructor
    public Customer(String id, String fullName, InsuranceCard insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claimList = new HashMap<>();
    }

    // Getters and Setters
    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected String getFullName() {
        return fullName;
    }

    protected void setFullName(String fullName) {
        this.fullName = fullName;
    }

    protected InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    protected void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    protected HashMap<String, Claim> getClaimList() {
        return claimList;
    }

    protected void setClaimList(HashMap<String, Claim> claimList) {
        this.claimList = claimList;
    }
}
