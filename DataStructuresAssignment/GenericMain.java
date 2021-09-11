package DataStructuresAssignment;

public class GenericMain {
    public static void main(String[] args) {

        // Queue for Songs
        GenericQueue<Song> songQueue = new GenericQueue<>();

        // Queue for Integers etc...
        //GenericQueue<Integer> integerQueue = new GenericQueue();
        //songQueue.enqueue(new Integer(5));

        // Add new Songs to queue
        // In this case, the value("data") of the QueueNode will be a Song object
        songQueue.enqueue(new Song("Bad Habits", "Ed Sheeran"));
        songQueue.enqueue(new Song("Phone Numbers", "Dominic Fluke"));
        songQueue.enqueue(new Song("Blinding Lights", "The Weeknd"));


        // see the value of the first item/song in queue
        songQueue.peek(); // return the first node within the queue
                          // in order to access the data within the QueueNode, you can do peek().data."ObjectField"
                          // System.out.println(songQueue.peek().data.title); // can print out the title/artist of the Song object passed to the queue like so
                          // songQueue.peek().data.details(); // can use peek() method to get QueueNode and .data to get Song object, then use details() method to print out title and artist of song

        songQueue.queueLength(); // check length of queue


        songQueue.dequeue(); // remove first node in queue
        songQueue.peek(); // verify that first node is now a different node
        songQueue.queueLength(); // print out length of queue

    }
}
