package prog2.exercise9.flight.booking.system;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.junit.Test;

public class FlightBookingTest 
{ 
    @Test
    public void testing_Buffered_Reading_Government_Seat_Booking_Method(){
        String fileName = "GovTest.txt";
        Path inputPath = Paths.get(fileName);

        System.out.println(inputPath);
        Path absPath = inputPath.toAbsolutePath();
        System.out.println(absPath);
        System.out.println("String" + absPath.toString());
        
        try{
            
            GovernmentFlightBooking gov = new GovernmentFlightBooking(new FlightBooking(3), fileName, 3);
        
            gov.displayTripDetails(3);

            String [] seatsBooked = new String[3];

            for(int i=0; i<3; ++i){
                gov.setTicketNumber(i);
                seatsBooked[i]=gov.getFlightSeats(i);
            }
            
            for(int j=0; j<3; ++j){
                String details = gov.getTicketDetails(seatsBooked[j].replace("-Booked", ""));
                String result = details.toLowerCase().trim().replace(" ", "");

                String expected = "Passenger Name.: " + gov.getPassengerFullName(j) + 
                "\n" + "Source: " + "BEIJING (BEIJING_CAPITAL_INTERNATOINAL_AIRPORT)" + "\n" + 
                "Destination: HELSINKI (HELSINKI_AIRPORT)" + "\n" + 
                "Departure: " + gov.getDepartureDate() + "\n" + "Return: " + gov.getReturnDate() + "\n" + 
                "Trip's Total Cost: " + gov.getTotalTicketPrice();

                String expectedResult = expected.toLowerCase().trim().replace(" ", "");

                assertEquals(expectedResult, result);

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    @Test
    public void testing_Buffered_Reading_Charter_Seat_Booking_Method(){
        String fileName = "CharterTest.txt";
        
        try{
            
            CharterFlightBooking charter = new CharterFlightBooking(fileName, 3);
            charter.displayTripDetails(3);

            String [] seatsBooked = new String[3];

            for(int j=0, k=18; j<3; ++j, ++k){
                charter.setTicketNumber(j);
                seatsBooked[j]=charter.getFlightSeats(k);
            }
            
            if(seatsBooked[0].equals("19CH-Booked") &&
            seatsBooked[1].equals("20CH-Booked") &&
            seatsBooked[2].equals("21CH-Booked")){
                assertEquals(1, 1);
            }
            else{
                assertEquals(1, 2);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}