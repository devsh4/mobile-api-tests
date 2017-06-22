package distributor_app_pageobjects;

public class BalanceTransferPage {

	
	protected String balanceButtonXpath	=	"//android.widget.TextView[contains(@resource-id,'com.mindsarray.pay1distributor:id/item_text') and @text='Balance Transfer']";
	protected String createRetailerButtonXpath	=	"//android.widget.TextView[contains(@resource-id,'com.mindsarray.pay1distributor:id/item_text') and @text='Create Retailer']";
	protected String retailerListButtonXpath	=	"//android.widget.TextView[contains(@resource-id,'com.mindsarray.pay1distributor:id/item_text') and @text='Retailer List']";
	protected String reportButtonXpath	=	"//android.widget.TextView[contains(@resource-id,'com.mindsarray.pay1distributor:id/item_text') and @text='Report: Balance Transfer']";

	protected String sidepanelId	=	"com.mindsarray.pay1distributor:id/action_side_panel";
	protected String balanceId	=	"com.mindsarray.pay1distributor:id/balance";


	//Transfer objects
	protected String	mobileId	=	"com.mindsarray.pay1distributor:id/retailer_mobile";
	protected String	amountId	=	"com.mindsarray.pay1distributor:id/balance_amount";
	
	protected String	transactionIdMessage	=	"com.mindsarray.pay1distributor:id/message";

}
