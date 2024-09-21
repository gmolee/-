import java.io.Serializable;

public final class Customer implements Serializable {
    private String firstName;
    private String secondName;
    private int age;
    private Address address;
    private static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer("Mature", "Unknown", 18, Address.EMPTY_ADDRESS);
    private static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer("Not Mature", "Unknown", 17, Address.EMPTY_ADDRESS);

    public Customer(String firstName, String secondName, int age, Address address) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным.");
        }
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.address = address;
    }
    public Customer(String firstName, String secondName, int ages) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным.");
        }
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public static Customer getMatureUnknownCustomer() {
        return MATURE_UNKNOWN_CUSTOMER;
    }

    public static Customer getNotMatureUnknownCustomer() {
        return NOT_MATURE_UNKNOWN_CUSTOMER;
    }
}