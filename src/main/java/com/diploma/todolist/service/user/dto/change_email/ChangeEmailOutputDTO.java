package com.diploma.todolist.service.user.dto.change_email;

import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ChangeEmailOutputDTO {
    private String newEmail;
}
