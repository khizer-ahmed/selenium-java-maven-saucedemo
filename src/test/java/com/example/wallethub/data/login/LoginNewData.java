package com.example.wallethub.data.login;


import com.univocity.parsers.annotations.Parsed;
import com.example.wallethub.data.BaseData;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LoginNewData extends BaseData {
    @Parsed(field = "User Name", defaultNullRead = "")
    private String userName;

    @Parsed(field = "Password", defaultNullRead = "")
    private String password;

    @Parsed(field = "Account Name", defaultNullRead = "")
    private String getUserName;
}