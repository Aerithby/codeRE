
import java.util.Random;

public class LimitedEditionDress extends Dress {
	
	private static final int MIN_ID = 100;
    private static final int ID_RANGE = 900;
    private int stock;
    
    public LimitedEditionDress(String name, int fabricPrice, String fabricType, int stock) {
        super(name, fabricPrice, fabricType);
        this.stock = stock;
    }

    @Override
    public String generateId() {
        return "LI" + (new Random().nextInt(ID_RANGE) + MIN_ID);
    }

    @Override
    public double calculateTotalPrice() {
        return fabricPrice + (fabricPrice * 0.2);
    }
    
    public static int getMinId() {
		return MIN_ID;
	}

	public static int getIdRange() {
		return ID_RANGE;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
        return stock;
    }
}
