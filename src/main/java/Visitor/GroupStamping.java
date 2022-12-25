package Visitor;

import java.util.*;


public class GroupStamping<T> implements Visitor<T>{
    Map<String, String> temporalData= new HashMap<>();
    @Override
    public Map<String, String> onSignature(Task<T> task) {
        temporalData.put(task.getId(), task.toString());
        temporalData.put("tasks", temporalData.get("tasks")+" "+task.toString());
        return temporalData;
    }
    @Override
    public Map<String, String> onGroupStart(Task<T> task) {
        temporalData.put("tasks", task.getHeader("tasks"));
        Map<String, String> copy=temporalData;
        temporalData.put("tasks", "");
        return copy;
    }
    @Override
    public Map<String, String> onGroupEnd(Task<T> task) {
        return temporalData;
    }
}
