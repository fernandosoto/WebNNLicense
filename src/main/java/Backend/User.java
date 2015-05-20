package Backend;

/**
 * Created by Anna on 2015-05-07.
 */
public class User {
    private String userName;
    private String password;
    private static String LoggedInUser;

    public User(){

    }

    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    public static String getLoggedInUser() {
        return LoggedInUser;
    }

    public static void setLoggedInUser(String loggedInUser) {
        LoggedInUser = loggedInUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getUserName().equals(((User)obj).getUserName())) {
            if(this.getPassword().equals(((User)obj).getPassword())) {
                return true;
            }
        }

        return false;
    }
}
