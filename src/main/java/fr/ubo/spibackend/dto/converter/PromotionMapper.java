package fr.ubo.spibackend.dto.converter;

import fr.ubo.spibackend.dto.PromotionDTO;
import fr.ubo.spibackend.entities.Promotion;
import org.mapstruct.*;

@Mapper
public interface PromotionMapper {
    @Mappings({
            @Mapping(target = "nombreHeureTD", ignore = true),
            @Mapping(target="nombreHeureTP", ignore = true),
            @Mapping(target="nombreHeureCM", ignore = true),
            @Mapping(target="nombreHeureETD", ignore = true)

    })
    PromotionDTO map(Promotion promo);

    @AfterMapping // or @BeforeMapping
    default void calculateTotal(Promotion promo, @MappingTarget PromotionDTO dto) {
                  dto.setNombreHeureTD(promo.getFormationByCodeFormation().getUniteEnseignements().stream().mapToDouble(e->e.getNbhTd()).sum());
                  dto.setNombreHeureCM(promo.getFormationByCodeFormation().getUniteEnseignements().stream().mapToDouble(e->e.getNbhCm()).sum());
                  dto.setNombreHeureTP(promo.getFormationByCodeFormation().getUniteEnseignements().stream().mapToDouble(e->e.getNbhTp()).sum());
                  dto.setNombreHeureETD(dto.getNombreHeureTD()+(dto.getNombreHeureTP()*(2.0/3.0))+(1.5*dto.getNombreHeureCM()));
    }
}
