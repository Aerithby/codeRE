package newBaru;

public abstract class Dress {
	protected String id;
	protected String name;
    protected int fabricPrice;
    protected String fabricType;

    public Dress(String name, int fabricPrice, String fabricType) {
        this.name = name;
        this.fabricPrice = fabricPrice;
        this.fabricType = fabricType;
        this.id = generateId();
    }

    public abstract String generateId();
    public abstract double calculateTotalPrice();
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFabricPrice() {
        return fabricPrice;
    }

    public String getFabricType() {
        return fabricType;
    }
}
