/**
 * @author <Nguyen Tran Tri An - s3974923>
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class ClaimView {
    // Attributes
    public static final String CLAIM_ID = "CLAIM_ID";
    public static final Date CLAIM_DATE = new Date();
    public static final String INSURED_PERSON = "INSURED_PERSON";
    public static final String CARD_NUMBER = "CARD_NUMBER";
    public static final Date EXAM_DATE = new Date();
    public static final ArrayList<String> DOCUMENT_LIST = new ArrayList<String>();
    public static final double CLAIM_AMOUNT = 0.0;
    public static final String STATUS = "STATUS";
    public static final String RECEIVER_BANKING_INFO_BANK = "RECEIVER_BANKING_INFO_BANK";
    public static final String RECEIVER_BANKING_INFO_NAME = "RECEIVER_BANKING_INFO_NAME";
    public static final String RECEIVER_BANKING_INFO_NUMBER = "RECEIVER_BANKING_INFO_NUMBER";



    // Override Methods
    public abstract void displayMainMenu(ClaimController controller, InsuranceCardController icController);

    public abstract void display(Claim c);

    public abstract HashMap<String, String> displayNewClaimForm();

    public abstract HashMap<String, String> displayGetOneClaimForm();

    public abstract HashMap<String, String> displayDeleteClaimForm();

    public abstract HashMap<String, String> displayUpdateClaimForm();
}
