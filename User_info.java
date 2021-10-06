package movie;

import java.sql.Connection;

class User_info extends Frame {
	Connection con;
	User_info(Connection con,String lbl, String id) {
		super(con,lbl);
	}

}
