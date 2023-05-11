package com.diploma.todolist.service.user.impl;

import com.diploma.todolist.adaptor.persistence.UserRepository;
import com.diploma.todolist.service.exceptions.error_code.bad_request.UserAlreadyExistException;
import com.diploma.todolist.service.exceptions.error_code.not_found.UserNotFoundException;
import com.diploma.todolist.service.user.UserService;
import com.diploma.todolist.service.user.dto.change_email.ChangeEmailInputDTO;
import com.diploma.todolist.service.user.dto.change_email.ChangeEmailOutputDTO;
import com.diploma.todolist.service.user.dto.reset_password.ResetPasswordInputDTO;
import com.diploma.todolist.service.user.dto.reset_password.ResetPasswordOutputDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public ResetPasswordOutputDTO resetPassword(ResetPasswordInputDTO resetPasswordInputDTO) throws MessagingException {
        try {
            final var userEmail = resetPasswordInputDTO.getEmail();
            final var user = userRepository.findByEmail(userEmail);
            if (user.isEmpty()) {
                log.error("User not found. User email: {}. Input data: {}", userEmail, resetPasswordInputDTO);
                throw new UserNotFoundException();
            }

            final var newPassword = RandomStringUtils.randomAlphanumeric(10);
            user.get().setPassword(newPassword);
            userRepository.save(user.get());

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(userEmail);
            helper.setSubject("Reset password");
            helper.setText("You new password is: " + newPassword);
            javaMailSender.send(message);

            return new ResetPasswordOutputDTO("success");
        } catch (Exception e) {
            return new ResetPasswordOutputDTO("Failed to reset password: " + e.getMessage());
        }
    }

    @Override
    public ChangeEmailOutputDTO changeEmail(Long userId, ChangeEmailInputDTO changeEmailInputDTO) {
        final var newEmail = changeEmailInputDTO.getNewEmail();
        final var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.error("User not found. User id: {} Request data: {}", userId, changeEmailInputDTO);
            throw new UserNotFoundException();
        }
        if (!userRepository.findByEmail(newEmail).isEmpty()) {
            log.error("User already exist. User id: {} Request data: {}", userId, changeEmailInputDTO);
            throw new UserAlreadyExistException();
        }
        user.get().setEmail(newEmail);
        userRepository.save(user.get());
        return new ChangeEmailOutputDTO(newEmail);
    }
}
