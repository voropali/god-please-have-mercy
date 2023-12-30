package cz.cvut.ear.clubevidence.model;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private final String name;
    Role(String name) {
        this.name = name;
    }

}
