package board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentDTO {
    private int comment_num;    // 댓글번호
    private int board_num;      // 게시글 번호
    private String writer;      // 작성자
    private String contents;    // 내용
    private Date reg_date;      // 작성일
}
