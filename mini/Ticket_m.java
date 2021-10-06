package test;

import java.sql.Connection;

class Ticket_m extends Frame{
	Connection con;
	Ticket_m(Connection con, String lbl, String id) {
		super(con,lbl);
	}

}
