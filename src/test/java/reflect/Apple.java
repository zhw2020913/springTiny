package reflect;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 17:27
 */
public class Apple {
    private String color;
    private float price;

    public Apple(String color, float price) {
        this.color = color;
        this.price = price;
    }
    public Apple (){}

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public float getPrice() {
        return price;
    }
}
