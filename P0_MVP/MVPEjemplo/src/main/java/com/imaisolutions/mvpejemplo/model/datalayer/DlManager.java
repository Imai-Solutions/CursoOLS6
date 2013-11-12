package com.imaisolutions.mvpejemplo.model.datalayer;

import com.imaisolutions.mvpejemplo.model.entities.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de n Datalayers - Transacciones múltiples
 */
public class DlManager implements  IDataLayer {
    DlSOA _dlSOA;
    DlSqlLite _dlSqlLite;

    List<IDataLayer> _lstdls;

    public DlManager(){
        _dlSOA=new DlSOA();
        _dlSqlLite=new DlSqlLite();

        _lstdls=new ArrayList<IDataLayer>();
        _lstdls.add(_dlSqlLite);
        _lstdls.add(_dlSOA);
    }

    @Override
    public Customer getClient(int id) {
       return _dlSqlLite.getClient(id);
    }

    @Override
    public int insert(Customer c) {
        int res=-1;
        try{
            for(IDataLayer dl:_lstdls)
                res=dl.insert(c);
        }catch (Exception err) // Especificar tipo excepción SQLLite / SOA
        {
            // Realizar Roolbacks de operación. En BBDD o Servicios
        }
        return res;
    }

    @Override
    public void remove(int id) {
        // Try Catch gestionar excepcion durante la transacción
        // PrepareDistributedTransaction();
        try{
            for(IDataLayer dl:_lstdls)
                dl.remove(id);
        }catch(Exception err) // Especificar tipo excepción SQLLite / SOA
        {
            // Realizar Roolbacks de operación. En BBDD o Servicios
        }
    }

    @Override
    public void update(Customer c) {
        // Try Catch gestionar excepcion durante la transacción
        // PrepareDistributedTransaction();
        try{
            for(IDataLayer dl:_lstdls)
                dl.update(c);
        }catch(Exception err) // Especificar tipo excepción SQLLite / SOA
        {
            // Realizar Roolbacks de operación. En BBDD o Servicios
        }
    }
}
