import java.util.Random;

public abstract class Dress {
	private String id;
	private String name;
    protected int fabricPrice;
    private String fabricType;

    public Dress(String name, int fabricPrice, String fabricType) {
        this.name = name;
        this.fabricPrice = fabricPrice;
        this.fabricType = fabricType;
        this.id = generateId();
    }

    protected String generateId() {
    	return "DR" + (new Random().nextInt(900) + 100);
    }
    
    public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFabricPrice(int fabricPrice) {
		this.fabricPrice = fabricPrice;
	}

	public void setFabricType(String fabricType) {
		this.fabricType = fabricType;
	}

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