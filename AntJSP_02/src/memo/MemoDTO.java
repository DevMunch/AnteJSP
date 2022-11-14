package memo;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemoDTO {
    private int idx;
    private String writer;
    private String memo;
    // java.sql.Date : 날짜만 나온다. 만일 시간까지 필요하다면 java.util.Date
    // toChar() 함수가 있어서 그냥 String로 사용해도 된다.
    private Date post_date;
    private String ip;
}
