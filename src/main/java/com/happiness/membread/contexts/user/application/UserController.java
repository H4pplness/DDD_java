package com.happiness.membread.contexts.user.application;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.user.domain.aggregates.info.UserInfo;
import com.happiness.membread.contexts.user.domain.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/info")
    public ApiResponse<UserInfo> getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return ApiResponse.<UserInfo>builder().result(userService.getUserInfo(userId)).build();
    }

    @PutMapping("/update")
    public ApiResponse<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return ApiResponse.<UserInfo>builder().result(userService.updateUserInfo(userId,userInfo)).build();
    }


}
