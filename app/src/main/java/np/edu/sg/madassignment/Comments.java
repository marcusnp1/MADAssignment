package np.edu.sg.madassignment;

public class Comments {

    private int userID;
    private String userComments;
    public Comments() {}
    public Comments(int id, String usercomments) {
        this.userID = id;
        this.userComments = usercomments;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getUserComments() {
        return userComments;
    }
    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }
}
