package com.web.backend.model.payment;

import com.web.backend.model.jobService.Service;
import com.web.backend.model.user.AppUser;
import com.web.backend.model.user.Client;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "invoices")
public class Invoice {
  @Id
  private String id;
  private AppUser customer;
  private List<Service> services;
  private LocalDate invoiceDate;
  private double invoiceTotal;
  private boolean paymentStatus;
  private LocalDate invoiceExpireDate;

  public double getTotal(){
    double totalValue = 0.00;

    for(Service service: services){
      totalValue = totalValue + service.getBasicPrice();
    }

    return totalValue;
  }

  public Invoice(AppUser customer, List<Service> services, LocalDate invoiceDate, boolean paymentStatus) {
    this.customer = customer;
    this.services = services;
    this.invoiceDate = invoiceDate;
    this.invoiceTotal = getTotal();
    this.paymentStatus = paymentStatus;
    this.invoiceExpireDate = invoiceDate.plusDays(7);
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

  public LocalDate getInvoiceDate() {
    return invoiceDate;
  }

  public void setInvoiceDate(LocalDate invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public double getInvoiceTotal() {
    return invoiceTotal;
  }

  public void setInvoiceTotal(double invoiceTotal) {
    this.invoiceTotal = invoiceTotal;
  }

  public boolean isPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(boolean paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public LocalDate getInvoiceExpireDate() {
    return invoiceExpireDate;
  }

  public void setInvoiceExpireDate(LocalDate invoiceExpireDate) {
    this.invoiceExpireDate = invoiceExpireDate;
  }

  @Override
  public String toString() {
    return "Invoice{" +
            "id='" + id + '\'' +
            ", customer=" + customer +
            ", services=" + services +
            ", invoiceDate=" + invoiceDate +
            ", invoiceTotal=" + invoiceTotal +
            ", paymentStatus=" + paymentStatus +
            ", invoiceExpireDate=" + invoiceExpireDate +
            '}';
  }
}

