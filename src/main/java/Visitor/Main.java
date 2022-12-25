package Visitor;

public class Main {
    public static void main(String[] args) {
        Group<Integer> nestedGroup = new Group<Integer>();
        nestedGroup.addTask(new Signature<Integer>(System.out::println)).addTask(new Signature<Integer>(x -> System.out.println(x * x)));
        Group<Integer> group = new Group<Integer>();
        group.addTask(nestedGroup).addTask(new Signature<Integer>(x -> System.out.println(x * x * x)));
        group.apply(10);
        group.apply(5);
        System.out.println(group.getHeader("tasks"));
        System.out.println(group.getHeader("previous_tasks"));
    }
}
