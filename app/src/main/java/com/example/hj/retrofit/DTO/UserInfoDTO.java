package com.example.hj.retrofit.DTO;

import java.io.Serializable;

public class UserInfoDTO implements Serializable {
    public String UserName = "";
    public String UserPhone = "";

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }


    public UserInfoDTO CopyObject()
    {
        UserInfoDTO objCopyDTO = new UserInfoDTO();
        objCopyDTO.UserName = UserName;
        objCopyDTO.UserPhone = UserPhone;

        return objCopyDTO;
    }
}
