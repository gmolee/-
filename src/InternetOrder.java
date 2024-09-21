import java.util.ArrayList;
import java.io.Serializable;
public class InternetOrder implements Order, Serializable {
    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;

    private class ListNode implements Serializable {
        ListNode next;
        MenuItem value;
        ListNode(MenuItem value) {
            this.value = value;
            this.next = null;
        }
    }

    public InternetOrder() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean add(MenuItem item) {
        ListNode newNode = new ListNode(item);
        newNode.value = item;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public String[] itemNames() {
        ArrayList<String> namesList = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            namesList.add(current.value.getName());
            current = current.next;
        }
        return namesList.toArray(new String[0]);
    }

    @Override
    public int itemsQuantity() {
        return size;
    }

    @Override
    public int itemQuantity(String itemName) {
        int quantity = 0;
        ListNode current = head;
        while (current != null) {
            if (current.value.getName().equals(itemName)) {
                quantity++;
            }
            current = current.next;
        }
        return quantity;
    }

    @Override
    public int itemQuantity(MenuItem item) {
        int quantity = 0;
        ListNode current = head;
        while (current != null) {
            if (current.value.equals(item)) {
                quantity++;
            }
            current = current.next;
        }
        return quantity;
    }

    @Override
    public MenuItem[] getItems() {
        MenuItem[] orderItems = new MenuItem[size];
        ListNode current = head;
        int index = 0;
        while (current != null) {
            orderItems[index] = current.value;
            current = current.next;
            index++;
        }
        return orderItems;
    }

    @Override
    public boolean remove(String itemName) {
        if (head == null) {
            return false;
        }
        if (head.value.getName().equals(itemName)) {
            head = head.next;
            size--;
            return true;
        }
        ListNode current = head;
        while (current.next != null) {
            if (current.next.value.getName().equals(itemName)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean remove(MenuItem item) {
        if (head == null) {
            return false;
        }
        if (head.value.equals(item)) {
            head = head.next;
            size--;
            return true;
        }
        ListNode current = head;
        while (current.next != null) {
            if (current.next.value.equals(item)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int removeAll(String itemName) {
        int removedCount = 0;
        while (head != null && head.value.getName().equals(itemName)) {
            head = head.next;
            size--;
            removedCount++;
        }
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.value.getName().equals(itemName)) {
                current.next = current.next.next;
                size--;
                removedCount++;
            } else {
                current = current.next;
            }
        }
        return removedCount;
    }

    @Override
    public int removeAll(MenuItem item) {
        int removedCount = 0;
        while (head != null && head.value.equals(item)) {
            head = head.next;
            size--;
            removedCount++;
        }
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.value.equals(item)) {
                current.next = current.next.next;
                size--;
                removedCount++;
            } else {
                current = current.next;
            }
        }
        return removedCount;
    }

    @Override
    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] sortedItems = getItems();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (sortedItems[j].getCost() < sortedItems[j + 1].getCost()) {
                    MenuItem temp = sortedItems[j];
                    sortedItems[j] = sortedItems[j + 1];
                    sortedItems[j + 1] = temp;
                }
            }
        }
        return sortedItems;
    }

    @Override
    public int costTotal() {
        int totalCost = 0;
        ListNode current = head;
        while (current != null) {
            totalCost += current.value.getCost();
            current = current.next;
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

