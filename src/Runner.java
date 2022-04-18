import by.epam.lab.BuisnessTrip;

public class Runner {
    public static void main(String[] args) {
        BuisnessTrip[] buisnessTrips;
        buisnessTrips = new BuisnessTrip[]{
                new BuisnessTrip("Ivan Petrov", 120568, 5),
                new BuisnessTrip("Andrey Losev", 500555, 7),
                null,
                new BuisnessTrip("Sergey Vitkov", 312345, 9),
                new BuisnessTrip()
        };

        BuisnessTrip maxBuisnessTrip = buisnessTrips[0];
        for (BuisnessTrip buisnessTrip : buisnessTrips) {
            if (buisnessTrip == null) {
                continue;
            }
            if (buisnessTrip.getTotal() > maxBuisnessTrip.getTotal()) {
                maxBuisnessTrip = buisnessTrip;
            }
            buisnessTrip.show();
        }
        System.out.println("\nthe most expensive trip is " + maxBuisnessTrip);

        buisnessTrips[buisnessTrips.length - 1].setTransportExpenses(451234);

        System.out.println("total duration of 2 trips: " + (buisnessTrips[0].getNumOfDays() + buisnessTrips[1].getNumOfDays()));

        for (BuisnessTrip buisnessTrip : buisnessTrips) {
            System.out.println(buisnessTrip);
        }
    }
}
