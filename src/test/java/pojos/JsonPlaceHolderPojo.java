package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true) //if data received from the database has nay extra/unknown attribute, ignore it

public class JsonPlaceHolderPojo {

    /*
    POJO => Plain Old Java Object
    Pojo class works as a perfect template for creating objects as and when we need them

        1) Create all variables as private (Encapsulation)
        2) Create constructors with and without parameters
        3) Create getter ad setter for all variables
        4) Create toString() method to print everything on the console
     */

    private Integer userId;
    private String title;
    private Boolean completed;

    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }


}
// JUST TO SEE WHAT VARIABLE WE HAVE
//            {
//                    "userId": 55,
//                    "title": "Tidy your room",
//                    "completed": false
//                    }