import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        TableOrdersManager tableOrdersManager = new TableOrdersManager(10);
        InternetOrdersManager internetOrdersManager = new InternetOrdersManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить заказ на столик");
            System.out.println("2. Добавить интернет-заказ");
            System.out.println("3. Вывести информацию о заказах");
            System.out.println("4. Сохранить заказ на столик в файл");
            System.out.println("5. Загрузить заказ на столик из файла");
            System.out.println("6. Сохранить интернет-заказ в файл");
            System.out.println("7. Загрузить интернет-заказ из файла");
            System.out.println("8. Выйти");

            int choice = scanner.nextInt();
            String userFirstName;
            String userSecondName;
            int userAge;
            //int choice;
           // String[] addressInfo;
            String city;
            int zipCode;
            String streetName;
            int buildingNumber;
            char buildingLetter;
            int apartmentNumber;

            Address address;
            Customer customer;
            MenuItem dish1 = new Dish(10, "Спагетти", "Вкусное блюдо из пасты");
            MenuItem drink1 = new Drink(2, "Кола", "Освежающая кола");
            MenuItem dish2 = new Dish(8, "Бургер", "Вкусный бургер с картошкой");
            MenuItem drink2 = new Drink(1, "Вода", "Бутилированная вода");
            switch (choice) {
                case 1:
                    System.out.println("Введите номер столика: ");
                    int tableNumber = scanner.nextInt();
                    scanner.nextLine(); // Считываем перевод строки

                    // Введите информацию о пользователе и адресе
                    System.out.print("Введите имя пользователя: ");
                    userFirstName = scanner.nextLine();
                    System.out.print("Введите фамилию пользователя: ");
                    userSecondName = scanner.nextLine();
                    System.out.print("Введите возраст пользователя: ");
                    userAge = scanner.nextInt();
                    scanner.nextLine(); // Считываем перевод строки

                    customer = new Customer(userFirstName, userSecondName, userAge);

                    TableOrder tableOrder = new TableOrder();
                    tableOrder.setCustomer(customer);
                    tableOrder.add(dish1);
                    tableOrder.add(drink1);

                    try {
                        tableOrdersManager.add(tableOrder, tableNumber);
                        System.out.println("Добавлен заказ на столик " + tableNumber);
                    } catch (IllegalTableNumber | OrderAlreadyAddedException e) {
                        System.err.println("Произошла ошибка: " + e.getMessage());
                    }
                    break;

                case 2:
                    scanner.nextLine(); // Считываем перевод строки
                    // Введите информацию о пользователе и адресе
                    System.out.print("Введите имя пользователя: ");
                    userFirstName = scanner.nextLine();
                    System.out.print("Введите фамилию пользователя: ");
                    userSecondName = scanner.nextLine();
                    System.out.print("Введите возраст пользователя: ");
                    userAge = scanner.nextInt();
                    scanner.nextLine(); // Считываем перевод строки

                    System.out.println("Введите информацию об адресе (город, индекс, улица, номер дома, буква дома, номер квартиры): ");
                    System.out.print("Город: ");
                    city = scanner.nextLine();
                    System.out.print("Индекс: ");
                    zipCode = scanner.nextInt();
                    scanner.nextLine(); // Считываем перевод строки
                    System.out.print("Улица: ");
                    streetName = scanner.nextLine();
                    System.out.print("Номер дома: ");
                    buildingNumber = scanner.nextInt();
                    scanner.nextLine(); // Считываем перевод строки
                    System.out.print("Буква дома: ");
                    buildingLetter = scanner.nextLine().charAt(0);
                    System.out.print("Номер квартиры: ");
                    apartmentNumber = scanner.nextInt();
                    scanner.nextLine(); // Считываем перевод строки

                    address = new Address(city, zipCode, streetName, buildingNumber, buildingLetter, apartmentNumber);
                    customer = new Customer(userFirstName, userSecondName, userAge, address);

                    InternetOrder internetOrder = new InternetOrder();
                    internetOrder.setCustomer(customer);

                    internetOrder.add(dish2);
                    internetOrder.add(drink2);

                    internetOrdersManager.add(internetOrder);
                    System.out.println("Добавлен интернет-заказ");
                    break;

                case 3:
                    System.out.println("Информация о заказах на столиках:");
                    for (int i = 0; i < tableOrdersManager.getTableCount(); i++) {
                        Order order = tableOrdersManager.getOrder(i);
                        if (order != null) {
                            System.out.println("Столик " + i + ":");
                            System.out.println("Блюда: " + String.join(", ", order.itemNames()));
                            System.out.println("Сумма заказа: " + order.costTotal());
                        }
                    }

                    System.out.println("Информация об интернет-заказах:");
                    for (Order order : internetOrdersManager.getOrders()) {
                        System.out.println("Блюда: " + String.join(", ", order.itemNames()));
                        System.out.println("Сумма заказа: " + order.costTotal());
                    }
                    break;

                case 4:
                    // Сохранение заказов на столик в файл
                    serializeToFile(tableOrdersManager, "table_orders.ser");
                    break;
                case 5:
                    // Загрузка заказов на столик из файла
                    tableOrdersManager = deserializeFromFile("table_orders.ser");
                    for (int i = 0; i < tableOrdersManager.getTableCount(); i++) {
                        Order order = tableOrdersManager.getOrder(i);
                        if (order != null) {
                            System.out.println("Столик " + i + ":");
                            System.out.println("Блюда: " + String.join(", ", order.itemNames()));
                            System.out.println("Сумма заказа: " + order.costTotal());
                        }
                    }
                    break;
                case 6:
                    // Сохранение интернет-заказов в файл
                    serializeToFile(internetOrdersManager, "internet_orders.ser");
                    break;
                case 7:
                    // Загрузка интернет-заказов из файла
                    internetOrdersManager = deserializeFromFile("internet_orders.ser");
                    System.out.println("Информация об интернет-заказах:");
                    for (Order order : internetOrdersManager.getOrders()) {
                        System.out.println("Блюда: " + String.join(", ", order.itemNames()));
                        System.out.println("Сумма заказа: " + order.costTotal());
                    }
                    break;
                case 8:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    private static void serializeToFile(Serializable object, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(object);
            System.out.println("Сохранено в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    private static <T extends Serializable> T deserializeFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
        }
        return null;
    }
}