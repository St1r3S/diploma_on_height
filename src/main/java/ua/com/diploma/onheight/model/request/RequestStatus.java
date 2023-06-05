package ua.com.diploma.onheight.model.request;

public enum RequestStatus {
    CREATED("CR", "Створено"),
    PENDING_PAYMENT("PP", "Очікує оплати"),
    PAID("PD", "Оплачено"),
    CONFIRMED("CN", "Підтверджено"),
    COMPLETED("CM", "Виконано"),
    CANCELED("CC", "Скасовано");

    private final String key;
    private final String value;

    RequestStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static RequestStatus getByKey(String key) {
        for (RequestStatus s : values()) {
            if (s.getKey().equals(key)) {
                return s;
            }
        }
        return null;
    }

    public static RequestStatus getByValue(String value) {
        for (RequestStatus s : values()) {
            if (s.getValue().equals(value)) {
                return s;
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
