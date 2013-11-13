package com.imaisolutions.mvpejemplo.model.datalayer;

        import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * Interfaz DataLayer
 */
public interface IDataLayer {
    public Customer getClient(int id);
    public int insert(Customer c);
    public void remove(int id);
    public void update(Customer c);
}
