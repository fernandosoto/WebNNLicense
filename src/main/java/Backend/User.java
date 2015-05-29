package Backend;

/**
 * Created by Anna on 2015-05-07.
 */
public class User {
    private String userName;
    private String password;
    private static String LoggedInUser;

    /**
     * Empty constructor
     */
    public User(){

    }

    /**
     * Constructor
     * @param userName
     * @param password
     */
    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    /**
     * Return loggedInUser which is static
     * @return
     */
    public static String getLoggedInUser() {
        return LoggedInUser;
    }

    /**
     * Set the loggedInUser which is static
     * @param loggedInUser
     */
    public static void setLoggedInUser(String loggedInUser) {
        LoggedInUser = loggedInUser;
    }

    /**
     * Return password of User.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password of User.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return userName of User
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the userName of user.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Overrides the equal method of the Object class.
     * Checks if this User is equal to another User obj
     * Return true if equal
     * Return false if not equal
     * @param obj other User object
     * @return true if equal
     * false if not equal.
     */
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
