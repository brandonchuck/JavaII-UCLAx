package DataStructuresAssignment;

// Generic Queue using a Linked List
public class GenericQueue<T> {

    // The GenericQueue has a first node and last node
    public QueueNode first; // first node
    public QueueNode last;  // last node
    public int length;      // length of queue: starts at 0


    // Queue Node
    public class QueueNode {

        public QueueNode Next; // the queue node points to the next node in the queue
        public T Data;         // The GenericQueue data structure will determine the data type of the value when created

        // QueueNode Constructor
        // each node in the queue has a value and pointer to next node
        public QueueNode(T data){
            this.Data = data;   // datatype set when instantiating node
            this.Next = null;   // node starts off pointing to null, or no node
        }
    }


    // GenericQueue Constructor
    public GenericQueue(){
        this.first = null;
        this.last = null;
        this.length = 0;
    }


    // Peek method -- returns the first node
    public QueueNode peek(){

        if (this.length == 0){
            System.out.println("Empty queue");
            return null;
        } else {
            System.out.println(this.first.Data);
            return this.first;
        }
    }


    // Enqueue method -- adds a node to the end of the queue
    public GenericQueue enqueue(T dataType){

        // create new node
        QueueNode new_node = new QueueNode(dataType);  // in this case we will be creating a new QueueNode that holds a Song as the value

        // checking if there are 0 nodes in queue
        if (this.length == 0){
            this.first = new_node;      // set first node in queue to be the new node w/ value
            this.last = new_node;       // set last node in queue to be the new node w/ value
        } else {
            this.last.Next = new_node;  // if queue is not empty, then just add new node to end of queue; setting a new pointer here
            this.last = new_node;       // set the new last node in queue to the newly created node
        }

        this.length++; // increase length property of queue
        return this; // return the GenericQueue instance
    }


    // Dequeue method -- removes a node from the beginning of the queue according to FIFO rules
    public GenericQueue dequeue(){

        // if there are 0 nodes in the queue
        if (this.length == 0){
            System.out.println("Empty Queue"); // there is nothing to dequeue!
        }

        // if there is only a single node in the queue must point this.last node to null
        if (this.first == this.last){
            this.last = null;
        }

        // otherwise we must remove from the beginning of the Linked list by changing this.first node pointer to be the next node in linked list
        // we are just removing the pointer to the first node in the queue in order to remove/dequeue
        this.first = this.first.Next; // removing node according to FIFO rule
        this.length--;                // shorten length of queue when dequeue
        return this;                  // return the queue
    }


    public void queueLength(){
        if (this.length == 0){
            System.out.println("The queue is empty");
        } else {
            System.out.println("Songs in Queue: " + this.length); // print out current length of queue
        }
    }

}
