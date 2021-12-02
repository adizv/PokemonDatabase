package org.izv.pokemon.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon",
        indices = {@Index(value = "name", unique = true)},
        foreignKeys = {@ForeignKey(entity = Type.class, parentColumns = "id", childColumns = "idtype", onDelete = ForeignKey.CASCADE)})
public class Pokemon {

    //id autonumérico
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "idtype")
    public long idtype;

    @ColumnInfo(name = "weight")
    public int weight;

    @ColumnInfo(name = "height")
    public double height;

    @ColumnInfo(name = "url")
    public String url;

    public boolean isValid() {
        return !(name.isEmpty() || height <= 0  || weight <= 0 || url.isEmpty() || idtype <= 0);
        //shortcut
    }

}