package com.example.splash_table;

import android.os.Parcel;
import android.os.Parcelable;

public class Pedido implements Parcelable {
    private String name;
    private String apellido;
    private String tamanno;



    //constructor vacio
    public Pedido() {
    }

    //constructor Parcel
    public Pedido(Parcel in){
        readFromParcel(in);
    }

    //constructor
    public Pedido(String name, String apellido, String tamanno){
        this.name = name;
        this.apellido = apellido;
        this.tamanno = tamanno;
    }

    //Getters and Setters--------------------------------------------///

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTamanno() {
        return tamanno;
    }

    public void setTamanno(String tamanno) {
        this.tamanno = tamanno;
    }

    //--toString*---------------------------------------------------///

    @Override
    public String toString() {
        return "Pedido{" +
                "name='" + name + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tamanno='" + tamanno + '\'' +
                '}';
    }

    //--Implementacion del parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    /***
     * @dest destino
     * @flags bandera -> no se usa, solo en alguns casos
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.apellido);
        dest.writeString(this.tamanno);

    }

    //no lleva override
    public void readFromParcel(Parcel in){
        name = in.readString();
        apellido = in.readString();
        tamanno = in.readString();
    }


    //Metodo Creator
    public static final Parcelable.Creator<Pedido> CREATOR = new Parcelable.Creator<Pedido>(){

        //el nombre es igual al de la clase
        public Pedido createFromParcel(Parcel in){
            return new Pedido(in);
        }

        public Pedido[] newArray(int size){
            return new Pedido[size];
        }
    };



}//final de la clase
