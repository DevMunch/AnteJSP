package shop;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.List;

public class CartDAO {
    //
    public List<CartDTO> cart_money(){
        SqlSession session = MybatisManager.getInstance().openSession();
        List<CartDTO> items = session.selectList("cart.product_money");
        session.close();
        return items;
    }

    // 장바구니 추가
    public void insert_cart(CartDTO dto){
        SqlSession session = null;
        try{
            session=MybatisManager.getInstance().openSession();
            session.insert("cart.insert_cart",dto);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
    }

    // 장바구니 세부 목록
    public List<CartDTO> list_cart(String userid){
        SqlSession session = null;
        List<CartDTO>list = null;
        try{
            session=MybatisManager.getInstance().openSession();
            list=session.selectList("cart.list_cart",userid);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
        return list;
    }

    // 장바구니 개별 삭제
    public void delete_cart(int cart_id){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.delete("cart.delete_cart", cart_id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
    }

    // 장바구니 목록 수정
    public void update_cart(CartDTO dto){
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            session.update("cart.update_cart", dto);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
    }

    // 장바구니 비우기
    public void clear_cart(String userid){
        SqlSession session = null;
        try{
            session=MybatisManager.getInstance().openSession();
            session.delete("cart.clear_cart", userid);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
    }

    // 장바구니 총 금액
    public int sum_money(String userid){
        int total = 0;
        SqlSession session = MybatisManager.getInstance().openSession();
        total = session.selectOne("cart.sum_money", userid);
        session.close();
        return total;
    }
}
