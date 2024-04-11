package alessia.U2W2D4Validation.Upload.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private String surname;
    private String EMail;
    private int birthdayYear;
    private String avatar;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<BlogPost> blogPostList = new ArrayList<>();

    public Author(String name, String surname, String eMail, int birthdayYear, String url) {
        this.name = name;
        this.surname = surname;
        this.EMail = eMail;
        this.birthdayYear = birthdayYear;
        this.avatar = url;
    }
}

