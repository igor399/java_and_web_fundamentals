import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.services.GlobalConstants.*;

public class JdbcRunner {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(INSERT_FREQUENCIES);
             ResultSet rs = st.executeQuery(SELECT_COORDINATES)) {
            List<NumLen> segmentList = new ArrayList<>();
            while (rs.next()) {
                NumLen numLen = new NumLen(rs.getInt(LEN_IND),
                        rs.getInt(NUM_IND));
                segmentList.add(numLen);
                System.out.println(numLen);
            }
            st.executeUpdate(DELETE_FREQUENCIES);
            for (NumLen line : segmentList) {
                ps.setInt(LEN_IND, line.getLen());
                ps.setInt(NUM_IND, line.getNum());
                ps.addBatch();
            }
            ps.executeBatch();
            try (ResultSet lenMoreNumRs = st.executeQuery(SELECT_FREQUENCIES)) {
                while (lenMoreNumRs.next()) {
                    System.out.println(lenMoreNumRs.getInt(LEN_IND) + SEMICOLON +
                            lenMoreNumRs.getInt(NUM_IND));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
