package com.example.splash_table;

import android.os.Parcel;
import android.os.Parcelable;

public class Tickete implements Parcelable {
    private String name;
    private String apellido;
    private int numeroCedula;
    private String paisOrigen;
    private String paisDestino;

    //constructor vacio
    public Tickete(){
    }

    //constructor
    public Tickete(String name, String apellido, int numeroCedula, String paisOrigen, String paisDestino){
        this.name = name;
        this.apellido = apellido;
        this.numeroCedula = numeroCedula;
        this.paisOrigen = paisOrigen;
        this.paisDestino = paisDestino;
    }

//-------Getters and Setters ---------//

    protected Tickete(Parcel in) {
        name = in.readString();
        apellido = in.readString();
        numeroCedula = in.readInt();
        paisOrigen = in.readString();
        paisDestino = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(apellido);
        dest.writeInt(numeroCedula);
        dest.writeString(paisOrigen);
        dest.writeString(paisDestino);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tickete> CREATOR = new Creator<Tickete>() {
        @Override
        public Tickete createFromParcel(Parcel in) {
            return new Tickete(in);
        }

        @Override
        public Tickete[] newArray(int size) {
            return new Tickete[size];
        }
    };

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public int getNumeroCedula(){
        return numeroCedula;
    }

    public void setNumeroCedula(int numeroCedula){
        this.numeroCedula = numeroCedula;
    }

    public String getPaisOrigen(){
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen){
        this.paisOrigen = paisOrigen;
    }

    public String getPaisDestino(){
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino){
        this.paisDestino = paisDestino;
    }

//------toString -------// falta por terminar el to String

    @Override
    public String toString() {
        return "Tickete{" +
                "name='" + name + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroCedula=" + numeroCedula +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", paisDestino='" + paisDestino + '\'' +
                '}';
    }


//------Implementacion de parcelable--------//



}//fin de la progra