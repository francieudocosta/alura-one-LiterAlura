package br.com.francieudo.alura.literalura.model;

public enum Languages {

    ES("es", "Espanhol"),
    EN("en", "Inglês"),
    FR ("fr", "Francês"),
    PT("pt", "português");

    private final String acronyms;
    private final String descricion;

    Languages(String acronyms, String descricion){

        this.acronyms = acronyms;
        this.descricion = descricion;
    }

    public String getAcronyms() {
        return acronyms;
    }

    public String getDescricion() {
        return descricion;
    }

    public static Languages fromAcronym(String acronym){

        for(Languages l : values()){
            if(l.getAcronyms().equalsIgnoreCase(acronym)){
                return l;
            }
        }

        throw new IllegalArgumentException("Enum não encontrado");
    }
}
