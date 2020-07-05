package hu.davidorcsik.dorm.inventory.logic;

import hu.davidorcsik.dorm.inventory.R;

public enum ItemCondition {
    SCRAP           (0, "Selejt",            "Felhasználhatatlan állapot",                                            R.raw.condition_1),
    HEAVILY_USED    (1, "Erősen használt",   "Még funkcionális, de jó érzésű ember kidobná",                          R.raw.condition_2),
    USED            (2, "Használt",          "Ugyan használat nyomai láthatók, de még jó állapotú",                   R.raw.condition_3),
    EXCELLENT       (3, "Kiváló",            "Újszerű állapot, rá lehetne mondani, hogy még nem volt használva",      R.raw.condition_4),
    UNDETERMINABLE  (4, "Nem megállapítható","Az állapot nem megállapítható, mert le van takarva stb.",               R.raw.condition_q),
    ;
    private final int conditionId;
    private final String name;
    private final String description;
    private final int iconResourceId;

    ItemCondition(int conditionId, String name, String description, int iconResourceId) {
        this.conditionId = conditionId;
        this.name = name;
        this.description = description;
        this.iconResourceId = iconResourceId;
    }

    public int getConditionId() {
        return conditionId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public static ItemCondition[] getValuesAscending() {
        return values();
    }

    public static ItemCondition[] getValuesDescending() {
        ItemCondition[] temp = new ItemCondition[values().length];
        for (int i = 0; i < values().length; ++i)
            temp[i] = values()[values().length - 1 - i];
        return temp;
    }
}
