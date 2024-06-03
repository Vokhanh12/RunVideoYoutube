package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.UserModel


object DataUser{
    val user01 = UserModel(
        id = 0,
        name = "Nguyen Vo Khanh",
        avartaURL = "https://ps.w.org/user-avatar-reloaded/assets/icon-128x128.png?rev=2540745",
    )

    val user02 = UserModel(
        id = 1,
        name = "Nguyen Trung Khanh",
        avartaURL = "https://ps.w.org/user-avatar-reloaded/assets/icon-128x128.png?rev=2540745",
    )

    val users : List<UserModel> = listOf(user01, user02)

}