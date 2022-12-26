package board;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO {
    //  키워드로 검색된 컬럼의 수
    public int count(String search_option, String keyword){
        int result=0;
        SqlSession session = MybatisManager.getInstance().openSession();

        try{
            Map<String, Object> map = new HashMap<>();
            map.put("search_option", search_option);
            map.put("keyword",keyword);
            result = session.selectOne("board.search_count", map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return result;
    }

    //  검색된 키워드의 리스트, 검색 결과를 제공하는 검색용 리스트
    public List<BoardDTO> list_search(String search_option, String keyword, int start, int end){
        List<BoardDTO> list = null;
        SqlSession session = MybatisManager.getInstance().openSession();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("search_option", search_option);
            map.put("keyword", keyword);
            map.put("start",start);
            map.put("end", end);
            list=session.selectList("board.search_list",map);
            for(BoardDTO dto : list){
                String writer = dto.getWriter();
                String subject = dto.getSubject();
                switch (search_option){
                    case "all":
                        writer=writer.replace(keyword, "<span style='color:red'>"+keyword+"</span>");
                        subject=subject.replace(keyword,"<span style='color:red'>"+keyword+"</span>");
                        break;
                    case "writer":
                        writer=writer.replace(keyword, "<span style='color:red'>"+keyword+"</span>");
                        break;
                    case "subject":
                        subject=subject.replace(keyword,"<span style='color:red'>"+keyword+"</span>");
                        break;
                }
                dto.setWriter(writer);
                dto.setSubject(subject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return list;
    }

    //  다운로드 클릭시 해당 글번호의 파일이름을 반환해준다.
    public String getFilename(int num){
        String result = null;
        SqlSession session = null;
        try {
            session = MybatisManager.getInstance().openSession();
            result = session.selectOne("board.filename", num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
        return result;
    }

    //  다운로드 시 횟수를 증가 시킨다.
    public void plus_down(int num){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.update("board.plus_down",num);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }

    //  댓글 저장
    public void insert_comment(BoardCommentDTO dto){
        SqlSession session = null;
        try {
            session = MybatisManager.getInstance().openSession();
            session.insert("board.insert_comment",dto);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }

    //  댓글 리스트
    public List<BoardCommentDTO> list_comment(int num){
        List<BoardCommentDTO> list = null;
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            list=session.selectList("board.list_comment", num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return list;
    }

    //  본인글인지 확인을 위한 비밀번호 체크
    public String check_pwd(int num, String passwd){
        String result = null;
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            Map<String,Object> map = new HashMap<>();
            map.put("num",num);
            map.put("passwd",passwd);
            result = session.selectOne("board.check_pwd",map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return result;
    }

    //  게시물 수정
    public void update(BoardDTO dto){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.update("board.update",dto);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }

    // 게시물 삭제
    public void delete(int num){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.delete("board.delete",num);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }

    //  게시물 리스트
    public List<BoardDTO> list(int pageStart, int pageEnd){
        List<BoardDTO> list = null;
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            Map<String,Object> map = new HashMap<>();
            map.put("start",pageStart);
            map.put("end", pageEnd);
            list=session.selectList("board.list",map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return list;
    }

    //  페이징을 위한 레코드의 개수
    public int count(){
        int result=0;
        SqlSession session = MybatisManager.getInstance().openSession();
        try{
            result = session.selectOne("board.count");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return result;
    }

    //  게시물 추가
    public void insert(BoardDTO dto){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.insert("board.insert",dto);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }

    //  조회수 증가
    public void plus_hit(int num, HttpSession count_session){ // 글번호, 세션
        SqlSession session = null; // 마이바티스를 실행하는 세션
        try{
            long read_time=0;
            //  아래의 세션은 서버의 세션, 처음엔 무조건 null 이므로 조회수가 증가한다.
            if(count_session.getAttribute("read_time_"+num)!=null) { // read_time_글번호
                read_time=(long)count_session.getAttribute("read_time_"+num); // 세션값을 꺼낸다.
            }
            long current_time=System.currentTimeMillis(); //현재 시간
            session=MybatisManager.getInstance().openSession(); // 마이바티스 객체 생성
            if(current_time - read_time > 5*1000) { // (현재 시간 - 최근 읽은 시간) > 5초
                session.update("board.plus_hit",num); //조회수 증가 처리
                session.commit();   // update 이후에는 반드시 커밋.
                //  열람 시간을 업데이트해서 조회수를 일정 시간 이후에 증가할수 있도록 처리한다.
                count_session.setAttribute("read_time_"+num, current_time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(session!=null)
                session.close();
        }
    }

    //  게시물 상세보기
    public BoardDTO view(int num){
        BoardDTO dto = null;
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            dto = session.selectOne("board.view",num);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return dto;
    }

    //  답변 순서 조정
    public void update_order(int group_num, int re_order){ // 그룹 번호, 답변글 그룹의 순서
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            Map<String, Object> map = new HashMap<>();
            map.put("group_num", group_num);
            map.put("re_order", re_order);
            session.update("board.update_order",map);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }

    //  답변을 저장하는 코드
    public void insert_reply(BoardDTO dto){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.insert("board.insert_reply",dto);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
    }
}
/*
num : 글번호 / group_num : 그룹번호 / re_order : 답변글 그룹의 순서 / re_depth : 글 깊이, 최상위 부모 0

            num group_num re_order re_depth
B            5      5        1         0
  Re:B       6      5        2         1
A            1      1        1         0
  Re:A       4      1        2         1
        // 만일 이곳에 새 답변이 달리면 re_order의 순서를 조정해주어야 한다.
    Re:A     9      1        3         2
      Re:A   7      1        4         2
      Re:A   8      1        5         3
    Re:A     2      1        6         1
      Re:A   3      1        7         2
*/