
package volleywebrequest.com.volleylibrarymoduleforreuse.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitorList implements Serializable
{

    @SerializedName("data_set")
    @Expose
    private List<DataSet> dataSet = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = -1608733221743378248L;

    @Override
    public String toString() {
        return "VisitorList{" +
                "dataSet=" + dataSet +
                ", id=" + id +
                '}';
    }

    public List<DataSet> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<DataSet> dataSet) {
        this.dataSet = dataSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
