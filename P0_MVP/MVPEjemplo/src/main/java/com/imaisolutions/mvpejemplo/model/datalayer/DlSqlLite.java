package com.imaisolutions.mvpejemplo.model.datalayer;

import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * Created by jsc
 * Implementación del DataLayer para SQLLite
 */
public class DlSqlLite implements IDataLayer {
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
