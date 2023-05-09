package com.diploma.todolist.service.user;

import com.diploma.todolist.service.user.dto.change_email.ChangeEmailInputDTO;
import com.diploma.todolist.service.user.dto.change_email.ChangeEmailOutputDTO;
import com.diploma.todolist.service.user.dto.reset_password.ResetPasswordInputDTO;
import com.diploma.todolist.service.user.dto.reset_password.ResetPasswordOutputDTO;

import javax.mail.MessagingException;

public interface UserService {
    ResetPasswordOutputDTO resetPassword(ResetPasswordInputDTO resetPasswordInputDTO) throws MessagingException;

    ChangeEmailOutputDTO changeEmail(Long userId, ChangeEmailInputDTO changeEmailInputDTO);
}
