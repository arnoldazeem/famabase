package com.example.adabooazeem.swift;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZoneOrders {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("order-id")
    @Expose
    private String orderId;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("vendor")
    @Expose
    private String vendor;
    @SerializedName("imgae")
    @Expose
    private String imgae;
    @SerializedName("vendor-phone")
    @Expose
    private String vendorPhone;
    @SerializedName("vendor-email")
    @Expose
    private String vendorEmail;
    @SerializedName("business")
    @Expose
    private String business;
    @SerializedName("business-location")
    @Expose
    private String businessLocation;
    @SerializedName("tracking-code")
    @Expose
    private String trackingCode;
    @SerializedName("pickup-primary-person")
    @Expose
    private String pickupPrimaryPerson;
    @SerializedName("pickup-primary-contact")
    @Expose
    private String pickupPrimaryContact;
    @SerializedName("pick-up-location")
    @Expose
    private String pickUpLocation;
    @SerializedName("pick-up-zone")
    @Expose
    private String pickUpZone;
    @SerializedName("notable-landmark1")
    @Expose
    private String notableLandmark1;
    @SerializedName("delivery-primary-person")
    @Expose
    private String deliveryPrimaryPerson;
    @SerializedName("delivery-primary-contact")
    @Expose
    private String deliveryPrimaryContact;
    @SerializedName("delivery-location")
    @Expose
    private String deliveryLocation;
    @SerializedName("delivery-zone")
    @Expose
    private String deliveryZone;
    @SerializedName("notable-landmark2")
    @Expose
    private String notableLandmark2;
    @SerializedName("delivery-alternate-name1")
    @Expose
    private String deliveryAlternateName1;
    @SerializedName("delivery-alternate-contact1")
    @Expose
    private String deliveryAlternateContact1;
    @SerializedName("delivery-alternate-name2")
    @Expose
    private String deliveryAlternateName2;
    @SerializedName("delivery-alternate-contact2")
    @Expose
    private String deliveryAlternateContact2;
    @SerializedName("pickup-date")
    @Expose
    private String pickupDate;
    @SerializedName("delivery-date")
    @Expose
    private String deliveryDate;
    @SerializedName("delivery-date-time")
    @Expose
    private String deliveryDateTime;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;

    /**
     * No args constructor for use in serialization
     *
     */
    public ZoneOrders() {
    }

    /**
     *
     * @param businessLocation
     * @param dateAdded
     * @param deliveryDateTime
     * @param pickupPrimaryContact
     * @param business
     * @param id
     * @param deliveryAlternateContact1
     * @param trackingCode
     * @param pickupDate
     * @param deliveryDate
     * @param deliveryAlternateContact2
     * @param orderId
     * @param deliveryPrimaryContact
     * @param imgae
     * @param pickUpZone
     * @param deliveryAlternateName2
     * @param pickUpLocation
     * @param deliveryAlternateName1
     * @param vendor
     * @param notableLandmark2
     * @param pickupPrimaryPerson
     * @param notableLandmark1
     * @param deliveryPrimaryPerson
     * @param vendorPhone
     * @param product
     * @param vendorEmail
     * @param deliveryLocation
     * @param deliveryZone
     */
    public ZoneOrders(String id, String orderId, String product, String vendor, String imgae, String vendorPhone, String vendorEmail, String business, String businessLocation, String trackingCode, String pickupPrimaryPerson, String pickupPrimaryContact, String pickUpLocation, String pickUpZone, String notableLandmark1, String deliveryPrimaryPerson, String deliveryPrimaryContact, String deliveryLocation, String deliveryZone, String notableLandmark2, String deliveryAlternateName1, String deliveryAlternateContact1, String deliveryAlternateName2, String deliveryAlternateContact2, String pickupDate, String deliveryDate, String deliveryDateTime, String dateAdded) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.vendor = vendor;
        this.imgae = imgae;
        this.vendorPhone = vendorPhone;
        this.vendorEmail = vendorEmail;
        this.business = business;
        this.businessLocation = businessLocation;
        this.trackingCode = trackingCode;
        this.pickupPrimaryPerson = pickupPrimaryPerson;
        this.pickupPrimaryContact = pickupPrimaryContact;
        this.pickUpLocation = pickUpLocation;
        this.pickUpZone = pickUpZone;
        this.notableLandmark1 = notableLandmark1;
        this.deliveryPrimaryPerson = deliveryPrimaryPerson;
        this.deliveryPrimaryContact = deliveryPrimaryContact;
        this.deliveryLocation = deliveryLocation;
        this.deliveryZone = deliveryZone;
        this.notableLandmark2 = notableLandmark2;
        this.deliveryAlternateName1 = deliveryAlternateName1;
        this.deliveryAlternateContact1 = deliveryAlternateContact1;
        this.deliveryAlternateName2 = deliveryAlternateName2;
        this.deliveryAlternateContact2 = deliveryAlternateContact2;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
        this.deliveryDateTime = deliveryDateTime;
        this.dateAdded = dateAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getImgae() {
        return imgae;
    }

    public void setImgae(String imgae) {
        this.imgae = imgae;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public void setBusinessLocation(String businessLocation) {
        this.businessLocation = businessLocation;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getPickupPrimaryPerson() {
        return pickupPrimaryPerson;
    }

    public void setPickupPrimaryPerson(String pickupPrimaryPerson) {
        this.pickupPrimaryPerson = pickupPrimaryPerson;
    }

    public String getPickupPrimaryContact() {
        return pickupPrimaryContact;
    }

    public void setPickupPrimaryContact(String pickupPrimaryContact) {
        this.pickupPrimaryContact = pickupPrimaryContact;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getPickUpZone() {
        return pickUpZone;
    }

    public void setPickUpZone(String pickUpZone) {
        this.pickUpZone = pickUpZone;
    }

    public String getNotableLandmark1() {
        return notableLandmark1;
    }

    public void setNotableLandmark1(String notableLandmark1) {
        this.notableLandmark1 = notableLandmark1;
    }

    public String getDeliveryPrimaryPerson() {
        return deliveryPrimaryPerson;
    }

    public void setDeliveryPrimaryPerson(String deliveryPrimaryPerson) {
        this.deliveryPrimaryPerson = deliveryPrimaryPerson;
    }

    public String getDeliveryPrimaryContact() {
        return deliveryPrimaryContact;
    }

    public void setDeliveryPrimaryContact(String deliveryPrimaryContact) {
        this.deliveryPrimaryContact = deliveryPrimaryContact;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryZone() {
        return deliveryZone;
    }

    public void setDeliveryZone(String deliveryZone) {
        this.deliveryZone = deliveryZone;
    }

    public String getNotableLandmark2() {
        return notableLandmark2;
    }

    public void setNotableLandmark2(String notableLandmark2) {
        this.notableLandmark2 = notableLandmark2;
    }

    public String getDeliveryAlternateName1() {
        return deliveryAlternateName1;
    }

    public void setDeliveryAlternateName1(String deliveryAlternateName1) {
        this.deliveryAlternateName1 = deliveryAlternateName1;
    }

    public String getDeliveryAlternateContact1() {
        return deliveryAlternateContact1;
    }

    public void setDeliveryAlternateContact1(String deliveryAlternateContact1) {
        this.deliveryAlternateContact1 = deliveryAlternateContact1;
    }

    public String getDeliveryAlternateName2() {
        return deliveryAlternateName2;
    }

    public void setDeliveryAlternateName2(String deliveryAlternateName2) {
        this.deliveryAlternateName2 = deliveryAlternateName2;
    }

    public String getDeliveryAlternateContact2() {
        return deliveryAlternateContact2;
    }

    public void setDeliveryAlternateContact2(String deliveryAlternateContact2) {
        this.deliveryAlternateContact2 = deliveryAlternateContact2;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

}