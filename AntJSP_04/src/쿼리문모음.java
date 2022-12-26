/*
쿼리문 모음

— 페이지 나누기 실습을 위한 기존 테이블을 데이터 없이 복사해 테이블 생성
Create table test
As select * from employees where 1=0;

— 생성한 test 테이블에 991건의 레코드를 입력
declare
    i number := 1;
begin
    while i <= 991 loop
        insert into test ( empno, ename )
        values ( i, '사원'|| i );
        i := i + 1;
    end loop;
end;
/
commit;

-- 페이징 쿼리문
select *
from (
    select A.*, rownum as rn
    from(
        select empno, ename
        form test
        order by empno
    ) A
)
where rn between 1 and 10;
commi;

— 페이징 쿼리문
select * from (
    select rownum num, n.* from (
        select * from notice order by regdate desc
    ) n
)
where num between 11 and 20;

— 페이징 쿼리문을 쉽게 사용하기 위해 뷰로 생성
CREATE VIEW NOTICE_VIEW
AS
SELECT * FROM(
    SELECT ROWNUM NUM, N.* FROM(
        SELECT * FROM NOTICE ORDER BY REGDATE DESC
    ) N
);

SELECT * FROM NOTICE_VIEW WHERE NUM BETWEEN 11 AND 20;

— 시작 페이지 계산방법
int start = 1 + (page-1)*10; // 1, 11, 21, 31...
— 종료 페이지 계산방법
int end = 10 * page; // 10, 20, 30...


-- to_char 함수를 활용한 조회 쿼리
select id, title, writer_id, content, to_char(regdate,'yyyy-mm-dd') as regdate, hit from notice order by id;

— 1 ~ 9999까지 증가하는 시퀀스
CREATE SEQUENCE NOTICE_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;

— 필드의 총 갯수를 알아내는 쿼리문, 스칼라 함수 사용시 as를 써야 부적합한 열이름을 피할수있다.
SELECT COUNT(ID) as COUNT FROM NOTICE

*/