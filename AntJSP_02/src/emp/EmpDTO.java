package emp;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {
    private int empno;
    private String ename;
    private String job;
    private Date hiredate;
    private int sal;
}
