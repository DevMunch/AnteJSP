package survey;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private int answer_idx;
    private int survey_idx;
    private int num;
}
