package com.enstripe.exceptin;



public class StripeException extends Exception {
    private final String requestId;
    private final Integer statusCode;
    private final String code;
    private final String declineCode;
    private final String param;
    private final String rawType;
    private final String rawMessage;

    // Constructor
    public StripeException(String message, String requestId, Integer statusCode, String code, String declineCode, String param, String rawType, String rawMessage) {
        super(message);
        this.requestId = requestId;
        this.statusCode = statusCode;
        this.code = code;
        this.declineCode = declineCode;
        this.param = param;
        this.rawType = rawType;
        this.rawMessage = rawMessage;
    }


    public String getRequestId() {
        return requestId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getCode() {
        return code;
    }

    public String getDeclineCode() {
        return declineCode;
    }

    public String getParam() {
        return param;
    }

    public String getRawType() {
        return rawType;
    }

    public String getRawMessage() {
        return rawMessage;
    }
}
