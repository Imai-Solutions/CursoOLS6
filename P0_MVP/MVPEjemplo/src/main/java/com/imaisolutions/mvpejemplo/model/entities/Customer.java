package com.imaisolutions.mvpejemplo.model.entities;

/**
 * Entidad Cliente
 */
public class Customer {

    private int _ID;
    public int getID() {
        return _ID;
    }

    public void setID(int ID) {
        this._ID = ID;
    }

    private String _FirstName;
    public String getFirstName() {
        return _FirstName;
    }

    public void setFirstName(String firstname) {
        _FirstName = firstname;
    }

    private String _LastName;

    public String getLastName() {
        return _LastName;
    }

    public void setLastName(String lastname) {
        _LastName = lastname;
    }

    public Customer(String nombre,String apellidos) {
        _FirstName=nombre;
        _LastName=apellidos;
    }
}
