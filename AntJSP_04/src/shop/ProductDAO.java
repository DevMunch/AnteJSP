package shop;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.List;

public class ProductDAO {

    // 상품 리스트
    public List<ProductDTO> listProduct(){
        SqlSession session = MybatisManager.getInstance().openSession();
        List<ProductDTO> list = session.selectList("product.list_product");
        session.close();
        return list;
    }

    // 상품 상세보기
    public ProductDTO detailProduct(int product_code){
        SqlSession session = MybatisManager.getInstance().openSession();
        ProductDTO dto = session.selectOne("product.detail_product",product_code);
        session.close();
        return dto;
    }

    // 상품 수정
    public void updateProduct(ProductDTO dto){
        SqlSession session = MybatisManager.getInstance().openSession();
        session.update("product.update_product",dto);
        session.commit();
        session.close();
    }

    // 상품 등록
    public void insertProduct(ProductDTO dto){
        SqlSession session = MybatisManager.getInstance().openSession();
        session.insert("product.insert_product",dto);
        session.commit();
        session.close();
    }

    // 상품 삭제
    public  void deleteProduct(int product_code){
        SqlSession session = MybatisManager.getInstance().openSession();
        session.delete("product.delete_product", product_code);
        session.commit();
        session.close();
    }

    // 상품 이미지 파일 이름
    public String fileInfo(int product_code){
        SqlSession session = MybatisManager.getInstance().openSession();
        String result = session.selectOne("product.detail_product",product_code);
        session.close();;
        return result;
    }
}
