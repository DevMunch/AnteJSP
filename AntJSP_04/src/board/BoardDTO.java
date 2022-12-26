package board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private int num;            //  글번호
    private String writer;      //  이름
    private String subject;     //  제목
    private String passwd;      //  수정, 삭제 비밀번호
    private String reg_date;    //  날짜
    private int hit;            //  조회수
    private int group_num;      //  답변관련 기능을 위한 데이터
    private int re_order;       //  답변관련 기능을 위한 데이터
    private int re_depth;       //  답변관련 기능을 위한 데이터
    private String contents;    //  본문
    private String ip;          //  아이피 주소
    private int count_comments; //  댓글 수
    private String filename;    //  파일이름
    private int filesize;       //  파일 사이즈
    private int down;           //  다운로드 횟수
}
