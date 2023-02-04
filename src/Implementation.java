import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Implementation {
    List<Product> productList = Arrays.asList(
            new Product("Book", 433, "discount", LocalDate.of(2021, 10, 3), 243124225),
            new Product("Newspaper", 260, null, LocalDate.of(2022, 5, 15), 342323111),
            new Product("Book", 220, "discount", LocalDate.of(2023, 2, 24), 47782943),
            new Product("Newspaper", 270, "discount", LocalDate.of(2022, 2, 11), 363723842),
            new Product("NoteBook", 300, null, LocalDate.of(2023, 11, 22), 464287492),
            new Product("Book", 251, "discount", LocalDate.of(2021, 11, 30), 427518744),
            new Product("Book", 220, "discount", LocalDate.of(2023, 10, 22), 456827922),
            new Product("Book", 34, null, LocalDate.of(2023, 6, 10), 562984944),
            new Product("Book", 75, "discount", LocalDate.of(2023, 1, 9), 372675839),
            new Product("Book", 251, "discount", LocalDate.of(2021, 3, 25), 323453454),
            new Product("Book", 50, null, LocalDate.of(2023, 5, 28), 968458955),
            new Product("Book", 45, "discount", LocalDate.of(2022, 7, 17), 823784644));

    /**
     * ВАЖЛИВО! Всі завдання виконуються виключно із застосуванням Stream API
     * 1.1 Даний клас Product, який складається з властивостей:
     * - тип
     * - ціна
     * 1.2 Реалізувати метод отримання всіх продуктів у вигляді списку, категорія яких еквівалентна “Book” та ціна більш ніж 250
     */
    public void prod1() {
        productList.stream()
                .filter(product -> Objects.equals(product.getType(), "Book") && product.getPrice() > 250)
                .forEach(System.out::println);
    }

    /**
     * 2.1 Дан клас Product, який складається з властивостей:
     * - тип
     * - ціна
     * - можливість застосування знижки
     * 2.2 Реалізувати метод отримання всіх продуктів як списку, категорія яких еквівалентна “Book” і з можливістю застосування знижки. Фінальний список повинен містити продукти з застосованою знижкою 10%.
     * Так, якщо Продукт A був з ціною 1.0 USD, то його фінальна ціна залишатиметься 0.9 USD
     */
    public void prod2() {
        productList.stream().filter(product -> product.getType()
                        .equals("Book") && product.getDiscount()
                        .equals("discount"))
                .map(product -> product.getPrice() * 0.9)
                .forEach(product -> System.out.println("Discounted price of the book = " + product + " c.u."));
    }

    /**
     * 3.1 Даний клас Product, який складається з властивостей:
     * - тип
     * - ціна
     * - можливість застосування знижки
     * 3.2 Реалізувати метод отримання найдешевшого продукту з категорії “Book”
     * 3.3 У випадку, якщо жоден продукт не знайдено (ситуація, коли немає продукту з категорією), викинути виняток з повідомленням “Продукт [категорія: ім'я_категорії] не знайдено”
     */
    public void prod3() {
        Product min = productList.stream()
                .filter(product -> product.getType().equals("Book"))
                .min((a, b) -> a.getPrice() - b.getPrice()).get();
        System.out.println(min);
    }

    /**
     * 4.1 Даний клас Product, який складається з властивостей:
     * - тип
     * - ціна
     * - можливість застосування знижки
     * - дата додавання (можна використовувати тип даних java.time.LocalDate, java.time.LocalDateTime або java.util.Date)
     * 4.2 Реалізувати метод отримання трьох останніх доданих продуктів
     */
    public void prod4() {
        Comparator<Product> nameComparator = (h1, h2) -> h1.getDateOfAddition().compareTo(h2.getDateOfAddition());
        List<Product> lastAdded = productList.stream().sorted(nameComparator).limit(3).toList();
        System.out.println("Last Added = " + lastAdded);
    }

    /**
     * 5.1 Цей клас Product, який складається з властивостей:
     * - тип
     * - ціна
     * - можливість застосування знижки
     * - дата додавання (можна використовувати тип даних java.time.LocalDate, java.time.LocalDateTime або java.util.Date)
     * 5.2 Реалізувати метод калькуляції загальної вартості продуктів, які відповідають наступним критеріям:
     * - продукт додано протягом поточного року
     * - продукт має тип “Book”
     * - ціна продукту не перевищує 75
     */
    public void prod5() {
        productList.stream().filter(product -> product.getType()
                        .equals("Book") && product.getPrice() <= 75 && product.getDateOfAddition().getYear() == 2023)
                .forEach(System.out::println);
        double avg = productList.stream().filter(product -> product.getType()
                        .equals("Book") && product.getPrice() <= 75 && product.getDateOfAddition().getYear() == 2023)
                .mapToInt(Product::getPrice)
                .average()
                .orElse(0);
        System.out.println("Average = " + avg);
    }

    /**
     * 6.1 Дан клас Product, який складається з властивостей:
     * - ідентифікаційний номер
     * - тип
     * - ціна
     * - можливість застосування знижки
     * – дата додавання (можна використовувати тип даних java.time.LocalDate, java.time.LocalDateTime або java.util.Date)
     * 6.2 Реалізувати метод групування об'єктів за типом продукту. Таким чином результатом виконання методу
     * буде тип даних “Словник”, що зберігає пару ключ-значення: {тип: список_продуктів}
     */
    public void prod6() {
        Map<String, List<Product>> groupedProducts = productList.stream().collect(
                Collectors.groupingBy(Product::getType));
        for (Entry<String, List<Product>> item : groupedProducts.entrySet()) {

            System.out.println("Group: " + item.getKey());
            for (Product product : item.getValue()) {

                System.out.println("Type: " + product.getType() + ", Price: " + product.getPrice() + ", Discount: " + product.getDiscount() + ", Date of addition: " + product.getDateOfAddition());
            }
            System.out.println();
        }
    }
}
