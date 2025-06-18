import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingCart {
    private Map<Integer, Item> cartItems;

    public ShoppingCart() {
        cartItems = new HashMap<>();
    }

    public void addItem(int itemNumber, String itemName, double price, int quantity) {
        cartItems.put(itemNumber, new Item(itemName, price, quantity));
    }

    public void removeItem(int itemNumber, int quantityToRemove) {
        Item item = cartItems.get(itemNumber);
        if (item != null) {
            int currentQuantity = item.getQuantity();
            if (quantityToRemove <= currentQuantity) {
                if (quantityToRemove == currentQuantity) {
                    cartItems.remove(itemNumber);
                } else {
                    item.setQuantity(currentQuantity - quantityToRemove);
                }
                System.out.println("Item removed successfully.");
            } else {
                System.out.println("Invalid quantity. Please enter a quantity less than or equal to " + currentQuantity);
            }
        } else {
            System.out.println("Invalid item number. Please select a valid item to remove.");
        }
    }

    public boolean containsItem(int itemNumber) {
        return cartItems.containsKey(itemNumber);
    }

    public double getItemPrice(int itemNumber) {
        return cartItems.get(itemNumber).getPrice();
    }

    public int getItemQuantity(int itemNumber) {
        return cartItems.get(itemNumber).getQuantity();
    }

    public void printCart() {
        System.out.println("Shopping Cart:");
        for (Map.Entry<Integer, Item> entry : cartItems.entrySet()) {
            Item item = entry.getValue();
            System.out.println(
                entry.getKey() + ". " + item.getName() + " (Price: $" + item.getPrice() + ") - Quantity: " + item.getQuantity()
            );
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : cartItems.values()) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    private static class Item {
        private String name;
        private double price;
        private int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        // Set initial prices
        cart.addItem(1, "Shirt", 30.0, 0);
        cart.addItem(2, "Pants", 70.0, 0);
        cart.addItem(3, "Shoes", 100.0, 0);
        cart.addItem(4, "Socks", 6.0, 0);

        // Display menu
        System.out.println("Welcome to the Shopping Cart!");
        System.out.println("Select items to add to the cart by entering the item number:");
        System.out.println("1. Shirt");
        System.out.println("2. Pants");
        System.out.println("3. Shoes");
        System.out.println("4. Socks");
        System.out.println("0. Done");

        while (true) {
            System.out.print("Enter your choice (0-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                break;
            } else if (choice >= 1 && choice <= 4) {
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                double itemPrice = cart.getItemPrice(choice);
                cart.addItem(choice, cart.cartItems.get(choice).getName(), itemPrice, quantity);
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        // Print the cart
        System.out.println("\nYour Shopping Cart:");
        cart.printCart();

        // Remove an item from the cart
        System.out.print("Do you want to remove an item from the cart? (yes/no): ");
        String removeChoice = scanner.nextLine();
        if (removeChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the item number to remove: ");
            int itemToRemove = scanner.nextInt();
            scanner.nextLine(); //Consume newline

            if (cart.containsItem(itemToRemove)) {
                System.out.print("Enter the quantity to remove: ");
                int quantityToRemove = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                int itemQuantity = cart.getItemQuantity(itemToRemove);
                if (quantityToRemove <= itemQuantity) {
                    cart.removeItem(itemToRemove, quantityToRemove);
                } else {
                    System.out.println("Invalid quantity. The selected item has a quantity of " + itemQuantity);
                }
            } else {
                System.out.println("Invalid item number. Please select a valid item to remove.");
            }
        }

        // Print the updated cart
        System.out.println("\nUpdated Shopping Cart:");
        cart.printCart();

        // Get the total price of the cart
        double totalPrice = cart.getTotalPrice();
        System.out.println("\nTotal Price for Selected Items: $" + totalPrice);
    }
}
