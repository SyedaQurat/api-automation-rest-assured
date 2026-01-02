import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Products {

    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
