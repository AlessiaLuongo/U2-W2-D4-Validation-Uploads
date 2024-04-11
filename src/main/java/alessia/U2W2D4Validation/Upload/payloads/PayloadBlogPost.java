package alessia.U2W2D4Validation.Upload.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayloadBlogPost {

    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;
    private int authorId;

}
