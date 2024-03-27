import java.util.Random;

public class RegularDress extends Dress {
	private static final int MIN_ID = 100;
    private static final int ID_RANGE = 900;
    
	private int discount;

    public RegularDress(String name, int fabricPrice, String fabricType, int discount) {
        super(name, fabricPrice, fabricType);
        this.discount = discount;
    }

    @Override
    public String generateId() {
        return "RE" + (new Random().nextInt(ID_RANGE) + MIN_ID);
    }

    @Override
    public double calculateTotalPrice() {
    	double priceAfterDiscount = fabricPrice - (fabricPrice * discount / 100.0);
        return priceAfterDiscount + (priceAfterDiscount * 0.1); 
    }
    
    public int getDiscount() {
        return discount;
    }

	public static int getMinId() {
		return MIN_ID;
	}

	public static int getIdRange() {
		return ID_RANGE;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
}