/*******************************************************************************
Enum which stores the x and y coordinates of all 12 possible slots that can be
displayed on the table.
 ******************************************************************************/
package sample;
public enum Timetable {
    SCREEN1_SLOT1(140,85), SCREEN1_SLOT2(335,85), SCREEN1_SLOT3(530,85), SCREEN1_SLOT4(725,85),
    SCREEN2_SLOT1(140,190), SCREEN2_SLOT2(335,190), SCREEN2_SLOT3(530,190), SCREEN2_SLOT4(725,190),
    SCREEN3_SLOT1(140,295), SCREEN3_SLOT2(335,295), SCREEN3_SLOT3(530,295), SCREEN3_SLOT4(725,295);

    private int x;
    private int y;
    Timetable(int xcordinate, int ycordinate){
        x=xcordinate;
        y=ycordinate;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
