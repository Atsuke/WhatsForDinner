package WhatsForDinner;
import java.sql.*;

//TODO String builder method probably a good idea
//TODO Add method to randomly pull recipe from db
//TODO search parameter for protein type

class Database{
String dbPath = "jdbc:h2:~/FoodDB";

   /********************************************************************************************************************
   *  Default Constructor
   *
   *
   ********************************************************************************************************************/
  Database() throws SQLException, ClassNotFoundException {

      createDB();
      createTable();

  }//Close default constructor.

   /********************************************************************************************************************
   * createDB
   *
   * Creates the database
   ********************************************************************************************************************/
    public void createDB() throws ClassNotFoundException {
        //Must declare database driver or the whole damn thing freaks out at you
        Class.forName("org.h2.Driver");


    }//Close createDB

   /********************************************************************************************************************
   * createTable
   *
   * Creates the table in the database
   ********************************************************************************************************************/
    public void createTable(){
        try{
            //Establish connection to the database
            Connection conn = DriverManager.getConnection(dbPath,"user","password");

            //Statement builders are apparently a necessity
            Statement stmt = conn.createStatement();

            //Command passed to SQL database as a String
            String sql = "CREATE TABLE IF NOT EXISTS FOOD "
                    +"(name varchar(20), "
                    +"type varchar(20))";

            //Execute passed statement
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

            //Establish connection to the database
            Connection conn = DriverManager.getConnection(dbPath ,"user","password");

            //Statement builders are apparently a necessity
            Statement statement = conn.createStatement();

            //Command passed to SQL database as a String
            String sql = "Insert into FOOD(name, type) values" + "('" + name + "', '" + type + "')";

            //Execute passed statement
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
        //TODO write me!
    }//Close addIngredient.

   /********************************************************************************************************************
   * printDB
   *
   * Prints entire database
   ********************************************************************************************************************/
   public void printDB(){
  //TODO fix this method so its useful in production
       try {

            //Establish connection to the database
            Connection conn = DriverManager.getConnection(dbPath ,"user","password");

            //Statement builders are apparently a necessity
            Statement statement = conn.createStatement();

           //Command passed to SQL database as a String
            String sql = "select * from FOOD";

           //Execute passed statement
            ResultSet resultSet = statement.executeQuery(sql);

       //Keep printing while there's stuff to print
       while(resultSet.next()){
           System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
       }


       } catch (SQLException e) {
           e.printStackTrace();
       }


   }//Close printDB
   /********************************************************************************************************************
   * clearDB
   *
   * Deletes the entire database
   ********************************************************************************************************************/
    public void clearDB(){
        try {

            //Establish connection to the database
            Connection conn = DriverManager.getConnection(dbPath ,"user","password");

            //Statement builders are apparently a necessity
            Statement statement = conn.createStatement();

            //Command passed to SQL database as a String
            String sql = "DELETE from FOOD";

            //Execute passed statement
            statement.execute(sql);

            } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Database mydb = new Database();
    //mydb.clearDB();
    //mydb.printDB();
    mydb.addName("burrito", "mexican");
    mydb.printDB();
    }//Close main


}//Close class