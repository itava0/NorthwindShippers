package org.pluralsight;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NorthwindDataManager {
    private DataSource dataSource;

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a NorthwindDataManager with the specified DataSource.
     *
     * @param dataSource The DataSource to be used for database connections.
     */
    public NorthwindDataManager(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public void deleteShipperById() {
        System.out.println("Enter the shipperID of the shipper to delete.");
        int shipperID = scanner.nextInt();
        try (
                Connection conn = dataSource.getConnection();

                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Shippers WHERE ShipperID = ?");
                PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * FROM Shippers");
                ResultSet rs2 = preparedStatement2.executeQuery();
        ) {
            preparedStatement.setInt(1, shipperID);
            int rows = preparedStatement.executeUpdate();

            System.out.printf("Rows deleted %d\n", rows);
            while (rs2.next()) {
                //getting the values and setting the variables to use later.
                int shipperIDs = rs2.getInt("ShipperID");
                String companyName = rs2.getString("CompanyName");
                String phone2 = rs2.getString("Phone");

                System.out.println("ShipperID: " + shipperIDs + " CompanyName: " + companyName + " Phone: " + phone2);

                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShipperNumber() {
        System.out.println("To change the phone number of the shipper enter the id of the shipper to change");
        int chosenID = scanner.nextInt();
        System.out.println("What is the new number?");
        String newNumber = scanner.next();
        scanner.nextLine();

        try (
                Connection conn = dataSource.getConnection();

                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Shippers SET Phone = ? \" +\n" + "\"WHERE ShipperID = ?");

                PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * FROM Shippers");
                ResultSet rs2 = preparedStatement2.executeQuery();

        ) {
//preparedStatements
            preparedStatement.setString(1, newNumber);
            preparedStatement.setInt(2, chosenID);

            int rows = preparedStatement.executeUpdate();
            // Display the number of rows that were updated
            System.out.printf("Rows updated %d\n", rows);

            while (rs2.next()) {
                //getting the values and setting the variables to use later.
                int shipperID = rs2.getInt("ShipperID");
                String companyName = rs2.getString("CompanyName");
                String phone2 = rs2.getString("Phone");

                System.out.println("ShipperID: " + shipperID + " CompanyName: " + companyName + " Phone: " + phone2);

                System.out.println();
            }
//
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAndDisplayShippers() {
        System.out.println("What is the name");
        String name = scanner.nextLine();
        System.out.println("What is the phone number");
        String phone = scanner.nextLine();
        try (
                Connection conn = dataSource.getConnection();
                //trying to get a connection

                //passing in the preparedStatement
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Shippers (CompanyName, Phone) VALUES (?, ?)");

                PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * FROM Shippers");
                ResultSet rs2 = preparedStatement2.executeQuery();
        )


        //Executes the passed in query

        {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("Rows updated %d\n", rows);


            while (rs2.next()) {
                //getting the values and setting the variables to use later.
                int shipperID = rs2.getInt("ShipperID");
                String companyName = rs2.getString("CompanyName");
                String phone2 = rs2.getString("Phone");

                System.out.println("ShipperID: " + shipperID + " CompanyName: " + companyName + " Phone: " + phone2);

                System.out.println();
            }
//
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
