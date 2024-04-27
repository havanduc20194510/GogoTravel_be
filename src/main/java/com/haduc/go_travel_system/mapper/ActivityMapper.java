package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.response.ActivityResponse;
import com.haduc.go_travel_system.entity.Activity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {
    Activity toEntity(ActivityResponse activityResponse);

    ActivityResponse toDto(Activity activity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Activity partialUpdate(ActivityResponse activityResponse, @MappingTarget Activity activity);
}
