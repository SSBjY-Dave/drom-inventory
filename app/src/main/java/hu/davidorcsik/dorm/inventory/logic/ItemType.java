package hu.davidorcsik.dorm.inventory.logic;

import hu.davidorcsik.dorm.inventory.R;

public enum ItemType {
    //TODO: Is there more?
    WALL_CABIN          (0,     "Zárható kétajtós fali szekrény",   R.mipmap.ic_launcher), //TODO: Set icon
    CABIN               (1,     "Zárható kétajtós ruhás szekrény",  R.mipmap.ic_launcher), //TODO: Set icon
    DESK                (2,     "Íróasztal",                        R.mipmap.ic_launcher), //TODO: Set icon
    DESK_OLD            (3,     "Íróasztal (régi)",                 R.mipmap.ic_launcher), //TODO: Set icon
    BED                 (4,     "Ágy",                              R.mipmap.ic_launcher), //TODO: Set icon
    BED_OLD             (5,     "Ágy (régi)",                       R.mipmap.ic_launcher), //TODO: Set icon
    READING_LAMP        (6,     "Olvasólámpa",                      R.mipmap.ic_launcher), //TODO: Set icon
    CHAIR               (7,     "Irodai szék",                      R.mipmap.ic_launcher), //TODO: Set icon
    CHAIR_OLD           (8,     "Szék (régi)",                      R.mipmap.ic_launcher), //TODO: Set icon
    DRAWER              (9,     "Kihúzhatós szekrény",              R.mipmap.ic_launcher), //TODO: Set icon
    SHELVING_CABINET    (10,    "Polcos szekrény",                  R.mipmap.ic_launcher), //TODO: Set icon
    SHELF               (11,    "Fali polc",                        R.mipmap.ic_launcher), //TODO: Set icon
    ;
    private final int typeId;
    private final String name;
    private final int iconResourceId;

    ItemType(int typeId, String name, int iconResourceId) {
        this.typeId = typeId;
        this.name = name;
        this.iconResourceId = iconResourceId;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }
}
