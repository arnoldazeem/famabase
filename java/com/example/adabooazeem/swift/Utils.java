package com.example.adabooazeem.swift;


public class Utils {
	
	//Email Validation pattern
	public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

	public static final String LOGIN_URL = "https://www.famabase.com/api/post/signin.php";

    public static final String SIGN_URL = "https://famabase.com/api/post/create.php";

	public static final String REQUEST_URL = "https://famabase.com/api/put/reset-password.php";

	public static final String RESET_URL = "https://famabase.com/api/put/reset.php";

	public static final String GET_ORDERS_URL = "https://famabase.com/api/post/getOrders";

	public static final String ACCEPT_ORDERS_URL = "https://famabase.com/api/post/getDeliveries";



	//Keys for email and password as defined in our $_POST['key'] in login.php
	public static final String KEY_FULLNAME = "name";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PASSWORD = "password";
    public static final String KEY_MOBILE = "phone";
    public static final String KEY_ORDERID = "order_id";
    public static final String KEY_LOCATION = "location";
	public static final String KEY_APPID = "appID";
	public static final String KEY_APIKEY = "apiKey";
	public static final String KEY_CODE = "code";



	public static final String Delivery_clientname = "fullname";
	public static final String Delivery_alternativename = "alternativename";
	public static final String Delivery_locationpickup = "location";
	public static final String Delivery_locationdelivery = "locationdelivery";
	public static final String Delivery_spinner_value = "deliveryday";



	//If server response is equal to this that means login is successful
	public static final String LOGIN_SUCCESS = "OK";

	public static final String signup_SUCCESS = "successful";
	//Keys for Sharedpreferences
	//This would be the name of our shared preferences
	public static final String SHARED_PREF_NAME = "famabase";
	//This would be used to store the email of current logged in user
	public static final String PHONE_SHARED_PREF = "phone";
    public static final String NAME_SHARED_PREF = "name";
	//We will use this to store the boolean in sharedpreference to track user is loggedin or not
	public static final String LOGGEDIN_SHARED_PREF = "loggedin";


	//Fragments Tags
	public static final String Login_Fragment = "Login_Fragment";
	public static final String SignUp_Fragment = "SignUp_Fragment";
	public static final String ForgotPassword_Fragment = "ForgotPassword_Fragment";

	private static final String ROOT_URL = "http://192.168.101.1/Android/Api.php?apicall=";

	public static final String URL_REGISTER = ROOT_URL + "signup";
	public static final String URL_LOGIN= ROOT_URL + "login";
	
}
