package com.example.hj.retrofit;

public class ConstantDefine {


    //Handler Message
    public static final int HANDLER_MSG_ERROR = 0;                                      // Retrofit Connection Fail
    public static final int HANDLER_MSG_RESPONSEREPO1 = HANDLER_MSG_ERROR + 1;          // RetrofitRepo 1
    public static final int HANDLER_MSG_RESPONSEREPO2 = HANDLER_MSG_RESPONSEREPO1 + 1;  // RetrofitRepo 2

    //Connection Log Message
    public static final String LOG_MSG_SUCCESS = "Connection Success";
    public static final String LOG_MSG_FAILURE = "Connection Failure";
    public static final String LOG_MSG_TAG_RETROFIT = "Test_Retrofit"; // Log Tag

    //region Server URL
    public static final String DEF_BASE_URL = "https://2018-anyfood-dev.workservice.kr:773/kr/test/"; // server base url
    public static final String DEF_SUB_END_POINT = "test.php";
    public static final String DEF_SUB_END_POINT2 = "test2.php";

    public static final int ORIGIN_METHOD_GET = 0; // Get
    public static final int ORIGIN_METHOD_POST = 1; // Post
    //endregion
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
