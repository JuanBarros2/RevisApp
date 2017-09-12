package com.revisapp.domain;

import java.io.Serializable;
import java.util.List;

import in.cubestack.android.lib.storm.FieldType;
import in.cubestack.android.lib.storm.annotation.Column;
import in.cubestack.android.lib.storm.annotation.PrimaryKey;
import in.cubestack.android.lib.storm.annotation.Table;

/**
 * Created by juan_ on 05/09/2017.
 */
@Table(name="Matter_tb")
public class Matter implements Serializable{
    @PrimaryKey
    @Column(name = "ID", type = FieldType.LONG)
    private long id;
    @Column(name = "NAME", type = FieldType.TEXT)
    private String name;
    @Column(name = "INIT", type = FieldType.LONG)
    private long init;
    @Column(name = "END", type = FieldType.LONG)
    private long end;

    private List<Photo> photos;

    public Matter() {
    }

    public Matter(String name, long init, long end) {
        setName(name);
        this.init = init;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isEmpty()){
            this.name = name;
        }
    }

    public long getInit() {
        return init;
    }

    public void setInit(long init) {
        this.init = init;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matter matter = (Matter) o;

        return name.equals(matter.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
