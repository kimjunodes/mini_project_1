package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class User {
	static void ticketing(Connection con, String id) throws SQLException {
		System.out.println("���� ����");
	}
	static void ticket_cancel(Connection con, String id) throws SQLException  {
		System.out.println("���� ���� ���");
	}
	static void ticket_check(Connection con, String id) throws SQLException {
		System.out.println("���� ���� Ȯ��");
	}
	static void ticket_update(Connection con, String id) throws SQLException {
		System.out.println("���� ���� ����");
	}
	static void movie_info(Connection con, String id) throws SQLException {
		System.out.println("��ȭ ���� ");
	}
}
