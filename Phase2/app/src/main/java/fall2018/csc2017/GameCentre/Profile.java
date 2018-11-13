package fall2018.csc2017.GameCentre;

/**
 * Profile data to make sending into database easier.
 */
public class Profile {
    /**
     * User data for the current profile
     */
    private String profileEmail, profileName;

    public Profile(){
    }

    Profile(String userEmail, String userName) {
        this.profileName = userName;
        this.profileEmail = userEmail;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String userEmail) {
        this.profileEmail = userEmail;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String userName) {
        this.profileName = userName;
    }
}