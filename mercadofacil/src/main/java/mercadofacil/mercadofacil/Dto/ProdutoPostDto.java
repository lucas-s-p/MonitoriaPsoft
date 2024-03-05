package mercadofacil.mercadofacil.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostDto {
    @JsonProperty("nomeProduto")
    @NotBlank(message = "Nome do produto não pode ser vazio")
    private String nomeProduto;
    @JsonProperty("valorProduto")
    @Positive(message = "Valor do produto deve ser positivo")
    private Double valorProduto;
    @JsonProperty("codigoBarras")
    @NotBlank(message = "Código de Barras do produto não pode ser vazio")
    private String codigoBarras;
}
