package com.example.splash_table;

import android.os.Parcel;
import android.os.Parcelable;

public class Tickete implements Parcel {
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

    //no lleva override
    public void readFromParcel(Parcel in){
        name = in.readString();
        apellido = in.readString();
        numeroCedula = in.readInt();
        paisOrigen = in.readString();
        paisDestino = in.readString();

    }

//----Metodo Creator-----//
    public static final Parcelable.Creator<Tickete>CREATOR = new Parcelable.Creator<Tickete>() {
    @Override
    public Tickete createFromParcel(Parcel parcel) {
        return new Tickete();
    }

    @Override
    public Tickete[] newArray(int size) {
        return new Tickete[size];
    }
    };


}//fin de la progra