import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

//        1.load the content of the file in.csv into the map where a purchase is a key and a weekday of last purchase is a value;
//        2.	print the map by for–each cycle;
//        3.	load the content of the file in.csv into the map where a purchase is a key and a weekday of first purchase is a value;
//        4.	print the map by for–each cycle;
//        5.	find the first and the last weekdays for bread with price 1.55;
//        6.	find the first weekday for bread with price 1.70;
//        7.	remove all entries from the first map where the purchase name is meat;
//        8.	remove all entries from the second map on FRIDAY;
//        9.	print maps by for–each cycle;
//        10.	load instances of the subclass PricePurchase from the file in.csv into the list (List<PricePurchase>);
//        11.	print the total cost of these purchases;
//        12.	load the content of the file in.csv into the enumerated map where a weekday is a key and purchases list for this weekday is a value;
//        13.	print the map by for–each cycle;
//        14.	print the total cost of all purchases for each weekday;
//        15.	find all purchases on MONDAY.


public class Runner {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {


            while (sc.hasNext()) {

            }


        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }
    }
}
