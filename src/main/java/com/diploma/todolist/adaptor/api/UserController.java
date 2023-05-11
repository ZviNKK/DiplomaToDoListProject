package com.diploma.todolist.adaptor.api;

import com.diploma.todolist.service.user.UserService;
import com.diploma.todolist.service.user.dto.change_email.ChangeEmailInputDTO;
import com.diploma.todolist.service.user.dto.change_email.ChangeEmailOutputDTO;
import com.diploma.todolist.service.user.dto.reset_password.ResetPasswordInputDTO;
import com.diploma.todolist.service.user.dto.reset_password.ResetPasswordOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/reset-password")
    public ResetPasswordOutputDTO resetPassword(@RequestBody ResetPasswordInputDTO resetPasswordInputDTO) throws MessagingException {
        var resetPasswordOutputDTO = userService.resetPassword(resetPasswordInputDTO);
        return resetPasswordOutputDTO;
    }

    @PostMapping("/change-email/{userId}")
    public ChangeEmailOutputDTO changeEmail(@PathVariable Long userId, @RequestBody @Valid ChangeEmailInputDTO changeEmailInputDTO) {
        var changeEmailOutputDTO = userService.changeEmail(userId, changeEmailInputDTO);
        return changeEmailOutputDTO;
    }
}
