import by.epam.lab.beans.NumLen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.services.GlobalConstants.*;

public class JdbcRunner {
    public static void main(String[] args) {
        try {
            Connection cn = null;
            Statement st = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                cn = DriverManager.getConnection(DB_URL, USER, PASS);
                st = cn.createStatement();
                rs = st.executeQuery(FIRST_SELECT_QUERY);
                List<NumLen> lines = new ArrayList<>();
                while (rs.next()) {
                    lines.add(new NumLen(rs.getInt(LENGTH), rs.getInt(NUMBER)));
                }
                for (NumLen line : lines) {
                    System.out.println(line);
                }
                ps = cn.prepareStatement(INSERT_QUERY);
                st.execute(DELETE_TABLE_QUERY);
                for (NumLen line : lines) {
                    ps.setInt(LENGTH, line.getLen());
                    ps.setInt(NUMBER, line.getNum());
                    ps.executeUpdate();
                }
                rs = st.executeQuery(SECOND_SELECT_QUERY);
                while (rs.next()) {
                    System.out.println(rs.getInt(LENGTH) + SEMICOLON + rs.getInt(NUMBER));
                }
            } finally {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
