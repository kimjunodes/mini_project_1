package movie;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Add_acount {
	public static void acount(Connection con) throws SQLException{
	
	System.out.println("회원가입");
	Scanner sc = new Scanner(System.in);
	String SearchSQL = "select m_id from members where m_id = ?";
	String InsertSQL = "insert into MEMBERS (M_ID, M_PW, P_NUMBER, M_ADDRESS) values (?, ?, ?, ?) ";
	
	PreparedStatement S_stmt = con.prepareStatement(SearchSQL);
	PreparedStatement P_stmt = con.prepareStatement(InsertSQL); 
	
	
	while(true) {
		System.out.print("아이디 (4글자 이상) : ");
		String id = sc.next();

		S_stmt.setString(1, id);
		ResultSet rs = S_stmt.executeQuery();
		
		if(rs.next()) {
				System.out.println("중복된 아이디가 존재합니다.");
		}
		else {
			if(id.length() < 4) {
				System.out.println("4글자 이상 입력해주세요.");
			}
			else {
				P_stmt.setString(1, id); 
				break;
			}
		}
		
	}

	while(true) {
		System.out.print("비밀번호 (4글자 이상) : ");
		String pw = sc.next();
		if(pw.length() < 4) {
			System.out.println("4글자 이상 입력해주세요.");
		}
		else {
			P_stmt.setString(2, pw); 
			break;
		}
	}

	while(true) {
		System.out.print("11자리의 전화번호를 입력해주세요 : ");
		String pn = sc.next();
		if(pn.length() != 11) {
			System.out.println("번호 형식에 맞춰 입력해주세요.");
		}
		else {
			P_stmt.setString(3, pn);
			break;
		}
	}
	
		System.out.print("주소 입력 : ");
		String ma = sc.next();
		P_stmt.setString(4, ma); 
		P_stmt.execute();
	}
	
}