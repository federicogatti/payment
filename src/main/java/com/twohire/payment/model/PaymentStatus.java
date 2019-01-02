package com.twohire.payment.model;

public enum PaymentStatus {

    AUTHORIZATIONREQUEST("AUTHORIZATIONREQUEST"),

    AUTHORIZATIONSUCCESS("AUTHORIZATIONSUCCESS"),

    AUTHORIZATIONFAILURE("AUTHORIZATIONFAILURE"),

    REFUNDREQUEST("REFUNDREQUEST"),

    REFUNDSUCCESS("REFUNDSUCCESS"),

    REFUNDFAILURE("REFUNDFAILURE");

    private String description;

    /**
     * Instantiates a new currency type.
     *
     * @param description the description
     */
    PaymentStatus(final String description) {
        this.description = description;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
