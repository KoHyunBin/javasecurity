package aes;

public class CipherMain5 {
	public static void main(String[] args) {
		String key = "abc1234567";
		//p1.txt : 평문 텍스트 파일 이름
		//c.sec : 암호문 파일
		CipherUtil.encryptFile("p1.txt","c.sec",key);
	}//메인 끝
}
