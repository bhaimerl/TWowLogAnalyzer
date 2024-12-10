package helper.classes;

public class NameClassWrapper implements Comparable<NameClassWrapper>{
	
	public String name;
	public String playerClass;
	public String guild;
	
	
	
	
	public NameClassWrapper(String name, String playerClass, String guild) {
		super();
		this.name = name;
		this.playerClass = playerClass;
		this.guild = guild;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlayerClass() {
		return playerClass;
	}
	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}
	public String getGuild() {
		return guild;
	}
	public void setGuild(String guild) {
		this.guild = guild;
	}
	@Override
	public String toString() {
		return "NameClassWrapper [name=" + name + ", playerClass=" + playerClass + ", guild=" + guild + "]";
	}
	@Override
	public int compareTo(NameClassWrapper o) {
		return this.playerClass.compareTo(o.getPlayerClass());
	}
	
	
	

}
