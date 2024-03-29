import java.util.*;
abstract class Product{
    private int productID;
    private String productName;
    private double price;

    //constructor
    public Product(int productID,String productName,double price){
        this.productID = productID;
        this.productName= productName;
        this.price=price; 
    }

    //getters
    public int getProductId() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    //abstract method to display product details
    public abstract void productDetails();

    //abstract mehod to calculate final price after applying descount
    public abstract double calculateFinalPrice(int discountPercent);
}

class Electronics extends Product{
    private int WarrantyPeriod;

    public Electronics(int productID,String productName,double price,int WarrantyPeriod){
        super(productID,productName,price);
        this.WarrantyPeriod = WarrantyPeriod;
    }

    public int getWarrantyPeriod(){
        return WarrantyPeriod;
    }

    public void productDetails(){
        System.out.println("Product id: "+ getProductId());
        System.out.println("Product name: "+ getProductName());
        System.out.println("Price: "+ getPrice());
        System.out.println("Warranty Period: "+ getWarrantyPeriod()+" months");
    }

    public double calculateFinalPrice(int discount) {
        return getPrice() - getPrice()*(double)(discount/100);
    }
}

class Clothing extends Product{
    private int size;
    private String color;
    
    public Clothing(int productID,String productName,double price,int size,String color){
        super(productID,productName,price);
        this.size = size;
        this.color = color;
    }
    //getters
    public int getsize(){
        return size;
    }

    public String getColor(){
        return color;
    } 
    public void productDetails() {
        System.out.println("Product ID: " + getProductId());
        System.out.println("Product Name: " + getProductName());
        System.out.println("Price: " + getPrice());
        System.out.println("Size: " + getsize());
        System.out.println("Color: " + getColor());
    }

    public double calculateFinalPrice(int discount) {
        return getPrice() - getPrice()*(double)(discount/100);
    }
}
//User interface for user actions
interface User{
    void addProduct(Product p);
    void createAccount();
    void browseProducts();
    void AddtoCart(Product p);
    void placeOrder();
}

class Order{
    private ArrayList<Product> orders;
    
    public Order(ArrayList<Product> orders){
        this.orders = new ArrayList<>(orders);
    }

    //total amount
    public int calculateTotalAmount() {
       int  totalAmount = 0;
        for (Product p : orders) {
            totalAmount += p.getPrice();
        }
        return totalAmount;
    }

    //order history
    public void orderHistory(){
        for(int i=0;i<orders.size();i++){
            System.out.println(orders.get(i).getProductName()); 
        }
    } 
}

class Guest implements User{
    private HashMap<Integer,Product> store;
    private ArrayList<Product> cart;
    public  ArrayList<Product> orderHistory;

    public Guest(){
        store = new HashMap<>();
        cart = new ArrayList<>();
        orderHistory= new ArrayList<>();
    }

    public void addProduct(Product p){
        store.put(p.getProductId(),p);
        System.out.println("Product added successfully!");
    }

    public void createAccount(){
         System.out.println("Guests cannot create accounts. Please proceed as a registered user.");
    }

    public void browseProducts() {
        System.out.println("Available Products:");
        for (Product product : store.values()) {
            product.productDetails();
        }
    }
    
    public void AddtoCart(Product p){
        cart.add(p);
        System.out.println("Item added to cart!");
    }
    
    public void placeOrder(){
        if(!cart.isEmpty()){
           for(int i=0;i<cart.size();i++){
              orderHistory.add(cart.get(i));
           }
           cart.clear();
           System.out.println("Order placed Successfully");
        }
        else {
            System.out.println("Cart is empty. Please add products to cart before placing an order.");
        }
    }
}

class RegisteredUser{
    private HashMap<Integer,Product> store = new HashMap<>();
    private HashMap<String,Integer> accounts = new HashMap<>();
    private ArrayList<Product> cart = new ArrayList<>();
    public static ArrayList<Product> orderHistory = new ArrayList<>();


    void addProduct(Product p){
        store.put(p.getProductId(),p);
        System.out.println("Product added successfully!");
    }

    void createAccount(String name,int USerID){
         accounts.put(name,USerID);
         System.out.println("USer added successfully!");
    }

    public void browseProducts() {
        System.out.println("Available Products:");
        for (Product product : store.values()) {
            product.productDetails();
        }
    }
    
    public void AddtoCart(Product p){
        cart.add(p);
        System.out.println("Item added to cart!");
        // System.out.println();
    }
    
