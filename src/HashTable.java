class Node{
    String name;
    String phoneNumber;
    Node next;
    public Node(String name,String phoneNumber){
        next = null;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Node(String name,String PhoneNumber, Node next){
        this.next = next;
        this.phoneNumber = PhoneNumber;
        this.name =name;
    }
    public Node insert(String name,String phoneNumber,Node first,int index){
        Node tmp = first;
        while(tmp.next != null)
            tmp = tmp.next;
        tmp.next = new Node(name,phoneNumber,null);
        System.out.println("Inserted contact At index: "+index);
        System.out.println("Contact Name: "+name);
        System.out.println("Contact Number: "+phoneNumber);
        System.out.println("___________________________________");
        return first;
    }
    public Node delete(String name,Node first){
        Node tmp = first.next;
        Node prev = first;
        if(prev.next == null){
            System.out.println("Contact found");
            System.out.println("Deleted contact's name: "+name+" \nDeleted contact's Phone: "+prev.phoneNumber);
            System.out.println("________________________________________________");
            return null;
        }
        while(!tmp.name.equals(name)||(!prev.name.equals(name)) && (tmp.next != null)){
            tmp =tmp.next;
            prev = prev.next;
        }
        System.out.println("Contact found");
        System.out.println("Deleted contact's name: "+name+" Deleted contact's Phone: "+tmp.phoneNumber);
        System.out.println("________________________________________________");
        prev.next = tmp.next;
        return first;
    }
    public void search (String name,Node first,int index){
        Node tmp = first;
        while(!tmp.name.equals(name)&& tmp.next != null)
            tmp = tmp.next;
        if(tmp.name.equals(name)){
            System.out.println("Contact found");
            System.out.println("index: "+index+"\nContact's Name: "+name+"\nContact's Phone Number: "+tmp.phoneNumber);
            System.out.println("_________________________________________");
            return;
        }
        System.out.println("Contact not found");

    }
    public Node update (String name,String phoneNumber,Node first){
        Node tmp = first;
        while(!tmp.name.equals(name)&& tmp.next != null)
            tmp = tmp.next;
        if(tmp.name.equals(name)){
            tmp.phoneNumber =phoneNumber;
            System.out.println("Contact's Name: "+name+"\nContact's new Phone Number: "+phoneNumber);
            return first ;
        }
        System.out.println("Contact not found");
        return first;
    }
    public void Display(Node first,int index){
        Node tmp = first;
        while(!(tmp == null)){
            System.out.println("Index: "+index+"\nContact's Number: "+tmp.phoneNumber+"\nContact's Name: "+tmp.name);
            System.out.println("________________________________________________");
            tmp = tmp.next;
        }
    }
}

public class HashTable {
   private final int capacity; //this is for the size of int
   private int size; //this is for the number items in hash table
   private final Node [] Table;
   private int removeCount;



   //Constructor for class
   public HashTable(int capacity){
       this.capacity = capacity;
       Table = new Node[capacity];
       size = 0;
       removeCount = 0;
   }

   //Calculating the hash value
    public static long calc_hash( String key, int table_size) {
       int i, l = key.length();
       long hash = 0;
       for (i = 0; i < l; i++) {
           hash += Character.getNumericValue(key.charAt(i));
           hash += (hash << 10);
           hash ^= (hash >> 6);
       }
       hash += (hash << 3);
       hash ^= (hash >> 11);
       hash += (hash << 15);
       if (hash > 0) return hash % table_size;
       else return -hash % table_size;
       }
    public boolean isEmpty(){
       return (size == 0);}
    public void insert(String Name ,String phoneNumber){
       int index = (int)calc_hash(Name,capacity);
       if(Table[index]==null){
           Table[index] = new Node(Name,phoneNumber);
           System.out.println("Inserted contact At index: "+index);
           System.out.println("Contact Name: "+Name);
           System.out.println("Contact Number: "+phoneNumber);
           System.out.println("___________________________________");
           size++;
           return;
       }
       Table[index] = Table[index].insert(Name,phoneNumber,Table[index],index);
       size++;
    }
    public void delete(String name){
       if(isEmpty()){
           System.out.println("Table is Empty");
           return;
       }
       int index = (int)calc_hash(name,capacity);
       if(Table[index]==null){
           System.out.println("Contact not found");
           return;
       }
      Table[index]=Table[index].delete(name,Table[index]);
      removeCount++;
      size--;
    }
    public boolean search(String name){
       if(isEmpty()){
           System.out.println("Table is Empty");
           return false;
       }
       int index = (int)calc_hash(name,capacity);
       if(Table[index]==null){
            System.out.println("Contact not found");
            return false;
        }
       Table[index].search(name,Table[index],index);
       return true;
    }
    public void update(String name,String phoneNumber){
       if(isEmpty()){
           System.out.println("Table is Empty");
           return;
       }
       int index = (int)calc_hash(name,capacity);
       Table[index] = Table[index].update(name,phoneNumber,Table[index]);
    }
    public void display(){
       System.out.println("Number of Contacts: "+size);
       System.out.println("Count of removes: "+ removeCount);
       if(isEmpty()){
           System.out.println("Table is Empty");
           return;
       }
       for(int index = 0; index < capacity; index++){
           try {
               Table[index].Display(Table[index],index);
               index++;
           }
           catch (NullPointerException _){

           }
       }
    }
   }
