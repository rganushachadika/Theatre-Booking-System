public class Ticket {
        private int row;
        private int seat;
        private double ticket_price;
        private Person person_details;

        public Ticket(int row, int seat, double ticket_price, Person person_details) {
            this.row = row;
            this.seat = seat;
            this.ticket_price = ticket_price;
            this.person_details = person_details;
        }

    public void  print(){
        System.out.println("Name : "+person_details.name);
        System.out.println("Surname : "+person_details.surname);
        System.out.println("Row number : "+(row+1)+" "+"Seat number : "+(seat+1));
        System.out.println("Price : "+ ticket_price);
    }



    public double getPrice(){

            return ticket_price;
    }
}