    public void placeOrder(){
        if(!cart.isEmpty()){
           orderHistory.addAll(cart);
        //    System.out.println(orderHistory.size());
           cart.clear();
           System.out.println("Order placed Successfully");
        }
        else {
            System.out.println("Cart is empty. Please add products to cart before placing an order.");
        }
    }
}

public class OnlineShoppingSystem{
    public static void main(String args[]){

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        boolean flag = true;
         
        RegisteredUser user = new RegisteredUser();
        Order o = new Order(RegisteredUser.orderHistory);

        do {
            System.out.println("****************Welcome to the Online Shopping System*****************");
            System.out.println("1. Add Products in App\n" +
                    "2. Create Account\n" +
                    "3. Browse Product\n" +
                    "4. Add to Cart\n" +
                    "5. Total Amount\n" +
                    "6. Place order\n" +
                    "7. Order History\n" +
                    "8. Exit");

            System.out.print("Enter your choice ");
            int n = sc1.nextInt();
            
            switch (n) {
                case 1:
                    System.out.print("If you want to add Clothes enter 'c' if you wnat to add elctronics enter 'e': ");
                    char ch = sc1.next().charAt(0);
                    System.out.print("Eneter the Product ID: ");
                    int id = sc1.nextInt();
                    System.out.print("Enter the Product name:");
                    String pname = sc2.nextLine();
                    System.out.print("Enter the price: ");
                    double price = sc2.nextDouble();
                    if (ch == 'c') {
                        System.out.print("Enter the size: ");
                        int size = sc1.nextInt();
                        System.out.print("Enter the color: ");
                        String color = sc1.nextLine();
                        Clothing c1 = new Clothing(id, pname, price, size, color);
                        user.addProduct(c1);
                    } else if (ch == 'e') { 
                        System.out.print("Warranty period in months: ");
                        int Warranty = sc1.nextInt();
                        Electronics e1 = new Electronics(id, pname, price, Warranty);
                        user.addProduct(e1);
                    } else {
                        System.out.println("Invalid Input!");
                    }
                    break;
                case 2:
                   System.out.println("Enter the name of the user: ");
                   String name = sc2.nextLine();
                   System.out.println("Enter the user ID:");
                   int userID = sc1.nextInt();
                   user.createAccount(name, userID);
                   System.out.println("Member Added Successfully!!");
                  break;

                case 3:
                   user.browseProducts();
                   break;

                case 4:
                  System.out.print("If you want to add Clothes to cart enter 'c' if you wnat to add elctronics enter 'e': ");
                  char c = sc1.next().charAt(0);
                  System.out.print("Enter the Product ID: ");
                  int i = sc1.nextInt();
                  System.out.print("Enter the Product name:");
                  String pn = sc2.nextLine();
                  System.out.print("Enter the price: ");
                  double p = sc2.nextDouble();
                if (c == 'c') {
                    System.out.print("Enter the size: ");
                    int s = sc1.nextInt();
                    System.out.print("Enter the color: ");
                    String clr = sc1.nextLine();
                    Clothing c1 = new Clothing(i, pn, p, s, clr);
                    user.AddtoCart(c1);;
                } else if (c == 'e') { 
                    System.out.print("Warranty period in months: ");
                    int W = sc1.nextInt();
                    Electronics e1 = new Electronics(i, pn, p, W);
                    user.AddtoCart(e1);;
                } else {
                    System.out.println("Invalid Input!");
                }
                break;
                case 5:
                   System.out.println(o.calculateTotalAmount()); 
                   break;       
                case 6:
                   user.placeOrder();
                   break;
                case 7:
                   o.orderHistory();
                   break;

                case 8:  
                    flag=false;
                    break;
                default:
                   System.out.println("Invalid input!");
                   break;
            }
        }while(flag);
        sc1.close();
        sc2.close();
        //  Product laptop = new Electronics(1, "Laptop", 999.99, 12);
        //  Product tShirt = new Clothing(2, "T-Shirt", 19.99, 10, "Blue");
 
        //  // Creating a guest user
    
 
        //  // Adding products to the store
        //  r.addProduct(laptop);
        //  r.addProduct(tShirt);
 
        //  // Browsing available products
        //  r.browseProducts();
 
        //  // Adding products to cart
        //  r.AddtoCart(laptop);
        //  r.AddtoCart(tShirt);
        

        //  for(int i=0;i<RegisteredUser.orderHistory.size();i++)
        //  {
        //     System.out.println("Product Name: " + RegisteredUser.orderHistory.get(i).getProductName());
        //  }
     
        

        //  // Placing an order
        //  r.placeOrder();
        }
}