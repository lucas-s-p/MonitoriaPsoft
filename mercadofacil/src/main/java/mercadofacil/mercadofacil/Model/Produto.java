package mercadofacil.mercadofacil.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "tb_produto")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "ds_nomeProduto", nullable = false)
    @JsonProperty("nomeProduto")
    private String nomeProduto;
    @Column(name = "ds_valorProduto")
    @JsonProperty("valorProduto")
    private Double valorProduto;

    @Column(name = "ds_codigoBarrras", nullable = false)
    @JsonProperty("codigoBarras")
    private String codigoBarras;
}
