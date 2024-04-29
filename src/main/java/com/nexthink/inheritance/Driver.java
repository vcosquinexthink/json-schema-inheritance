package com.nexthink.inheritance;

public interface Driver {
    String getDriverInfo();
}
class MouseDriver implements Driver {
    @Override
    public String getDriverInfo() {
        return "I am a mouse";
    }
}

class KeyboardDriver implements Driver {
    @Override
    public String getDriverInfo() {
        return "I am a keyboard";
    }
}
