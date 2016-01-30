package no.difi.data.skos.los;

public enum Language {
    nb("nob"),
    nn("nno"),
    en("eng");

    private String alternative;

    Language(String alternative) {
        this.alternative = alternative;
    }

    public static Language getByAlterantive(String alternative) {
        for (Language language : Language.values())
            if (language.alternative.equalsIgnoreCase(alternative))
                return language;

        return null;
    }
}
