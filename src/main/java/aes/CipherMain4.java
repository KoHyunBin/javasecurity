package aes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * usersecurity 테이블의 내용을 출력하기
 * 이메일은 복호화하여 출력하기.
 * 1. 암호화키와 동일하게 처리해야함.
 * 
 */
public class CipherMain4 {
	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gdudb","gdu","1234");
		PreparedStatement pstmt = conn.prepareStatement("select * from usersecurity");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String email = rs.getString("email");
			String userid = rs.getString("userid");
			String key = CipherUtil.makehash(userid); //hash코드
			String plain2 = CipherUtil.decrypt(email,key);
			System.out.println("복호화 email : " + plain2);
		}
	}//메인 끝
}
