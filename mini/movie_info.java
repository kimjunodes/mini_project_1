package test;

import java.sql.Connection;

class movie_info extends Frame {
	Connection con;
	movie_info(Connection con,String lbl) {
		super(con,lbl);
	}

}
