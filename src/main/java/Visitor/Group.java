package Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Group<T> extends Task<T> {
    public String groupUuid;
    private List<Task<T>> tasks;
    public GroupStamping<T> groupStamping=new GroupStamping<T>();

    public Group<T> addTask(Task<T> task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);

        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        groupUuid = UUID.randomUUID().toString();
        for (Task<T> task: tasks) {
            task.freeze();
        }
    }
    @Override
    void stamp(Visitor<T> visitor) {
        try{
            this.setHeader("previous_tasks", visitor.onGroupStart(this).get("tasks"));
        }
        finally {
            for (Task<T> task : tasks) {
                task.stamp(visitor);
            }
            this.setHeader("tasks", visitor.onGroupEnd(this).get("tasks"));
        }
    }

    @Override
    public void apply(T arg) {
        this.freeze();
        tasks = Collections.unmodifiableList(tasks);
        for (Task<T> task: tasks) {
            task.apply(arg);
        }
        this.stamp(groupStamping);
    }
}
