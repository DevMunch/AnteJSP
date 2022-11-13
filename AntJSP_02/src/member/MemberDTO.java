package member;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String userid;
    private String passwd;
    private String name;
    private String reg_date;
    private String address;
    private String tel;

    public MemberDTO(String userid, String passwd, String name, String address, String tel){
        this.userid = userid;
        this.passwd = passwd;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }
}
