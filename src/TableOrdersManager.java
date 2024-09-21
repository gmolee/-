import java.io.Serializable;

public class TableOrdersManager implements OrdersManager, Serializable {
    private Order[] orders;
    private int tableCount;

    public TableOrdersManager(int tableCount) {
        this.tableCount = tableCount;
        orders = new Order[tableCount];
    }

    void add(Order order, int tableNumber) {
        if (tableNumber >= 0 && tableNumber < tableCount) {
            if (orders[tableNumber] != null) {
                throw new OrderAlreadyAddedException();
            } else {
                orders[tableNumber] = order;
            }
        } else {
            throw new IllegalTableNumber();
        }
    }

    void addItem(MenuItem item, int tableNumber) {
        if (tableNumber >= 0 && tableNumber < tableCount && orders[tableNumber] != null) {
            orders[tableNumber].add(item);
        }
    }

    int freeTableNumber() {
        for (int i = 0; i < tableCount; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1; // No free table
    }

    int[] freeTableNumbers() {
        int[] freeTables = new int[tableCount];
        int count = 0;
        for (int i = 0; i < tableCount; i++) {
            if (orders[i] == null) {
                freeTables[count] = i;
                count++;
            }
        }
        int[] result = new int[count];
        System.arraycopy(freeTables, 0, result, 0, count);
        return result;
    }

    Order getOrder(int tableNumber) {
        if (tableNumber >= 0 && tableNumber < tableCount) {
            return orders[tableNumber];
        }
        return null;
    }

    void remove(int tableNumber) {
        if (tableNumber >= 0 && tableNumber < tableCount) {
            orders[tableNumber] = null;
        }
    }

    int remove(Order order) {
        int removedCount = 0;
        for (int i = 0; i < tableCount; i++) {
            if (orders[i] == order) {
                orders[i] = null;
                removedCount++;
            }
        }
        return removedCount;
    }

    int removeAll(Order order) {
        int removedCount = 0;
        for (int i = 0; i < tableCount; i++) {
            if (orders[i] == order) {
                orders[i] = null;
                removedCount++;
            }
        }
        return removedCount;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int quantity = 0;
        for (Order order : orders) {
            if (order != null) {
                quantity += order.itemQuantity(itemName);
            }
        }
        return quantity;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int quantity = 0;
        for (Order order : orders) {
            if (order != null) {
                quantity += order.itemQuantity(item);
            }
        }
        return quantity;
    }

    @Override
    public Order[] getOrders() {
        return orders;
    }

    @Override
    public int ordersCostSummary() {
        int totalCost = 0;
        for (Order order : orders) {
            if (order != null) {
                totalCost += order.costTotal();
            }
        }
        return totalCost;
    }

    @Override
    public int ordersQuantity() {
        int quantity = 0;
        for (Order order : orders) {
            if (order != null) {
                quantity++;
            }
        }
        return quantity;
    }
    public int getTableCount() {
        return tableCount;
    }
}