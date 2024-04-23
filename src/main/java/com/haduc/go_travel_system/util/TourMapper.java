package com.haduc.go_travel_system.util;

import com.haduc.go_travel_system.dto.response.*;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.TourImage;
import com.haduc.go_travel_system.entity.TourSchedule;

import java.util.List;


public class TourMapper {
    public static TourResponse toDto(Tour tour) {
        TourResponse response = new TourResponse();
        response.setTourId(tour.getTourId());
        response.setName(tour.getName());
        response.setAdultPrice(tour.getAdultPrice());
        response.setChildPrice(tour.getChildPrice());
        response.setBabyPrice(tour.getBabyPrice());
        response.setUnit(tour.getUnit());
        response.setDescription(tour.getDescription());
        response.setNumberOfDays(tour.getNumberOfDays());
        response.setNumberOfNights(tour.getNumberOfNights());
        response.setVehicle(tour.getVehicle());
        response.setDepartureLocation(tour.getDepartureLocation());
        response.setHotelStar(tour.getHotelStar());
        response.setNumberOfSeats(tour.getNumberOfSeats());
        response.setAvailableSeats(tour.getAvailableSeats());
        response.setStatus(tour.getStatus());
        response.setNote(tour.getNote());
        response.setTourType(tour.getTourType());
        List<TourImage> images = tour.getImages();
        if(images != null) {
            response.setImages(images.stream().map(image -> TourImageReponse.builder()
                    .id(image.getId())
                    .url(image.getUrl())
                    .build()).toList());
        }
        List<DepartureTime> departureTimes = tour.getDepartureTimes();
        if(departureTimes != null) {
            response.setDepartureTimes(departureTimes.stream().map(departureTime -> DepartureTimeResponse.builder()
                    .id(departureTime.getId())
                    .startDate(departureTime.getStartDate())
                    .build()).toList());
        }
        List<TourSchedule> schedules = tour.getSchedules();
        if(schedules != null) {
            response.setSchedules(schedules.stream().map(schedule -> TourScheduleResponse.builder()
                    .id(schedule.getId())
                    .timeInDays(schedule.getTimeInDays())
                    .title(schedule.getTitle())
                    .scheduleDetail(schedule.getScheduleDetail().stream().map(detail -> ScheduleDetailResponse.builder()
                            .id(detail.getId())
                            .timeLine(detail.getTimeLine())
                            .place(detail.getPlace())
                            .activity(detail.getActivity())
                            .build()).toList())
                    .task(TaskResponse.builder()
                            .id(schedule.getTask().getId())
                            .name(schedule.getTask().getName())
                            .description(schedule.getTask().getDescription())
                            .coin(schedule.getTask().getCoin())
                            .reward(schedule.getTask().getReward())
                            .deadline(schedule.getTask().getDeadline())
                            .status(schedule.getTask().getStatus())
                            .taskType(schedule.getTask().getTaskType())
                            .build())
                    .build()).toList());
        }
        return response;
    }

}
