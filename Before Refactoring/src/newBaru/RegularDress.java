package newBaru;

import java.util.Random;

public class RegularDress extends Dress {
	int discount;

    public RegularDress(String name, int fabricPrice, String fabricType, int discount) {
        super(name, fabricPrice, fabricType);
        this.discount = discount;
    }

    @Override
    public String generateId() {
        return "RE" + (new Random().nextInt(900) + 100);
    }

    @Override
    public double calculateTotalPrice() {
        return (fabricPrice + 50000) * ((100 - discount) / 100.0);
    }
    
    public int getDiscount() {
        return discount;
    }
}
