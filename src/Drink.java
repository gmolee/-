import java.io.Serializable;

public final class Drink extends MenuItem implements Alcoholable, Serializable {
    private double alcoholVol;
    private DrinkTypeEnum type;

    public Drink(int cost, String name, String description) {
        super(name, cost, description);
        if (cost < 0 || name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("Неверное название напитка");
        }
    }
    public DrinkTypeEnum getType() {
        return type;
    }
    @Override
    public boolean isAlcoholicDrink() {
        return type == DrinkTypeEnum.BEER || type == DrinkTypeEnum.WINE ||
                type == DrinkTypeEnum.VODKA || type == DrinkTypeEnum.BRANDY ||
                type == DrinkTypeEnum.CHAMPAGNE || type == DrinkTypeEnum.WHISKEY ||
                type == DrinkTypeEnum.TEQUILA || type == DrinkTypeEnum.RUM ||
                type == DrinkTypeEnum.VERMUTH || type == DrinkTypeEnum.LIQUOR ||
                type == DrinkTypeEnum.JAGERMEISTER;
    }
    @Override
    public double getAlcoholVol() {
        if (isAlcoholicDrink()) {
            switch (type) {
                case BEER:
                    return 4.5;
                case WINE:
                    return 13.5;
                case VODKA:
                    return 40.0;
                case BRANDY:
                    return 35.0;
                case CHAMPAGNE:
                    return 12.0;
                case WHISKEY:
                    return 40.0;
                case TEQUILA:
                    return 38.0;
                case RUM:
                    return 40.0;
                case VERMUTH:
                    return 16.0;
                case LIQUOR:
                    return 20.0;
                case JAGERMEISTER:
                    return 35.0;
                default:
                    return 0.0;
            }
        } else {
            return 0.0;
        }
    }
}