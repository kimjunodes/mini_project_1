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
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("0. 프로그램 종료");
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
				System.out.println("잘못된 숫자가 입력되었습니다.");
				continue;
 			}
		}
	}
	private static void login(Connection con) throws SQLException{
		String id = null;         // id 담을 변수
 		System.out.println("로그인 메소드");
		// if id== 회원
		user_menu(con,id);
		// else if id == manager
		manager_menu(con);
		// else 로그인 실패 (다시 입력받거나 취소하는 것 추가)
	}
	private static void join_membership(Connection con) throws SQLException{
		//계정 추가.
		Add_acount.acount(con);
		System.out.println("teset");
	}
	
	private static void user_menu(Connection con, String id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {		
			System.out.println("==========메뉴===========");
			System.out.println("1. 영화 예매");
			System.out.println("2. 영화 예매 취소");
			System.out.println("3. 영화 예매 조회");
			System.out.println("4. 영화 예매 수정");
			System.out.println("5. 영화 정보");
			System.out.println("0. 로그아웃");
			System.out.println("=======================");
			System.out.print("원하는 메뉴 : ");

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
					System.out.println("잘못된 숫자가 입력되었습니다.");
					continue;
			}	
			break;
		}
	}
	private static void manager_menu(Connection con) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {		
			System.out.println("=======================");
			System.out.println("메뉴");
			System.out.println("1. 영화 추가");
			System.out.println("2. 영화 수정");
			System.out.println("3. 영화 삭제");
			System.out.println("4. 로그아웃");
			System.out.println("=======================");
			System.out.print("원하는 메뉴 : ");

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
