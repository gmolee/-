import java.io.Serializable;

class InternetOrdersManager implements OrdersManager, Serializable {
    private QueueNode head;
    private QueueNode tail;
    private int size;

    public InternetOrdersManager() {
        head = null;
        tail = null;
        size = 0;
    }

    boolean add(Order order) {
        QueueNode newNode = new QueueNode(order);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    Order remove() {
        if (head == null) {
            return null;
        }
        QueueNode removedNode = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return removedNode.value;
    }

    Order order() {
        return (head != null) ? head.value : null;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int quantity = 0;
        QueueNode currentNode = head;
        while (currentNode != null) {
            quantity += currentNode.value.itemQuantity(itemName);
            currentNode = currentNode.next;
        }
        return quantity;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int quantity = 0;
        QueueNode currentNode = head;
        while (currentNode != null) {
            quantity += currentNode.value.itemQuantity(item);
            currentNode = currentNode.next;
        }
        return quantity;
    }

    @Override
    public Order[] getOrders() {
        Order[] orderArray = new Order[size];
        QueueNode currentNode = head;
        int index = 0;
        while (currentNode != null) {
            orderArray[index] = currentNode.value;
            currentNode = currentNode.next;
            index++;
        }
        return orderArray;
    }

    @Override
    public int ordersCostSummary() {
        int totalCost = 0;
        QueueNode currentNode = head;
        while (currentNode != null) {
            totalCost += currentNode.value.costTotal();
            currentNode = currentNode.next;
        }
        return totalCost;
    }

    @Override
    public int ordersQuantity() {
        return size;
    }

    private static class QueueNode implements Serializable{
        Order value;
        QueueNode next;
        QueueNode prev;

        QueueNode(Order value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}
