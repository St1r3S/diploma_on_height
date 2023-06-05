package ua.com.diploma.onheight.model.request;

public enum DeliveryMethod {

    COURIER("COUR", "Кур'єрська доставка"),
    PICKUP("PICK", "Самовивіз"),
    POST("POST", "Поштова доставка"),
    EXPRESS("EXPR", "Експрес-доставка"),
    TRANSPORT("TRAN", "Транспортна доставка"),
    DIGITAL("DIGI", "Цифрова доставка");

    private final String key;
    private final String value;

    DeliveryMethod(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static DeliveryMethod getByKey(String key) {
        for (DeliveryMethod m : values()) {
            if (m.getKey().equals(key)) {
                return m;
            }
        }
        return null;
    }

    public static DeliveryMethod getByValue(String value) {
        for (DeliveryMethod m : values()) {
            if (m.getValue().equals(value)) {
                return m;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

