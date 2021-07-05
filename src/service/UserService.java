package service;

import model.Student;
import model.User;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;
import service.exception.UserLimitException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final List<User> userDB = new ArrayList<>();

    static {
        User user1 = new User("Admin", "Admin 1", "admin1@gmail.com", "0712948372", "password1");
        User user2 = new User("User", "Normal User 1", "normaluser1@gmail.com", "0777948311", "password2");
        User user3 = new User("Admin", "Admin 2", "admin2@gmail.com", "0772948370", "password3");
        User user4 = new User("User", "Normal User 2", "normaluser2@gmail.com", "0782948323", "password4");
        User user5 = new User("Admin", "Admin 3", "admin3@gmail.com", "0772948368", "password5");

        userDB.add(user1);
        userDB.add(user2);
        userDB.add(user3);
        userDB.add(user4);
        userDB.add(user5);
    }

    public UserService() {
    }

    public void saveUser(User user) throws DuplicateEntryException, UserLimitException {
        if (exitsUser(user.getEmail())) {
            throw new DuplicateEntryException();
        }
        if(user.getUserType().equals("Admin")){
            if(isUserLimitExceed()){
                throw new UserLimitException();
            }
        }

        userDB.add(user);
    }

    private boolean isUserLimitExceed() {
        List<User> users = new ArrayList<>();
        for (User user:userDB) {
            if(user.getUserType().equals("Admin")){
               users.add(user);
            }
        }
        if(users.size() < 3){
            return false;
        }
        return true;
    }

    private boolean exitsUser(String nic) {
        for (User user : userDB) {
            if (user.getEmail().equals(nic)) {

                return true;
            }
        }
        return false;
    }

    public void updateUser(User user) throws NotFoundException {
        User u = findUser(user.getEmail());
        int index = userDB.indexOf(u);
        userDB.set(index, user);
    }

    public void deleteUser(String email) throws NotFoundException {
        User user = findUser(email);
        userDB.remove(user);
    }

    public List<User> getAllUsers() {
        return userDB;
    }

    private User findUser(String email) throws NotFoundException {
        for (User user : userDB) {

            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new NotFoundException();

    }
}
