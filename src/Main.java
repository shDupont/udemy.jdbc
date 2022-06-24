import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Connection  conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);

            st = conn.createStatement();

            int row = st.executeUpdate("UPDATE seller SET BaseSalary = 4090 Where DepartmentId = 1");

            int x = 1;
            if (x < 2){
                throw new SQLException("Fake error");
            }

            int row2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 Where DepartmentId = 2");

            conn.commit();
            System.out.println("Row1: " + row);
            System.out.println("Row2: " + row2);

        } catch (SQLException e) {

            try {
                conn.rollback();
                throw new DbException("Transaction rolled back!" + e.getMessage());
            }
            catch (SQLException e1){
                throw new DbException("Error rolled back!" + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
