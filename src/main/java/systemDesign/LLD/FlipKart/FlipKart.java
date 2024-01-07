package systemDesign.LLD.FlipKart;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import systemDesign.LLD.utility.Address;
import systemDesign.LLD.utility.User;
import systemDesign.LLD.utility.UserService;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 Sellers should be able to add, delete and modify products they want to sell.
 The website should include a catalog of products.
 Buyers can search products by name, keyword or category.
 Buyers can add, delete or update items in a cart.
 Buyers can purchase items in the cart and make payments.
 Buyers can view their previous orders.
 Buyers can review and rate purchased products.

    functional requirement:
    /products -> add/update/remove
    /inventory -> add/update/remove
    /catalog  -> product search
    /search -> by name, keyword, category etc.
    /cart -> add/remove/update
    /orders
    /review
    /payments


    use-case diagram
    seller:
        products: add/remove/update
        list his product
    buyers:
        search
        cart
        payment
        orders
        review
    system:
        catalog
        notification
        suggestion

    class level design:
    User
        Seller
        Buyer
        System
    Product:
        builder
    ProductCategory
    Inventory
    Cart
    Order
    Review
    Payment
        CREDIT_CARD
        DEBIT_CARD
        UPI
    BillingItem
    Notification:
        Email
        sms
        push


    OOPs+SOLID+Design Patterns
 */
public class FlipKart {
}

class Seller extends User {
    String username;
    String password;
    String GSTIN;
}

class Buyer extends User {
    String username;
    String password;
    String phoneNumber;

    Address billingAddress;

    Address shippingAddress;
}

enum ProductCategory {
    ELECTRONICS,
    FASHION,
    HOME,
    BOOKS,
    BEAUTY,
    TOYS,
    SPORTS,
    AUTOMOTIVE,
    OTHERS
}

class Product {
    String id;
    String name;
    String description;
    Double price;
    ProductCategory category;
    String sellerId;

    String brand;

    List<String> images;

    String color;
    String size;
    String weight;
    String dimension;

    String manufacturer;

    String modelNumber;
    String releaseDate;
    String barcodeId;

    String HSNCode;

    List<Review> reviews;

    // Others
}

class ProductBuilder{ // builder design pattern
    String id;
    String name;
    String description;
    Double price;
    ProductCategory category;
    String sellerId;

    String brand;

    // Others

    private ProductBuilder() {};

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public ProductBuilder category(ProductCategory productCategory) {
        this.category = productCategory;
        return this;
    }

    // setter for all other fields

    public Product build() {
        Product product = new Product();
        product.id = this.id;
        // set all other fields
        return product;
    }
}

class Inventory {
    String id;
    String sellerId;
    String productId;
    Integer quantity;
}

class Item {
    String id;
    String productId;
    Integer quantity;
    boolean isAvailable;
}
class Cart {
    String id;
    String userId;
    List<Item> items;
}

enum OrderStatus {
    PENDING,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

class Order {
    String id;
    String userId;
    Map<Product, Integer> products;
    Double totalPrice;
    Address shippingAddress;
    Address billingAddress;
    String paymentId;

    OrderStatus orderStatus;
}

class Review {
    String id;
    String userId;
    String productId;
    Integer rating;
    String description;
}

class ProductRequest {
    String id;
    String name;
    String description;
    Double price;
    ProductCategory category;
    String sellerId;

    String brand;

    Integer quantity;

    // Others
}

//  <----------- Services ------------>
class ProductService {

    List<Product> products;

    private InventoryService inventoryService;
    public Product saveProduct(Product product) {
        return null;
    }
    public Product getProduct(String id) {
        return null;
    }

    public Product addProduct(ProductRequest productRequest) {
        // create product object
        Product product = ProductBuilder.builder()
                .category(productRequest.category)
                .build();
        // add product to inventory
        inventoryService.addProductToInventory(product.id, 1, product.sellerId);
        return product;
    }

    public Product updateProduct(ProductRequest productRequest, String id) {
        Product product = getProduct(id);
        // update product as per request

        // update inventory
        Inventory inventory = inventoryService.getInventory(product.sellerId, product.id);
        inventory.quantity =  productRequest.quantity;
        inventoryService.updateInventory(inventory);
        return null;
    }

    public boolean deleteProduct(String id) {
        Product product = getProduct(id);
        // delete product

        // delete inventory
        Inventory inventory = inventoryService.getInventory(product.sellerId, product.id);
        inventoryService.deleteInventory(inventory.id);
        return true;
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return null;
    }

    public List<Product> getProductsBySeller(String sellerId) {
        return null;
    }

