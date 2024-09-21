import java.io.Serializable;

class MenuItem implements Serializable {
    protected int cost;
    protected String name;
    protected String description;

    public MenuItem(String name, int cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    int getCost() {
        return cost;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }
}