package business.model;

public class UserModel {
	
	private int userId;
	private String username;
	private String password;
	private int isAdmin;
	
	public UserModel(int userId, String username, String password, int isAdmin) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public UserModel(String username, String password, int isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public UserModel() {
	
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String toString(UserModel user) {
		return "username: " + user.getUsername() + " password: " + user.getPassword() + " isAdmin: " + getIsAdmin();
	}
}
