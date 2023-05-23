package ua.com.diploma.onheight.model.product;

public enum ProductType {

    HOMEMADE_CLOTHING("HMCL", "Саморобний одяг"),
    TECHNOLOGY("TECH", "Технології"),
    ARTWORK("ART", "Художні роботи"),
    FOOD_AND_BEVERAGES("F&B", "Їжа та напої"),
    HOME_DECOR("DECOR", "Декор для дому"),
    BEAUTY_AND_PERSONAL_CARE("BEAUTY", "Краса та особиста гігієна"),
    JEWELRY("JEWELRY", "Ювелірні вироби"),
    SPORTS_AND_FITNESS("SPORTS", "Спорт та фітнес"),
    TOYS_AND_GAMES("TOYS", "Іграшки та ігри"),
    BOOKS_AND_STATIONERY("BOOKS", "Книги та канцелярія");

    private final String key;
    private final String value;

    ProductType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static ProductType get(String key) {
        for (ProductType t : values()) {
            if (t.getKey().equals(key)) {
                return t;
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

