package basic.tuntilappuohjelma;



import java.util.ArrayList;

public class DataHandler {

    private static DataHandler handler;
    private ArrayList<String[]> exportlist;
    private ArrayList<String> taskList;

    private DataHandler() {
    }

    public static DataHandler getInstance() {
        if (handler == null)
            handler = new DataHandler();
        return handler;
    }


    public ArrayList<String[]> getExportlist() {
        return exportlist;
    }

    public void setExportList(ArrayList<String[]> exportlist) {
        this.exportlist = exportlist;
    }


    public ArrayList<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<String> taskList) {
        this.taskList = taskList;
    }
}

