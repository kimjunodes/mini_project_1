package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class User {
	static void ticketing(Connection con, String id) throws SQLException {
		System.out.println("유저 예매");
	}
	static void ticket_cancel(Connection con, String id) throws SQLException  {
		System.out.println("유저 예매 취소");
	}
	static void ticket_check(Connection con, String id) throws SQLException {
		System.out.println("유저 예매 확인");
	}
	static void ticket_update(Connection con, String id) throws SQLException {
		System.out.println("유저 예매 수정");
	}
	static void movie_info(Connection con, String id) throws SQLException {
		System.out.println("영화 정보 ");
	}
}
