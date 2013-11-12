package com.imaisolutions.mvpejemplo.model.datalayer;

import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * Implementar IDataLayer - Operaciones con Servicios Web REST
 */
public class DlSOA implements IDataLayer {
    @Override
    public Customer getClient(int id) {
        return null;
    }

    @Override
    public int insert(Customer c) {
        return 0;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Customer c) {

    }
}
