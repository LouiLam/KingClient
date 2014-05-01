package object;

public class Account {
	  public String id;

	  public String password;

	  public String isAutoSave;

	  public Account(String id, String password, String isAutoSave) {
	    this.id = id;
	    this.password = password;
	    this.isAutoSave = isAutoSave;
	  }
}