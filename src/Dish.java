import java.io.Serializable;

public final class Dish extends MenuItem implements Serializable {
    public Dish(int cost, String name, String description) {
        super(name, cost, description);
        if (cost < 0 || name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("Неверное название блюда");
        }
    }
}