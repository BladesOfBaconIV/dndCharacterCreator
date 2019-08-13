import java.util.Arrays;

public class Race {

    private String name;
    private Stats modifiers;
    private String[] languages;
    private String attributes;
    private int speed;

    public Race(String name, Stats modifiers, String[] languages, String attributes, int speed) {
        this.name = name;
        this.modifiers = modifiers;
        this.languages = languages;
        this.attributes = attributes;
        this.speed = speed;

    }

    public Race(String name, Stats modifiers, String[] languages, String attributes) {
        this(name, modifiers, languages, attributes, 30);

    }

    @Override
    public String toString() {
        return String.format("Race: %s, Modifiers: %s, Languages: %s, Attributes: %s",
                this.name,
                this.modifiers,
                Arrays.toString(this.languages),
                this.attributes
        );
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getModifiers() {
        return this.modifiers;
    }

    public void setModifiers(Stats modifiers) {
        this.modifiers = modifiers;
    }

    public String[] getLanguages() {
        return this.languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getAttributes() {
        return this.attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

}
