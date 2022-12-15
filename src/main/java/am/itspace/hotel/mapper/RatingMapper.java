package am.itspace.hotel.mapper;

import am.itspace.hotel.dto.RatingDto;
import am.itspace.hotel.entity.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper extends EntityMapper<RatingDto, Rating> {
}
