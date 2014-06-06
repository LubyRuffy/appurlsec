package qsbk.app.utils;

import java.util.Vector;
import qsbk.app.provider.DataUnit;

public class AuditQueue extends Vector<DataUnit> {
    public void clear() {
        super.removeAllElements();
    }

    public boolean empty() {
        return super.isEmpty();
    }

    public DataUnit pop() {
        if (empty()) {
            return null;
        }
        DataUnit r0_DataUnit = (DataUnit) super.elementAt(0);
        super.removeElementAt(0);
        return r0_DataUnit;
    }

    public void push(DataUnit r1_DataUnit) {
        super.addElement(r1_DataUnit);
    }
}