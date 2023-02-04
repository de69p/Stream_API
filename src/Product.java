import java.time.LocalDate;

public class Product {

    public Product(String type, int price, String discount, LocalDate dateOfAddition, int identificationNumber) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.dateOfAddition = dateOfAddition;
        this.identificationNumber = identificationNumber;
    }

    String type;
    int price;
    String discount;
    LocalDate dateOfAddition;
    int identificationNumber;
    LocalDate year = LocalDate.now();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public LocalDate getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDate dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type = '" + type + '\'' +
                ", price = " + price + '\'' +
                ", discount = " + discount + '\'' +
                ", identificationNumber = " + identificationNumber + '\'' +
                '}';
    }

}
