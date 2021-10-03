package movie;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Add_acount {
	public static void acount(Connection con) throws SQLException{
	
	System.out.println("ȸ������");
	Scanner sc = new Scanner(System.in);
	String SearchSQL = "select m_id from members where m_id = ?";
	String InsertSQL = "insert into MEMBERS (M_ID, M_PW, P_NUMBER, M_ADDRESS) values (?, ?, ?, ?) ";
	
	PreparedStatement S_stmt = con.prepareStatement(SearchSQL);
	PreparedStatement P_stmt = con.prepareStatement(InsertSQL); 
	
	
	while(true) {
		System.out.print("���̵� (4���� �̻�) : ");
		String id = sc.next();

		S_stmt.setString(1, id);
		ResultSet rs = S_stmt.executeQuery();
		
		if(rs.next()) {
				System.out.println("�ߺ��� ���̵� �����մϴ�.");
		}
		else {
			if(id.length() < 4) {
				System.out.println("4���� �̻� �Է����ּ���.");
			}
			else {
				P_stmt.setString(1, id); 
				break;
			}
		}
		
	}

	while(true) {
		System.out.print("��й�ȣ (4���� �̻�) : ");
		String pw = sc.next();
		if(pw.length() < 4) {
			System.out.println("4���� �̻� �Է����ּ���.");
		}
		else {
			P_stmt.setString(2, pw); 
			break;
		}
	}

	while(true) {
		System.out.print("11�ڸ��� ��ȭ��ȣ�� �Է����ּ��� : ");
		String pn = sc.next();
		if(pn.length() != 11) {
			System.out.println("��ȣ ���Ŀ� ���� �Է����ּ���.");
		}
		else {
			P_stmt.setString(3, pn);
			break;
		}
	}
	
		System.out.print("�ּ� �Է� : ");
		String ma = sc.next();
		P_stmt.setString(4, ma); 
		P_stmt.execute();
	}
	
}