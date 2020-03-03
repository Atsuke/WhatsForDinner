package WhatsForDinner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//TODO Add method to randomly pull recipe from db
//TODO search parameter for protein type

class Database{

String dbPath = "jdbc:h2:~/FoodDB";
private List<String> arguments = new ArrayList<>();
private StringBuilder sb = new StringBuilder();
private int counter = 3;


   /********************************************************************************************************************
   *  Default Constructor
   *
   *
   ********************************************************************************************************************/
  Database() throws SQLException {

      createDB();
      createTable();
      resetArguments();


  }//Close default constructor.

   /********************************************************************************************************************
   * createDB
   *
   * Creates the database
   ********************************************************************************************************************/
    public void createDB() {
        //Must declare database driver or the whole damn thing freaks out at you
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }//Close createDB

   /********************************************************************************************************************
   * createTable
   *
   * Creates the table in the database
   ********************************************************************************************************************/
    public void createTable(){
        try{
            //Establish connection to the database
            //Connection conn = DriverManager.getConnection(dbPath,"user","password");
            Connection conn = DriverManager.getConnection(dbPath ,"user","password");
            //Statement builders are apparently a necessity
            Statement stmt = conn.createStatement();

            //Command passed to SQL database as a String
            String sql = "CREATE TABLE IF NOT EXISTS FOOD "
                    +"(Name varchar(20), "
                    +"Type varchar(20), "
                    +"Protein varchar(20),"
                    +"Ingredient1 varchar(20),"
                    +"Ingredient2 varchar(20),"
                    +"Ingredient3 varchar(20),"
                    +"Ingredient4 varchar(20),"
                    +"Ingredient5 varchar(20),"
                    +"Ingredient6 varchar(20),"
                    +"Ingredient7 varchar(20),"
                    +"Ingredient8 varchar(20),"
                    +"Ingredient9 varchar(20),"
                    +"Ingredient10 varchar(20),"
                    +"Ingredient11 varchar(20),"
                    +"Ingredient12 varchar(20),"
                    +"Ingredient13 varchar(20),"
                    +"Ingredient14 varchar(20),"
                    +"Ingredient15 varchar(20),"
                    +"Instructions varchar(500))"

            ;

            //Execute passed statement
            stmt.executeUpdate(sql);

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }//Close createTable

   /********************************************************************************************************************
   * resetArguments
   *
   * Reset arguements array to blank so nothing stupid happens.
   ********************************************************************************************************************/

   public void resetArguments(){
       for(int i=0;i< 18; ++i){
           arguments.add(i, " ");
       }
   }//close resetArguments

   /********************************************************************************************************************
   * addName
   * @param name
   * inserts food name into database.
   ********************************************************************************************************************/
    public void addName(String name)  {

        arguments.set(0, name);

    }//Close addName


   /********************************************************************************************************************
   * addType
   * @param type
   * inserts food type into string for database.
   ********************************************************************************************************************/
   public void addType(String type)  {

    arguments.set(1,type);

   }//Close addType


   /********************************************************************************************************************
   * addType
   * @param protein
   * inserts food type into string for database.
   ********************************************************************************************************************/
    public void addProtein(String protein)  {

        arguments.set(2, protein);

    }//Close addProtein

   /********************************************************************************************************************
   * addInstructions
   * @param instruction
   * inserts food type into string for database.
   ********************************************************************************************************************/
    public void addInstructions(String instruction)  {

        arguments.add(18, instruction);

    }//Close addProtein


   /********************************************************************************************************************
   * addIngredient
   * @param ingredient
   * adds ingredient to the sql string to be inserted into the database.
   ********************************************************************************************************************/
    public void addIngredient(String ingredient){
        arguments.add(counter, ingredient);
        counter++;
    }//Close addIngredient.


   /********************************************************************************************************************
   * Insert Recipe
   *
   * Inserts the Recipe into the Database.
   ********************************************************************************************************************/


   public void insertRecipe()  {
       //Command passed to SQL database as a String
       Statement statement = null;
       try {
           Connection conn = DriverManager.getConnection(dbPath ,"user","password");
           statement = conn.createStatement();

           String sqlInsert = "Insert into FOOD values" +
           "('" + arguments.get(0) + "', '" + arguments.get(1) + "', '" + arguments.get(2) + "', '" + arguments.get(3) + "'," +
           " '" + arguments.get(4) + "', '" + arguments.get(5) + "', '" + arguments.get(6) + "', '" + arguments.get(7) + "'," +
           " '" + arguments.get(8) + "', '" + arguments.get(9) + "', '" + arguments.get(10) + "','" + arguments.get(11) + "'," +
           " '" + arguments.get(12) + "', '" + arguments.get(13) + "', '" + arguments.get(14) + "', '" + arguments.get(15) + "'," +
           " '" + arguments.get(16) + "', '" + arguments.get(17) + "', '" + arguments.get(18) + "')";

           //Execute passed statement
           statement.executeUpdate(sqlInsert);

           counter = 3;
           resetArguments();

       } catch (SQLException e) {
           e.printStackTrace();
       }



   }//close insertCommand

   /********************************************************************************************************************
   * searchByName
   * @param name
   * @return ArrayList resultList
   * Searches Database by name of food
   ********************************************************************************************************************/
   public ArrayList<ArrayList<String>> searchByName(String name){

       ArrayList<ArrayList<String>> resultList = new ArrayList<>();

       try {

           //Open Database connections
           Connection conn = DriverManager.getConnection(dbPath ,"user","password");

           //create the statement to be passed to the SQL database
           Statement statement = conn.createStatement();

           //our search query
           String sql = "select * from FOOD where name = '"+ name +"' ";

           //Execute passed statement
           ResultSet resultSet = statement.executeQuery(sql);

           //add each part of the recipe to an array list of strings
           while(resultSet.next()){
               ArrayList recipe = new ArrayList<>();
               for(int i =1; i<=19;i++){
                   recipe.add(resultSet.getString(i));
               }
               //add each recipe to the list of results that matched the query
                resultList.add(recipe);

           }//close while

       } catch (SQLException e) {
           e.printStackTrace();
       }

       //an array list of array lists.
       return resultList;
   }//close searchByName

   /********************************************************************************************************************
   * searchByType
   * @param type
   * @return ArrayList resultList
   * Searches DB for type of food
   ********************************************************************************************************************/
   public ArrayList<ArrayList<String>> searchByType(String type){

       ArrayList<ArrayList<String>> resultList = new ArrayList<>();

       try {

           //Open Database connections
           Connection conn = DriverManager.getConnection(dbPath ,"user","password");

           //create the statement to be passed to the SQL database
           Statement statement = conn.createStatement();

           //our search query
           String sql = "select * from FOOD where type = '"+ type +"' ";

           //Execute passed statement
           ResultSet resultSet = statement.executeQuery(sql);

           //add each part of the recipe to an array list of strings
           while(resultSet.next()){
               ArrayList recipe = new ArrayList<>();
               for(int i =1; i<=19;i++){
                   recipe.add(resultSet.getString(i));
               }
               //add each recipe to the list of results that matched the query
               resultList.add(recipe);

           }//close while

       } catch (SQLException e) {
           e.printStackTrace();
       }

       //an array list of array lists.
       return resultList;
   }//close searchByType

    /********************************************************************************************************************
    * searchbyProtein
    * @param protein
    * @return ArrayList resultList
    * Seraches DB for type of protein
    ********************************************************************************************************************/
    public ArrayList<ArrayList<String>> searchByProtein(String protein){

        ArrayList<ArrayList<String>> resultList = new ArrayList<>();

        try {

            //Open Database connections
            Connection conn = DriverManager.getConnection(dbPath ,"user","password");

            //create the statement to be passed to the SQL database
            Statement statement = conn.createStatement();

            //our search query
            String sql = "select * from FOOD where protein = '"+ protein +"' ";

            //Execute passed statement
            ResultSet resultSet = statement.executeQuery(sql);

            //add each part of the recipe to an array list of strings
            while(resultSet.next()){
                ArrayList recipe = new ArrayList<>();
                for(int i =1; i<=19;i++){
                    recipe.add(resultSet.getString(i));
                }
                //add each recipe to the list of results that matched the query
                resultList.add(recipe);

            }//close while

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //an array list of array lists.
        return resultList;
    }//close searchByProtein


    /********************************************************************************************************************
   * printDB
   *
   * Prints entire database
   ********************************************************************************************************************/
   public void printDB(){
  //TODO fix this method so its useful in production
       try {
           Connection conn = DriverManager.getConnection(dbPath ,"user","password");
            //Statement builders are apparently a necessity
            Statement statement = conn.createStatement();

           //Command passed to SQL database as a String
            String sql = "select * from FOOD";

           //Execute passed statement
            ResultSet resultSet = statement.executeQuery(sql);


       //Keep printing while there's stuff to print
       while(resultSet.next()){
           System.out.println(
                         resultSet.getString(1) + " " + resultSet.getString(2) +
                   " " + resultSet.getString(3) + " " + resultSet.getString(4)+
                   " " + resultSet.getString(5) + " " + resultSet.getString(6)+
                   " " + resultSet.getString(7) + " " + resultSet.getString(8)+
                   " " + resultSet.getString(9) + " " + resultSet.getString(10)+
                   " " + resultSet.getString(11) + " " + resultSet.getString(12)+
                   " " + resultSet.getString(13) + " " + resultSet.getString(14)+
                   " " + resultSet.getString(15) + " " + resultSet.getString(16)+
                   " " + resultSet.getString(17) + " " + resultSet.getString(18)+
                   " " + resultSet.getString(19)

           );
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
    mydb.addName("burrito");
    mydb.addType("murican");
    mydb.addProtein("chicken");
    mydb.addIngredient("1");
    mydb.addIngredient("2");
    mydb.addIngredient("3");
    mydb.addIngredient("4");
    mydb.addIngredient("5");
    mydb.addIngredient("1");
    mydb.addIngredient("2");
    mydb.addIngredient("3");
    mydb.addIngredient("4");
    mydb.addIngredient("5");
    //mydb.addIngredient("1");
    //mydb.addIngredient("2");
    //mydb.addIngredient("3");
    //mydb.addIngredient("4");
    //mydb.addIngredient("5");
    mydb.addInstructions("Instruct a motherfucker on how to do a thing");
    mydb.insertRecipe();
    mydb.printDB();
    //System.out.println(mydb.searchByProtein("pork"));
    }//Close main


}//Close class