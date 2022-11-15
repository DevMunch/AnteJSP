package SecurityMember;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberEncryptDTO {
    private String userid;
    private String passwd;
    private String name;
}
