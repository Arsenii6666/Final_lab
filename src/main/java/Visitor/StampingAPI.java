package Visitor;

import java.util.Map;

public class StampingAPI extends GroupStamping{

    @Override
    public Map<String, String> onSignature(Task task) {
        return null;
    }

    @Override
    public Map<String, String> onGroupStart(Task task) {
        return null;
    }

    @Override
    public Map<String, String> onGroupEnd(Task task) {
        return null;
    }
}
