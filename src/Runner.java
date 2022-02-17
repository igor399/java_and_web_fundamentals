import by.epam.lab.BuisnessTrip;

//1. Create an array of minimum 5 objects (the element with the index 2 should be empty;
//the last element of the array should be created by the no-arg constructor
public class Runner {
    public static void main(String[] args) {
        BuisnessTrip[] buisnessTrips = {
                new BuisnessTrip("Ivan Petrov", 120568, 5),
                new BuisnessTrip("Andrey Losev", 500555, 7),
                null,
                new BuisnessTrip("Sergey Vitkov", 312345, 9),
                new BuisnessTrip()
        };

//2. Output the array content to the console, using show( ) method, and the business trip with maximum cost
        BuisnessTrip maxBuisnessTrip = buisnessTrips[0];

        for (BuisnessTrip buisnessTrip : buisnessTrips) {

            if (buisnessTrip == null) {
                continue;
            }

            if (buisnessTrip.getTotal() > maxBuisnessTrip.getTotal()) {
                maxBuisnessTrip = buisnessTrip;
            }
            buisnessTrip.show();
            System.out.println();
        }
        System.out.println("the most expencive trip is " + maxBuisnessTrip);

//3. Update the employee`s transportation expenses for the last object of the array
        buisnessTrips[buisnessTrips.length - 1].setTransportExpenses(451234);
        System.out.println();

//4. Output the total duration of two initial business trips by the single operator
        System.out.println("total deration of 2 trips: " + (buisnessTrips[0].getNumOfDays() + buisnessTrips[1].getNumOfDays()));
        System.out.println();

//5. Output the array content to the console (one element per line), using toString( ) method implicitly
        for (BuisnessTrip buisnessTrip : buisnessTrips) {
            System.out.println(buisnessTrip);
        }
    }
}
