
interface Order {
    boolean add(MenuItem item);
    String[] itemNames();
    int itemsQuantity();
    int itemQuantity(String itemName);
    int itemQuantity(MenuItem itemName);
    MenuItem[] getItems();
    boolean remove(String itemName);
    boolean remove(MenuItem itemName);
    int removeAll(String itemName);
    int removeAll(MenuItem itemName);
    MenuItem[] sortedItemsByCostDesc();
    int costTotal();
    Customer getCustomer();
    void setCustomer(Customer customer);
}