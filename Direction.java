public enum Direction {
    N {
        /**will return the next 8-directional direction counter-clockwise*/
        @Override
        public Direction left() {
            return Direction.NW;
        }
    },
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW {
        /**will return the next 8-directional direction clockwise.*/
        @Override
        public Direction right() {
            return Direction.N;
        }
    };

    /**will return the next 8-directional direction clockwise.*/
    public Direction right() {
        return values()[ordinal() + 1];
    }

    /**will return the next 8-directional direction counter-clockwise*/
    public Direction left() {
        return values()[ordinal() - 1];
    }
}
