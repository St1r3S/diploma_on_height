package ua.com.diploma.onheight.model.product;

public enum PriceCurrency {

    USD("USD", "Долар США"),
    EUR("EUR", "Євро"),
    UAH("UAH", "Українська гривня"),
    GBP("GBP", "Фунт стерлінгів"),
    JPY("JPY", "Японська єна"),
    AUD("AUD", "Австралійський долар"),
    CAD("CAD", "Канадський долар"),
    CHF("CHF", "Швейцарський франк"),
    CNY("CNY", "Китайський юань"),
    NZD("NZD", "Новозеландський долар");

    private final String key;
    private final String value;

    PriceCurrency(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static PriceCurrency get(String key) {
        for (PriceCurrency c : values()) {
            if (c.getKey().equals(key)) {
                return c;
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