    public List<Product> getProductsByKeyword(String keyword) {
        return null;
    }
}

enum PredicateType {
    SELLER,
    BRAND,
    CATEGORY,
    KEYWORD
}

class PredicateBuilder{
    Predicate<Product> productPredicate;
    private PredicateBuilder() {
        productPredicate = product -> true;
    }



    public static PredicateBuilder builder() {
        return new PredicateBuilder();
    }

    PredicateBuilder seller(String sellerId) {
        if (sellerId == null) {
            return this;
        }
        productPredicate = productPredicate.and(product -> product.sellerId.equals(sellerId));
        return this;
    }

    PredicateBuilder brand(String brand) {
        if (brand == null) {
            return this;
        }
        productPredicate = productPredicate.and(product -> product.brand.equals(brand));
        return this;
    }

    PredicateBuilder category(ProductCategory category) {
        if (category == null) {
            return this;
        }
        productPredicate = productPredicate.and(product -> product.category.equals(category));
        return this;
    }

    PredicateBuilder keyword(String keyword) {
        if (keyword == null) {
            return this;
        }
        productPredicate = productPredicate.and(product -> product.name.contains(keyword));
        return this;
    }

    Predicate<Product> build() {
        return productPredicate;
    }
}



class InventoryService {
    public Inventory getInventory(String id) {
        return null;
    }

    public Inventory getInventory(String sellerId, String productId) {
        // get inventory by sellerId and productId
        return null;
    }
    public Inventory deleteInventory(String inventoryId) {
        // get inventory by sellerId and productId
        return null;
    }

    public Inventory updateInventory(Inventory inventory) {
        // get inventory by sellerId and productId
        return null;
    }

    public Inventory addProductToInventory(String productId, Integer quantity, String sellerId) {
        // get inventory by sellerId and productId
        // if inventory exists, update quantity
        // else create new inventory
        return null;
    }
}

class CatalogService {
    private ProductService productService;
    List<Product> products;

    public void getProducts(String keyword) {
         products =  productService.getProductsByKeyword(keyword);
    }
    public List<Product> filterProduct(Map<PredicateType, Object> searchMap) {
        PredicateBuilder predicateBuilder = PredicateBuilder.builder();
        if (searchMap.containsKey(PredicateType.SELLER)) {
            predicateBuilder.seller((String) searchMap.get(PredicateType.SELLER));
        }
        if (searchMap.containsKey(PredicateType.BRAND)) {
            predicateBuilder.brand((String) searchMap.get(PredicateType.BRAND));
        }
        if (searchMap.containsKey(PredicateType.CATEGORY)) {
            predicateBuilder.category((ProductCategory) searchMap.get(PredicateType.CATEGORY));
        }
        if (searchMap.containsKey(PredicateType.KEYWORD)) {
            predicateBuilder.keyword((String) searchMap.get(PredicateType.KEYWORD));
        }
        return products.stream().filter(predicateBuilder.build()).toList();
    }
}


class CartService {
    InventoryService inventoryService;
    ProductService productService;

    OrderService orderService;
    public Cart getCart(String id) {
        return null;
    }

    public Cart getCardByUserId(String userId) {
        // get cart by userId
        Cart cart = getCart(userId);
        // if cart exists,
        if (cart != null) {
            //    for each item in cart, check inventory
            for (Item item : cart.items) {
                Inventory inventory = inventoryService.getInventory(item.id);
                if (inventory.quantity < item.quantity) {
                    item.isAvailable = false;
                }
            }
        }
        return cart;
    }

    public Cart addToCart(String productId, Integer quantity, String userId) {
        // get cart by userId
        Cart cart = getCart(userId);
        // if cart exists
        if (cart != null) {
            //     if product exists, update quantity (check inventory)
            Item item = cart.items.stream().filter(item1 -> item1.productId.equals(productId)).findFirst().orElse(null);
            if (item != null) {
                Inventory inventory = inventoryService.getInventory(item.id);
                if (inventory.quantity < item.quantity + quantity) {
                    throw new RuntimeException("Product out of stock");
                }
                item.quantity += quantity;
            } else {
                //     else add product
                Product product = productService.getProduct(productId);
                if (product == null) {
                    throw new RuntimeException("Product not found");
                }
                Inventory inventory = inventoryService.getInventory(product.sellerId, product.id);
                if (inventory.quantity < quantity) {
                    throw new RuntimeException("Product out of stock");
                }
                cart.items.add(new Item());
            }
        } else {
            // else create new cart
            cart = new Cart();
            cart.userId = userId;
            Product product = productService.getProduct(productId);
            if (product == null) {
                throw new RuntimeException("Product not found");
            }
            Inventory inventory = inventoryService.getInventory(product.sellerId, product.id);
            if (inventory.quantity < quantity) {
                throw new RuntimeException("Product out of stock");
            }
            cart.items.add(new Item());
        }
        return cart;
    }

