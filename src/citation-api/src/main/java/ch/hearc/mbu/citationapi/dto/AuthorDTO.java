package ch.hearc.mbu.citationapi.dto;
import jakarta.validation.constraints.*;
public class AuthorDTO {

    @NotBlank(message = "Name is mandatory")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
