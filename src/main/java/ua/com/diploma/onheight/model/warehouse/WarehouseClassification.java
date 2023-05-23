package ua.com.diploma.onheight.model.warehouse;

public enum WarehouseClassification {

    RAW_MATERIALS("RAW", "Склад сировини"),
    MATERIALS("MAT", "Склад матеріалів"),
    COMPONENTS("COMP", "Склад комплектуючих"),
    WORK_IN_PROGRESS("WIP", "Склад незавершеного виробництва"),
    FINISHED_GOODS("FG", "Склад готових товарів"),
    PACKAGING("PACK", "Склад тари"),
    DEFECTIVE_PRODUCTS("DEFECT", "Склад продукції, котру необхідно утилізувати"),
    TOOLS("TOOLS", "Склад інструментів");

    private final String key;
    private final String value;

    WarehouseClassification(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static WarehouseClassification get(String key) {
        for (WarehouseClassification c : values()) {
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
