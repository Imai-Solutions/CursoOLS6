package com.imaisolutions.mvpejemplo.view;

import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * Interfaz de la vista.
 */
public interface ICustomerView {
    void setCustomer(Customer c);
    Customer getCustomer();

    // Métodos auxiliares
    // Recogida de datos
    void setLastName (String lastName);
    void setFirstName (String firstName);
    String getLastName ();
    String getFirstName ();
}
