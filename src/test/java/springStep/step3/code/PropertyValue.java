package springStep.step3.code;

/**
 * 用于bean的属性注入
 *
 */
public class PropertyValue {

    private final String name;//bean的属性名

    private final Object value;//bean的属性名对应的属性值

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
