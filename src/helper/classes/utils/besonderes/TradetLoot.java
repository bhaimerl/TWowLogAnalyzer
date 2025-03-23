package helper.classes.utils.besonderes;

public class TradetLoot {
	
	public String item;
	public String giver;
	public String receiver;
	
	public TradetLoot(String item, String giver, String receiver) {
		super();
		this.item = item;
		this.giver = giver;
		this.receiver = receiver;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getGiver() {
		return giver;
	}
	public void setGiver(String giver) {
		this.giver = giver;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	

}
