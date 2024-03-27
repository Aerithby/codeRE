package newBaru;

import java.util.Random;

public class LimitedEditionDress extends Dress {
	int stock;

    public LimitedEditionDress(String name, int fabricPrice, String fabricType, int stock) {
        super(name, fabricPrice, fabricType);
        this.stock = stock;
    }

    @Override
    public String generateId() {
        return "LI" + (new Random().nextInt(900) + 100);
    }

    @Override
    public double calculateTotalPrice() {
        return fabricPrice + 50000 + ((10 - stock) * 10000);
    }
    
    public int getStock() {
        return stock;
    }
}
