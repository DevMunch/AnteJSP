package survey;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDTO {
    private int survey_idx;
    private int num;
    private int sum_num;
    private double rate;
}
