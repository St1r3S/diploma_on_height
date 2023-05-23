package ua.com.diploma.onheight.model.user;

public enum Post {

    OWNER("OWN", "Власник"),
    MANAGER("MGR", "Менеджер"),
    WORKER("WRK", "Працівник");

    private final String key;
    private final String value;

    Post(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Post get(String key) {
        for (Post p : values()) {
            if (p.getKey().equals(key)) {
                return p;
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
