package model;

public class Medicine {
    int id;
    String name;
    int count;
    boolean morning;
    boolean noon;
    boolean evening;

    public Medicine(){}

    public Medicine(String name, int count, boolean morning, boolean noon, boolean evening) {
        this.name = name;
        this.count = count;
        this.morning = morning;
        this.noon = noon;
        this.evening = evening;
    }

    public Medicine(int id, String name, int count, boolean morning, boolean noon, boolean evening) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.morning = morning;
        this.noon = noon;
        this.evening = evening;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public void setNoon(boolean noon) {
        this.noon = noon;
    }

    public void setEvening(boolean evening) {
        this.evening = evening;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public boolean isMorning() {
        return morning;
    }

    public boolean isNoon() {
        return noon;
    }

    public boolean isEvening() {
        return evening;
    }
}
