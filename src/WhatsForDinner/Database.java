package WhatsForDinner;
import java.sql.*;



class Database{
String dbPath = "jdbc:h2:~/FoodDB";

   /********************************************************************************************************************
   *  Default Constructor
   *
   *
   ********************************************************************************************************************/
  Database() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
      createDB();
      createTable();

  }//Close default constructor.

   /********************************************************************************************************************
   * createDB
   *
   * Creates the database
   ********************************************************************************************************************/
    public void createDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(dbPath,"user","password");
    }//Close createDB

   /********************************************************************************************************************
   * createTable
   *
   * Creates the table in the database
   ********************************************************************************************************************/
    public void createTable(){
        try{
            Connection conn = DriverManager.getConnection(dbPath,"user","password");
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS FOOD "
                    +"(name varchar(20), "
                    +"type varchar(20))";

            stmt.executeUpdate(sql);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }//Close createTable

   /********************************************************************************************************************
   * addName
   * @param name
   * inserts food name into database.
   ********************************************************************************************************************/
    public void addName(String name,String type)  {

        try {
            Connection conn = DriverManager.getConnection(dbPath ,"user","password");
            Statement statement = conn.createStatement();
            String sql = "Insert into FOOD(name, type) values" + "('" + name + "', '" + type + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }//Close addName
   /********************************************************************************************************************
   * addIngerdient
   * @param ingredient
   *  inserts food name into database.
   ********************************************************************************************************************/
    public void addIngredient(String ingredient){

    }//Close addIngredient.

   /********************************************************************************************************************
   * printDB
   *
   * Prints entire database
   ********************************************************************************************************************/
   public void printDB(){

       try {
       Connection conn = DriverManager.getConnection(dbPath ,"user","password");
       Statement statement = conn.createStatement();
       String sql = "select * from FOOD";
       ResultSet resultSet = statement.executeQuery(sql);
       while(resultSet.next()){
           System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
       }


       } catch (SQLException e) {
           e.printStackTrace();
       }


   }//Close printDB

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
    Database mydb = new Database();
    mydb.printDB();
    mydb.addName("burrito", "mexican");
    mydb.printDB();
    }//Close main


}//Close class