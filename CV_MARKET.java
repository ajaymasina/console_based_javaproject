import java.util.*;

class CV_MARKET {
    static final String blue = "\033[94m", red = "\u001B[31m", green = "\u001B[32m";
    static final String yellow = "\u001B[33m", reset = "\u001B[0m", pink = "\u001B[95m", orange = "\u001B[38;5;214m", white = "\u001B[38;5;255m"; 
    static final String s = "                                            ";
    
    static String[] vegetables = {"Onions", "Tomatoes", "Brinjal"};
    static float[] veg_avail = {10, 10, 10};
    static double[] veg_price = {20, 30, 50};
    static String[] veg_units = {"kg", "kg", "kg"};

    static String[] fruits = {"Apple", "Banana", "Grapes"};
    static float[] fruits_avail = {10, 10, 10};
    static double[] fruit_price = {20, 30, 50};
    static String[] fruit_units = {"kg", "dozen", "kg"};
    
    static String[] diary = {"milk", "curd", "ghee"};
    static float[] diary_avail = {10, 10, 10};
    static double[] diary_price = {20, 30, 50};
    static String[] diary_units = {"L", "L", "kg"};
	
	static String[] groceries = {"chickpea", "dal", "groundnut"};
    static float[] groceries_avail = {10, 10, 10};
    static double[] groceries_price = {200, 300, 500};
    static String[] groceries_units = {"kg", "kg", "kg"};

    static ArrayList<String> cartItem = new ArrayList<>();
    static ArrayList<Float> cartquantity = new ArrayList<>();
    static ArrayList<Double> cartprice = new ArrayList<>();
    static ArrayList<String> ItemUnit = new ArrayList<>();

    static void showBanner(String message) {
        System.out.println(yellow + s + "*--------------------------------------------*" + reset);
        System.out.println(yellow + s + "              " + message + "              " + reset);
        System.out.println(yellow + s + "*--------------------------------------------*" + reset);
    }

    static void show(String[] product, float[] avail, double[] price, String[] unit, String color) {
        for (int i = 0; i < product.length; i++) {
            System.out.println(color + s + (i + 1) + ") " + product[i] + " : Rs" + price[i] + "/" + unit[i] + " - Available " + avail[i] + " " + unit[i] + reset);
        }
        System.out.println(color + s + (product.length + 1) + ") Back to view products" + reset);
    }

    static void addItemToCart(String itemName, double price, float quantity, float[] avail, int index, String[] unit) {
        int itemIndex = cartItem.indexOf(itemName);

        if (itemIndex != -1) {  
            cartquantity.set(itemIndex, cartquantity.get(itemIndex) + quantity);
            cartprice.set(itemIndex, cartprice.get(itemIndex) + (price * quantity));
        } else {  
            cartItem.add(itemName);
            cartquantity.add(quantity);
            cartprice.add(price * quantity);
            ItemUnit.add(unit[index]);  
        }

        avail[index] -= quantity;
        System.out.println(s + quantity + " " + unit[index] + " x " + itemName + " added to cart.");
    }

    static void productSelection(String[] products, float[] avail, double[] price, String[] unit, String color) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            show(products, avail, price, unit, color);
            System.out.println(green + "Select an item to add to cart :" + reset);
            int choice = sc.nextInt();
            if (choice >= 1 && choice <= products.length) {
                System.out.println(green + "Enter quantity in " + unit[0] + "'s:" + reset);  
                float quantity = sc.nextFloat();
                if (quantity > 0 && quantity <= avail[choice - 1]) {
                    addItemToCart(products[choice - 1], price[choice - 1], quantity, avail, choice - 1, unit);  
                } else {
                    System.out.println(red + s + "Insufficient quantity available." + reset);
                }
            } else if (choice == products.length + 1) {
                break;
            } else {
                System.out.println(red + s + "Invalid input, please try again." + reset);
            }
        }
    }

    public static void main(String[] args) {
        showBanner("WELCOME TO CV_MARKET");
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println(green + s + "1. View Products\n" + s + "2. View Cart\n" + s + "3. Check Out\n" + s + "4. Exit" + reset);
            int op1 = sc.nextInt();
            
            if (op1 == 1) {
                while (true) {
                    System.out.println(blue + s + "1. Fruits\n" + s + "2. Vegetables\n" + s + "3. Dairy products\n" + s + "4. Groceries\n" + s + "5. Back to Main Menu" + reset);
                    int op2 = sc.nextInt();
                    
                    if (op2 == 1) {
                        productSelection(fruits, fruits_avail, fruit_price, fruit_units, orange);
                    } else if (op2 == 2) {
                        productSelection(vegetables, veg_avail, veg_price, veg_units, pink);
                    } else if (op2 == 3) {
                        productSelection(diary, diary_avail, diary_price, diary_units, white);
                    }else if (op2 == 4) {
                        productSelection(groceries, groceries_avail, groceries_price, groceries_units, blue);
                    } else if (op2 == 5) {
                        break; 
                    } else {
                        System.out.println(red + s + "Invalid input, please try again." + reset);
                    }
                }
            } else if (op1 == 2) {
                showBanner("ITEMS IN YOUR CART");
                if (cartItem.isEmpty()) {
                    System.out.println(red + s + "Your cart is empty." + reset);
                } else {
                    for (int i = 0; i < cartItem.size(); i++) {
                        String unit = ItemUnit.get(i);  
                        System.out.println(green + s + cartItem.get(i) + " - " + cartquantity.get(i) + " " + unit + " - Rs" + cartprice.get(i) + reset);
                    }
                }
            }
            else if(op1 == 3) {
                showBanner("CHECKOUT"); 
            
                if (!(cartItem.isEmpty())) {
                    double sum = 0;
                    for(double d : cartprice)
                        sum = sum + d;
                    double tax = 0.5 * sum;
                    System.out.println(yellow + s + "ITEMS WORTH      --> RS" + sum);
                    System.out.println(s + "CVTAX            --> RS" + tax);
                    System.out.println(s + "- - - - - - - - - - - - - - ");
                    System.out.println(s + "TOTAL BILL       --> RS" + (sum + tax));
                    System.out.println(s + "- - - - - - - - - - - - - - " + reset);
                }
            } 
            else if (op1 == 5) {
                break;
            } else {
                System.out.println(red + s + "under development." + reset);
            }
        }
    }
}
