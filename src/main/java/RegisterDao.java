import java.sql.*;


    public class RegisterDao {

        static final String dbUrl = "jdbc:postgresql://localhost:5432/users";
        static final String dbUname = "postgres";
        static final String dbPassword = "12345";
        static final String dbDriver = "org.postgresql.Driver";

        public void loadDriver(String dbDriver)
        {
            try {
                Class.forName(dbDriver);
                System.out.println("Нашло драйвер в loadDriver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Не нашло драйвер в loadDriver");
            }
        }

        public Connection getConnect()
        {
            Connection connection = null;
            try {
                Class.forName(dbDriver);
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
                System.out.println(e.getMessage());
            }
            try {
                connection = DriverManager.getConnection(dbUrl, dbUname,dbPassword);
                System.out.println("PostgreSQL JDBC Driver successfully connected");
                return connection;
            } catch (SQLException e) {
                System.out.println("Не подключилось");


            }
            return connection;
        }


        public String insert(Member member)
        {
            loadDriver(dbDriver);
            Connection con = getConnect();
            String result = "Data entered successfully";
            String sql = "insert into member values(?,?,?,?)";

            PreparedStatement ps;
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, member.getUname());
                ps.setString(2, member.getPassword());
                ps.setString(3, member.getEmail());
                ps.setString(4, member.getPhone());
                ps.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result = "Data not entered";
            }
            return result;
        }

    }