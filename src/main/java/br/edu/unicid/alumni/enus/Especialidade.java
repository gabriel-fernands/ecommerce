package br.edu.unicid.alumni.enus;

public enum Especialidade {

    ORTOPEDIA,
    CARDIOLOGIA,
    GINECOLOGIA,
    DERMATOLOGIA;

    public static Especialidade devolveEspecialidade(Integer especialidade){
        return switch (especialidade){
            case 0 -> ORTOPEDIA;
            case 1 -> CARDIOLOGIA;
            case 2 -> GINECOLOGIA;
            case 3 -> DERMATOLOGIA;
            default -> throw new RuntimeException("Especialidade de medica");
        };
    }
}
