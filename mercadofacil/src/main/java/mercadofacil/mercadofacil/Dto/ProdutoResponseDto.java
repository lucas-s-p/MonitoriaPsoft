package mercadofacil.mercadofacil.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nomeProduto")
    private String nomeProduto;
    @JsonProperty("valorProduto")
    private Double valorProduto;
    @JsonProperty("codigoBarras")
    private String codigoBarras;
}
