package hashtable;

public class HashTable {

    private Entry[] bucket;

    private int currentSize;

    private int capacity;

    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashTable() {
        this.currentSize = 0;
        this.capacity = 16; // better be 2^power
        bucket = new Entry[this.capacity];
    }
    public boolean isEmpty(){
        return currentSize == 0;
    }


    public int getcurrentSize() {
        return currentSize;
    }

    public int hashFunc(String key){
        return key.hashCode() % capacity;
    }

    public void insert(String key, int value){
        int index = hashFunc(key);
        Entry head = bucket[index];

        //Checks if the key is already exists
        while(head != null) {
            if (head.getKey().equals(key)) {
                // reset value to newest value
                head.setValue(value);
                return;
            }
            head = head.getNext();
        }
        // key not exists


        // Inserts key in the chain (insert to the head)
        currentSize++;
        head = bucket[index];
        Entry newEntry = new Entry(key, value);
        newEntry.setNext(head);
        bucket[index] = newEntry;

        //Checks if array >= 75% of the array gets filled
        // rehashing
        if ((1.0 * currentSize) / capacity >= DEFAULT_LOAD_FACTOR) {
            capacity = 2 * capacity;
            currentSize = 0;
            Entry[] temp = bucket;
            bucket = new Entry[capacity];


            for (Entry headNode : temp) {
                while (headNode != null) {
                    insert(headNode.getKey(), headNode.getValue());
                    headNode = headNode.getNext();
                }
            }
        }

    }

    public Integer getValue(String key) {
        // Find the head of chain
        int index = hashFunc(key);
        Entry head = bucket[index];

        // Search key in the capacity
        while (head != null) {
            if (head.getKey().equals(key))
                return head.getValue();
            head = head.getNext();
        }

        // If key not found
        return null;
    }
    public int size() {
        return currentSize;
    }


    public Integer delete(String key) {
        //Find index
        int index = hashFunc(key);

        // Get head of the chain for that index
        Entry head = bucket[index];

        //Find the key in capacity
        Entry prev = null;
        while (head != null) {
            //If key exists
            if (head.getKey().equals(key)){
                // Remove key
                currentSize--;
                if (prev != null)
                    prev.setNext(head.getNext());
                else // prev == null -> we on the head
                    bucket[index] = head.getNext();

                return head.getValue();
            }
            // Else keep moving in chain
            prev = head;
            head = head.getNext();
        }

        // If key does not exist
        return null;


    }


    public static void main(String[] args) {
        HashTable table = new HashTable(); //Create a HashTable
        //Before Insertion
        System.out.println("Table currentSize = " + table.getcurrentSize());
        table.insert("This",1 ); //Key-Value Pair
        table.insert("is",2 );
        table.insert("a",3 );
        table.insert("Test",4 );
        table.insert("Driver",5 );
        System.out.println(table.getValue("This") == 1);
        System.out.println(table.getValue("is") == 2);
        System.out.println(table.getValue("a") == 3);
        System.out.println(table.getValue("Test") == 4);
        System.out.println(table.getValue("Driver") == 5);
        System.out.println("Table currentSize = " + table.getcurrentSize());
        table.delete("This");
        System.out.println(table.size() == 4);
        table.delete("is");
        System.out.println(table.size() == 3);

        table.delete("a");
        System.out.println(table.size() == 2);

        table.delete("Test");
        System.out.println(table.size() == 1);


        table.insert("This",1 );
        table.insert("is",2 );
        table.insert("a",3 );
        table.insert("Test",4 );
        table.insert("Driver",5 );

        if(table.isEmpty())
            System.out.println("Table is Empty");
        else
            System.out.println("Table is not Empty");

    }

}
