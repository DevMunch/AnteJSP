package guestbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookDTO {
    private int idx;
    private String name;
    private String email;
    private String passwd;
    private String contents;
    private String post_date;
}
