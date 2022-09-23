package com.web.backend.model.payment;

import com.web.backend.model.jobService.Service;
import com.web.backend.model.user.AppUser;
import com.web.backend.model.user.Client;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "invoices")
public class Invoice {
  @Id
  private String id;
  private AppUser customer;
  private List<Service> services;

    public double getTotal(){

      return 0.0;
    }


  public Invoice(AppUser customer, List<Service> services) {
    this.customer = customer;
    this.services = services;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AppUser getCustomer() {
    return customer;
  }

  public void setCustomer(AppUser customer) {
    this.customer = customer;
  }

  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }

  @Override
  public String toString() {
    return "Invoice{" +
            "id='" + id + '\'' +
            ", customer=" + customer +
            ", services=" + services +
            '}';
  }
}
