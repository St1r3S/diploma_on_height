package ua.com.diploma.onheight.model.request;

public enum PaymentMethod {

    CASH("CASH", "Готівка"),
    CARD("CARD", "Кредитна/дебетова картка"),
    BANK_TRANSFER("BANK", "Банківський переказ"),
    CRYPTOCURRENCY("CRYP", "Криптовалюта"),
    VOUCHER("VOUC", "Подарунковий ваучер");

    private final String key;
    private final String value;

    PaymentMethod(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static PaymentMethod get(String key) {
        for (PaymentMethod m : values()) {
            if (m.getKey().equals(key)) {
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

