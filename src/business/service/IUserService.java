package business.service;

import java.util.List;

import business.model.UserModel;

public interface IUserService {
	
	public List<UserModel> findAllUsers();
	public UserModel findUser(int id);
	public void deleteUser(int id);
	public void updateUser(int id, UserModel model);
	public void insertUser(UserModel model);

}
