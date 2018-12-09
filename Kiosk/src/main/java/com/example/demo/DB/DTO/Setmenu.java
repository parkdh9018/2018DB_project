package com.example.demo.DB.DTO;

public class Setmenu {
    private String setmenuid;
    private String settype;
    private String totalprice;
    private String[] component;

    public String getSetmenuid() {
        return setmenuid;
    }

    public void setSetmenuid(String setmenuid) {
        this.setmenuid = setmenuid;
    }

    public String getSettype() {
        return settype;
    }

    public void setSettype(String settype) {
        this.settype = settype;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String[] getComponent() {
        return component;
    }

    public void setComponent(String[] component) {
        this.component = component;
    }

    public String toStringCompoenet(){
        String result = null;
        for(int i=0;i<component.length;i++)
            result += "+"+component[i];
        return result;
    }
}
