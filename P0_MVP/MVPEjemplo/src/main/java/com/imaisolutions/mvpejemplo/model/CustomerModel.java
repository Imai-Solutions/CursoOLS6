package com.imaisolutions.mvpejemplo.model;

import com.imaisolutions.mvpejemplo.model.entities.Customer;
import com.imaisolutions.mvpejemplo.model.datalayer.*;

/**
 * CustomerModel
 */
public class CustomerModel implements  ICustomerModel {
    private IDataLayer dl;

    public CustomerModel()
    {
        // Podemos elejir un modo de almacenamiento
        // o Grabar en los dos.
        dl=new DlManager();
    }

    @Override
    public void saveCustomer(Customer c) {
        // Guardar cliente en BBDD
        dl.insert(c);
    }

    @Override
    public Customer loadCustomer(int id) {
        // Obtener cliente.
        return dl.getClient(id);
    }
}
