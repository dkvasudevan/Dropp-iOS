package myProject;

import javax.persistence.*;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class that represents a "dropp" object being populated on the map
 */
@Entity
class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int l_id;

    @ManyToOne
    @JoinColumn(name = "user_location_id", nullable = false)
    @JsonIgnore
    private User2 user_location;

    @Column
    private String username;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private String dropptime;

    @Column
    private String deletetime;

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
    private String note;

    //getter method
    public int getId() {

        return l_id;
    }

    public Double getLongitude() {

        return longitude;
    }

    public Double getLatitude() {

        return latitude;
    }

    public String getDropptime() {
        return dropptime;
    }

    public User2 getUser_location() {
        return user_location;
    }

    public String getDeletetime() {
        return deletetime;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public int getL_id() {
        return l_id;
    }

    public String getNote() {
        return note;
    }

    public String getTiktok() {
        return tiktok;
    }

    public String getUsername(){
        return username;
    }
    //setter method
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }


    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setId(int id) {
        this.l_id = l_id;
    }

    public void setDropptime(String dropptime) {
        this.dropptime = dropptime;
    }

    public void setDeletetime(String deletetime) {
        this.deletetime = deletetime;
    }

    public void setUser_location(User2 user_location) {
        this.user_location = user_location;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString(){
        return l_id + ":" + user_location.getId() + ":" + longitude + ":" + dropptime;
    }
}
