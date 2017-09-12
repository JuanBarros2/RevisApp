package com.revisapp.domain;

import in.cubestack.android.lib.storm.FieldType;
import in.cubestack.android.lib.storm.annotation.Column;
import in.cubestack.android.lib.storm.annotation.PrimaryKey;
import in.cubestack.android.lib.storm.annotation.Table;

/**
 * Created by juan_ on 11/09/2017.
 */

@Table(name="Content_tb")
public class Photo {

    @PrimaryKey
    @Column(name = "ID", type = FieldType.LONG)
    private long id;
    @Column(name = "TYPE", type = FieldType.TEXT)
    private String type;
    @Column(name = "URL", type = FieldType.TEXT)
    private String url;

    public Photo(long id, String type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return id == photo.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public enum Types{
        PHOTO("PHOTO"),AUDIO("AUDIO");

        public String valor;

        Types(String valor){
            this.valor = valor;
        }
    }
}
