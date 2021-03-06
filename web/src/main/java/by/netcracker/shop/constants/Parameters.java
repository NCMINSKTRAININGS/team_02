package by.netcracker.shop.constants;

public class Parameters {
//    Roles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CLIENT = "ROLE_CLIENT";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

//    Controllers
    public static final String CONTROLLER_USER = "";
    public static final String CONTROLLER_ADMIN = "/admin";
    public static final String CONTROLLER_INDEX = "/";
    public static final String CONTROLLER_ORDER = "/order";
    public static final String CONTROLLER_PRODUCT = "/product";
    public static final String CONTROLLER_PRODUCT_IMAGE = "/image";

//    Request Mapping
    public static final String REQUEST_USER_LOGIN = "/login";
    public static final String REQUEST_USER_REGISTRATION = "/registration";
    public static final String REQUEST_USER_LOGOUT = "/logout";
    public static final String REQUEST_USER_PROFILE = "/profile";
    public static final String REQUEST_ORDER_LIST = "/list";
    public static final String REQUEST_ORDER_SHOW = "/show-order-{id}";
    public static final String REQUEST_PRODUCT_LIST = "/list";
    public static final String REQUEST_PRODUCT_CREATE = "/new";
    public static final String REQUEST_PRODUCT_EDIT = "/update-product-{id}";
    public static final String REQUEST_PRODUCT_DELETE = "/delete-product-{id}";
    public static final String REQUEST_PRODUCT_SHOW = "/show-product-{id}";
    public static final String REQUEST_PRODUCT_IMAGE_LIST = "/list";
    public static final String REQUEST_PRODUCT_IMAGE_CREATE = "/new";
    public static final String REQUEST_PRODUCT_IMAGE_EDIT = "/update-image-{id}";
    public static final String REQUEST_PRODUCT_IMAGE_DELETE = "/delete-image-{id}";
    public static final String REQUEST_USERS = "/users";
    public static final String REQUEST_USER_EDIT = "/update-user-{id}";

//    Parameters
    public static final String PARAMETER_LOGOUT = "logout";
    public static final String PARAMETER_ERROR = "error";
    public static final String PARAMETER_ID = "id";

//    Sessions
    public static final String ENTITY_USER = "user";

//    Fields
    public static final String FIELD_MESSAGE = "msg";
    public static final String FIELD_ERROR_MESSAGE = "errorMessage";
    public static final String FIELD_ORDERS = "orders";
    public static final String FIELD_PRODUCTS = "products";
    public static final String FIELD_PRODUCT = "product";
    public static final String FIELD_PRODUCT_DTO = "dto";
    public static final String FIELD_USER = "user";
    public static final String FIELD_PRODUCT_IMAGES = "images";
    public static final String FIELD_PRODUCT_IMAGE = "image";
    public static final String FIELD_IMAGE_DTO = "dto";
    public static final String FIELD_USERS = "users";
    public static final String FIELD_USER_DTO = "dto";

//    Messages
    public static final String MESSAGE_LOGIN_ERROR = "Invalid username or password!";
    public static final String MESSAGE_LOGOUT = "You've been logged out successfully.";
    public static final String MESSAGE_WRONG_DATA_ERROR = "Wrong data";
    public static final String MESSAGE_USERNAME_EXIST_ERROR = "Username already exist";

//    Tiles
    public static final String TILES_LOGIN = "user/login";
    public static final String TILES_REGISTRATION = "user/registration";
    public static final String TILES_USER_PROFILE = "user/profile";
    public static final String TILES_ORDER_LIST = "order/list";
    public static final String TILES_ORDER_DETAILS = "order/details";
    public static final String TILES_PRODUCT_LIST = "product/list";
    public static final String TILES_PRODUCT_NEW = "product/new";
    public static final String TILES_PRODUCT_DETAILS = "product/details";
    public static final String TILES_PRODUCT_IMAGE_LIST = "image/list";
    public static final String TILES_PRODUCT_IMAGE_NEW = "image/new";
    public static final String TILES_USERS = "admin/users";
    public static final String TILES_USER_EDIT = "admin/edit-user";

    public static final String EDIT = "edit";
    public static final String AVAILABLE = "available";

    public static final int RECORDS_PER_PAGE = 3;

}
