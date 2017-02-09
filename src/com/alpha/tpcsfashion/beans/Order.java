package com.alpha.tpcsfashion.beans;

public class Order
{


  public String getPaymentTerms()
  {
    return PaymentTerms;
  }
  public void setPaymentTerms(String paymentTerms)
  {
    PaymentTerms = paymentTerms;
  }
  public String getDeliveryTerms()
  {
    return DeliveryTerms;
  }
  public void setDeliveryTerms(String deliveryTerms)
  {
    DeliveryTerms = deliveryTerms;
  }
  public String getInsurance()
  {
    return Insurance;
  }
  public void setInsurance(String insurance)
  {
	  Insurance = insurance;
  }
  public String getDeliveryTo()
  {
    return DeliveryTo;
  }
  public void setDeliveryTo(String deliveryTo)
  {
	  DeliveryTo = deliveryTo;
  }
  public String getShipmentTo()
  {
    return ShipmentTo;
  }
  public void setShipmentTo(String shipmentTo)
  {
	  ShipmentTo = shipmentTo;
  }
  public String getShipMode()
  {
    return ShipMode;
  }
  public void setShipMode(String shipMode)
  {
	  ShipMode = shipMode;
  }
  
  private String PaymentTerms;
  private String DeliveryTerms;
  private String Insurance;
  private String DeliveryTo;
  private String ShipmentTo;
  private String ShipMode;

}
