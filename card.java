package movie;

import java.sql.Connection;

public class card extends Frame {

	card(Connection con, String lbl,String id, int m_id) {
		super(con, lbl);
	}

}
