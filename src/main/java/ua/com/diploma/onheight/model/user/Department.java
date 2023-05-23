package ua.com.diploma.onheight.model.user;

public enum Department {

    MANAGEMENT("MGT", "Управління"),
    MARKETING("MKT", "Маркетинг"),
    FINANCE("FIN", "Фінанси"),
    SALES("SLS", "Продажі"),
    HR("HR", "Кадри"),
    PURCHASE("PUR", "Закупівлі");


    private final String key;
    private final String value;

    Department(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Department get(String key) {
        for (Department d : values()) {
            if (d.getKey().equals(key)) {
                return d;
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
