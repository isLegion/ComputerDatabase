package data;

public enum Fields {
    NAME("name"),
    INTRODUCED("introduced"),
    DISCONTINUED("discontinued");

    private String field;

    Fields(String field)
    {
        this.field = field;
    }

    @Override
    public String toString() {
        return this.field;
    }
}
