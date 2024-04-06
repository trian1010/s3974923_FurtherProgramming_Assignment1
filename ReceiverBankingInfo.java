public class ReceiverBankingInfo {
    // Attributes
    private String bank;
    private String name;
    private String number;

    // Constructors
    public ReceiverBankingInfo(String bank, String name, String number) {
        this.bank = bank;
        this.name = name;
        this.number = number;
    }

    // Default Constructor
    public ReceiverBankingInfo() {}

    // Getters and Setters
    protected String getBank() {
        return bank;
    }

    protected void setBank(String bank) {
        this.bank = bank;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getNumber() {
        return number;
    }

    protected void setNumber(String number) {
        this.number = number;
    }

    // Override
    @Override
    public String toString() {
        return "ReceiverBankingInfo{" +
                "bank='" + bank + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
