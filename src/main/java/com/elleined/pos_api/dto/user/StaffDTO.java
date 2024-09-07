package com.elleined.pos_api.dto.user;

import com.elleined.pos_api.model.user.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StaffDTO extends UserDTO {
    private String email;
    private Staff.Status status;
}
