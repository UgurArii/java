package sample;

class MovieData{
    private boolean[] seats=new boolean[20];
    private int stdnseats;
    private int slotid;

    public MovieData(boolean[] seats, int stdnseats, int slotid){
        this.seats=seats;
        this.stdnseats=stdnseats;
        this.slotid=slotid;
    }
    public boolean[] getSeats() {
        return seats;
    }
    public void setSeats(boolean[] seats) {
        this.seats = seats;
    }

    public int getStdnseats() {
        return stdnseats;
    }
    public int getVipseats() {
        int total = 0;
        for (boolean seat : seats)
            if (!seat)
                total++;
        return total;
    }

    //Getters and Setters
    public int getTotalseats() {
        return getStdnseats() + getVipseats();
    }
    public void setStdnseats(int stdnseats) {
        this.stdnseats = stdnseats;
    }
    public int getSlotid() {
        return slotid;
    }
    public void setSlotid(int slotid) {
        this.slotid = slotid;
    }
}