package DataStructuresAssignment;

public class Song {

    public String title;
    public String artist;


    public Song(String t, String a){
        this.title = t;
        this.artist = a;
    }


    // Method to print details
    public void details(){
        System.out.println("Title: " + this.title);
        System.out.println("Artist: " + this.artist + "\n");
    }

}
