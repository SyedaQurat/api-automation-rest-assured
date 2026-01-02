import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Products {

    private Integer id;
    private String title;
    private Integer userId;
    private String body;
}
