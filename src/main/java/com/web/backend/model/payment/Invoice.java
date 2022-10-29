package com.web.backend.model.payment;

import com.web.backend.model.jobService.Service;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Invoice {
  @Id
  private String id;
  //customer details
  @Indexed
  private String email;
  private String firstName;
  private String lastName;
  private String address;
  //services related details.
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

  //constructor.
  public Invoice(String email,
                 String firstName,
                 String lastName,
                 String address,
                 List<Service> services,
                 LocalDate invoiceDate,
                 boolean paymentStatus
  ){
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.services = services;
    this.invoiceDate = invoiceDate;
    this.invoiceTotal = getTotal();
    this.paymentStatus = paymentStatus;
    this.invoiceExpireDate = invoiceDate.plusDays(7);
  }

  public boolean isPaymentStatus() {
    return paymentStatus;
  }
  //TODO: setup the way to auto remove the invoices after 7 days.
}

