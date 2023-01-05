package day_04.models;

public class SectionRange {
    private final int rangeStart;
    private final int rangeEnd;


    public SectionRange(String range) {
        String[] rangeBorders = range.split("-");
        rangeStart = Integer.parseInt(rangeBorders[0]);
        rangeEnd = Integer.parseInt(rangeBorders[1]);
    }

    public boolean containsWholeRange(SectionRange otherRange) {
        return otherRange.rangeStart >= this.rangeStart && otherRange.rangeEnd <= this.rangeEnd;
    }

    public boolean overlapsWithRange(SectionRange otherRange) {
        return otherRange.rangeStart <= this.rangeEnd && otherRange.rangeEnd >= this.rangeStart;
    }
}
