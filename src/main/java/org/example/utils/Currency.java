package org.example.utils;

public class Currency {
    private String name;
    private String code;
    private String id;

    public Currency() {
    }

    public Currency(String name, String code, String id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Currency {" +
                " name='" + name + '\'' +
                ", code(ISO)='" + code + '\'' +
                ", id='" + id + '\'' +
                " }";
    }
}
