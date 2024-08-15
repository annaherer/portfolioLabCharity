package pl.coderslab.charity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {
    private String newPassword = "";
    private String newPasswordRetyped = "";
}