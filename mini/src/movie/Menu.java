package movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Menu {
	
	public static void menu(Connection con) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {		
			System.out.println("=======================");
			System.out.println("1. �α���");
			System.out.println("2. ȸ������");
			System.out.println("0. ���α׷� ����");
			System.out.println("=======================");
			int k = sc.nextInt();
			
			switch(k){
			case 1 : 
				login(con);  
				break;
			case 2 :
				join_membership(con);
				break;
			case 0 :
				System.exit(0);
			default :
				System.out.println("�߸��� ���ڰ� �ԷµǾ����ϴ�.");
				continue;
 			}
		}
	}
	private static void login(Connection con) throws SQLException{
		String id = null;         // id ���� ����
 		System.out.println("�α��� �޼ҵ�");
		// if id== ȸ��
		user_menu(con,id);
		// else if id == manager
		manager_menu(con);
		// else �α��� ���� (�ٽ� �Է¹ްų� ����ϴ� �� �߰�)
	}
	private static void join_membership(Connection con) throws SQLException{
		//���� �߰�.
		Add_acount.acount(con);
		System.out.println("teset");
	}
	
	private static void user_menu(Connection con, String id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {		
			System.out.println("==========�޴�===========");
			System.out.println("1. ��ȭ ����");
			System.out.println("2. ��ȭ ���� ���");
			System.out.println("3. ��ȭ ���� ��ȸ");
			System.out.println("4. ��ȭ ���� ����");
			System.out.println("5. ��ȭ ����");
			System.out.println("0. �α׾ƿ�");
			System.out.println("=======================");
			System.out.print("���ϴ� �޴� : ");

			int k = sc.nextInt();
			switch (k) {
				case 1 :
					User.ticketing(con, id);
					break;
				case 2:
					 User.ticket_cancel(con, id);
					 break;
				case 3:
					User.ticket_check(con, id);
					 break;
				case 4:
					User.ticket_update(con, id);
					 break;
				case 5:
					User.movie_info(con, id);
					 break;
				case 0:
					System.exit(0);
				default:
					System.out.println("�߸��� ���ڰ� �ԷµǾ����ϴ�.");
					continue;
			}	
			break;
		}
	}
	private static void manager_menu(Connection con) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {		
			System.out.println("=======================");
			System.out.println("�޴�");
			System.out.println("1. ��ȭ �߰�");
			System.out.println("2. ��ȭ ����");
			System.out.println("3. ��ȭ ����");
			System.out.println("4. �α׾ƿ�");
			System.out.println("=======================");
			System.out.print("���ϴ� �޴� : ");

			int k = sc.nextInt();
			switch (k) {
				case 1 :
					Manager.Manager_add(con);
					break;
				case 2:
					Manager.Manager_Update(con);
					break;
				case 3:
					Manager.Manager_delete(con);
					break;
				case 4:
					 menu(con);
					 break;
				default:
					 continue;
				}	
				break;
			}
	}

	
}
