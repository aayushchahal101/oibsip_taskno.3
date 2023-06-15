import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	
	public Bank(String name) {
		this.name=name;
		
		this.users=new ArrayList<User>();
		
		this.accounts=new ArrayList<Account>();
	}
	

	public String getNewUserid() {
		
		String userid;
		Random rng= new Random();
		int len=7;
		boolean nonUnique;
		
		do{
			userid="";
			for(int c=0;c<len;c++) {
				userid +=((Integer)rng.nextInt(10)).toString();
			}
			
			nonUnique=false;
			for(User u: this.users) {
				if(userid.compareTo(u.getuserid())==0) {
					nonUnique= true;
					break;
				}
			}
		} while(nonUnique);
		
		return userid;
	}
	
	public String getNewAccountuserid() {
		String userid;
		Random rng= new Random();
		int len=11;
		boolean nonUnique;
		
		do{
			userid="";
			for(int c=0;c<len;c++) {
				userid +=((Integer)rng.nextInt(10)).toString();
			}
			
			nonUnique=false;
			for(Account a: this.accounts) {
				if(userid.compareTo(a.getuserid())==0) {
					nonUnique= true;
					break;
				}
			}
		} while(nonUnique);
		
		return userid;
		
	}
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	public User addUser(String firstName,String lastName,String pin) {
		
		User newUser=new User(firstName,lastName,pin,this);
        this.users.add(newUser);
        
        Account newAccount =new Account("Savings",newUser,this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        
        return newUser;
	}
	
	public User userLogin(String userID,String pin) {
		for(User u:this.users) {
			if(u.getuserid().compareTo(userID)==0 && u.validatePin(pin)) {
				return u;
			}
			
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
}
