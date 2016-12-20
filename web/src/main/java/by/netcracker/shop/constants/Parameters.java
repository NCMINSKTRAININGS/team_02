package by.netcracker.shop.constants;

public class Parameters {
//    Controllers
    public static final String CONTROLLER_USER = "";
    public static final String CONTROLLER_INDEX = "/";
    public static final String CONTROLLER_ORDER = "/order";
    public static final String CONTROLLER_PRODUCT = "/product";

//    Request Mapping
    public static final String REQUEST_USER_LOGIN = "/login";
    public static final String REQUEST_USER_REGISTRATION = "/registration";
    public static final String REQUEST_ORDER_LIST = "/list";
    public static final String REQUEST_ORDER_SHOW = "/show-order-{id}";
    public static final String REQUEST_PRODUCT_LIST = "/list";
    public static final String REQUEST_PRODUCT_CREATE = "/createproduct";
    public static final String REQUEST_PRODUCT_EDIT = "/update-product-{id}";
    public static final String REQUEST_PRODUCT_DELETE = "/delete-product-{id}";

//    Sessions
    public static final String ENTITY_USER = "user";

//    Fields
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_FIRST_NAME = "firstname";
    public static final String FIELD_LAST_NAME = "lastname";
    public static final String FIELD_ERROR_MESSAGE = "errorMessage";
    public static final String FIELD_ORDERS = "orders";
    public static final String FIELD_PRODUCTS = "products";
    public static final String FIELD_PRODUCT = "product";
    public static final String FIELD_USER = "user";

//    Messages
    public static final String MESSAGE_LOGIN_ERROR = "Invalid username or password";
    public static final String MESSAGE_WRONG_DATA_ERROR = "Wrong data";
    public static final String MESSAGE_USERNAME_EXIST_ERROR = "Username already exist";

//    Tiles
    public static final String TILES_LOGIN = "user/login";
    public static final String TILES_REGISTRATION = "user/registration";
    public static final String TILES_ORDER_LIST = "order/list";
    public static final String TILES_ORDER_DETAILS = "order/details";
    public static final String TILES_PRODUCT_LIST = "product/list";
    public static final String TILES_PRODUCT_NEW = "product/new";
    public static final String TILES_PRODUCT_EDIT = "product/edit";
}
