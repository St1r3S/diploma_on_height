package ua.com.diploma.onheight.model.user;

public enum Gender {
    MALE("M", "чоловік"),
    FEMALE("F", "жінка");

    private final String key;
    private final String value;

    Gender(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Gender get(String key) {
        for (Gender g : values()) {
            if (g.getKey().equals(key)) {
                return g;
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
