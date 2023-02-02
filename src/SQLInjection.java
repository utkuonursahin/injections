import java.sql.*;
import java.util.*;

public class SQLInjection {
    public static void main(String[] args) {
        String db_url = "jdbc:mysql://127.0.0.1:3306/site?allowMultiQueries=true";
        try(Connection connection = DriverManager.getConnection(db_url,"root","test1234")){
            System.out.println("*Connection successfully initialized!*");
            Statement statement = connection.createStatement();

            int id; double budget; String name; String type; String command;
            Scanner input = new Scanner(System.in);
            Random rand = new Random();
            PreparedStatement preparedStatement;
            ////// CREATING USERS //////
            command = "INSERT INTO site.user (iduser,name,budget)"+"VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(command);
            for(int i = 0; i<3; i++){
                id = rand.nextInt(1000);
                preparedStatement.setInt(1,id);
                System.out.print("Enter user name: ");
                name = input.nextLine();
                preparedStatement.setString(2,name);
                System.out.print("Enter user budget: ");
                budget = input.nextDouble();
                input.nextLine();
                preparedStatement.setDouble(3,budget);
                preparedStatement.executeUpdate();
            }

            //executeQuery is for READ operations
            System.out.println("--------//// PRINT ALL USERS ////--------");
            ResultSet result = statement.executeQuery("SELECT * FROM site.user");
            while(result.next()) System.out.println(result.getString("name")+" "+result.getString("budget"));

            //// SQL INJECTION BASED ON 1=1 Always True ////
            System.out.println("--------//// SQL INJECTION BASED ON 1=1 Always True ////--------");
            result = statement.executeQuery("SELECT * FROM site.user WHERE name='Utku' or 1=1");
            //This will select all users and print the same result as above lines...
            while(result.next()) System.out.println(result.getString("name")+" "+result.getString("budget"));

            //// SQL INJECTION BASED ON 'char'='char' Always True ////
            System.out.println("--------//// SQL INJECTION BASED ON 'char'='char' Always True ////--------");
            result = statement.executeQuery("SELECT * FROM site.user WHERE name='Utku' or 'u'='u' ");
            //This will select all users and print the same result as above lines...
            while(result.next()) System.out.println(result.getString("name")+" "+result.getString("budget"));

            //// CREATING ANOTHER TABLE IN DATABASE ////
            System.out.println("--------//// CREATING ANOTHER TABLE IN DATABASE ////--------");
            statement.executeUpdate("CREATE TABLE site.animals (idanimal int(11) NOT NULL, name varchar(45) NOT NULL, type varchar(45) NOT NULL, PRIMARY KEY (idanimal))");
            System.out.println("*Table created successfully!*");

            //// CREATING SOME ANIMAL ROWS IN ANIMALS TABLE ////
            System.out.println("--------//// CREATING SOME ANIMALS ////--------");
            command = "INSERT INTO site.animals (idanimal,name,type)"+"VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(command);
            for(int i = 0; i<3; i++){
                id = rand.nextInt(1000);
                preparedStatement.setInt(1,id);
                System.out.print("Enter animal name: ");
                name = input.nextLine();
                preparedStatement.setString(2,name);
                System.out.print("Enter animal type: ");
                type = input.nextLine();
                preparedStatement.setString(3,type);
                preparedStatement.executeUpdate();
            }

            //// SQL INJECTION BASED ON BATCHED STATEMENTS ////
            System.out.println("--------//// SQL INJECTION BASED ON BATCHED STATEMENTS ////--------");
            //This will drop the table animals if it exists and print the user named Utku...
            result = statement.executeQuery("SELECT * FROM site.user WHERE name='Utku';DROP TABLE IF EXISTS site.animals;");
            while(result.next()) System.out.println(result.getString("name")+" "+result.getString("budget"));

        } catch(SQLException err){throw new Error("Houston, we have a problem!",err);}
    }
}