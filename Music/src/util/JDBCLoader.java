package util;

import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class JDBCLoader extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
				
		try {
			String drivers = config.getInitParameter("jdbcdriver"); 
			StringTokenizer st = new StringTokenizer(drivers, ",");
			while (st.hasMoreTokens()) {
				String jdbcDriver = st.nextToken();
				Class.forName(jdbcDriver); 
				Class.forName("org.apache.commons.dbcp.PoolingDriver");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}










