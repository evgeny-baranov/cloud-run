package cloudcode.helloworld.web.dto;

import com.lp.domain.model.RoleEnum;
import com.lp.domain.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDto extends AbstractUuidDto {
    private String name;
    private String email;
    private Collection<RoleEnum> roles = new HashSet<>();
    private StatusEnum status;
}
