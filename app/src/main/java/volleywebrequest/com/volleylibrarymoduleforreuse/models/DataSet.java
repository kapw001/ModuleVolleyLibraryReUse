
package volleywebrequest.com.volleylibrarymoduleforreuse.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSet implements Serializable
{

    @SerializedName("visitor_type")
    @Expose
    private String visitorType;
    @SerializedName("check_in")
    @Expose
    private String checkIn;

    @Override
    public String toString() {
        return "DataSet{" +
                "visitorType='" + visitorType + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                '}';
    }

    @SerializedName("check_out")
    @Expose
    private String checkOut;
    private final static long serialVersionUID = 268506235426883968L;

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

}
