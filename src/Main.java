
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashTable list = new HashTable(1009);
        String name, phone;

        while (true) {
            int choice;
            System.out.println("""
                    Choose an operation from below
                    1-Insert a Contact.
                    2-Search for a Contact.
                    3-Delete a Contact.
                    4-Update a Contact number.
                    5-Display all contacts.
                    6-Exit Program.
                    """);
            try {
                choice = input.nextInt();
            }
            catch (InputMismatchException e) {
                choice = 0;
                input.next();
            }
            if(choice == 6){
                System.out.println("Exit!");
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter a name: ");
                    name = input.next();
                    System.out.println("Enter a phone: ");
                    phone = input.next();
                    list.insert(name, phone);
                    break;
                case 2:
                    if(!list.isEmpty()){
                    System.out.println("Enter the name for search:");
                    name = input.next();
                    list.search(name);
                    }
                    System.out.println("Table is Empty");
                    break;
                case 3:
                    if(!list.isEmpty()) {
                        System.out.println("Enter the name for delete:");
                        name = input.next();
                        list.delete(name);
                    }
                    System.out.println("Table is Empty");
                    break;
                case 4:
                    if(!list.isEmpty()) {
                        System.out.println("Enter the name to update");
                        name = input.next();
                        if (list.search(name)) {
                            System.out.println("Enter the updated phone number");
                            phone = input.next();
                            list.update(name, phone);
                        }
                    }
                    System.out.println("Table is Empty");
                    break;
                case 5:
                    list.display();
                    break;
                default:
                    System.out.println("Invalid choice, Try again");
            }

        }
    }
}
