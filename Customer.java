/**
 * @author <Nguyen Tran Tri An - s3974923>
 */

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

// Policy Holder
class PolicyHolder extends Customer {
    // Attributes
    private HashMap<String, Dependent> dependentList;

    // Default Constructor
    public PolicyHolder() {}

    // Constructor
    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard) {
        super(id, fullName, insuranceCard);
        this.dependentList = new HashMap<>();
    }

    // Getters and Setters
    protected HashMap<String, Dependent> getDependentList() {
        return dependentList;
    }

    protected void setDependentList(HashMap<String, Dependent> dependentList) {
        this.dependentList = dependentList;
    }
}

// Dependent
class Dependent extends Customer{
    // Attributes
    private PolicyHolder policyHolder;

    // Default Constructor
    public Dependent() {};

    // Constructor
    public Dependent(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    public Dependent(String id, String fullName, InsuranceCard insuranceCard, PolicyHolder policyHolder) {
        super(id, fullName, insuranceCard);
        this.policyHolder = policyHolder;
    }

    // Getters and Setters
    protected PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    protected void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }
}
