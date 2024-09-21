import java.io.Serializable;
import java.util.Arrays;

public class TableOrder implements Order, Serializable {
    private int size;
    private MenuItem[] items;
    private Customer customer;

    public TableOrder() {
        size = 0;
        items = new MenuItem[10];
    }

    @Override
    public boolean add(MenuItem item) {
        if (size < items.length) {
            items[size] = item;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public String[] itemNames() {
        String[] itemNames = new String[size];
        for (int i = 0; i < size; i++) {
            itemNames[i] = items[i].getName();
        }
        return itemNames;
    }

    @Override
    public int itemsQuantity() {
        return size;
    }

    @Override
    public int itemQuantity(String itemName) {
        int quantity = 0;
        for (MenuItem item : items) {
            if (item != null && item.getName().equals(itemName)) {
                quantity++;
            }
        }
        return quantity;
    }

    @Override
    public int itemQuantity(MenuItem item) {
        int quantity = 0;
        for (MenuItem menuItem : items) {
            if (menuItem != null && menuItem.equals(item)) {
                quantity++;
            }
        }
        return quantity;
    }

    @Override
    public MenuItem[] getItems() {
        MenuItem[] orderItems = new MenuItem[size];
        System.arraycopy(items, 0, orderItems, 0, size);
        return orderItems;
    }

    @Override
    public boolean remove(String itemName) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].getName().equals(itemName)) {
                for (int j = i; j < size - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(MenuItem item) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int removeAll(String itemName) {
        int removedCount = 0;
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].getName().equals(itemName)) {
                for (int j = i; j < size - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[size - 1] = null;
                size--;
                removedCount++;
                i--;
            }
        }
        return removedCount;
    }

    @Override
    public int removeAll(MenuItem item) {
        int removedCount = 0;
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[size - 1] = null;
                size--;
                removedCount++;
                i--;
            }
        }
        return removedCount;
    }

    @Override
    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] sortedItems = getItems();
        Arrays.sort(sortedItems, (item1, item2) -> Integer.compare(item2.getCost(), item1.getCost()));
        return sortedItems;
    }

    @Override
    public int costTotal() {
        int totalCost = 0;
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                totalCost += items[i].getCost();
            }
        }
        return totalCost;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}