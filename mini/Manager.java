package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class Manager {
	static void Manager_add(Connection con) throws SQLException  {
		System.out.println("관리자 영화 추가");
	}
	static void Manager_Update(Connection con) throws SQLException  {
		System.out.println("관리자 수정");
	}
	static void Manager_delete(Connection con) throws SQLException  {
		System.out.println("관리자 삭제");
	}
}
