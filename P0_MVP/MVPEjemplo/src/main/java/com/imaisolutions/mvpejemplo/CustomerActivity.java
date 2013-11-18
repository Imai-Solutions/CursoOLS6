package com.imaisolutions.mvpejemplo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imaisolutions.mvpejemplo.model.entities.Customer;
import com.imaisolutions.mvpejemplo.presenter.CustomerPresenter;

public class CustomerActivity extends ActionBarActivity
                              implements ICustomerView {

    EditText txtNombre;
    EditText txtApellidos;
    Button btnSave;
    CustomerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellidos=(EditText)findViewById(R.id.txtApellidos);
        btnSave=(Button)findViewById(R.id.btnSave);
        presenter=new CustomerPresenter(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveCustomer();
            }
        });

     }

    @Override
    public void setCustomer(Customer c) {
        txtNombre.setText(c.getFirstName());
        txtApellidos.setText(c.getLastName());
    }

    @Override
    public Customer getCustomer() {
        Customer c=new Customer(txtNombre.getText().toString(),
                txtApellidos.getText().toString());
        return c;
    }

    @Override
    public void setFirstName(String firstName) {
        txtNombre.setText(firstName);
    }

    @Override
    public String getLastName() {
        return txtApellidos.getText().toString();
    }

    @Override
    public String getFirstName() {
        return txtNombre.getText().toString();
    }

    @Override
    public void setLastName(String lastName) {
        txtApellidos.setText(lastName);
    }
}
