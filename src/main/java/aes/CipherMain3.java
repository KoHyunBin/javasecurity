package aes;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * useraccount 테이블의 email 값을 읽어서, usersecurity 테이블에 AES 알고리즘으로 암호화하여 저장
 * 1. usersecurity 테이블의 email 컬럼의 크기를 1000으로 크기 변경하기
 * 2. key는 userid의 해쉬값(SHA-256)의 앞 16자리로 설정한다.
 */
public class CipherMain3 {
	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gdudb","gdu","1234");
		PreparedStatement pstmt = conn.prepareStatement("select * from useraccount");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String email = rs.getString("email");
			String userid = rs.getString("userid");
			String key = CipherUtil.makehash(userid); //hash코드
			String cipherEmail = CipherUtil.encrypt(email,key);
			String plain2 = CipherUtil.decrypt(cipherEmail,key);
			System.out.println("email : " + plain2);
			pstmt = conn.prepareStatement("update usersecurity set email=? where userid=?");
			pstmt.setString(1, cipherEmail);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		}
		
	}//메인 끝
}
