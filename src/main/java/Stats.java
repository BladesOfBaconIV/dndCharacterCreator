public class Stats {

    public enum StatNames {
        STR ("strength"),
        DEX ("dexterity"),
        CON ("constitution"),
        INT ("intelligence"),
        WIS ("wisdom"),
        CHR ("charisma");
        private final String name;

        StatNames(String name) { this.name = name; }
        String getName() { return this.name; }
    }

    private byte[] stats;

    public Stats(byte[] stats) {
        this.stats = stats;
    }

    public Stats(byte str, byte dex, byte con, byte int_, byte wis, byte chr) {
        this(new byte[] {str, dex, con, int_, wis , chr});
    }

    public byte getStatByName(StatNames name) {
        return this.stats[name.ordinal()];
    }

    public void addModifiers(Stats mods) {
        for (int i = 0; i < 6; i++) {
            this.stats[i] += mods.getStats()[i];
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (StatNames stat: StatNames.values()) {
            s += String.format("%s: %d ", stat.getName(), this.getStatByName(stat));
        }
        return s;
    }

    public byte[] getStats() {
        return this.stats;
    }

    public void setStats(byte[] stats) {
        this.stats = stats;
    }
}
