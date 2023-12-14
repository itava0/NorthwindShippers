package org.pluralsight;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        // Set up the DataSource for database connection
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(System.getenv("MY_DB_USERNAME"));
        dataSource.setPassword(System.getenv("MY_DB_PASSWORD"));
        NorthwindDataManager dataManager = new NorthwindDataManager(dataSource);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What do you want to do?");
            System.out.println("1) Add new shipper data and phone");
            System.out.println("2) Update shipper phone number");
            System.out.println("3) Delete a shipper based using shipperID");
            System.out.println("0) Exit program");
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1: {
                    dataManager.addAndDisplayShippers();
                    break;
                }
                case 2: {
                    dataManager.updateShipperNumber();
                    break;
                }
                case 3: {
                    dataManager.deleteShipperById();
                    break;
                }
                case 0:
                    scanner.close();
                    try {
                        dataSource.close();
                    } catch (SQLException e) {
                        System.out.println("Error occurred while closing the dataSource");
                    }
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("ERROR: please choose a valid input");
                    break;
            }
        }
    }
}