    public Cart deleteFromCart(String productId, String userId) {
        // get cart by userId
        Cart cart = getCart(userId);
        // if cart exists,
        if (cart != null) {
            //     if product exists, update quantity
            Item item = cart.items.stream().filter(item1 -> item1.productId.equals(productId)).findFirst().orElse(null);
            if (item != null) {
                cart.items.remove(item);
            }
        }
        return cart;
    }

    public void checkout(String userId) {
        // get cart by userId
        Cart cart = getCart(userId);
        // if cart exists,
        if (cart != null) {
            // for each product in cart, check inventory
            for (Item item : cart.items) {
                Inventory inventory = inventoryService.getInventory(item.id);
                if (inventory.quantity < item.quantity) {
                    throw new RuntimeException("Product out of stock");
                }
            }
            // create order
            orderService.createOrder(cart);
            // update inventory
            for (Item item : cart.items) {
                Inventory inventory = inventoryService.getInventory(item.id);
                inventory.quantity -= item.quantity;
                inventoryService.updateInventory(inventory);
            }
            // send notification
        }
        //     for each product in cart, check inventory
        //          // if inventory is less than quantity, throw exception ("Product out of stock"")
        // create order
        // update inventory
        // send notification
    }
}


class OrderService {

    private ProductService productService;

    private InventoryService inventoryService;

    private UserService userService;
    public Order getOrder(String id) {
        return null;
    }

    public void saveOrder(Order order) {
        // save order
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.userId = cart.userId;
        order.products = cart.items.stream().collect(Collectors.toMap(item -> productService.getProduct(item.productId), item -> item.quantity));
        order.totalPrice = cart.items.stream().map(item -> item.quantity * productService.getProduct(item.productId).price).reduce(0.0, Double::sum);
        Buyer buyer  = (Buyer) userService.getUser(cart.userId);
        order.shippingAddress = buyer.shippingAddress;
        order.billingAddress = buyer.billingAddress;
        order.orderStatus =  OrderStatus.CONFIRMED;
        saveOrder(order);
        // create order

        // update inventory
        for (Item item : cart.items) {
            // get lock on inventory
            Inventory inventory = inventoryService.getInventory(item.id);
            inventory.quantity -= item.quantity;
            inventoryService.updateInventory(inventory);
            // release lock
        }

        // make payment

        // send notification
        return order;
    }

    public Order cancelOrder(String id) {
        // get Order
        Order order = getOrder(id);
        order.orderStatus = OrderStatus.CANCELLED;
        saveOrder(order);
        // update inventory
        for (Map.Entry<Product, Integer> entry : order.products.entrySet()) {
            // get lock on inventory
            Inventory inventory = inventoryService.getInventory(entry.getKey().id);
            inventory.quantity += entry.getValue();
            inventoryService.updateInventory(inventory);
            // release lock
        }
        // refund initiate
        // send notification
        return order;
    }

    public Order buyDirect(Product product, Integer quantity, String userId) {
        // create order
        Order order = new Order();
        order.userId = userId;
        order.products = Map.of(product, quantity);
        order.totalPrice = quantity * product.price;
        Buyer buyer  = (Buyer) userService.getUser(userId);
        order.shippingAddress = buyer.shippingAddress;
        order.billingAddress = buyer.billingAddress;
        order.orderStatus =  OrderStatus.CONFIRMED;
        saveOrder(order);
        // update inventory
        Inventory inventory = inventoryService.getInventory(product.sellerId, product.id);
        inventory.quantity -= quantity;
        inventoryService.updateInventory(inventory);
        // make payment
        // send notification
        return order;
    }
}

class ReviewService {

    ProductService productService;

    public void save(Review review){
        // save review
    }
    public Review getReview(String id) {
        return null;
    }

    public Review createReview(String userId, String productId, String description, Integer rating) {
        // create review
        Review review = new Review();
        review.userId = userId;
        review.productId = productId;
        review.description = description;
        review.rating = rating;
        save(review);

        // add review to product
        Product product = productService.getProduct(productId);
        product.reviews.add(review);
        productService.saveProduct(product);
        // send notification
        return review;
    }

    public Review deleteReview(String id) {
        // delete review
        Review review = getReview(id);

        // remove review from product
        Product product = productService.getProduct(review.productId);
        product.reviews.remove(review);
        productService.saveProduct(product);
        // send notification
        return review;
    }
}



// payment from utility
// notification from utility
// user from utility


