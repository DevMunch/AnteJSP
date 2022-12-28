package shop;

import lombok.Data;

@Data
public class CartDTO {
    private int cart_id;
    private String userid;
    private int product_code;
    private int amount;
    // 아래 3개의 필드는 테이블에는 없지만 조인을 하거나 계산을 하기위한 필드
    private String product_name;
    private int price;
    private int money;
}
