package com.imaisolutions.mvpejemplo.model.datalayer;

import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * Created by jsc on 12/11/13.
 */
public interface IDataLayer {
    public Customer getClient(int id);
    public int insert(Customer c);
    public void remove(int id);
    public void update(Customer c);
}
