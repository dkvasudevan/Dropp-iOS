package myProject;

import javax.persistence.*;
import javax.persistence.Lob;
import java.util.UUID;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Class to represent users interacting with the application and their data
 */
@Entity
class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany( mappedBy = "user_location")
    private List<Location> locations;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column (unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String birthday;

    @Column
    private String bio;

    @Column
    private String snapchat;

    @Column
    private String instagram;

    @Column
    private String tiktok;

    @Column
    private String linkedin;

    @Column
    private String twitter;

    @Column
    private String facebook;

    @Column
    private String photo = "null";
    /*@Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;*/


    //Getters
    /**
     * Access user user's id
     * @return user id
     */
    public int getId() { return id; }

    /**
     * Access user's first name
     * @return user first_name
     */
    public String getFirst_name() { return first_name; }

    /**
     * Access user's last name
     * @return user last_name
     */
    public String getLast_name() { return last_name; }

    /**
     * Access user's username
     * @return user username
     */
    public String getUsername() { return username; }

    /**
     * Decrrypts and return the user's password
     * @return unencrypted user password
     */
    public String getPassword() {
        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        String decryptedPassword = aesEncryptionDecryption.decrypt(this.password, aesEncryptionDecryption.passwordKey);
        return decryptedPassword;
    }

    /**
     * Access user's email
     * @return user email
     */
    public String getEmail() { return email; }

    /**
     * Access user's birthday
     * @return user birthday
     */
    public String getBirthday() { return birthday; }

    /**
     * Access user's short bio
     * @return user bio field
     */
    public String getBio() {return bio;}

    /**
     * Access user's posted location
     * @return list of users' posted location
     */public List<Location> getLocations() {
        return locations;
    }

    /**
     * Access user's snapchat
     * @return user snapchat
     */
    public String getSnapchat() { return snapchat; }

    /**
     * Access user's instagram
     * @return user instagram
     */
    public String getInstagram() { return instagram; }

    /**
     * Access user's tiktok
     * @return user tiktok
     */
    public String getTiktok() { return tiktok; }

    /**
     * Access user's facebook
     * @return user facebook
     */
    public String getFacebook() { return facebook; }

    /**
     * Access user's linkedIn
     * @return user linkedIn
     */
    public String getLinkedin() { return linkedin; }

    /**
     * Access user's twitter
     * @return user twitter
     */
    public String getTwitter() { return twitter; }

    /**
     * Access user's photo
     * @return user photo field
     */public String getPhoto() {return photo; }
    //public byte[] getPhoto() { return photo; }


    //Setters

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setting/updating user's first_name
     * @param firstName
     */
    public void setFirst_name(String firstName) { this.first_name = firstName; }

    /**
     * Setting/updating user's lastname
     * @param lastName
     */
    public void setLast_name(String lastName) { this.last_name = lastName; }

    /**
     * Setting/updating user's username
     * @param Username
     */
    public void setUsername(String Username) { this.username= Username; }

    /**
     * Encrypt and set/update the user's password
     * @param Password
     */
    public void setPassword(String Password) {
        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        String encryptedPassword = aesEncryptionDecryption.encrypt(Password, aesEncryptionDecryption.passwordKey);
        this.password= encryptedPassword;
    }

    /**
     * Setting/updating user's email
     * @param Email
     */
    public void setEmail(String Email) { this.email = Email; }

    /**
     * Setting/updating user's birthday
     * @param Birthday
     */
    public void setBirthday(String Birthday) { this.birthday = Birthday; }

    /**
     * Setting/updating user's short bio
     * @param bio
     */
    public void setBio(String bio) {this.bio=bio; }

    /**
     * Setting/updating user's posted location
     * @param locations
     */
    public void setLocations(List<Location> locations) { this.locations = locations; }

    /**
     * Setting/updating user's snapchat
     * @param snapchat
     */
    public void setSnapchat(String snapchat) { this.snapchat = snapchat; }

    /**
     * Setting/updating user's instagram
     * @param instagram
     */
    public void setInstagram(String instagram) { this.instagram = instagram; }

    /**
     * Setting/updating user's tiktok
     * @param tiktok
     */
    public void setTiktok(String tiktok) { this.tiktok= tiktok; }

    /**
     * Setting/updating user's facebook
     * @param facebook
     */
    public void setFacebook(String facebook) { this.facebook = facebook;}

    /**
     * Setting/updating user's linkedIn
     * @param linkedin
     */
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    /**
     * Setting/updating user's twitter
     * @param twitter
     */
    public void setTwitter(String twitter) { this.twitter = twitter; }

    /**
     * Setting/updating user's profile photo
     * @param photo
     */
    public void setPhoto(String photo) {this.photo = photo; }
    //public void setPhoto(byte[] photo) { this.photo = photo; }

    // public Integer getId() { return id; }
    //public void setAddress(String address) { this.address = address; }
}
