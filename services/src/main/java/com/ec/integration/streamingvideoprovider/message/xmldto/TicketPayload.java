package com.ec.integration.streamingvideoprovider.message.xmldto;

import java.io.Serializable;

public class TicketPayload implements Serializable {
    
    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;
    private String packageName;
    private String ticketTitle;
    private String ticketCurrency;
    private Double ticketPrice;
    private String ticketDescription;

    

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketCurrency() {
        return ticketCurrency;
    }

    public void setTicketCurrency(String ticketCurrency) {
        this.ticketCurrency = ticketCurrency;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    
}