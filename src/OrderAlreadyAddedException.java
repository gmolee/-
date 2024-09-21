public class OrderAlreadyAddedException extends RuntimeException {
    public OrderAlreadyAddedException() {
        super("Заказ уже добавлен к столику или по адресу.");
    }
}