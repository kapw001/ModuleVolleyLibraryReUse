
package volleywebrequest.com.volleylibrarymoduleforreuse.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
{

    @SerializedName("todate")
    @Expose
    private String todate;
    @SerializedName("fromdate")
    @Expose
    private String fromdate;
    @SerializedName("university_id")
    @Expose
    private List<Integer> universityId = null;
    @SerializedName("visitor_list")
    @Expose
    private List<VisitorList> visitorList = null;
    private final static long serialVersionUID = 7688616038522685006L;

    @Override
    public String toString() {
        return "Result{" +
                "todate='" + todate + '\'' +
                ", fromdate='" + fromdate + '\'' +
                ", universityId=" + universityId +
                ", visitorList=" + visitorList +
                '}';
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public List<Integer> getUniversityId() {
        return universityId;
    }

    public void setUniversityId(List<Integer> universityId) {
        this.universityId = universityId;
    }

    public List<VisitorList> getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List<VisitorList> visitorList) {
        this.visitorList = visitorList;
    }

}
