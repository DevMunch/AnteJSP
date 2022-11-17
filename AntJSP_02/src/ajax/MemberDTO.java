package ajax;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {
    private String userid;
    private String passwd;
    private String name;
}
