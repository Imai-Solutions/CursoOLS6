package com.imaisolutions.mvpejemplo.presenter;

import com.imaisolutions.mvpejemplo.ICustomerView;
import com.imaisolutions.mvpejemplo.model.CustomerModel;
import com.imaisolutions.mvpejemplo.model.ICustomerModel;
import com.imaisolutions.mvpejemplo.model.entities.Customer;

/**
 * CustomerPresenter
 */
public class CustomerPresenter {
    private ICustomerView mCustomerView;
    private ICustomerModel mCustomerModel;

    public CustomerPresenter(ICustomerView view) {
        mCustomerView = view;
        mCustomerModel = new CustomerModel();
    }

    public void saveCustomer() {
        Customer c=mCustomerView.getCustomer();
        mCustomerModel.saveCustomer(c);
    }

    public void loadCustomer(int id) {
        Customer c= mCustomerModel.loadCustomer(id);
        mCustomerView.setCustomer(c);
    }
}
