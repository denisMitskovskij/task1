package sort;

class UtilsSort implements Cloneable {
    int[] vals;

    UtilsSort(int[] vals) {
        this.vals = vals;
    }

    boolean isSorted() {
        for (int i = 1; i < vals.length; i++)
            if (vals[i] < vals[i - 1])
                return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        UtilsSort clone = (UtilsSort) super.clone();
        clone.vals = vals.clone();
        return clone;
    }
}