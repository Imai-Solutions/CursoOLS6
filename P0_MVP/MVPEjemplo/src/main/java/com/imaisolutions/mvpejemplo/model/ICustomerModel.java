package com.imaisolutions.mvpejemplo.model;

import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * Interfaz Modelo.
 */
public interface ICustomerModel {
    public void saveCustomer (Customer c);
    public Customer loadCustomer (int id);
}
